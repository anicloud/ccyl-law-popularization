/**
 * Created by sunminggui on 2017/12/15.
 */
/*我的积分页面*/
import React, {Component} from "react";
import {Button, Toast,Dialog} from 'react-weui';
import Back from './Back';
import axios from 'axios';
import {connect} from 'react-redux';
import "../media/styles/myscore.less";
import touxiang from "../media/images/scoredetail.png";
import disPlus from "../media/imgs/displus.png";
import testPng from "../media/images/test.jpg";

class MyScore extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '/home',
            scoreInfo: null,
            myPrizeTitle:"我的奖品",
            showMyPrize:false,
            showPrizeDetail:false,
            currentAward:{},
            myPrizeButtons: [
            {
                label: '返回我的积分',
                onClick: this.hideMyPrizeDialog.bind(this)
            }
            ],
            prizeDetailButtons: [
                {
                    label: '返回我的奖品',
                    onClick: this.hidePrizeDetailDialog.bind(this)
                }
            ],
            myAwardInfo:[{
                lastScore:1,
                awardType:"TENCENT_VIP",
                codeSecret:"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                isExpired:false,
                isReceivedAward:false,
                createTime:"2017-11-12"
            },{
                lastScore:1,
                awardType:"OFO_COUPON",
                codeSecret:"aaaa",
                isExpired:false,
                isReceivedAward:true,
                createTime:"2017-11-12"
            },{
                lastScore:1,
                awardType:"TENCENT_VIP",
                codeSecret:"aaaa",
                isExpired:false,
                isReceivedAward:false,
                createTime:"2017-11-12"
            },{
                lastScore:1,
                awardType:"FIVE_COUPON",
                codeSecret:"aaaa",
                isExpired:false,
                isReceivedAward:true,
                createTime:"2017-11-12"
            }]
        };
        this.handleShopping = this.handleShopping.bind(this);
        this.handleInfo = this.handleInfo.bind(this);
        this.changeMyPrize = this.changeMyPrize.bind(this);
        this.handleSignIn = this.handleSignIn.bind(this);
        this.handleShare = this.handleShare.bind(this);
        this.getPrizeDetail = this.getPrizeDetail.bind(this);
    }

    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        //剩余积分获取
        axios.get(`${host}/score/findTotalScore`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        });
        //我的奖品获取
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
    changeMyPrize() {
        this.setState({ showMyPrize: true});
    }

    handleShopping() {
        this.props.history.push({
            pathname: '/scoreshopping',
            state: '/tasks'
        });
    }
    handleSignIn() {
        this.props.history.push({
            pathname: '/signin',
            state: '/tasks'
        });
    }
    handleShare() {
        const {history} = this.props;
        history.push({
            pathname: '/prize',
            state: '/tasks'
        });
    }
    handleInfo() {
        const {history} = this.props;
        history.push('/announce');
    }

    hideMyPrizeDialog() {
        this.setState({
            showMyPrize: false,
        });
    }

    hidePrizeDetailDialog() {
        this.setState({
            showPrizeDetail: false,
            showMyPrize: true,
        });
    }

    getPrizeDetail(award){
        this.setState({
            currentAward:award,
            showPrizeDetail: true,
            showMyPrize: false,
        });
    }

    copyCode(code){
        window.clipboardData.setData('text',code);
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

    render() {
        let scoreInfo = this.state.scoreInfo;
        let _this = this;
        return (
            <div className="root">
                <div className="MyScore score-bg">
                    <div className='clearfix'>
                        <Back location='/home' history={this.props.history}/>
                    </div>
                    <div className="detail">
                        <img src={touxiang}></img>
                        <br/>
                        <span>时刻都爱上</span>
                        <br/>
                        <span>当前积分:{scoreInfo? (scoreInfo.score? scoreInfo.score : 0) : 0}</span>
                    </div>
                </div>
                <div className="bottomDiv">
                    <div className="buttonDiv">
                        <div className="iconDiv">
                            <i className='scoreshopping'/>
                            <i className='myprize'/>
                        </div>
                        <div className="spanDiv">
                            <span className="shoppingSpan" onClick={this.handleShopping}>积分商城</span>
                            <span className="prizeSpan" onClick={this.changeMyPrize}>我的奖品</span>
                        </div>
                    </div>

                    <div className="task">
                        <div className="leftDiv">
                            <span>签到</span>
                            <span className="desc">已签{scoreInfo? scoreInfo.signInCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleSignIn}>
                            <i className="plus"/>

                        </div>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>分享答题</span>
                            <span className="desc">已成功分享{scoreInfo? scoreInfo.shareCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleShare}>
                            <i className="plus"/>

                        </div>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>邀请答题</span>
                            <span className="desc">已成功邀请{scoreInfo? scoreInfo.inviteCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleShare}>
                            <i className="plus"/>

                        </div>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>好友点赞</span>
                            <span className="desc">已被点赞{scoreInfo? scoreInfo.thumbUpCount : 0}次</span>
                        </div>
                        <div className="rightDiv" onClick={this.handleShare}>
                            <i className="plus"/>

                        </div>
                    </div>
                </div>
                <Dialog type="ios" title={this.state.myPrizeTitle} buttons={this.state.myPrizeButtons} show={this.state.showMyPrize}>
                    <div className="listdiv">
                        {this.state.myAwardInfo.map(function(item,index){
                            return (

                            <div className="task" key={index}>
                                <div className="leftDiv">
                                    <span>{_this.getNameFromEnum(item.awardType)}</span>
                                    <span className="desc">获奖时间:{item.createTime}</span>
                                    <span className="desc">状态:{item.isReceivedAward===true?"已领取":item.isExpired===false?"待领取":"已过期"}</span>
                                    <span className="desc">有效期:6天</span>
                                </div>
                                {item.isReceivedAward===false&&item.isExpired===true?<div className="rightDiv"><img src={disPlus}/></div>:<div className="rightDiv" onClick={() => _this.getPrizeDetail(item)}><i className="plus"/></div>}
                            </div>
                            );
                        })}
                    </div>
                </Dialog>
                <Dialog type="ios" title={_this.getNameFromEnum(this.state.currentAward.awardType)} buttons={this.state.prizeDetailButtons} show={this.state.showPrizeDetail}>
                    {/*this.state.currentAward.awardType==="FIVE_COUPON"||this.state.currentAward.awardType==="TEN_COUPON"?<img src={this.state.currentAward.codeSecret}></img>:<div className="myPrize"><span className="codeLable">兑换码:</span><span className="codeSecret">{this.state.currentAward.codeSecret}</span><Button className="copyCode" onClick={()=>_this.copyCode(this.state.currentAward.codeSecret)}>复制兑换码</Button></div>*/}
                    {this.state.currentAward.awardType==="FIVE_COUPON"||this.state.currentAward.awardType==="TEN_COUPON"?<img src={testPng} className="srcImg"></img>:<div className="myPrize"><span className="codeLable">兑换码:</span><span className="codeSecret">{this.state.currentAward.codeSecret}</span><Button className="copyCode" onClick={()=>_this.copyCode(this.state.currentAward.codeSecret)}>复制兑换码</Button></div>}
                </Dialog>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
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

export default connect(mapStateToProps)(MyScore);