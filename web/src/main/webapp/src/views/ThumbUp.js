import React, {Component} from 'react';
import {connect} from 'react-redux';
import axios from 'axios';
import {Toast} from 'react-weui';
import '../media/styles/thumb.less';
import icon from '../media/images/signin_icon.png';

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
        axios.get(`${host}/score/findDailyTotalScore?id=${userId}`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        })
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
        axios.get(`${host}/score/thumbUp?toAccountId=${userId}`).then(function (response) {
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
            <div className='thumb main-bg'>
                {scoreInfo? (
                    <div>
                        <h2 className="text-center title">
                            <span>{scoreInfo.name}的荣誉</span>
                        </h2>
                        <div className={scoreInfo.score === 15? 'flower-gold' : scoreInfo.score === 10? 'flower-silver' : scoreInfo.score === 5 ? 'flower-copper' : 'flower'}>
                            <img src={scoreInfo.portrait} alt=""/>
                            <p className='text-center score-prize'>{scoreInfo.score === 15? '获得金牌' : scoreInfo.score === 10? '获得银牌' : scoreInfo.score === 5? '获得铜牌' : ''}</p>
                        </div>
                        <p className='text-center score-ques'>
                            答题获得积分：<span>{scoreInfo.score} 积分</span>
                        </p>
                        <p className='text-center share'>
                            <button className='btn btn-success' onClick={this.handleThumb}>
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
                )}
                <Toast icon="success-no-circle" show={this.state.showToast}>点赞成功</Toast>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading
    };
}

export default connect(mapStateToProps)(ThumbUp);