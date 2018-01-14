import React, {Component} from 'react';
import '../media/styles/error.less';
import qrcode from '../media/imgs/qrcode.bmp';

class Error extends Component {
    render() {
        return (
            <div className='error answer-bg'>
                <img src={qrcode} alt=""/>
                <p className='text-center'>扫描二维码，关注点击我要答题</p>
            </div>
        )
    }
}

export default Error;