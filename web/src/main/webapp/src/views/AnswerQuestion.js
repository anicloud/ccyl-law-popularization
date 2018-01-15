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
            scoreInfo: null,
            correctCount:0,
        };
        this.userId = getCookie('LOGIN_COOKIE');
        this.backAnswer = this.backAnswer.bind(this);
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
        jsSdkConfig(axios, host);
        window.wx.ready(function () {
            console.log(1);
            _this.setState({
                isReady: true
            });
            axios.get(`${host}/share/findShareInfo?id=${_this.userId}`).then(function (response) {
                if (response.data.state === 0) {
                    let scoreInfo = response.data.data;
                    _this.setState({
                        correctCount: scoreInfo.correctCount
                    });
                    window.wx.onMenuShareTimeline({
                        title: `我正在争当普法先锋，大家快来给我点赞，助我涨积分赢奖品`,
                        link: scoreInfo.url,
                        imgUrl: scoreInfo.portrait,
                        success: function (res) {
                            axios.get(`${host}/share/share`).then(function (response) {
                                console.log(response);
                            }).catch(function (errors) {
                                console.log(errors);
                            });
                        },
                        fail: function (res) {

                        }
                    });
                    /*我在中国共青团青少年学法用法知识竞赛答对${scoreInfo.correctCount}道题，快来支持我吧!*/
                    window.wx.onMenuShareAppMessage({
                        title: `我正在争当普法先锋，大家快来给我点赞，助我涨积分赢奖品`,
                        link: scoreInfo.url,
                        imgUrl: scoreInfo.portrait,
                        desc: '第十四届全国青少年学法用法网上知识竞赛',
                        success: function (res) {
                            axios.get(`${host}/share/share`).then(function (response) {
                                console.log(response);
                            }).catch(function (errors) {
                                console.log(errors);
                            });
                        },
                        fail: function (res) {

                        }
                    });
                }
            }).catch(function (errors) {
                console.log(errors);
            });
        });
        axios.get(`${host}/question/findCurrentQuestion`).then(function (response) {
            if (response.data.state === 0) {
                if (response.data.data !== null) {
                    _this.setState({
                        question: Map(response.data.data)
                    });
                }else{
                    axios.get(`${host}/score/findSelfRank`).then(function (response) {
                        if (response.data.data !== null) {
                            _this.setState({
                                mySelfRank:response.data.data.ranking
                            });
                            //剩余积分获取
                            axios.get(`${host}/score/findResidueScore`).then(function (response) {
                                if (response.data.state === 0) {
                                    _this.setState({
                                        scoreInfo: response.data.data,
                                        isComplete: true
                                    })
                                }
                            }).catch(function (errors) {
                                console.log(errors);
                            });
                        }
                    }).catch(function(errors){
                        console.log(errors);
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
        this.setState(
            function (prevState) {
                return {
                    showPopup: !prevState.showPopup
                }
            }
        );
    }
    backAnswer() {
        alert("a");
        const {history,location} = this.props;
        location.reload(true);
    }
    render() {
        let question = this.state.question? this.state.question.toJS() : '';
        let isComplete = this.state.isComplete;
        let scoreInfo = this.state.scoreInfo;
        let correctCount = this.state.correctCount;
        let mySelfRank = this.state.mySelfRank;
        return (
            <div className="answer-main">
            {
            question === ''? (
                (isComplete&&scoreInfo)? (
                   <div className="answer1-main myprize-bg">
                       <div className="answer1">
                           <div className='clearfix'>
                               <Back location={this.state.location} history={this.props.history} />
                           </div>
                           <div className='wrapper'>
                               <h2 className='wrapper-title'>
                                   {
                                       correctCount === 5? (
                                           <span>已答完</span>
                                       ) : (
                                           <span onClick={this.backAnswer}>重答 <img src={reback} alt=""/></span>
                                       )
                                   }
                               </h2>
                               <div className='sum-score'>
                                   <div><span className="score">+{correctCount*2?correctCount*2:0}</span></div>
                               </div>
                               <div className="sum-detail">
                                   <p className='first'>恭喜你！今日答对{correctCount}题</p>
                                   <p className='second'>当前积分：<span>{scoreInfo.score?scoreInfo.score:0}</span></p>
                                   {mySelfRank!==-1?(<p className="third">当前排名:<span>{mySelfRank?mySelfRank:0}</span></p>):(null)}
                               </div>
                               <div className="sum-bottom">
                                   <p className='first'>马上拉好友为你点赞吧</p>
                                   <p className='second'>每天都有新题，等你来答!</p>
                                   <p className="third">一起参与答题，涨积分赢奖品</p>
                               </div>
                               <div className='share' onClick={this.handleShare}>马上拉好友点赞</div>
                           </div>
                           <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                           <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
                       </div>
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
                        <div className='popup' style={{display: this.state.showPopup? 'block' : 'none'}}>
                            <div className='arrow'>
                                <img src={arrow} alt=""/>
                            </div>
                            <div className='first'>
                                <img src={first} alt=""/>
                            </div>
                            <div className='second'>
                                <img src={second} alt=""/>
                            </div>
                            <div className='text-center know'>
                                <img src={know} onClick={this.handleShare} alt=""/>
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