/**
 * Created by sunminggui on 2017/12/13.
 */
/**
 * 选择题组件
 */
import React,{Component} from "react";
import {Form,FormCell,CellHeader,CellBody,Radio,Button, Toast} from 'react-weui';
import "../../media/styles/question.less"
import {connect} from 'react-redux';
import axios from 'axios';

class ChoiceQuestion extends Component {
    constructor(props) {
        super(props);
        this.state = {
            question: null,
            answer: null,
            value: '',
            isValue: false
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    componentDidMount() {
        this.setState({
            question: this.props.question
        });
    }
    componentWillReceiveProps(nextProps) {
        if (!this.state.question || (nextProps.question.order !== this.state.question.order)) {
            this.setState({
                question: nextProps.question,
                answer: null,
                value: '',
                isValue: false
            }, function () {
                document.querySelector('.weui-check:checked').checked = false;
            });
        }
    }
    handleChange(e) {
        let value = e.target.value;
        this.setState({
            value,
            isValue: false
        });
    }
    handleSubmit() {
        let _this = this;
        if (_this.state.value) {
            const {id} = _this.state.question;
            const {host, handleShowNext} = _this.props;
            axios.get(`${host}/question/verify?id=${id}&answer=${_this.state.value}`).then(function (response) {
                if (response.data.state === 0) {
                    _this.setState({
                        answer: response.data.data
                    }, function () {
                        handleShowNext();
                    });
                }
            }).catch(function (errors) {
                console.log(errors);
            })
        } else {
            _this.setState({
                isValue: true
            })
        }
    }
    render() {
        let question = this.state.question;
        let result = this.state.answer;
        let isValue = this.state.isValue;
        return (
            <div className="question">
                {
                    question? (
                        <div>
                            <p className='content'>
                                {question.content}
                            </p>
                            <Form checkbox className='form'>
                                <FormCell checkbox>
                                    <CellHeader>
                                        <Radio name="radio1" value="A" onChange={(e) => {this.handleChange(e)}}/>
                                        A.
                                    </CellHeader>
                                    <CellBody>{question.optionOne}</CellBody>
                                </FormCell>
                                <FormCell checkbox>
                                    <CellHeader>
                                        <Radio name="radio1" value="B" onChange={(e) => {this.handleChange(e)}}/>
                                        B.
                                    </CellHeader>
                                    <CellBody>{question.optionTwo}</CellBody>
                                </FormCell>
                                <FormCell checkbox>
                                    <CellHeader>
                                        <Radio name="radio1" value="C" onChange={(e) => {this.handleChange(e)}}/>
                                        C.
                                    </CellHeader>
                                    <CellBody>{question.optionThree}</CellBody>
                                </FormCell>
                            </Form>
                            {
                               isValue? (
                                   <p className='text-danger result'>请选择答案</p>
                               ) : (null)
                            }
                            {
                                result? (result.isCorrect? (
                                    <p className='text-success result'>恭喜你答对了，积分+2</p>
                                ) : (
                                    <p className='text-danger result'>很遗憾，正确答案为： {result.answer}</p>
                                )) : (
                                    <div className='question-button' onClick={this.handleSubmit}>提交</div>
                                )
                            }
                        </div>
                    ) : (
                        <Toast icon="loading" show={true}>Loading...</Toast>
                    )
                }
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    };
}

export default connect(mapStateToProps)(ChoiceQuestion);