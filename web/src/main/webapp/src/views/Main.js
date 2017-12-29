/**
 * Created by sunminggui on 2017/12/13.
 */
import React, {Component} from 'react';
import '../media/styles/main.less';
import star from '../media/images/star_idol.png';
import {jsSdkConfig} from "../utils/index";
import {connect} from 'react-redux';
import {Toast} from 'react-weui';
import axios from 'axios';

class Main extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showLoading : false
        }
    }
    handleTouch() {
       this.props.history.push('/home');
    }
    componentWillMount() {
        let _this = this;
        const {host} = _this.props;
        /*配置微信jssdk*/
        _this.setState({
            showLoading: true
        });
        jsSdkConfig(axios, host);
        window.wx.ready(function () {
            _this.setState({
                showLoading: false
            });
            axios.get(`${host}/wechat/shareUrl`).then(function (response) {
                if (response.data.state === 0) {
                    window.wx.onMenuShareTimeline({
                        title: `我正在中国共青团青少年学法用法知识答题，快来参加吧`,
                        link: response.data.data,
                        imgUrl: '../media/images/logo.png'
                    });
                    window.wx.onMenuShareAppMessage({
                        title: `我正在中国共青团青少年学法用法知识答题，快来参加吧`,
                        link: response.data.data,
                        imgUrl: '../media/images/logo.png',
                        desc: '共青团中央2018年第十四届青少年学法用法知识竞赛'
                    });
                }
            }).catch(function (errors) {
                console.log(errors);
            });
        });
        window.wx.error(function(res) {
            if (res.errMsg === 'config:require subscribe') {
                _this.props.history.push('/error');
            }
        });
    }
    render() {
        return (
            <div className="main main-bg">
                <h2 className="text-center title">
                    <span>法律PK赛</span>
                </h2>
                <div className="star">
                    <img src={star} alt=""/>
                </div>
                <div className='text-center greate'>
                    <button className='btn btn-success' onClick={() => this.handleTouch()}>我行我上</button>
                </div>
                <Toast icon="loading" show={this.state.showLoading}>Loading...</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host
    }
}

export default connect(mapStateToProps)(Main);