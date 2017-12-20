import React, {Component} from 'react';
import Back from './Back';
import icon from '../media/images/signin_icon.png';
import {connect} from 'react-redux';
import {jsSdkConfig, getCookie} from "../utils/index";
import {Toast} from 'react-weui';
import axios from 'axios';
import '../media/styles/prize.less';

class SharePrize extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userInfo: null,
            showToast: false,
            toastText: '分享成功'
        };
        this.userId = getCookie('LOGIN_COOKIE');
        this.toastTimer = null;
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/account/findById?id=${_this.userId}`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    userInfo: response.data.data
                });
            }
        }).catch(function (errors) {
            console.log(errors);
        });
        jsSdkConfig(axios, host);
    }
    componentWillUnmount() {
        this.toastTimer && clearTimeout(this.toastTimer);
    }
    handleShare() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/wechat/shareUrl`).then(function (response) {
            if (response.data.state === 0) {
                window.wx.ready(function () {
                    window.wx.onMenuShareTimeline({
                        title: '我正在参加中国共青团青少年学法用法知识竞赛，快来支持我并答题吧!',
                        link: response.data.data,
                        imgUrl: _this.state.userInfo.portrait,
                        success: function (res) {
                            _this.setState({
                                showToast: true,
                                toastText: '分享成功'
                            });
                            _this.toastTimer = setTimeout(()=> {
                                _this.setState({showToast: false});
                            }, 2000);
                        },
                        cancel: function (res) {
                            _this.setState({
                                showToast: true,
                                toastText: '分享取消'
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
                });
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }
    render() {
        let userInfo = this.state.userInfo;
        return (
            <div className='share-prize main-bg'>
                <div className='clearfix'>
                    <Back location='/answer' history={this.props.history} />
                </div>
                <h3 className='text-center'>荣誉奖状</h3>
                <div className='flower'>
                    {
                        userInfo? (
                            <img src={userInfo.portrait} alt=""/>
                        ) : (null)
                    }
                </div>
                <p className='text-center share'>
                    <button className='btn btn-success' onClick={this.handleShare}>立即分享</button>
                </p>
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