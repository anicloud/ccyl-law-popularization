import React, {Component} from 'react';
import {connect} from 'react-redux';
import axios from 'axios';
import {Toast} from 'react-weui';
import '../media/styles/thumb.less';
import icon from '../media/images/signin_icon.png';
import header from '../media/imgs/header_thumbup.png';
import btn_thumbup from '../media/imgs/btn_thumbup.png';

class ThumbUp extends Component {
    constructor(props) {
        super(props);
        console.log(this.props);
        this.state = {
            scoreInfo: null,
            showToast: false,
            isThumbUp: false
        };
        this.handleAnswer = this.handleAnswer.bind(this);
        this.handleThumb = this.handleThumb.bind(this);
        this.toastTimer = null;
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        const userId = _this.props.location.state;
        alert(_this.props.location.state);
        alert(userId);
        axios.get(`${host}/share/findThumbUpInfo?id=${userId}`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data,
                    isThumbUp: response.data.data.isThumbUp
                })
            }
        }).catch(function (errors) {
            console.log(errors);
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
        let scoreInfo = this.state.scoreInfo;
        return (
            <div className='thumb common-bg'>
                <div className='text-center header'>
                    <img src={header} alt=""/>
                </div>
                {
                    isThumbUp? (
                        scoreInfo? (
                            <div className='wrapper'>
                                <div className='wrapper-thumb'>
                                    <div className='third'>
                                        <p>恭喜你已为好友</p>
                                        <p>{scoreInfo.NickName}</p>
                                        <p>点赞成功</p>
                                    </div>
                                    <div className='four'>
                                        <p>我也要争做普法小先锋与好友PK赢奖品</p>
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
                                            <p>我正在争当普法小先锋，目前积分<span>{scoreInfo.totalScore}</span>，快来为我点赞，帮我增加积分赢取奖品哦~</p>
                                        </div>
                                    </div>
                                </div>
                                <div className='text-center thumb-btn'>
                                    <img src={btn_thumbup} onClick={this.handleThumb} alt=""/>
                                </div>
                            </div>
                        ) : (null)
                    )
                }
                {/*{scoreInfo? (
                    <div>
                        <h2 className="text-center title">
                            <span>{scoreInfo.name}的荣誉</span>
                        </h2>
                        <div className={scoreInfo.score === 10? 'flower-gold' : scoreInfo.score === 8? 'flower-silver' : scoreInfo.score === 6 ? 'flower-copper' : 'flower'}>
                            <img src={scoreInfo.portrait} alt=""/>
                            <p className='text-center score-prize'>{scoreInfo.score === 15? '获得金牌' : scoreInfo.score === 10? '获得银牌' : scoreInfo.score === 5? '获得铜牌' : ''}</p>
                        </div>
                        <p className='text-center score-ques'>
                            答题获得积分：<span>{scoreInfo.score} 积分</span>
                        </p>
                        <p className='text-center share'>
                            <button className='btn btn-success' disabled={isThumbUp} onClick={this.handleThumb}>
                                <i className='glyphicon glyphicon-thumbs-up' style={{color: isThumbUp? '#f60' : '#fff'}} />
                                <span className='thumb-up' style={{color: isThumbUp? '#f60' : '#fff'}}>{isThumbUp? '已点赞' : '点赞'}</span>
                            </button>
                            <button className='btn btn-success' onClick={this.handleAnswer}>
                                <i className='glyphicon glyphicon-share-alt'/>
                                <span className='thumb-up'>去答题</span>
                            </button>
                        </p>
                    </div>
                ) : (
                    <Toast icon="loading" show={true}>Loading...</Toast>
                )}*/}
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