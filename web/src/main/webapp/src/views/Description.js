/**
 * Created by wxw on 2017/12/13.
 */
import React, {Component} from 'react';
import '../media/styles/description.less';
import Back from './Back';

class Description extends Component {
    render() {
        return (
            <div className="description main-bg">
                <div className='clearfix'>
                    <Back location='/home' history={this.props.history} />
                </div>
                <h2 className="text-center title">
                    <span>赛事说明</span>
                </h2>
                <div className="info">
                    <ul>
                        <li>1.本次普法答题竞赛周期为6周。</li>
                        <li>2.线上答题以当天获得的积分累计进行排行， 显示分数最高的前20名，其中第1-20名可获得现金红包奖。</li>
                        <li>
                            3.线上答题积分获得方式：参与《每日必答》，参与《普法先锋》；完成任务后，全天满分25分。各阶段可获积分如下：
                            <ul>
                                <li>(1)参与《每日必答》，每道题答对记5分，答错不积分，满分15分/天。</li>
                                <li>
                                    (2)参与《普法先锋》，完成任务【普法小奖状】、【签到】、【加分包】可获得活动积分，全部完成，满分10分/天；。各任务可获积分如下：
                                    完成【普法小奖状】每日分享任务，可获得3个积分；完成【我的法典】每日签到任务，可获得2个积分；完成加分包，可获得5个积分。
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        );
    }
}

export default Description;