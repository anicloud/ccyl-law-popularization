/**
 * Created by wangxingwen on 2017/12/12.
 */
/**
 * 注册界面
 */
import React, {Component} from 'react';
import { Button, Form, FormCell, CellHeader, CellBody, Input, Label, Select } from 'react-weui';
import '../media/styles/regist.less';
import {connect} from 'react-redux';
import axios from 'axios';

class Regist extends Component {
    constructor() {
        super();
        this.state = {
            smsCodeInfo: {
                second: 60,
                disabled: false,
                message: '发送验证码'
            },
        };
        this.handleSelect = this.handleSelect.bind(this);
        this.getValidateCode = this.getValidateCode.bind(this);
        this.second = 60;
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
        axios.get(`${host}/getCode?phone=18790909090`).then(function (response) {
            if (response.data.state === 0) {
                console.log(response.data.data);
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    handleSelect(e) {
        console.log(e.target.value);
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
                            <Input type="text" placeholder="请输入姓名" />
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>性别</Label>
                        </CellHeader>
                        <CellBody>
                            <Select onChange={this.handleSelect}>
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
                            <Select>
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
                            <Select>
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
                            <Input type="text" placeholder="请输入学校/单位" />
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>邮箱</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" placeholder="请输入邮箱" />
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>手机号</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" placeholder="请输入手机号" />
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
                            <Input type="text" placeholder="请输入验证码" />
                        </CellBody>
                    </FormCell>
                    <Button className='reg-btn'>领取奖品</Button>
                </Form>
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