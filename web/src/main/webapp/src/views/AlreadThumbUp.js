import React, {Component} from 'react';
import {connect} from 'react-redux';
import axios from 'axios';
import {Toast} from 'react-weui';
import {jsSdkConfig} from "../utils/index";
import '../media/styles/alreadyThumb.less';

class AlreadyThumbUp extends Component {
    constructor(props) {
        super(props);
        console.log(this.props);
        this.state = {
            scoreInfo: null,
            isThumbUp: false,
            isReady: false
        };
        this.handleAnswer = this.handleAnswer.bind(this);
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        const userId = _this.props.location.state;
        jsSdkConfig(axios, host);
        window.wx.ready(function () {
            _this.setState({
                isReady: true
            });
            axios.get(`${host}/share/findThumbUpInfo?toAccountId=${userId}`).then(function (response) {
                if (response.data.state === 0) {
                    _this.setState({
                        scoreInfo: response.data.data,
                        isThumbUp: response.data.data.isThumbUp
                    })
                }
            }).catch(function (errors) {
                console.log(errors);
            });
        });
    }
    handleAnswer() {
        const {history} = this.props;
        history.push('/');
    }
    render() {
        let scoreInfo = this.state.scoreInfo;
        let isReady = this.state.isReady;
        return (
            <div className='thumb comeon-bg'>
                {
                    isReady?(<div className='wrapper'>
                    <div className='wrapper-thumb'>
                        <div className='third'>
                            <p>你今日已为好友</p>
                            <p>{scoreInfo.NickName}</p>
                            <p>点过赞了</p>
                        </div>
                        <div className='four'>
                            <p>我也要争当普法先锋，与好友PK赢奖品</p>
                        </div>
                    </div>
                    <div className='text-center thumb-btn'>
                        <div className='right-now' onClick={this.handleAnswer}>马上答题</div>
                    </div>
                    </div>):(null)
                }
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
    };
}

export default connect(mapStateToProps)(AlreadyThumbUp);