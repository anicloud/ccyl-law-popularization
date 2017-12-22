import React, {Component} from 'react';
import Back from './Back';
import axios from 'axios';
import {connect} from 'react-redux';
import {Toast} from 'react-weui';
import {getPartName} from "../utils/index";
import icon from '../media/images/signin_icon.png';
import '../media/styles/score.less';

class ScoreBoard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rankingInfo: null,
            location: this.props.location.state? this.props.location.state : '/home'
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
        })
    }
    render() {
        let rankingInfo = this.state.rankingInfo;
        return (
            <div className='score main-bg'>
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <h2 className="text-center title">荣耀排行榜</h2>
                {
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
                }
                <div className='score-info'>
                    <p>
                        <span>排行榜说明:</span>
                        活动以天为单位，进行当日积分累计排行，显示前TOP20。共计6周。前20名将获得现金红包奖励。
                    </p>
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

export default connect(mapStateToProps)(ScoreBoard);