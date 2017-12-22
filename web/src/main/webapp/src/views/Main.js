/**
 * Created by sunminggui on 2017/12/13.
 */
import React, {Component} from 'react';
import {jsSdkConfig} from '../utils/index';
import {connect} from 'react-redux';
import axios from 'axios';
import '../media/styles/main.less';
import star from '../media/images/star_idol.png';

class Main extends Component {
    handleTouch() {
       this.props.history.push('/home');
    }
    componentWillMount() {
        const {host} = this.props;
        jsSdkConfig(axios, host);
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