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

class ScoreShopping extends Component{
    constructor(props){
       super(props);
        this.state = {
            location: this.props.location.state? this.props.location.state : '/home',
            awardInfo: [{
                            awardType:"TENCENT_VIP",
                            isUsedUp:false,
                            score:1000,
                            myScore:0
                        },
                        {
                            awardType:"OFO_COUPON",
                            isUsedUp:false,
                            score:500,
                            myScore:0
                        },
                        {
                            awardType:"FIVE_COUPON",
                            isUsedUp:false,
                            score:200,
                            myScore:0
                        },
                        {
                            awardType:"TEN_COUPON",
                            isUsedUp:false,
                            score:100,
                            myScore:0
                        }]
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
                        <div className="rightDiv"><i className="plus"/></div>
                        { /*{this.state.awardInfo[0].myScore>this.getCurrentAwardScore("TENCENT_VIP").score?<div className="rightDiv" onClick={() =>_this.handleShare}><i className="plus"/></div>:<div className="rightDiv"><i className="disPlus"/></div>}>*/}
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>ofo用车券</span>
                            <span className="desc">{this.getCurrentAwardScore("OFO_COUPON").score}积分</span>
                        </div>
                        <div className="rightDiv"><i className="disPlus"/></div>
                        {/* {this.state.awardInfo[0].myScore>this.getCurrentAwardScore("OFO_COUPON").score?<div className="rightDiv" onClick={() =>_this.handleShare}><i className="plus"/></div>:<div className="rightDiv"><i className="disPlus"/></div>}>*/}
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满49减5)</span>
                            <span className="desc">{this.getCurrentAwardScore("FIVE_COUPON").score}积分</span>
                        </div>
                        {this.state.awardInfo[0].myScore>this.getCurrentAwardScore("FIVE_COUPON").score?<div className="rightDiv" onClick={() =>_this.handleShare}><i className="plus"/></div>:<div className="rightDiv"><i className="disPlus"/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满99减10)</span>
                            <span className="desc">{this.getCurrentAwardScore("TEN_COUPON").score}积分</span>
                        </div>
                        {this.state.awardInfo[0].myScore>this.getCurrentAwardScore("TEN_COUPON").score?<div className="rightDiv" onClick={() =>_this.handleShare}><i className="plus"/></div>:<div className="rightDiv"><i className="disPlus"/></div>}>
                    </div>
                </div>

                {/*<div className="scoreshopping main-bg">
              <div className='clearfix'>
                  <Back location={this.state.location} history={this.props.history} />
              </div>
              <h2 className="text-center h2 title">
                  <span>积分商城</span>
              </h2>
              <p className='text-center current-score'>剩余积分:{this.state.awardInfo[0].myScore}</p>
              <div className="listdiv">
                  <div className="largest">
                    <span className="largestImg" />
                      <div className="largestDetail">
                        <span className="largestSpan">腾讯视频会员月卡</span>
                        <span className="scoreSpan">{this.getCurrentAwardScore("TENCENT_VIP").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("TENCENT_VIP").isUsedUp===false?false:true}>{this.getCurrentAwardScore("TENCENT_VIP").isUsedUp===false?"兑换上限":this.state.awardInfo[0].myScore>this.getCurrentAwardScore("TENCENT_VIP").score?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>

                  <div className="larger">
                      <span className="largerImg" />
                      <div className="largerDetail">
                          <span className="largerSpan">ofo用车券</span>
                          <span className="scoreSpan">{this.getCurrentAwardScore("OFO_COUPON").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("OFO_COUPON").isUsedUp===false?false:true}>{this.getCurrentAwardScore("OFO_COUPON").isUsedUp===false?"兑换上限":this.state.awardInfo[0].myScore>this.getCurrentAwardScore("OFO_COUPON").score?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>
                  <div className="normal">
                      <span className="normalImg" />
                      <div className="normalDetail">
                          <span className="normalSpan">购物优惠券</span>
                          <span className="scoreSpan">{this.getCurrentAwardScore("FIVE_COUPON").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("FIVE_COUPON").isUsedUp===false?false:true}>{this.getCurrentAwardScore("FIVE_COUPON").isUsedUp===false?"兑换上限":this.state.awardInfo[0].myScore>this.getCurrentAwardScore("FIVE_COUPON").score?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>
                  <div className="smaller">
                      <span className="smallerImg" />
                      <div className="smallerDetail">
                          <span className="smallerSpan">购物优惠券</span>
                          <span className="scoreSpan">{this.getCurrentAwardScore("TEN_COUPON").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("TEN_COUPON").isUsedUp===false?false:true}>{this.getCurrentAwardScore("TEN_COUPON").isUsedUp===false?"兑换上限":this.state.awardInfo[0].myScore>this.getCurrentAwardScore("TEN_COUPON").score?"立即兑换":"积分不足"}</Button>
                  </div>
              </div>
              <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
          </div>*/}
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