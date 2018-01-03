import React, {Component} from 'react';
import './App.less';
import {connect} from 'react-redux';
import {Toast} from 'react-weui';

class App extends Component {
    constructor(props) {
        super(props);
        this.handleTouch = this.handleTouch.bind(this);
        this.handleSign = this.handleSign.bind(this);
    }
    handleTouch(num) {
        switch (num) {
            case 0:
                this.props.history.push('/description');
                break;
            case 1:
                this.props.history.push('/answer');
                break;
            case 2:
                this.props.history.push('/myprize');
                break;
            case 3:
                this.props.history.push('/tasks');
                break;
            case 4:
                this.props.history.push('/scoreshopping');
                break;
            case 5:
                this.props.history.push('/score');
                break;
            case 6:
                this.props.history.push('/regist');
                break;
            default:
                return;
        }
    }
    handleSign() {
        this.props.history.push('/signin');
    }
    render() {
        return (
            <div className="app main-bg">
                <div className='text-center right-now'>
                    <div onClick={() => {this.handleTouch(1)}}>马上答题</div>
                </div>
                <div className='list'>
                    <div className='list-first' onClick={() => {this.handleTouch(5)}}>荣耀榜</div>
                    <div className='list-second' onClick={() => {this.handleTouch(0)}}>竞赛介绍</div>
                    <div className='list-third' onClick={() => {this.handleTouch(3)}}>我的积分</div>
                    <div className='list-four' onClick={() => {this.handleTouch(3)}}>每日签到</div>
                </div>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
            </div>
        );
    }
}

function mapStateToProps (state) {
    return {
        showLoading: state.showLoading
    }
}

export default connect(mapStateToProps)(App);