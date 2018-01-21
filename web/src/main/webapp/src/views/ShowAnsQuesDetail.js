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
import '../media/styles/showAnsQuesDetail.less';

class ShowAnsQuesDetail extends Component{
    constructor(props){
        super(props);
        this.state = {
            rankingInfo: [],
            location: this.props.location.state? this.props.location.state : '/home',
        };
        this.handleAnswer = this.handleAnswer.bind(this);
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/share/findInviteInfo`).then(function (response) {
             if (response.data.state === 0) {
                 if (response.data.data !== null) {
                     _this.setState({
                        rankingInfo: response.data.data
                     })
                 }
             }
         }).catch(function (errors) {
         console.log(errors);
         })
    }

    handleAnswer() {
        let _this = this;
        const {history, host} = _this.props;
        axios.get(`${host}/share/findShareInfo`).then(function (response) {
            if (response.data.state === 0) {
                let scoreInfo = response.data.data;
                if (scoreInfo.correctCount === 0) {
                    history.push({
                        pathname: '/answer',
                        state: '/tasks'
                    });
                } else {
                    history.push({
                        pathname: '/prize',
                        state: '/tasks'
                    });
                }
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }
    render(){
        let rankingInfo = this.state.rankingInfo;
        return (
            <div className='showThumb showAnsQues-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                {/*<div className='text-right header'>
                    <img src={header} alt=""/>
                </div>*/}
                <div className='clearfix info'>
                    <div className='pull-left first'>
                        好友详情
                    </div>
                    <div className='pull-right second'>
                        答题时间
                    </div>
                </div>
                {
                    rankingInfo? (
                        rankingInfo.length===0?(<div className='text-center ranking'><div className="middle">暂无邀请答题相关信息</div></div>):
                            rankingInfo.map(function (item, index) {
                                return (
                                    <div className='top-ranking clearfix'>
                                        <div className='pull-left first'>
                                            <img src={item.portrait} alt=""/>
                                        </div>
                                        <div className='pull-left second'>
                                            <div>{item.nickName}</div>
                                        </div>
                                        <div className='pull-right third'>{item.updateTime}</div>
                                    </div>
                                );
                            })
                    ) : (
                        <div className='text-center ranking'><div className="middle">暂无邀请答题相关信息</div></div>
                    )
                }
                <div className='text-center thumb-btn'>
                    <div className='right-now right-thumb' onClick={this.handleAnswer}>继续得分</div>
                </div>
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
