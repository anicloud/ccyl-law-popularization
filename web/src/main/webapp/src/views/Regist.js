/**
 * Created by wangxingwen on 2017/12/12.
 */
/**
 * 注册界面
 */
import React, {Component} from 'react';
import { Button, Form, FormCell, CellHeader, CellBody, Input, Label, Select, Toast } from 'react-weui';
import '../media/styles/regist.less';
import {connect} from 'react-redux';
import axios from 'axios';
import {Map} from 'immutable';

class Regist extends Component {
    constructor() {
        super();
        this.state = {
            smsCodeInfo: {
                second: 60,
                disabled: false,
                message: '发送验证码'
            },
            showToast: false,
            showLoading: false,
            toastTimer: null,
            errorStatus: Map({
                name: Map({
                    status: false,
                    msg: '请输入姓名'
                }),
                sex: Map({
                    status: false,
                    msg: '请选择性别'
                }),
                age: Map({
                    status: false,
                    msg: '请选择年龄'
                }),
                address: Map({
                    status: false,
                    msg: '请选择地区'
                }),
                orgName: Map({
                    status: false,
                    msg: '请输入学校或单位'
                }),
                email: Map({
                    status: false,
                    msg: '请输入邮箱'
                }),
                phone: Map({
                    status: false,
                    msg: '请输入手机号'
                }),
                code: Map({
                    status: false,
                    msg: '请输入验证码'
                })
            }),
            registInfo: Map({
                name: '',
                sex: '',
                age: '',
                address: '',
                orgName: '',
                email: '',
                phone: '',
                code: ''
            })
        };
        this.getValidateCode = this.getValidateCode.bind(this);
        this.submitForm = this.submitForm.bind(this);
        this.second = 60;
    }
    componentWillUnmount() {
        this.state.toastTimer && clearTimeout(this.state.toastTimer);
    }
    getValidateCode() {
        let _this = this;
        const {host} = this.props;
        _this.timer = setInterval(function () {
            if (_this.state.smsCodeInfo.second === 0) {
                clearInterval(_this.timer);
                _this.second = 60;
                _this.setState({
                    smsCodeInfo: {
                        second: _this.second,
                        disabled: false,
                        message: '重新发送'
                    }
                })
            } else {
                _this.setState({
                    smsCodeInfo: {
                        second: _this.second--,
                        disabled: true,
                        message: `(${_this.second + 1}s)后重新发送`
                    }
                });
            }
        }, 1000);
        _this.setState({
            showLoading: true
        });
        axios.get(`${host}/getCode?phone=18790909090`).then(function (response) {
            _this.setState({
                showLoading: false
            });
            if (response.data.state === 0) {
                console.log(response.data.data);
            }
        }).catch(function (errors) {
            _this.setState({
                showLoading: false
            });
            console.log(errors);
        })
    }
    handleForm(field, e) {
        let value = e.target.value;
        this.setState((prevState) => {
            return {registInfo: prevState.registInfo.set(field, value)};
        });
    }
    submitForm() {
        let _this = this;
        const {host} = _this.props;
        let registInfo = this.state.registInfo.toJS();
        _this.setState({
            showLoading: true
        });
        axios.post(`${host}/account/saveSelfInfo`, registInfo).then(function (response) {
            _this.setState({
                showLoading: false
            });
            if (response.data.state === 0) {
                _this.setState({showToast: true});
                _this.state.toastTimer = setTimeout(()=> {
                    _this.setState({showToast: false});
                }, 2000);
            }
        }).catch(function (errors) {
            console.log(errors);
            _this.setState({
                showLoading: false
            });
        });
        console.log(registInfo);

    }
    render() {
        return (
            <div className="regist main-bg">
                <h2 className="text-center title">
                    <span>知识竞赛选手注册</span>
                </h2>
                <Form className='regist-info'>
                    <FormCell>
                        <CellHeader>
                            <Label>姓名</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" onChange={(e) => this.handleForm('name', e)} placeholder="请输入姓名" />
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>性别</Label>
                        </CellHeader>
                        <CellBody>
                            <Select onChange={(e) => this.handleForm('sex', e)}>
                                <option value="">请选择性别</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </Select>
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>年龄</Label>
                        </CellHeader>
                        <CellBody>
                            <Select onChange={(e) => this.handleForm('age', e)}>
                                <option value="">请选择年龄</option>
                                <option value="1">10</option>
                                <option value="2">11</option>
                            </Select>
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>地区</Label>
                        </CellHeader>
                        <CellBody>
                            <Select onChange={(e) => this.handleForm('address', e)}>
                                <option value="">请选择地区</option>
                                <option value="1">北京</option>
                                <option value="2">上海</option>
                                <option value="2">深圳</option>
                            </Select>
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>学校/单位</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" onChange={(e) => this.handleForm('orgName', e)} placeholder="请输入学校/单位" />
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>邮箱</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" onChange={(e) => this.handleForm('email', e)} placeholder="请输入邮箱" />
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>手机号</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" onChange={(e) => this.handleForm('phone', e)} placeholder="请输入手机号" />
                        </CellBody>
                    </FormCell>
                    <div className="code">
                        <button className="btn btn-danger btn-sm" disabled={this.state.smsCodeInfo.disabled} onClick={this.getValidateCode}>
                            {this.state.smsCodeInfo.message}
                        </button>
                    </div>
                    <FormCell>
                        <CellHeader>
                            <Label>验证码</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" onChange={(e) => this.handleForm('code', e)} placeholder="请输入验证码" />
                        </CellBody>
                    </FormCell>
                    <Button className='reg-btn' onClick={this.submitForm}>领取奖品</Button>
                </Form>
                <Toast icon="success-no-circle" show={this.state.showToast}>Done</Toast>
                <Toast icon="loading" show={this.state.showLoading}>Loading...</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host
    }
}

export default connect(mapStateToProps)(Regist);