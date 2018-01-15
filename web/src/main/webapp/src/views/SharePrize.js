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
import reback from '../media/imgs/reback.png';
import '../media/styles/prize.less';

class SharePrize extends Component {
    constructor(props) {
        super(props);
        this.state = {
            scoreInfo: {
                score:10,
            },
            showToast: false,
            toastText: '分享成功',
            location: this.props.location.state? this.props.location.state : '/answer',
            showPopup: false,
            isReady:true,
            mySelfRank:0,
            correctCount:0
        };
        this.userId = getCookie('LOGIN_COOKIE');
        this.toastTimer = null;
        this.handleShare = this.handleShare.bind(this);
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findSelfRank`).then(function (response) {
            if (response.data.data !== null) {
                _this.setState({
                    mySelfRank:response.data.data.ranking
                });
            }
        }).catch(function(errors){
            console.log(errors);
        });
        //剩余积分获取
        axios.get(`${host}/score/findResidueScore`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        });
        jsSdkConfig(axios, host);
        window.wx.ready(function () {
            console.log(1);
            _this.setState({
                isReady: true
            });
            axios.get(`${host}/share/findShareInfo?id=${_this.userId}`).then(function (response) {
                if (response.data.state === 0) {
                    let scoreInfo = response.data.data;
                    _this.setState({
                        correctCount:scoreInfo.correctCount
                    });
                    window.wx.onMenuShareTimeline({
                        title: `我正在争当普法先锋，大家快来给我点赞，助我涨积分赢奖品`,
                        link: scoreInfo.url,
                        imgUrl: scoreInfo.portrait,
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
                    /*我在中国共青团青少年学法用法知识竞赛答对${scoreInfo.correctCount}道题，快来支持我吧!*/
                    window.wx.onMenuShareAppMessage({
                        title: `我正在争当普法先锋，大家快来给我点赞，助我涨积分赢奖品`,
                        link: scoreInfo.url,
                        imgUrl: scoreInfo.portrait,
                        desc: '第十四届全国青少年学法用法网上知识竞赛',
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
        this.setState(
            function (prevState) {
                return {
                    showPopup: !prevState.showPopup
                }
            }
        );
    }
    render() {
        let scoreInfo = this.state.scoreInfo;
        let isReady = this.state.isReady;
        let mySelfRank = this.state.mySelfRank;
        let correctCount = this.state.correctCount;
        return (
            <div className='share-prize myprize-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                {/*<div className='text-center header'>
                    <img src={Achievement} alt=""/>
                </div>*/}
                {
                    (isReady && scoreInfo)? (
                        <div className='text-center complete'>
                            <div className='wrapper'>
                                <div className='sum-score'>
                                    <div><span className="score">+{correctCount*5?correctCount*5:0}</span></div>
                                </div>
                                <div className="sum-detail">
                                    <p className='first'>恭喜你！今日答对{correctCount}题</p>
                                    <p className='second'>当前积分：<span>{scoreInfo.score?scoreInfo.score:0}</span></p>
                                    <p className="third">当前排名:<span>{mySelfRank?mySelfRank:0}</span></p>
                                </div>
                                <div className="sum-bottom">
                                    <p className='first'>马上拉好友为你点赞吧</p>
                                    <p className='second'>每天都有新题，等你来答!</p>
                                    <p className="third">一起参与答题，涨积分赢奖品</p>
                                </div>
                                <div className='share' onClick={this.handleShare}>马上拉好友点赞</div>
                            </div>
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