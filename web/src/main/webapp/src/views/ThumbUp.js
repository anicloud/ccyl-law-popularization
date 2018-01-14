import React, {Component} from 'react';
import {connect} from 'react-redux';
import axios from 'axios';
import {Toast} from 'react-weui';
import {jsSdkConfig} from "../utils/index";
import '../media/styles/thumb.less';
import btn_thumbup from '../media/imgs/btn_thumbup.png';

class ThumbUp extends Component {
    constructor(props) {
        super(props);
        console.log(this.props);
        this.state = {
            scoreInfo: null,
            showToast: false,
            isThumbUp: false,
            isReady: false,
            alreadyThumbUp:false
        };
        this.handleAnswer = this.handleAnswer.bind(this);
        this.handleThumb = this.handleThumb.bind(this);
        this.toastTimer = null;
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
                        alreadyThumbUp: response.data.data.isThumbUp
                    })
                }
            }).catch(function (errors) {
                console.log(errors);
            });
        });
    }
    componentWillUnmount() {
        this.toastTimer && clearTimeout(this.toastTimer);
    }
    handleAnswer() {
        const {history} = this.props;
        history.push('/');
    }
    handleThumb() {
        let _this = this;
        const {host} = _this.props;
        const userId = _this.props.location.state;
        axios.get(`${host}/share/thumbUp?toAccountId=${userId}`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    showToast: true,
                    isThumbUp: true
                });
                _this.toastTimer = setTimeout(()=> {
                    _this.setState({showToast: false});
                }, 2000);
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    render() {
        let isThumbUp = this.state.isThumbUp;
        let alreadyThumbUp = this.state.alreadyThumbUp;
        let scoreInfo = this.state.scoreInfo;
        let isReady = this.state.isReady;
        return (
            <div className='thumb comeon-bg'>
                {/*<div className='text-center header'>
                    <img src={header} alt=""/>
                </div>*/}
                {
                    isReady && alreadyThumbUp?(
                        <div className='wrapper'>
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
                        </div>
                    ):(
                        isReady && isThumbUp? (
                            scoreInfo? (
                                <div className='wrapper'>
                                    <div className='wrapper-thumb'>
                                        <div className='third'>
                                            <p>恭喜你已为好友</p>
                                            <p>{scoreInfo.NickName}</p>
                                            <p>点赞成功</p>
                                        </div>
                                        <div className='four'>
                                            <P>已有{scoreInfo.thumbUpCount?scoreInfo.thumbUpCount+1:1}人为好友{scoreInfo.NickName}点赞,增加积分{scoreInfo.thumbUpCount?scoreInfo.thumbUpCount+1:1}</P>
                                            <p>我也要争当普法先锋，与好友PK赢奖品</p>
                                        </div>
                                    </div>
                                    <div className='text-center thumb-btn'>
                                        <div className='right-now' onClick={this.handleAnswer}>马上答题</div>
                                    </div>
                                </div>
                            ) : (null)
                        ) : (
                            scoreInfo? (
                                <div className='wrapper'>
                                    <div className='clearfix wrapper-thumb'>
                                        <div className='pull-left first'>
                                            <img src={scoreInfo.toPortrait} alt=""/>
                                            <p>{scoreInfo.toNickName}</p>
                                        </div>
                                        <div className='pull-right second'>
                                            <div>
                                                <p>为<span>{scoreInfo.toNickName}</span>增加积分领取奖品，力挺TA争当普法先锋</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div className='text-center thumb-btn'>
                                        <img src={btn_thumbup} onClick={this.handleThumb} alt=""/>
                                    </div>
                                </div>
                            ) : (null)
                        )
                    )
                }
                <Toast icon="success-no-circle" show={this.state.showToast}>点赞成功</Toast>
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

export default connect(mapStateToProps)(ThumbUp);