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

class AnswerQuestion extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '/home',
            question: null,
            showNext: false
        };
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
                _this.setState({
                    question: Map(response.data.data)
                });
            }
        }).catch(function (errors) {
            console.log(errors);
        })
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
                _this.setState({
                    question: Map(response.data.data)
                });
                _this.handleShowNext();
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    handleShare() {
        const {history} = this.props;
        history.push({
            pathname: '/prize'
        })
    }
    render() {
        let question = this.state.question? this.state.question.toJS() : '';
        return (
            <div className="answer main-bg">
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <h2 className="text-center h2 title">
                    <span>今日必答</span>
                </h2>
                {/*<ChoiceQuestion handleShowNext={this.handleShowNext} question={{content: 'asdjkasjdassd奥斯卡大胜靠德拉克丝懒得看来到拉萨的卡拉斯科带来快乐asdjkasjdassd奥斯卡大胜靠德拉克丝懒得看来到拉萨的卡拉斯科带来快乐'}} />*/}
                {
                    question === ''? (null) : (
                        <div>
                            <h3 className="text-center h3 title">
                                <span>第{question.dayNum}天</span>
                            </h3>
                            {
                                question.type === 'CHOICE'? (
                                    <ChoiceQuestion question={question} handleShowNext={this.handleShowNext} />
                                ) : (
                                    <TrueFalseQuestion question={question} />
                                )
                            }
                        </div>
                    )
                }
                {
                    this.state.showNext? (
                        <Button className='questionButton' type="primary" plain onClick={this.handleNext}>下一题</Button>
                    ) : (null)
                }
                {
                    question? (
                        question.order === 3? (
                            <Button className='questionButton' type="primary" plain onClick={this.handleShare}>分享荣誉</Button>
                            ) : (null)
                    ) : (null)
                }
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading
    }
}

export default connect(mapStateToProps)(AnswerQuestion);