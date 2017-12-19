import React, {Component} from 'react';
import { Provider } from 'react-redux';
import {BrowserRouter} from 'react-router-dom';
import store from './redux/index';
import Routes from './routes/index';
import axios from 'axios';
import {showLoading} from './redux/actions';

class Root extends Component {
    componentWillMount() {
        // 添加请求拦截器
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
            return response;
        }, function (error) {
            // 对响应错误做点什么
            store.dispatch(showLoading(false));
            return Promise.reject(error);
        });
    }
    render() {
        return (
            <Provider store={store}>
                <BrowserRouter basename='/leg'>
                    <Routes></Routes>
                </BrowserRouter>
            </Provider>
        )
    }
}

export default Root;