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

ReactDOM.render(
    <Root/>,
    document.getElementById('root'));
registerServiceWorker();
