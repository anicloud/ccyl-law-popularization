/**
 * Created by sunminggui on 2017/12/28.
 */
/**
 *我的獎品
 **/
import React,{Component} from "react";
import Back from './Back';
import "../media/styles/myprize.less";
import {Button, Toast} from 'react-weui';
import axios from 'axios';
import {connect} from 'react-redux';

class MyPrize extends Component{
    constructor(props){
        super(props);
        this.state = {
            location: this.props.location.state? this.props.location.state : '/home',
            myAwardInfo:[]
        };
        this.getPrizeDetail = this.getPrizeDetail.bind(this);
        this.getConditionFromEnum = this.getConditionFromEnum.bind(this);
        this.getNameFromEnum = this.getNameFromEnum.bind(this);
    }
     componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findMyAwards`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    myAwardInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }

   getConditionFromEnum(type){
        let result = "";
        switch(type){
            case "TENCENT_VIP":
                result = "1000积分";
                break;
            case "OFO_COUPON":
                result = "500积分";
                break;
            case "FIVE_COUPON":
                result = "200积分";
                break;
            case "TEN_COUPON":
                result = "100积分";
                break;
            case "TOP_1":
                result = "积分榜前20";
                break;
            case "TOP_2":
                result = "积分榜前20";
                break;
            case "TOP_3":
                result = "积分榜前20";
                break;
            case "TOP_4S":
                result = "积分榜前20";
                break;
            case "LUCKY":
                result = "幸运奖";
                break;
        }
        return result;
    }

    getNameFromEnum(type){
        let result = "";
        switch(type){
            case "TENCENT_VIP":
                result = "腾讯视频会员月卡";
                break;
            case "OFO_COUPON":
                result = "ofo用车券";
                break;
            case "FIVE_COUPON":
                result = "购物优惠券";
                break;
            case "TEN_COUPON":
                result = "购物优惠券";
                break;
            case "TOP_1":
                result = "京东购物卡";
                break;
            case "TOP_2":
                result = "京东购物卡";
                break;
            case "TOP_3":
                result = "京东购物卡";
                break;
            case "TOP_4S":
                result = "腾讯视频会员月卡";
                break;
            case "LUCKY":
                result = "摩拜单车骑行券";
                break;
        }
        return result;
    }

    getPrizeDetail(myAward){
        alert("a");
        this.props.history.push({
            pathname:"/prizedetail",
            state:{
                path:"/myprize",
                myAward:myAward
            }
        });
    }

    render(){
        let _this = this;
        return (
            <div className="myprize main-bg">
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <h2 className="text-center h2 title">
                    <span>积分商城</span>
                </h2>
                <div className="listdiv">
                    {this.state.myAwardInfo.map(function(item,index){
                      return (
                          <div className='prizediv' key={index}>
                              <div className='topDiv'>
                                  <span className='timeSpan'>获奖时间:{item.createTime}</span>
                                  <span className='typeSpan'>状态:{item.isReceivedAward===true?"已领取":item.isExpired===false?"待领取":"已过期"}</span>
                              </div>
                              <span className='prizeImg'></span>
                              <div className='prizeDetail'>
                                  <p className='nameSpan'>{_this.getNameFromEnum(item.awardType)}</p>
                                  <p className='scoreSpan'>{_this.getConditionFromEnum(item.awardType)}</p>
                                  <p className='trueSpan'>有效期:6天</p>
                              </div>
                              {item.isReceivedAward===true?<Button >立即兑换</Button>:<Button onClick={ () => _this.getPrizeDetail(item)}>查看奖品</Button>}
                          </div>
                      );
                    })}
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

export default connect(mapStateToProps)(MyPrize);