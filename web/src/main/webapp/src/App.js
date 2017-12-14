import React, {Component} from 'react';
import './App.less';
import star from './media/images/star_idol.png';
import {Toast} from 'react-weui';
import axios from 'axios';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showToast: false,
            showLoading: false
        };
        this.handleTouch = this.handleTouch.bind(this);
        this.handleSign = this.handleSign.bind(this);
    }
    componentWillMount() {
        axios.get(`/wechat/getJsSDKConfig?timestamp=${new Date().getTime()}&nonceStr='nonceStr'&url=${window.location.href}`).then(function (response) {
            console.log(response);
            /*配置微信jssdk*/
            window.wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: response.data.data.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
                timestamp: response.data.data.timestamp, // 必填，生成签名的时间戳
                nonceStr: response.data.data.nonceStr, // 必填，生成签名的随机串
                signature: response.data.data.signature,// 必填，签名，见附录1
                jsApiList: [
                    'checkJsApi',
                    'getLocation'
                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
            window.wx.ready(function () {
                window.wx.getLocation({
                    success: function (res) {
                        console.log(res);
                    },
                    cancel: function (res) {
                        console.log('用户拒绝授权获取地理位置');
                    }
                })
            })
        }).catch(function (errors) {
            console.log(errors);
        });
    }
    handleTouch(num) {
        switch (num) {
            case 0:
                this.props.history.push('/description');
                break;
            case 1:
                this.props.history.push('/answer');
                break;
            default:
                return;
        }
    }
    handleSign() {
        this.props.history.push('/sign');
    }
    render() {
        return (
            <div className="app main-bg">
                <div className="sign text-right">
                    <span onClick={this.handleSign}>每日签到</span>
                </div>
                <h2 className="text-center title">
                    <span>法律PK赛</span>
                </h2>
                <ul className="lists">
                    <li className="text-center" onClick={() => this.handleTouch(0)}>
                        <span>赛事说明</span>
                    </li>
                    <li className="text-center" onClick={() => this.handleTouch(1)}>
                        <span>每日必答</span>
                    </li>
                    <li className="text-center">
                        <span>我的任务</span>
                    </li>
                    <li className="text-center">
                        <span>我的积分</span>
                    </li>
                    <li className="text-center">
                        <span>积分兑奖</span>
                    </li>
                    <li className="text-center">
                        <span>荣耀榜</span>
                    </li>
                </ul>
                <div className="star">
                    <img src={star} alt=""/>
                </div>
                <Toast icon="success-no-circle" show={this.state.showToast}>签到成功，积分已到账户</Toast>
                <Toast icon="loading" show={this.state.showLoading}>Loading...</Toast>
            </div>
        );
    }
}

export default App;