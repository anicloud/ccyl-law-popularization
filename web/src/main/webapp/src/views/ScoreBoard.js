import React, {Component} from 'react';
import '../media/styles/score.less';
import Back from './Back';
import icon from '../media/images/signin_icon.png';

class ScoreBoard extends Component {
    render() {
        return (
            <div className='score main-bg'>
                <div className='clearfix'>
                    <Back location='/home' history={this.props.history} />
                </div>
                <h2 className="text-center title">荣耀排行榜</h2>
                {
                    [1, 2, 3, 4, 5, 6, 7].map((item, index) => {
                        return (
                            <div className='row score-list' key={index}>
                                <div className='col-xs-3 text-center head'>
                                    <img src={icon} alt=""/>
                                </div>
                                <div className='col-xs-3 text-center'>112...</div>
                                <div className='col-xs-3 text-center'>109分</div>
                                <div className='col-xs-3 text-center'>9:12:59</div>
                            </div>
                        )
                    })
                }
                <div className='score-info'>
                    <p>
                        <span>排行榜说明:</span>
                        活动以天为单位，进行当日积分累计排行，显示前TOP20。共计6周。前20名将获得现金红包奖励。
                    </p>
                </div>
            </div>
        )
    }
}

export default ScoreBoard;