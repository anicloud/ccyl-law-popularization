/**
 * Created by wangxingwen on 2017/12/12.
 */
/**
 * 注册界面
 */
import React, {Component} from 'react';
import { Button, Form, FormCell, CellHeader, CellBody, Input, Label, Select } from 'react-weui';
import '../media/styles/regist.less';

class Regist extends Component {
    constructor() {
        super();
        this.handleSelect = this.handleSelect.bind(this);
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
                        <button className="btn btn-danger btn-sm">获取验证码</button>
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

export default Regist;