import React, {Component} from 'react';
import Back from './Back';
import axios from 'axios';
import {connect} from 'react-redux';
import {Toast} from 'react-weui';
import '../media/styles/score.less';

class ScoreBoard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rankingInfo: null,
            location: this.props.location.state? this.props.location.state : '/home',
            myRankInfo: null
        }
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findTop20`).then(function (response) {
            if (response.data.state === 0) {
                if (response.data.data !== null) {
                    _this.setState({
                        rankingInfo: response.data.data
                    })
                }
            }
        }).catch(function (errors) {
            console.log(errors);
        });
        axios.get(`${host}/score/findSelfRank`).then(function (response) {
            if (response.data.data !== null) {
                _this.setState({
                    myRankInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }
    render() {
        let rankingInfo = this.state.rankingInfo;
        let myRankInfo = this.state.myRankInfo;
        return (
            <div className='score scoreboard-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                {/*<div className='text-right header'>
                    <img src={header} alt=""/>
                </div>*/}
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
                            <div className='pull-right third'>{myRankInfo.ranking===-1?"已获奖，不再参与排名":myRankInfo.ranking}</div>
                        </div>
                    ) : (null)
                }
                {
                    rankingInfo?(
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
                        <div className='text-center ranking'><div className="middle">暂无荣耀榜相关信息</div></div>
                    )
                }
                {/*<div className='top-ranking clearfix'>
                    <div className='pull-left first'>
                        <img src={icon} alt=""/>
                    </div>
                    <div className='pull-left second'>
                        <div>1,080</div>
                        <div>Alex Nelson</div>
                    </div>
                    <div className='pull-right third'>1</div>
                </div>
                <div className='top-ranking clearfix'>
                    <div className='pull-left first'>
                        <img src={icon} alt=""/>
                    </div>
                    <div className='pull-left second'>
                        <div>1,080</div>
                        <div>Alex Nelson</div>
                    </div>
                    <div className='pull-right third'>2</div>
                </div>
                <div className='top-ranking clearfix'>
                    <div className='pull-left first'>
                        <img src={icon} alt=""/>
                    </div>
                    <div className='pull-left second'>
                        <div>1,080</div>
                        <div>Alex Nelson</div>
                    </div>
                    <div className='pull-right third'>3</div>
                </div>
                <div className='top-ranking clearfix'>
                    <div className='pull-left first'>
                        <img src={icon} alt=""/>
                    </div>
                    <div className='pull-left second'>
                        <div>1,080</div>
                        <div>Alex Nelson</div>
                    </div>
                    <div className='pull-right third'>4</div>
                </div>
                <div className='top-ranking clearfix'>
                    <div className='pull-left first'>
                        <img src={icon} alt=""/>
                    </div>
                    <div className='pull-left second'>
                        <div>1,080</div>
                        <div>Alex Nelson</div>
                    </div>
                    <div className='pull-right third'>5</div>
                </div>*/}
                {/*{
                    rankingInfo? (
                        rankingInfo.map((item, index) => {
                            return (
                                <div className='row score-list' key={index}>
                                    <div className='col-xs-3 text-center head'>
                                        <img src={item.portrat} alt=""/>
                                    </div>
                                    <div className='col-xs-3 text-center'>
                                        {getPartName(item.name)}
                                    </div>
                                    <div className='col-xs-3 text-center'>{item.score}分</div>
                                    <div className='col-xs-3 text-center'>{item.updateTime}</div>
                                </div>
                            )
                        })
                    ) : (
                        <div className='text-center ranking'>暂无排行榜信息</div>
                    )
                }*/}
                {/*<div className='score-info'>
                    <p>
                        <span>排行榜说明:</span>
                        活动以天为单位，进行当日积分累计排行，显示前TOP20。共计6周。前20名将获得现金红包奖励。
                    </p>
                </div>*/}
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

export default connect(mapStateToProps)(ScoreBoard);