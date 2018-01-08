/**
 * Created by sunminggui on 2018/1/8.
 */
/**
 * 展示所有接受自己邀请答题的人的列表
 */
import React,{Component} from "react";
import Back from './Back';
import axios from 'axios';
import {connect} from 'react-redux';
import {Toast} from 'react-weui';
import header from '../media/imgs/header_glory.png';
import '../media/styles/showAnsQuesDetail.less';

class ShowAnsQuesDetail extends Component{
    constructor(props){
        super(props);
        this.state = {
            rankingInfo: [{
                portrat:'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0',
                score:1,
                name:'sdfasd'
            },{
                portrat:'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0',
                score:22,
                name:'asdfda'
            },{
                portrat:'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0',
                score:56,
                name:'a;sodih'
            },{
                portrat:'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0',
                score:88,
                name:'gopsdihngi'
            },{
                portrat:'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0',
                score:434,
                name:'gosidhn'
            }],
            location: this.props.location.state? this.props.location.state : '/home',
            myRankInfo: {
                portrait:'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0',
                totalScore:100,
                nickName:'sdsgsd'
            }
        }
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        /*axios.get(`${host}/score/findSelfRank`).then(function (response) {
            if (response.data.data !== null) {
                _this.setState({
                    myRankInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        });*/
        /*axios.get(`${host}/score/findTop20`).then(function (response) {
         if (response.data.state === 0) {
         if (response.data.data !== null) {
         _this.setState({
         rankingInfo: response.data.data
         })
         }
         }
         }).catch(function (errors) {
         console.log(errors);
         })*/
    }
    render(){
        let rankingInfo = this.state.rankingInfo;
        let myRankInfo = this.state.myRankInfo;
        return (
            <div className='score common-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <div className='text-right header'>
                    <img src={header} alt=""/>
                </div>
                <div className='clearfix info'>
                    <div className='pull-left first'>
                        今日得分
                    </div>
                    <div className='pull-right second'>
                        全国排名
                    </div>
                </div>
                {
                    myRankInfo? (
                        <div className='my-ranking clearfix'>
                            <div className='pull-left first'>
                                <img src={myRankInfo.portrait} alt=""/>
                            </div>
                            <div className='pull-left second'>
                                <div>{myRankInfo.totalScore? myRankInfo.totalScore : 0}</div>
                                <div>{myRankInfo.nickName}</div>
                            </div>
                            <div className='pull-right third'>{myRankInfo.ranking}</div>
                        </div>
                    ) : (null)
                }
                {
                    rankingInfo? (
                        rankingInfo.map(function (item, index) {
                            return (
                                <div className='top-ranking clearfix'>
                                    <div className='pull-left first'>
                                        <img src={item.portrat} alt=""/>
                                    </div>
                                    <div className='pull-left second'>
                                        <div>{item.score}</div>
                                        <div>{item.name}</div>
                                    </div>
                                    <div className='pull-right third'>{index + 1}</div>
                                </div>
                            );
                        })
                    ) : (
                        <div className='text-center ranking'>暂无邀请答题相关信息</div>
                    )
                }
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    }
}

export default connect(mapStateToProps)(ShowAnsQuesDetail);
