/**
 * Created by sunminggui on 2017/12/13.
 */
/**
 * 判断题组件
 */
import React,{Component} from "react";
import {Form,FormCell,CellHeader,CellBody,Radio,Button, Toast} from 'react-weui';
import {connect} from 'react-redux';
import axios from 'axios';
import "../../media/styles/question.less"

class TrueFalseQuestion extends Component {
    constructor(props) {
        super(props);
        this.state = {
            question: null,
            answer: null,
            value: ''
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
                value: ''
            });
        }
    }
    handleChange(e) {
        let value = e.target.value;
        this.setState({
            value
        });
    }
    handleSubmit() {
        let _this = this;
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
    }
    render() {
        let question = this.state.question;
        let result = this.state.answer;
        return (
            <div className="question">
                {
                    question? (
                        <div>
                            <h3 className="text-center title">
                                <span>第{question.order}题</span>
                            </h3>
                            <p className='content'>{question.content}</p>
                            {
                                question.order === 1? (
                                    <Form checkbox className='form'>
                                        <FormCell checkbox>
                                            <CellHeader>
                                                <Radio name="radio4" value={question.optionOne} onChange={(e) => {this.handleChange(e)}}/>
                                            </CellHeader>
                                            <CellBody>对</CellBody>
                                        </FormCell>
                                        <FormCell checkbox>
                                            <CellHeader>
                                                <Radio name="radio4" value={question.optionTwo} onChange={(e) => {this.handleChange(e)}}/>
                                            </CellHeader>
                                            <CellBody>错</CellBody>
                                        </FormCell>
                                    </Form>
                                ) : question.order === 2? (
                                    <Form checkbox className='form'>
                                        <FormCell checkbox>
                                            <CellHeader>
                                                <Radio name="radio5" value={question.optionOne} onChange={(e) => {this.handleChange(e)}}/>
                                            </CellHeader>
                                            <CellBody>对</CellBody>
                                        </FormCell>
                                        <FormCell checkbox>
                                            <CellHeader>
                                                <Radio name="radio5" value={question.optionTwo} onChange={(e) => {this.handleChange(e)}}/>
                                            </CellHeader>
                                            <CellBody>错</CellBody>
                                        </FormCell>
                                    </Form>
                                ) : question.order === 3? (
                                    <Form checkbox className='form'>
                                        <FormCell checkbox>
                                            <CellHeader>
                                                <Radio name="radio6" value={question.optionOne} onChange={(e) => {this.handleChange(e)}}/>
                                            </CellHeader>
                                            <CellBody>对</CellBody>
                                        </FormCell>
                                        <FormCell checkbox>
                                            <CellHeader>
                                                <Radio name="radio6" value={question.optionTwo} onChange={(e) => {this.handleChange(e)}}/>
                                            </CellHeader>
                                            <CellBody>错</CellBody>
                                        </FormCell>
                                    </Form>
                                ) : (null)
                            }
                            {
                                result? (result.isCorrect? (
                                    <p className='text-danger result'>恭喜你答对了，积分+5</p>
                                ) : (
                                    <p className='text-danger result'>很遗憾，正确答案为： {result.answer}</p>
                                )) : (
                                    <Button className='question-button' type="primary" plain onClick={this.handleSubmit}>提交</Button>
                                )
                            }
                        </div>
                    ) : (
                        <Toast icon="loading" show={true}>Loading...</Toast>
                    )
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

export default connect(mapStateToProps)(TrueFalseQuestion);
