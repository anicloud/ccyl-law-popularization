import React, {Component} from 'react';
import './App.less';
import star from './media/images/star_idol.png';
import Back from './views/Back';
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
                {/*<div className="sign text-right clearfix">*/}
                    {/*<Back location={'/main'} history={this.props.history} />*/}
                    {/*<span className='pull-right' onClick={this.handleSign}>每日签到</span>*/}
                {/*</div>*/}
                {/*<ul className="lists">
                    <li className="text-center" onClick={() => {this.handleTouch(0)}}>
                        <span>赛事说明</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(1)}}>
                        <span>每日必答</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(2)}}>
                        <span>我的任务</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(2)}}>
                        <span>我的奖品</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(3)}}>
                        <span>我的积分</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(4)}}>
                        <span>积分兑奖</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(5)}}>
                        <span>荣耀榜</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(6)}}>
                        <span>补填信息</span>
                    </li>
                </ul>*/}
                {/*<div className="star">
                    <img src={star} alt=""/>
                </div>*/}
                <div className='text-center right-now'>
                    <div>马上答题</div>
                </div>
                <div className='list'>
                    <div className='list-first'>荣耀榜</div>
                    <div className='list-second'>竞赛介绍</div>
                    <div className='list-third'>我的积分</div>
                    <div className='list-four'>每日签到</div>
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