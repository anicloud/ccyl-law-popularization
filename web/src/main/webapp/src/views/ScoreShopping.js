/**
 * Created by sunminggui on 2017/12/19.
 */
/**
 * 积分商城
 */
import React,{Component} from "react";
import Back from './Back';
import {Button,Toast,Dialog} from 'react-weui';
import axios from 'axios';
import {connect} from 'react-redux';
import disPlus from "../media/imgs/displus.png";
import "../media/styles/scoreshopping.less";

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
            userInfo:[],
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
            ]
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
            if(type===awardInfo.awardType) {
                return awardInfo;
            }
        }
    }

    goToRegist(){
        let _this = this;
        const {history} = _this.props;
        history.push({
            pathname:'/regist',
            state:'/scoreshopping'
        });
    }

    hideTiShiDialog(){
        this.setState({
            showTishi: false,
        });
    }

    exchangePrizes(type){
        let _this = this;
        const {host} = _this.props;
        //发请求
        axios.get(`${host}/account/findInfoIsCompleted`).then(function (response) {
            if (response.data.state === 0) {
                if(response.data.data===false){
                    _this.setState({
                        showTishi: true,
                    });
                }else{
                    axios.get(`${host}/score/convertAward?awardType=${type}`).then(function (response) {
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
                            //查找所有奖品
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
                        }
                        if(response.data.state !== 0){
                            _this.state.timer && clearTimeout(_this.state.timer);
                            _this.setState({
                                warningInfo:response.data.msg,
                                showWarning:true
                            });
                            _this.state.timer = setTimeout(function () {
                                _this.setState({
                                    showWarning:false
                                });
                            }, 2000);
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
                }
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }
    render(){
        let _this = this;
        let userInfo = this.state.userInfo;
        let tencent_vip = _this.getCurrentAwardScore("TENCENT_VIP");
        if(tencent_vip === null||tencent_vip===undefined){
            tencent_vip = {};
        }
        let ofo_Coupon = _this.getCurrentAwardScore("OFO_COUPON");
        if(ofo_Coupon === null||ofo_Coupon===undefined){
            ofo_Coupon = {};
        }
        let five_coupon = _this.getCurrentAwardScore("FIVE_COUPON");
        if(five_coupon === null||five_coupon===undefined){
            five_coupon = {};
        }
        let ten_coupon = _this.getCurrentAwardScore("TEN_COUPON");
        if(ten_coupon === null||ten_coupon===undefined){
            ten_coupon = {};
        }
        return (
            <div className="root-shop">
                <div className="scoreshopping score-bg">
                    <div className='clearfix'>
                        <Back location='/home' history={this.props.history}/>
                    </div>
                    <div className="detail">
                        <img src={userInfo.portrait} className="touxiang"></img>
                        <br/>
                        <span>{userInfo.nickName}</span>
                        <br/>
                        <span>剩余积分:<span className='detail-span'>{_this.state.awardInfo[0]?_this.state.awardInfo[0].myScore:0}</span>分</span>
                    </div>
                </div>
                <div className="bottomDiv">
                    <div className="task">
                        <div className="leftDiv">
                            <span>腾讯视频会员月卡</span>
                            <span className="desc">{_this.state.awardInfo.length===0?1000:tencent_vip.score}积分</span>
                            {/*<span className="desc">剩余{tencent_vip.awardCount?tencent_vip.awardCount:0}个</span>*/}
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>=tencent_vip.score&&tencent_vip.usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("TENCENT_VIP")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>摩拜用车券</span>
                            <span className="desc">{_this.state.awardInfo.length===0?500:ofo_Coupon.score}积分</span>
                            {/*<span className="desc">剩余{ofo_Coupon.awardCount?ofo_Coupon.awardCount:0}个</span>*/}
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>=ofo_Coupon.score&&ofo_Coupon.usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("OFO_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满49减5)</span>
                            <span className="desc">{_this.state.awardInfo.length===0?200:five_coupon.score}积分</span>
                            {/*<span className="desc">剩余{five_coupon.awardCount?five_coupon.awardCount:0}个</span>*/}
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>=five_coupon.score&&five_coupon.usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("FIVE_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                    <div className="task">
                        <div className="leftDiv">
                            <span>购物优惠券(满99减10)</span>
                            <span className="desc">{_this.state.awardInfo.length===0?100:_this.getCurrentAwardScore("TEN_COUPON").score}积分</span>
                            {/*<span className="desc">剩余{ten_coupon.awardCount?ten_coupon.awardCount:0}个</span>*/}
                        </div>
                        {_this.state.awardInfo.length===0?<div className="rightDiv"><img src={disPlus}/></div>:_this.state.awardInfo[0].myScore>=ten_coupon.score&&ten_coupon.usedUp===false?<div className="rightDiv" onClick={() =>_this.exchangePrizes("TEN_COUPON")}><i className="plus"/></div>:<div className="rightDiv"><img src={disPlus}/></div>}>
                    </div>
                </div>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.state.showWarning}>{this.state.warningInfo}</Toast>
                <Toast icon="success-no-circle" show={this.state.showSuccess}>{this.state.successInfo}</Toast>
                <Dialog type="ios" title="提示" buttons={this.state.tishiButtons} show={this.state.showTishi}>
                    兑换奖品前需补全信息，是否跳转
                </Dialog>
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