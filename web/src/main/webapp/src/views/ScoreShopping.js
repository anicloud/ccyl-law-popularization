/**
 * Created by sunminggui on 2017/12/19.
 */
/**
 * 积分商城
 */
import React,{Component} from "react";
import Back from './Back';
import {Button,Toast} from 'react-weui';
import "../media/styles/scoreshopping.less";
import axios from 'axios';
import {connect} from 'react-redux';
import touxiang from "../media/images/scoredetail.png";
import disPlus from "../media/imgs/displus.png";

class ScoreShopping extends Component{
    constructor(props){
       super(props);
        this.state = {
            location: this.props.location.state? this.props.location.state : '/home',
            awardInfo: []
        };
    }

    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findAllAwards`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    awardInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    getCurrentAwardScore(type){
        let awardInfos = this.state.awardInfo;
        console.log(awardInfos);
        for(let i=0;i<4;i++){
            let awardInfo = awardInfos[i];
            console.log(awardInfo);
            if(type===awardInfo.awardType) {
                return awardInfo;
            }
        }
    }

    exchangePrizes(type){

    }
    render(){
        let _this = this;
        return (
            <div className="root">
                <div className="scoreshopping score-bg">
                    <div className='clearfix'>
                        <Back location='/home' history={this.props.history}/>
                    </div>
                    <div className="detail">
                        <img src={touxiang}></img>
                        <br/>
                        <span>时刻都爱上</span>
                        <br/>
                        <span>剩余积分:{this.state.awardInfo[0].myScore}</span>
                    </div>
                </div>
                <div className="bottomDiv">
                    <div className="task">
                        <div className="leftDiv">
                            <span>腾讯视频会员月卡</span>
                            <span className="desc">{this.getCurrentAwardScore("TENCENT_VIP").score}积分</span>
                        </div>
                        {this.state.awardInfo[0].myScore>this.getCurrentAwardScore("TENCENT_VIP").score&&this.getCurrentAwardScore("TENCENT_VIP").isUsedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("TENCENT_VIP")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>ofo用车券</span>
                            <span className="desc">{this.getCurrentAwardScore("OFO_COUPON").score}积分</span>
                        </div>
                        {this.state.awardInfo[0].myScore>this.getCurrentAwardScore("OFO_COUPON").score&&this.getCurrentAwardScore("OFO_COUPON").isUsedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("OFO_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满49减5)</span>
                            <span className="desc">{this.getCurrentAwardScore("FIVE_COUPON").score}积分</span>
                        </div>
                        {this.state.awardInfo[0].myScore>this.getCurrentAwardScore("FIVE_COUPON").score&&this.getCurrentAwardScore("FIVE_COUPON").isUsedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("FIVE_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满99减10)</span>
                            <span className="desc">{this.getCurrentAwardScore("TEN_COUPON").score}积分</span>
                        </div>
                        {this.state.awardInfo[0].myScore>this.getCurrentAwardScore("TEN_COUPON").score&&this.getCurrentAwardScore("TEN_COUPON").isUsedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("TEN_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                </div>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    }
}

export default connect(mapStateToProps)(ScoreShopping);