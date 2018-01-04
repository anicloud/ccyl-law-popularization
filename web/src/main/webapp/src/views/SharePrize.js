import React, {Component} from 'react';
import Back from './Back';
import {connect} from 'react-redux';
import {getCookie, jsSdkConfig} from "../utils/index";
import {Toast} from 'react-weui';
import axios from 'axios';
import first from '../media/images/first.png';
import second from '../media/images/second.png';
import arrow from '../media/images/arrow.png';
import know from '../media/images/know.png';
import Achievement from '../media/imgs/Achievement.png';
import reback from '../media/imgs/reback.png';
import '../media/styles/prize.less';

class SharePrize extends Component {
    constructor(props) {
        super(props);
        this.state = {
            scoreInfo: null,
            showToast: false,
            toastText: '分享成功',
            location: this.props.location.state? this.props.location.state : '/answer',
            showPopup: false,
            isReady: false
        };
        this.userId = getCookie('LOGIN_COOKIE');
        this.toastTimer = null;
        this.handleShare = this.handleShare.bind(this);
        this.backAnswer = this.backAnswer.bind(this);
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        jsSdkConfig(axios, host);
        window.wx.ready(function () {
            _this.setState({
                isReady: true
            });
            axios.get(`${host}/share/findShareInfo?id=${_this.userId}`).then(function (response) {
                if (response.data.state === 0) {
                    _this.setState({
                        scoreInfo: response.data.data
                    });
                    let scoreInfo = response.data.data;
                    window.wx.onMenuShareTimeline({
                        title: `我在中国共青团青少年学法用法知识竞赛答对${scoreInfo.correctCount}道题，快来支持我吧!`,
                        link: scoreInfo.url,
                        imgUrl: 'https://8c1abed8.ngrok.io/leg/build/static/media/silver.png',
                        success: function (res) {
                            axios.get(`${host}/share/share`).then(function (response) {
                                console.log(response);
                            }).catch(function (errors) {
                                console.log(errors);
                            });
                        },
                        fail: function (res) {

                        }
                    });
                    window.wx.onMenuShareAppMessage({
                        title: `我在中国共青团青少年学法用法知识竞赛答对${scoreInfo.correctCount}道题，快来支持我吧!`,
                        link: scoreInfo.url,
                        imgUrl: scoreInfo.portrait,
                        desc: '共青团中央2018年第十四届青少年学法用法知识竞赛',
                        success: function(res) {
                            axios.get(`${host}/share/share`).then(function (response) {
                                console.log(response);
                            }).catch(function (errors) {
                                console.log(errors);
                            });
                        },
                        fail: function(res) {

                        }
                    });
                }
            }).catch(function (errors) {
                console.log(errors);
            });
        })
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
    backAnswer() {
        const {history} = this.props;
        history.push('/answer');
    }
    render() {
        let scoreInfo = this.state.scoreInfo;
        let isReady = this.state.isReady;
        return (
            <div className='share-prize common-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <div className='text-center header'>
                    <img src={Achievement} alt=""/>
                </div>
                {
                    (isReady && scoreInfo)? (
                        <div className='wrapper'>
                            <h2 className='wrapper-title'>
                                {
                                    scoreInfo.correctCount === 5? (
                                        <span>已答完</span>
                                    ) : (
                                        <span onClick={this.backAnswer}>重答 <img src={reback} alt=""/></span>
                                    )
                                }
                            </h2>
                            <div className='sum-score'>
                                <div>+{scoreInfo.correctCount * 2}</div>
                                <p className='first'>当前积分：<span>{scoreInfo.totalScore}</span></p>
                                <p className='second'>答对<span>{scoreInfo.correctCount}</span>题，答错<span>{5 - scoreInfo.correctCount}</span>题</p>
                            </div>
                            <div className='thumb-up' onClick={this.handleShare}>拉好友点赞</div>
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
                {/*<Toast icon="success-no-circle" show={this.state.showToast}>{this.state.toastText}</Toast>*/}
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    };
}

export default connect(mapStateToProps)(SharePrize);