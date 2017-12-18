import React, {Component} from 'react';
import './App.less';
import star from './media/images/star_idol.png';
import axios from 'axios';
import {connect} from 'react-redux';
import {jsSdkConfig} from './utils/index';
import Back from './views/Back';

class App extends Component {
    constructor(props) {
        super(props);
        this.handleTouch = this.handleTouch.bind(this);
        this.handleSign = this.handleSign.bind(this);
    }
    componentWillMount() {
        const {host} = this.props;
        jsSdkConfig(axios, host);
        window.wx.ready(function () {
            window.wx.getLocation({
                success: function (res) {
                    console.log(res);
                },
                cancel: function (res) {
                    console.log('用户拒绝授权获取地理位置');
                }
            })
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
            case 2:
                this.props.history.push('/tasks');
                break;
            case 3:
                this.props.history.push('/myscore');
                break;
            case 5:
                this.props.history.push('/score');
                break;
            default:
                return;
        }
    }
    handleSign() {
        this.props.history.push('/signin');
    }
    render() {
        return (
            <div className="app main-bg">
                <div className="sign text-right clearfix">
                    <Back location={'/main'} history={this.props.history} />
                    <span className='pull-right' onClick={this.handleSign}>每日签到</span>
                </div>
                <h2 className="text-center title">
                    <span>法律PK赛</span>
                </h2>
                <ul className="lists">
                    <li className="text-center" onClick={() => {this.handleTouch(0)}}>
                        <span>赛事说明</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(1)}}>
                        <span>每日必答</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(2)}}>
                        <span>我的任务</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(3)}}>
                        <span>我的积分</span>
                    </li>
                    <li className="text-center">
                        <span>积分兑奖</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(5)}}>
                        <span>荣耀榜</span>
                    </li>
                </ul>
                <div className="star">
                    <img src={star} alt=""/>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host
    };
}

export default connect(mapStateToProps)(App);