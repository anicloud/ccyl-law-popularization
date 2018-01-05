import React, {Component} from 'react';
import { Provider } from 'react-redux';
import {BrowserRouter} from 'react-router-dom';
import store from './redux/index';
import Routes from './routes/index';
import axios from 'axios';
import {showLoading, showError} from './redux/actions';
import {jsSdkConfig} from "./utils/index";
import {Toast} from 'react-weui';
import Error from './views/Error';

class Root extends Component {
    constructor() {
        super();
        this.state = {
            showLoading: false,
            isError: false
        }
    }
    componentWillMount() {
        this.setState({
            showLoading: true
        });
        // 添加请求拦截器
        let timer = null;
        axios.interceptors.request.use(function (config) {
            // 在发送请求之前做些什么
            store.dispatch(showLoading(true));
            return config;
        }, function (error) {
            // 对请求错误做些什么
            return Promise.reject(error);
        });

        // 添加响应拦截器
        axios.interceptors.response.use(function (response) {
            // 对响应数据做点什么
            store.dispatch(showLoading(false));
            console.log(response);
            if (response.data.state === 0) {
                return response;
            } else {
                timer && clearTimeout(timer);
                store.dispatch(showError(true));
                timer = setTimeout(function () {
                    store.dispatch(showError(false));
                }, 2000);
            }
            return response;
        }, function (error) {
            // 对响应错误做点什么
            store.dispatch(showLoading(false));

            timer && clearTimeout(timer);
            store.dispatch(showError(true));
            timer = setTimeout(function () {
                store.dispatch(showError(false));
            }, 2000);

            return Promise.reject(error);
        });
    }
    componentDidMount() {
        let _this = this;
        // TODO: 测试使用
        _this.setState({
            showLoading: false
        });
        jsSdkConfig(axios, store.getState().host);
        window.wx.ready(function () {
            window.wx.getLocation({
                success: function (res) {
                    axios.get(`${store.getState().host}/account/updateProvince?log=${res.longitude}&lat=${res.latitude}`).then(function (response) {

                    }).catch(function (errors) {
                        console.log(errors);
                    })
                }
            });
            axios.get(`${store.getState().host}/share/findShareInfo`).then(function (response) {
                if (response.data.state === 0) {
                    window.wx.onMenuShareTimeline({
                        title: `我正在中国共青团青少年学法用法知识答题，快来参加吧`,
                        link: response.data.data.url,
                        imgUrl: response.data.data.portrait
                    });
                    window.wx.onMenuShareAppMessage({
                        title: `我正在中国共青团青少年学法用法知识答题，快来参加吧`,
                        link: response.data.data.url,
                        imgUrl: response.data.data.portrait,
                        desc: '共青团中央2018年第十四届青少年学法用法知识竞赛'
                    });
                    _this.setState({
                        showLoading: false
                    });
                }
            }).catch(function (errors) {
                console.log(errors);
            });
        });
        window.wx.error(function(res) {
            if (res.errMsg === 'config:require subscribe') {
                _this.setState({
                    isError: true
                })
            }
        });
    }
    render() {
        let showLoading = this.state.showLoading;
        let isError = this.state.isError;
        if (isError) {
            return (<Error/>)
        }
        if (showLoading) {
            return (<Toast icon="loading" show={showLoading}>Loading...</Toast>)
        } else {
            return (
                <Provider store={store}>
                    <BrowserRouter basename='/leg'>
                        <Routes></Routes>
                    </BrowserRouter>
                </Provider>
            )
        }
    }
}

export default Root;