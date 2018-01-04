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
import disPlus from "../media/imgs/displus.png";

class ScoreShopping extends Component{
    constructor(props){
       super(props);
        this.state = {
            showSuccess:false,
            showWarning:false,
            location: this.props.location.state? this.props.location.state : '/home',
            warningInfo:"",
            successInfo:"",
            timer:null,
            awardInfo: [],
            userInfo:[]
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
            _this.state.timer && clearTimeout(_this.state.timer);
            _this.setState({
                warningInfo:"请求失败",
                showWarning:true
            });
            _this.state.timer = setTimeout(function () {
                _this.setState({
                    showWarning:false
                });
            }, 2000);
            console.log(errors);
        });
        axios.get(`${host}/account/findById`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    userInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            _this.state.timer && clearTimeout(_this.state.timer);
            _this.setState({
                warningInfo:"请求失败",
                showWarning:true
            });
            _this.state.timer = setTimeout(function () {
                _this.setState({
                    showWarning:false
                });
            }, 2000);
            console.log(errors);
        })
    }
    getCurrentAwardScore(type){
        let awardInfos = this.state.awardInfo;
        for(let i=0;i<awardInfos.length;i++){
            let awardInfo = awardInfos[i];
            console.log(awardInfo);
            if(type===awardInfo.awardType) {
                return awardInfo;
            }
        }
    }

    exchangePrizes(type){
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/convertAward?awardType=${type}`).then(function (response) {
            console.log(response);
            if (response.data.state === 0) {
                _this.state.timer && clearTimeout(_this.state.timer);
                _this.setState({
                    successInfo:"兑换成功",
                    showSuccess:true,
                });
                _this.state.timer = setTimeout(function () {
                    _this.setState({
                        showSuccess:false
                    });
                }, 2000);
            }
            if(response.data.state !== 0){
                _this.state.timer && clearTimeout(_this.state.timer);
                _this.setState({
                    warningInfo:response.date.msg,
                    showWarning:true
                });
                _this.state.timer = setTimeout(function () {
                    _this.setState({
                        showWarning:false
                    });
                }, 2000);
            }
        }).catch(function (errors) {
            console.log(errors);
            _this.state.timer && clearTimeout(_this.state.timer);
            _this.setState({
                warningInfo:"请求失败",
                showWarning:true
            });
            _this.state.timer = setTimeout(function () {
                _this.setState({
                    showWarning:false
                });
            }, 2000);
            console.log(errors);
        });
    }
    render(){
        let _this = this;
        let userInfo = this.state.userInfo;
        return (
            <div className="root">
                <div className="scoreshopping score-bg">
                    <div className='clearfix'>
                        <Back location='/home' history={this.props.history}/>
                    </div>
                    <div className="detail">
                        <img src={userInfo.portrait} className="touxiang"></img>
                        <br/>
                        <span>{userInfo.nickName}</span>
                        <br/>
                        <span>剩余积分:{_this.state.awardInfo[0]?_this.state.awardInfo[0].myScore:0}</span>
                    </div>
                </div>
                <div className="bottomDiv">
                    <div className="task">
                        <div className="leftDiv">
                            <span>腾讯视频会员月卡</span>
                            <span className="desc">{_this.state.awardInfo.length===0?1000:_this.getCurrentAwardScore("TENCENT_VIP").score}积分</span>
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>_this.getCurrentAwardScore("TENCENT_VIP").score&&_this.getCurrentAwardScore("TENCENT_VIP").usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("TENCENT_VIP")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>ofo用车券</span>
                            <span className="desc">{_this.state.awardInfo.length===0?500:_this.getCurrentAwardScore("OFO_COUPON").score}积分</span>
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>_this.getCurrentAwardScore("OFO_COUPON").score&&_this.getCurrentAwardScore("OFO_COUPON").usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("OFO_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满49减5)</span>
                            <span className="desc">{_this.state.awardInfo.length===0?200:_this.getCurrentAwardScore("FIVE_COUPON").score}积分</span>
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>_this.getCurrentAwardScore("FIVE_COUPON").score&&_this.getCurrentAwardScore("FIVE_COUPON").usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("FIVE_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满99减10)</span>
                            <span className="desc">{_this.state.awardInfo.length===0?100:_this.getCurrentAwardScore("TEN_COUPON").score}积分</span>
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>this.getCurrentAwardScore("TEN_COUPON").score&&this.getCurrentAwardScore("TEN_COUPON").usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("TEN_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                </div>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.state.showWarning}>{this.state.warningInfo}</Toast>
                <Toast icon="success-no-circle" show={this.state.showSuccess}>{this.state.successInfo}</Toast>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError,
    }
}

export default connect(mapStateToProps)(ScoreShopping);