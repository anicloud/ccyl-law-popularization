import React, {Component} from 'react';
import $ from 'jquery';
import {ActionSheet, Button} from 'react-weui';

class AsyncZen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ios_show: false,
            android_show: false,
            menus: [{
                label: 'Option 1',
                onClick: ()=> {}
            }, {
                label: 'Option 2',
                onClick: ()=> {}
            }],
            actions: [
                {
                    label: 'Cancel',
                    onClick: this.hide.bind(this)
                }
            ]
        }
    }
    hide(){
        this.setState({
            auto_show: false,
            ios_show: false,
            android_show: false,
        });
    }
    componentWillMount() {
        console.log($('.App'))
    }
    render() {
        return (
            <div>
                AsyncZen component
                <button className='btn btn-default'>
                    <i className='glyphicon glyphicon-adjust'></i>
                    按钮
                </button>
                <i className='material-icons'>alarm_off</i>
                <Button type="default" onClick={e=>this.setState({ios_show: true})}>IOS ActionSheet</Button>
                <ActionSheet
                    menus={this.state.menus}
                    actions={this.state.actions}
                    show={this.state.ios_show}
                    type="ios"
                    onRequestClose={e=>this.setState({ios_show: false})}
                />

                <br/>

                <Button type="default" onClick={e=>this.setState({android_show: true})}>Android ActionSheet</Button>
                <ActionSheet
                    menus={this.state.menus}
                    actions={this.state.actions}
                    show={this.state.android_show}
                    type="android"
                    onRequestClose={e=>this.setState({android_show: false})}
                />
            </div>
        )
    }
}

export default AsyncZen;