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
import disPlus from "../media/imgs/displus.png";
import copy from "copy-to-clipboard";

class MyScore extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '/home',
            scoreInfo: {},
            myPrizeTitle:"我的奖品",
            showMyPrize:false,
            showPrizeDetail:false,
            showSuccess:false,
            successInfo:"",
            currentAward:{},
            timer:null,
            showTishi:false,
            tishiButtons: [
                {
                    label: '补全信息',
                    onClick: this.goToRegist.bind(this)
                },
                {
                    label: '返回',
                    onClick: this.hideTiShiDialog.bind(this)
                }
            ],
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
            myAwardInfo:[]
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
        if(_this.props.location!==undefined&&_this.props.location!==null&&_this.props.location.state!==undefined&&_this.props.location.state!==null&&_this.props.location.state.ifFromRegist!==undefined&&_this.props.location.state.ifFromRegist!==null){
            console.log(_this.props.location.state.ifFromRegist);
            this.changeMyPrize();
        }
    }
    changeMyPrize() {
        //我的奖品获取
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findMyAwards`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    myAwardInfo: response.data.data
                });
                _this.setState({ showMyPrize: true});
            }
        }).catch(function (errors) {
            console.log(errors);
            _this.setState({ showMyPrize: true});
        })
    }

    handleShopping() {
        this.props.history.push({
            pathname: '/scoreshopping',
            state: '/tasks'
        });
    }

    handleSignIn() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/signIn`).then(function (response) {
            if (response.data.state === 0) {
                _this.state.timer && clearTimeout(_this.state.timer);
                _this.setState({
                    scoreInfo:response.data.data,
                    successInfo:"签到成功",
                    showSuccess:true
                });
                _this.state.timer = setTimeout(function () {
                    _this.setState({
                        showSuccess:false
                    });
                }, 2000);
            }
        }).catch(function (errors) {
            console.log(errors);
        });
        //this.props.history.push({
        //    pathname: '/signin',
        //    state: '/tasks'
        //});
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

    goToRegist(){
        let _this = this;
        const {history} = _this.props;
        history.push({
            pathname:'/regist',
            state:'/tasks'
        });
    }

    hideTiShiDialog(){
        this.setState({
            showTishi: false,
        });
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
        //发请求
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/account/findInfoIsCompleted`).then(function (response) {
            if (response.data.state === 0) {
                if(response.data.data===false){
                    this.setState({
                        showTishi:true,
                    });
                }else{
                    this.setState({
                        currentAward:award,
                        showPrizeDetail: true,
                        showMyPrize: false,
                    });
                }
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }

    copyCode(code){
        let _this = this;
        copy(code);
        console.log(code);
        _this.state.timer && clearTimeout(_this.state.timer);
        _this.setState({
            successInfo:"复制成功",
            showSuccess:true
        });
        _this.state.timer = setTimeout(function () {
            _this.setState({
                showSuccess:false
            });
        }, 2000);
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
                        <img src={scoreInfo.portrait} className="touxiang"></img>
                        <br/>
                        <span>{scoreInfo.nickName}</span>
                        <br/>
                        <span>获得总积分:{scoreInfo? (scoreInfo.score? scoreInfo.score : 0) : 0}</span>
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
                            <span className="desc">已签{scoreInfo?scoreInfo.signInCount : 0}次</span>
                        </div>
                        {scoreInfo.isSignIn===true?<div className="rightDiv"><img src={disPlus}/></div>:<div className="rightDiv" onClick={this.handleSignIn}> <i className="plus"/></div>}
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
                    {this.state.myAwardInfo.length===0?(
                        <div>
                            <span className="noAward">暂无奖品</span>
                        </div>
                    ):(
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
                    )}
                </Dialog>
                <Dialog type="ios" title={_this.getNameFromEnum(this.state.currentAward.awardType)} buttons={this.state.prizeDetailButtons} show={this.state.showPrizeDetail}>
                    {/*this.state.currentAward.awardType==="FIVE_COUPON"||this.state.currentAward.awardType==="TEN_COUPON"?<img src={this.state.currentAward.codeSecret}></img>:<div className="myPrize"><span className="codeLable">兑换码:</span><span className="codeSecret">{this.state.currentAward.codeSecret}</span><Button className="copyCode" onClick={()=>_this.copyCode(this.state.currentAward.codeSecret)}>复制兑换码</Button></div>*/}
                    {this.state.currentAward.awardType==="FIVE_COUPON"||this.state.currentAward.awardType==="TEN_COUPON"?<img src={this.state.currentAward.codeSecret} className="srcImg"></img>:<div className="myPrize"><span className="codeLable">兑换码:</span><span className="codeSecret">{this.state.currentAward.codeSecret}</span><Button className="copyCode" onClick={()=>_this.copyCode(this.state.currentAward.codeSecret)}>复制兑换码</Button></div>}
                </Dialog>
                <Dialog type="ios" title="提示" buttons={this.state.tishiButtons} show={this.state.showTishi}>
                    兑换奖品前需补全信息，是否跳转
                </Dialog>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
                <Toast icon="success-no-circle" show={this.state.showSuccess}>{this.state.successInfo}</Toast>
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