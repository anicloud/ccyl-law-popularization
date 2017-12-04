import React, {Component} from 'react';
import './App.less';
import { Button, Form, FormCell, CellHeader, CellBody, Input, Label } from 'react-weui';

class App extends Component {
    constructor() {
        super();
        this.state = {
            userName: '',
            password: ''
        }
    }
    handleUserName(e) {
        this.setState({
            userName: e.target.value
        });
    }
    handlePassword(e) {
        this.setState({
            password: e.target.value
        })
    }
    render() {
        return (
            <div className="App">
                <Form className='login'>
                    <FormCell>
                        <CellHeader>
                            <Label>用户名</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="text" placeholder="请输入用户名" onChange={(e) => this.handleUserName(e)} />
                        </CellBody>
                    </FormCell>
                    <FormCell>
                        <CellHeader>
                            <Label>密码</Label>
                        </CellHeader>
                        <CellBody>
                            <Input type="password" placeholder="请输入密码" onChange={(e) => this.handlePassword(e)} />
                        </CellBody>
                    </FormCell>
                    <Button className='login-btn'>登录</Button>
                </Form>
            </div>
        );
    }
}

export default App;