import React, {Component} from 'react';
import './App.less';
import star from './media/images/star_idol.png';
import Back from './views/Back';

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
                <div className="sign text-right clearfix">
                    <Back location={'/main'} history={this.props.history} />
                    <span className='pull-right' onClick={this.handleSign}>每日签到</span>
                </div>
                <h2 className="text-center title">
                    <span>法律PK赛</span>
                </h2>
                <ul className="lists">
                    <li className="text-center" onClick={() => {this.handleTouch(0)}}>
                        <span>赛事说明</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(1)}}>
                        <span>每日必答</span>
                    </li>
                    {/*<li className="text-center" onClick={() => {this.handleTouch(2)}}>
                        <span>我的任务</span>
                    </li>*/}
                    <li className="text-center" onClick={() => {this.handleTouch(3)}}>
                        <span>我的积分</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(4)}}>
                        <span>积分兑奖</span>
                    </li>
                    <li className="text-center" onClick={() => {this.handleTouch(5)}}>
                        <span>荣耀榜</span>
                    </li>
                    {/*<li className="text-center" onClick={() => {this.handleTouch(6)}}>
                        <span>补填信息</span>
                    </li>*/}
                </ul>
                <div className="star">
                    <img src={star} alt=""/>
                </div>
            </div>
        );
    }
}

export default App;