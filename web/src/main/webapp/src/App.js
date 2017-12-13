import React, {Component} from 'react';
import './App.less';
import star from './media/images/star_idol.png';

class App extends Component {
    constructor(props) {
        super(props);
        this.handleTouch = this.handleTouch.bind(this);
    }
    handleTouch(num) {
        if (num === 1) {
            this.props.history.push('/answer');
        }
    }
    render() {
        return (
            <div className="app main-bg">
                <h2 className="text-center title">
                    <span>法律PK赛</span>
                </h2>
                <ul className="lists">
                    <li className="text-center">
                        <span>赛事说明</span>
                    </li>
                    <li className="text-center" onClick={() => this.handleTouch(1)}>
                        <span>每日必答</span>
                    </li>
                    <li className="text-center">
                        <span>我的任务</span>
                    </li>
                    <li className="text-center">
                        <span>我的积分</span>
                    </li>
                    <li className="text-center">
                        <span>积分兑奖</span>
                    </li>
                    <li className="text-center">
                        <span>荣耀榜</span>
                    </li>
                </ul>
                <div className="star">
                    <img src={star} alt=""/>
                </div>
            </div>
        );
    }
}

export default App;