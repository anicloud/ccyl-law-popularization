/**
 * Created by sunminggui on 2017/12/15.
 */
/*我的积分页面*/
import React, {Component} from "react";
import {Button, Toast} from 'react-weui';
import Back from './Back';
import axios from 'axios';
import {connect} from 'react-redux';
import "../media/styles/myscore.less";
import touxiang from "../media/images/scoredetail.png";

class MyScore extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '/home',
            scoreInfo: null
        };
        this.handleShopping = this.handleShopping.bind(this);
        this.handleInfo = this.handleInfo.bind(this);
        this.handleTopList = this.handleTopList.bind(this);
        this.handleSignIn = this.handleSignIn.bind(this);
        this.handleShare = this.handleShare.bind(this);
    }

    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findTotalScore`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    handleTopList() {
        const {history} = this.props;
        history.push({
            pathname: '/score',
            state: '/tasks'
        });
    }
    handleShopping() {
        this.props.history.push({
            pathname: '/scoreshopping',
            state: '/tasks'
        });
    }
    handleSignIn() {
        this.props.history.push({
            pathname: '/signin',
            state: '/tasks'
        });
    }
    handleShare() {
        const {history} = this.props;
        history.push({
            pathname: '/prize',
            state: '/tasks'
        });
    }
    handleInfo() {
        const {history} = this.props;
        history.push('/announce');
    }

    render() {
        let scoreInfo = this.state.scoreInfo;
        return (
            <div className="root">
                <div className="MyScore score-bg">
                    <div className='clearfix'>
                        <Back location='/home' history={this.props.history}/>
                    </div>
                    <div className="detail">
                        <img src={touxiang}></img>
                        <br/>
                        <span>时刻都爱上</span>
                        <br/>
                        <span>当前积分:{scoreInfo? (scoreInfo.score? scoreInfo.score : 0) : 0}</span>
                    </div>
                </div>
                <div className="bottomDiv">
                    <div className="buttonDiv">
                        <div className="iconDiv">
                            <i className='scoreshopping'/>
                            <i className='myprize'/>
                        </div>
                        <div className="spanDiv">
                            <span className="shoppingSpan" onClick={this.handleShopping}>积分商城</span>
                            <span className="prizeSpan" onClick={this.handleTopList}>我的奖品</span>
                        </div>
                    </div>

                    <div className="task">
                        <div className="leftDiv">
                            <span>签到</span>
                            <span className="desc">已签{scoreInfo? scoreInfo.signInCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleSignIn}>
                            <i className="plus"/>

                        </div>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>分享答题</span>
                            <span className="desc">已成功分享{scoreInfo? scoreInfo.shareCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleShare}>
                            <i className="plus"/>

                        </div>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>邀请答题</span>
                            <span className="desc">已成功邀请{scoreInfo? scoreInfo.inviteCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleShare}>
                            <i className="plus"/>

                        </div>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>好友点赞</span>
                            <span className="desc">已被点赞{scoreInfo? scoreInfo.thumbUpCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleShare}>
                            <i className="plus"/>

                        </div>
                    </div>
                </div>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    }
}

export default connect(mapStateToProps)(MyScore);