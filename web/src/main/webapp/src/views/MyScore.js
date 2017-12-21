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
            <div className="root main-bg">
                <div className="MyScore score-bg">
                    <div className='clearfix'>
                        <Back location='/home' history={this.props.history}/>
                    </div>
                    <div className="cloud">
                        <h2 className="text-center h2 title">
                            <span>我的积分</span>
                        </h2>
                        <h3 className="text-center h3 title">
                            <span>{scoreInfo? scoreInfo.score : 0}</span>
                        </h3>
                    </div>
                </div>
                <div className="bottomDiv">
                    <div className="buttonDiv">
                        <span onClick={this.handleShopping}><i className='scoreshopping'/>积分商城</span>
                        <span onClick={this.handleTopList}><i className='scoredetail'/>积分榜</span>
                    </div>
                    <div className="scoreTask">
                        <span>积分任务</span>
                        <i onClick={this.handleInfo} className='detail'/>
                    </div>
                    <div className="task">
                        <span>签到</span>
                        <Button onClick={this.handleSignIn}>签到</Button>
                        <span className="desc">+2积分/次</span>
                    </div>
                    <div className="task">
                        <span>普法小奖状</span>
                        <Button onClick={this.handleShare}>查看</Button>
                        <span className="desc">+3积分/次</span>
                    </div>
                    <div className="task">
                        <span>好友点赞</span>
                        <Button onClick={this.handleShare}>分享</Button>
                        <span className="desc">+2积分/次</span>
                    </div>
                </div>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading
    }
}

export default connect(mapStateToProps)(MyScore);