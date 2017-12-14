import React, {Component} from 'react';
import './App.less';
import star from './media/images/star_idol.png';
import {Toast} from 'react-weui';

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