/**
 * Created by wxw on 2017/12/13.
 */
import React, {Component} from 'react';
import ChoiceQuestion from "./questiontype/ChoiceQuestion";
import TrueFalseQuestion from "./questiontype/TrueFalseQuestion";
import Back from './Back';
import {connect} from 'react-redux';
import {Map} from 'immutable';
import axios from 'axios';
import {Toast, Button} from 'react-weui';
import '../media/styles/answer.less';
import {getCookie} from "../utils/index";
import reback from '../media/imgs/reback.png';

class AnswerQuestion extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '/home',
            question: null,
            showNext: false,
            isComplete: false,
            scoreInfo: {},
        };
        this.userId = getCookie('LOGIN_COOKIE');
        this.handleShowNext = this.handleShowNext.bind(this);
        this.handleNext = this.handleNext.bind(this);
        this.handleShare = this.handleShare.bind(this);
    }
    componentWillMount() {
        const {state} = this.props.location;
        if (state === '/tasks') {
            this.setState({
                location: state
            });
        }
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/question/findCurrentQuestion`).then(function (response) {
            if (response.data.state === 0) {
                if (response.data.data !== null) {
                    _this.setState({
                        question: Map(response.data.data)
                    });
                }else{
                    _this.setState({
                        isComplete: true
                    });

                }
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }
    handleShowNext() {
        this.setState((prevState) => {
            return {
                showNext: !prevState.showNext
            };
        });
    }
    handleNext() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/share/findShareInfo?id=${_this.userId}`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                });
            }
        });
        axios.get(`${host}/question/findCurrentQuestion`).then(function (response) {
            if (response.data.state === 0) {
                if (response.data.data !== null) {
                    _this.setState({
                        question: Map(response.data.data)
                    });
                    _this.handleShowNext();
                }
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    handleShare() {
        let _this = this;
        const {host} = _this.props;

        const {history} = this.props;
        history.push({
            pathname: '/prize',
            state: '/home'
        });
    }
    backAnswer() {
        const {history} = this.props;
        history.push('/answer');
    }
    render() {
        let question = this.state.question? this.state.question.toJS() : '';
        let isComplete = this.state.isComplete;
        let scoreInfo = this.state.scoreInfo;
        return (
            <div className="answer-main">
            {
            question === ''? (
                (isComplete&&scoreInfo)? (
                   <div className="answer myprize-bg">
                       <div className='clearfix'>
                           <Back location={this.state.location} history={this.props.history} />
                       </div>
                       <div className='wrapper'>
                           <h2 className='wrapper-title'>
                               {
                                   scoreInfo.correctCount === 5? (
                                       <span>已答完</span>
                                   ) : (
                                       <span onClick={this.backAnswer}>重答 <img src={reback} alt=""/></span>
                                   )
                               }
                           </h2>
                           <div className='sum-score'>
                               <div>+{scoreInfo.correctCount * 2}</div>
                               <p className='first'>当前积分：<span>{scoreInfo.totalScore}</span></p>
                               <p className='second'>答对<span>{scoreInfo.correctCount}</span>题，答错<span>{5 - scoreInfo.correctCount}</span>题</p>
                           </div>
                           <div className='thumb-up' onClick={this.handleShare}>分享答题</div>
                       </div>
                       <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                       <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
                   </div>
                    ) : (null)
                ) : (
                    <div className="answer answer-bg">
                        <div className='clearfix'>
                            <Back location={this.state.location} history={this.props.history} />
                        </div>
                        {/*<ChoiceQuestion
                            handleShowNext={this.handleShowNext}
                            question={{content: 'asdjkasjdassd奥斯卡大胜靠德拉克丝懒得看来到拉萨的卡拉斯科带来快乐asdjkasjdassd奥斯卡大胜靠德拉克丝懒得看来到拉萨的卡拉斯科带来快乐', id: 1, optionOne: 'Y', optionTwo: 'N'}}
                        />*/}
                        {/*<div className='wrapper'>
                            <h2 className='wrapper-title'>今日必答 - 第<span>1</span>日</h2>
                            <div className='wrapper-body'>
                                <h4>
                                    第<span>3</span>题 <i>共5题</i>
                                </h4>
                                <TrueFalseQuestion
                                    handleShowNext={this.handleShowNext}
                                    question={{content: 'asdjkasjdassd奥斯卡大胜靠德拉克丝懒得看来到拉萨的卡拉斯科带来快乐asdjkasjdassd奥斯卡大胜靠德拉克丝懒得看来到拉萨的卡拉斯科带来快乐', id: 1, optionOne: 'Y', optionTwo: 'N'}}
                                />
                            </div>
                        </div>*/}
                                <div className='wrapper'>
                                    <h2 className='wrapper-title'>今日必答 - 第<span>{question.dayNum}</span>日</h2>
                                    <div className='wrapper-body'>
                                        <h4>
                                            第<span>{question.order}</span>题 <i>共5题</i>
                                        </h4>
                                        {
                                            question.type === 'CHOICE'? (
                                                <ChoiceQuestion question={question} handleShowNext={this.handleShowNext} />
                                            ) : question.type === 'JUDGEMENT'?(
                                                <TrueFalseQuestion question={question} handleShowNext={this.handleShowNext} />
                                            ) : question.type === 'NINETEENCHOICE'?(
                                                <ChoiceQuestion question={question} handleShowNext={this.handleShowNext} />
                                            ):(
                                               <TrueFalseQuestion question={question} handleShowNext={this.handleShowNext} />
                                            )
                                        }
                                        {
                                            (this.state.showNext && question.order !== 5 )? (
                                                <div className='questionButton' onClick={this.handleNext}>下一题</div>
                                            ) : (null)
                                        }
                                        {
                                            (this.state.showNext && question.order === 5)? (
                                                <div className='share' onClick={this.handleShare}>完成今日答题</div>
                                            ) : (null)
                                        }
                                    </div>
                                </div>
                        <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                        <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
                    </div>
                )
            }
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    }
}

export default connect(mapStateToProps)(AnswerQuestion);