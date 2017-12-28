/**
 * Created by sunminggui on 2017/12/19.
 */
/**
 * 积分商城
 */
import React,{Component} from "react";
import Back from './Back';
import "../media/styles/scoreshopping.less";
import {Button} from 'react-weui';
import axios from 'axios';

class ScoreShopping extends Component{
    constructor(props){
       super(props);
        this.state = {
            location: this.props.location.state? this.props.location.state : '/home'
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
        if(type==="TENCENT_VIP"){
            for(let i=0;i<4;i++){
                let awardInfo = awardInfos[i];
                if(awardInfo.awardType==="TENCENT_VIP"){
                    return awardInfo;
                }
            }
        }else if(type==="OFO_COUPON"){
            for(let i=0;i<4;i++){
                let awardInfo = awardInfos[i];
                if(awardInfo.awardType==="OFO_COUPON"){
                    return awardInfo;
                }
            }
        }else if(type==="FIVE_COUPON"){
            for(let i=0;i<4;i++){
                let awardInfo = awardInfos[i];
                if(awardInfo.awardType==="FIVE_COUPON"){
                    return awardInfo;
                }
            }
        }else if(type==="TEN_COUPON"){
            for(let i=0;i<4;i++){
                let awardInfo = awardInfos[i];
                if(awardInfo.awardType==="TEN_COUPON"){
                    return awardInfo;
                }
            }
        }
        return null;
    }
    getCurrentScore(){
        let awardInfos = this.state.awardInfo;
        let score = awardInfos[0]?0:awardInfos[0].myScore?0:awardInfos[0].myScore;
        return score;
    }
    render(){
        return (
          <div className="scoreshopping main-bg">
              <div className='clearfix'>
                  <Back location={this.state.location} history={this.props.history} />
              </div>
              <h2 className="text-center h2 title">
                  <span>积分商城</span>
              </h2>
              <p className='text-center current-score'>当前积分:{this.getCurrentScore()}</p>
              <div className="listdiv">
                  <div className="largest">
                    <span className="largestImg" />
                      <div className="largestDetail">
                        <span className="largestSpan">腾讯视频会员月卡</span>
                        <span className="scoreSpan">{this.getCurrentAwardScore("TENCENT_VIP").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("TENCENT_VIP").isUsedUp===false?false:true}>{this.getCurrentAwardScore("TENCENT_VIP").isUsedUp===false?"兑换上限":this.getCurrentScore()>this.getCurrentAwardScore("TENCENT_VIP").score?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>

                  <div className="larger">
                      <span className="largerImg" />
                      <div className="largerDetail">
                          <span className="largerSpan">ofo共享单车用车券10张1元券</span>
                          <span className="scoreSpan">{this.getCurrentAwardScore("OFO_COUPON").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("OFO_COUPON").isUsedUp===false?false:true}>{this.getCurrentAwardScore("OFO_COUPON").isUsedUp===false?"兑换上限":this.getCurrentScore()>this.getCurrentAwardScore("OFO_COUPON").score?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>
                  <div className="normal">
                      <span className="normalImg" />
                      <div className="normalDetail">
                          <span className="normalSpan">5元优惠券（购物券49元减5元）</span>
                          <span className="scoreSpan">{this.getCurrentAwardScore("FIVE_COUPON").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("FIVE_COUPON").isUsedUp===false?false:true}>{this.getCurrentAwardScore("FIVE_COUPON").isUsedUp===false?"兑换上限":this.getCurrentScore()>this.getCurrentAwardScore("FIVE_COUPON").score?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>
                  <div className="smaller">
                      <span className="smallerImg" />
                      <div className="smallerDetail">
                          <span className="smallerSpan">10元优惠券（购物券99元减10元）</span>
                          <span className="scoreSpan">{this.getCurrentAwardScore("TEN_COUPON").score}积分</span>
                      </div>
                      <Button disabled={this.getCurrentAwardScore("TEN_COUPON").isUsedUp===false?false:true}>{this.getCurrentAwardScore("TEN_COUPON").isUsedUp===false?"兑换上限":this.getCurrentScore()>this.getCurrentAwardScore("TEN_COUPON").score?"立即兑换":"积分不足"}</Button>
                  </div>
              </div>
          </div>
        );
    }
}

export default ScoreShopping;