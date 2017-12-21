import React, {Component} from 'react';
import '../media/styles/tasks.less';
import Back from './Back';

class Tasks extends Component {
    handleTouch(num) {
        switch (num) {
            case 0:
                this.props.history.push({
                    pathname: '/answer',
                    state: '/tasks'
                });
                break;
            case 1:
                this.props.history.push({
                    pathname: '/signin',
                    state: '/tasks'
                });
                break;
            case 2:
                this.props.history.push('/tasks');
                break;
            default:
                return;
        }
    }
    render() {
        return (
            <div className='tasks main-bg'>
                <div className='clearfix'>
                    <Back location='/tasks' history={this.props.history} />
                </div>
                <h2 className="text-center title">
                    <span>每日任务</span>
                    <p className='text-right descript'>-完成可领积分</p>
                </h2>
                <div className='task'>
                    <div className='one'>
                        <h3 className='text-center'>任务一： 签到</h3>
                        <p><span>任务简述：</span> 每日签到，签到后可获得积分+2。</p>
                        {/*<p className='text-center'>
                            <button className='btn btn-success' onClick={() => {this.handleTouch(1)}}>前往做任务</button>
                        </p>*/}
                    </div>
                    <div className='two'>
                        <h3 className='text-center'>任务二： 分享</h3>
                        <p><span>任务简述：</span> 每次答题完毕后生成小奖状，可分享至朋友圈邀请好友答题。好友可通过进入答题，答题者可获得积分+3。</p>
                        {/*<p className='text-center'>
                            <button className='btn btn-success' onClick={() => {this.handleTouch(0)}}>前往做任务</button>
                        </p>*/}
                    </div>
                    <div className='three'>
                        <h3 className='text-center'>任务三： 好友点赞</h3>
                        <p><span>任务简述：</span> 好友通过链接点赞可得2个积分。</p>
                        {/*<p className='text-center'><button className='btn btn-success'>前往做任务</button></p>*/}
                    </div>
                </div>
            </div>
        )
    }
}

export default Tasks;
