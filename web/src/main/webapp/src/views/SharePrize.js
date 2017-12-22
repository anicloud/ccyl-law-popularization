import React, {Component} from 'react';
import Back from './Back';
import {connect} from 'react-redux';
import {getCookie} from "../utils/index";
import {Toast} from 'react-weui';
import axios from 'axios';
import first from '../media/images/first.png';
import second from '../media/images/second.png';
import arrow from '../media/images/arrow.png';
import know from '../media/images/know.png';
import '../media/styles/prize.less';

class SharePrize extends Component {
    constructor(props) {
        super(props);
        this.state = {
            scoreInfo: null,
            showToast: false,
            toastText: '分享成功',
            location: this.props.location.state? this.props.location.state : '/answer',
            showPopup: false
        };
        this.userId = getCookie('LOGIN_COOKIE');
        this.toastTimer = null;
        this.handleShare = this.handleShare.bind(this);
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findDailyTotalScore?id=${_this.userId}`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                });
                let scoreInfo = response.data.data;
                let prize = scoreInfo.score === 15? '金牌' : scoreInfo.score === 10? '银牌' : scoreInfo.score === 5? '铜牌' : '鼓励';
                axios.get(`${host}/wechat/shareUrl`).then(function (response) {
                    if (response.data.state === 0) {
                        window.wx.onMenuShareTimeline({
                            title: `我在中国共青团青少年学法用法知识竞赛获得了${prize}，快来支持我并答题吧!`,
                            link: response.data.data,
                            imgUrl: scoreInfo.portrait,
                            success: function (res) {
                                _this.setState({
                                    showToast: true,
                                    toastText: '分享成功'
                                });
                                _this.toastTimer = setTimeout(()=> {
                                    _this.setState({showToast: false});
                                }, 2000);
                            },
                            fail: function (res) {
                                _this.setState({
                                    showToast: true,
                                    toastText: '分享失败'
                                });
                                _this.toastTimer = setTimeout(()=> {
                                    _this.setState({showToast: false});
                                }, 2000);
                            }
                        });
                        window.wx.onMenuShareAppMessage({
                            title: `我在中国共青团青少年学法用法知识竞赛获得了${prize}，快来支持我并答题吧!`,
                            link: response.data.data,
                            imgUrl: scoreInfo.portrait,
                            desc: '共青团中央2018年第十四届青少年学法用法知识竞赛',
                            success: function(res) {
                                _this.setState({
                                    showToast: true,
                                    toastText: '分享成功'
                                });
                                _this.toastTimer = setTimeout(()=> {
                                    _this.setState({showToast: false});
                                }, 2000);
                            },
                            fail: function(res) {
                                _this.setState({
                                    showToast: true,
                                    toastText: '分享失败'
                                });
                                _this.toastTimer = setTimeout(()=> {
                                    _this.setState({showToast: false});
                                }, 2000);
                            }
                        });
                    }
                }).catch(function (errors) {
                    console.log(errors);
                });
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }
    componentWillUnmount() {
        this.toastTimer && clearTimeout(this.toastTimer);
    }
    handleShare() {
        this.setState(function (prevState) {
            return {
                showPopup: !prevState.showPopup
            }
        });
    }
    render() {
        let scoreInfo = this.state.scoreInfo;
        return (
            <div className='share-prize main-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <h3 className='text-center'>荣誉奖状</h3>
                {
                    scoreInfo? (
                        <div>
                            <div className={scoreInfo.score === 15? 'flower-gold' : scoreInfo.score === 10? 'flower-silver' : scoreInfo.score === 5 ? 'flower-copper' : 'flower'}>
                                <img src={scoreInfo.portrait} alt=""/>
                                <p className='text-center score-prize'>{scoreInfo.score === 15? '获得金牌' : scoreInfo.score === 10? '获得银牌' : scoreInfo.score === 5? '获得铜牌' : ''}</p>
                            </div>
                            <p className='text-center score-ques'>
                                答题获得积分：<span>{scoreInfo.score} 积分</span>
                            </p>
                            <p className='text-center share'>
                                <button className='btn btn-success' onClick={this.handleShare}>立即分享</button>
                            </p>
                        </div>
                    ) : (null)
                }
                <div className='popup' style={{display: this.state.showPopup? 'block' : 'none'}}>
                    <div className='arrow'>
                        <img src={arrow} alt=""/>
                    </div>
                    <div className='first'>
                        <img src={first} alt=""/>
                    </div>
                    <div className='second'>
                        <img src={second} alt=""/>
                    </div>
                    <div className='text-center know'>
                        <img src={know} onClick={this.handleShare} alt=""/>
                    </div>
                </div>
                <Toast icon="success-no-circle" show={this.state.showToast}>{this.state.toastText}</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host
    };
}

export default connect(mapStateToProps)(SharePrize);