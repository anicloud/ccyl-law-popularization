import React, {Component} from 'react';
import Back from './Back';
import {connect} from 'react-redux';
import {getCookie, jsSdkConfig} from "../utils/index";
import {Toast} from 'react-weui';
import axios from 'axios';
import {changeCountJsSdk} from '../redux/actions';
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
            scoreInfo: null,
            showToast: false,
            toastText: '分享成功',
            location: this.props.location.state ? this.props.location.state : '/answer',
            showPopup: false,
            isReady: false,
            mySelfRank: 0,
            correctCount: 0,
            showReAnswer: false
        };
        this.userId = getCookie('LOGIN_COOKIE');
        this.toastTimer = null;
        this.toastTimer2 = null;
        this.handleShare = this.handleShare.bind(this);
        this.backAnswer = this.backAnswer.bind(this);
    }

    componentDidMount() {
        let _this = this;
        const {host, history} = _this.props;
        axios.get(`${host}/score/findSelfRank`).then(function (response) {
            if (response.data.data !== null) {
                _this.setState({
                    mySelfRank: response.data.data.ranking
                });
            }
        }).catch(function (errors) {
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
        jsSdkConfig(axios, host, 1);
        /*axios.get(`${host}/wechat/getJsSDKConfig?timestamp=${new Date().getTime()}&nonceStr=nonceStr&url=${window.location.href}`).then(function (response) {
         if (response.data.state === 0) {
         /!*配置微信jssdk*!/
         _this.setState({
         loading: true
         });
         window.wx.config({
         debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
         appId: response.data.data.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
         timestamp: response.data.data.timestamp, // 必填，生成签名的时间戳
         nonceStr: response.data.data.nonceStr, // 必填，生成签名的随机串
         signature: response.data.data.signature,// 必填，签名，见附录1
         jsApiList: [
         'getLocation',
         'onMenuShareTimeline',
         'onMenuShareAppMessage'
         ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
         });
         }
         }).catch(function (errors) {
         console.log('errors', errors);
         });*/
        window.wx.ready(function () {
            console.log(1);
            // alert('share ready');
            _this.setState({
                isReady: true
            });
            axios.get(`${host}/share/findShareInfo`).then(function (response) {
                if (response.data.state === 0) {
                    let scoreInfo = response.data.data;
                    // alert("分享url："+scoreInfo.url);
                    // alert(window.location.href.split('#')[0]);
                    _this.setState({
                        correctCount: scoreInfo.correctCount
                    });
                    window.wx.onMenuShareTimeline({
                        title: `我正在争当普法先锋，大家快来给我点赞，助我涨积分赢奖品`,
                        link: scoreInfo.url,
                        imgUrl: scoreInfo.portrait,
                        success: function (res) {
                            axios.get(`${host}/share/share`).then(function (response) {
                                console.log(response);
                                history.push('/tasks');
                            }).catch(function (errors) {
                                console.log(errors);
                            });
                        },
                        fail: function (res) {
                            // alert("分享失败");
                            // alert(res);
                        }
                    });
                    /*我在中国共青团青少年学法用法知识竞赛答对${scoreInfo.correctCount}道题，快来支持我吧!*/
                    window.wx.onMenuShareAppMessage({
                        title: `我正在争当普法先锋，大家快来给我点赞，助我涨积分赢奖品`,
                        link: scoreInfo.url,
                        imgUrl: scoreInfo.portrait,
                        desc: '第十四届全国青少年学法用法网上知识竞赛',
                        success: function (res) {
                            axios.get(`${host}/share/share`).then(function (response) {
                                console.log(response);
                                history.push('/tasks');
                            }).catch(function (errors) {
                                console.log(errors);
                            });
                        },
                        fail: function (res) {
                            // alert("分享失败");
                            // alert(res);
                        }
                    });
                    /*_this.setState({
                        loading: false
                    });*/
                }
            }).catch(function (errors) {
                console.log(errors);
            });
        });
        window.wx.error(function(res) {
            alert(res.errMsg);
            _this.props.handleJsConfig(_this.props.countJsSdk + 1);
            alert(_this.props.countJsSdk);
            if (_this.props.countJsSdk <= 3) {
                jsSdkConfig(axios, host, _this.props.countJsSdk);
            }
        });
    }

    componentWillUnmount() {
        this.toastTimer && clearTimeout(this.toastTimer);
        this.toastTimer2 && clearTimeout(this.toastTimer2);
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

    backAnswer() {
        let _this = this;
        const {history} = _this.props;
        if (_this.state.scoreInfo.questionTime === 2) {
            _this.setState({
                showReAnswer: true
            });
            _this.toastTimer2 = setTimeout(function () {
                _this.setState({
                    showReAnswer: false
                });
            }, 2000);
        } else {
            history.replace('/answer');
        }
    }

    render() {
        let scoreInfo = this.state.scoreInfo;
        let isReady = this.state.isReady;
        let mySelfRank = this.state.mySelfRank;
        let correctCount = this.state.correctCount;
        return (
            <div className='share-prize myprize-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history}/>
                </div>
                {/*<div className='text-center header'>
                    <img src={Achievement} alt=""/>
                </div>*/}
                {
                    (isReady && scoreInfo) ? (
                        <div className='text-center complete'>
                            <div className='wrapper'>
                                <h2 className='wrapper-title'>
                                    {
                                        correctCount === 5 ? (
                                            <span>已答完</span>
                                        ) : (
                                            <span onClick={this.backAnswer}>重答 <img src={reback} alt=""/></span>
                                        )
                                    }
                                </h2>
                                <div className='sum-score'>
                                    <div><span className="score">+{correctCount * 2 ? correctCount * 2 : 0}</span></div>
                                </div>
                                <div className="sum-detail">
                                    <p className='first'>恭喜你！今日答对{correctCount}题</p>
                                    <p className='second'>当前积分：<span>{scoreInfo.score ? scoreInfo.score : 0}</span></p>
                                    {mySelfRank !== -1 ? (
                                        <p className="third">当前排名:<span>{mySelfRank ? mySelfRank : 0}</span>
                                        </p>) : (null)}
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
                <div className='popup' style={{display: this.state.showPopup ? 'block' : 'none'}}>
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
                <Toast icon="warn" show={this.state.showReAnswer}>重答次数已用完</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError,
        countJsSdk: state.countJsSdk
    };
}

function mapDispatchToProps(dispatch) {
    return {
        handleJsConfig: (num) => {
            dispatch(changeCountJsSdk(num));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SharePrize);