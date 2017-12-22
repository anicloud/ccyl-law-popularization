import React from 'react';
import ReactDOM from 'react-dom';
import Root from  './Root';
import 'weui';
import 'react-weui/build/packages/react-weui.css';
import './index.less';
import registerServiceWorker from './registerServiceWorker';
import {jsSdkConfig} from "./utils/index";
import axios from 'axios';
/*配置微信jssdk*/
jsSdkConfig(axios, '/leg');
window.wx.error(function(res) {
    console.log(res);
});
/*window.wx.ready(function () {
    window.wx.getLocation({
        success: function (res) {
            console.log(res);
        },
        cancel: function (res) {
            console.log('用户拒绝授权获取地理位置');
        }
    })
});*/

ReactDOM.render(
    <Root/>,
    document.getElementById('root'));
registerServiceWorker();
