/**
 * Created by sunminggui on 2017/12/28.
 */
/**
 *我的獎品
 **/
import React,{Component} from "react";
import Back from './Back';
import "../media/styles/MyPrize.less";
import {Button} from 'react-weui';
import axios from 'axios';
import {connect} from 'react-redux';

class MyPrize extends Component{
    constructor(props){
        super(props);
        this.state = {
            location: this.props.location.state? this.props.location.state : '/home',
        };
    }
    //
    render(){
        return (
            <div className="myprize main-bg">
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <h2 className="text-center h2 title">
                    <span>积分商城</span>
                </h2>
                <div className="listdiv">
                    <div className="prizediv">
                        <div className="topDiv">
                            <span className="timeSpan">获奖时间:2017/12/28 18:28</span>
                            <span className="typeSpan">状态:已领取</span>
                        </div>
                        <span className="prizeImg" />
                        <div className="prizeDetail">
                            <p className="nameSpan">腾讯视频会员月卡</p>
                            <p className="scoreSpan">1000积分</p>
                            <p className="trueSpan">有效期至:2017/12/28</p>
                        </div>
                        <Button disabled={false}>立即兑换</Button>
                    </div>
                    <hr className="line"/>
                    <div className="prizediv">
                        <div className="topDiv">
                            <span className="timeSpan">获奖时间:2017/12/28 18:28</span>
                            <span className="typeSpan">状态:已领取</span>
                        </div>
                        <span className="prizeImg" />
                        <div className="prizeDetail">
                            <p className="nameSpan">腾讯视频会员月卡</p>
                            <p className="scoreSpan">500积分</p>
                            <p className="trueSpan">有效期至:2017/12/29</p>
                        </div>
                        <Button disabled={true}>兑换上限</Button>
                    </div>
                    <hr className="line"/>
                </div>
            </div>
        );
    }
}

export default MyPrize;