/**
 * Created by sunminggui on 2017/12/13.
 */
import React, {Component} from 'react';
import '../media/styles/main.less';
import star from '../media/images/star_idol.png';

class Main extends Component {
    constructor(props) {
        super(props);
    }
    handleTouch() {
       this.props.history.push('/home');
    }
    render() {
        return (
            <div className="main main-bg">
                <h2 className="text-center title">
                    <span>法律PK赛</span>
                </h2>
                <div className="star">
                    <img src={star} alt=""/>
                </div>
                <div className='text-center greate'>
                    <button className='btn btn-success' onClick={() => this.handleTouch()}>我行我上</button>
                </div>
            </div>
        )
    }
}

export default Main;