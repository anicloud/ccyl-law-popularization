/**
 * Created by sunminggui on 2017/12/15.
 */
/*我的积分页面*/
import React,{Component} from "react";
import "../media/styles/myscore.less";
import {Button} from 'react-weui';
import Back from './Back';

class MyScore extends Component{
    constructor(props){
        super(props);
        this.state = {
            location: '/home'
        };
        this.handleShopping = this.handleShopping.bind(this);
    }
    handleShopping() {
        this.props.history.push('/scoreshopping');
    }
    render() {
        return(
            <div className="root">
              <div className="MyScore score-bg">
                  <div className='clearfix'>
                      <Back location='/home' history={this.props.history} />
                  </div>
                  <div className="cloud">
                      <h2 className="text-center h2 title">
                          <span>我的积分</span>
                      </h2>
                      <h3 className="text-center h3 title">
                          <span>111</span>
                      </h3>
                  </div>
              </div>
              <div className="bottomDiv">
                      <div className="buttonDiv">
                          <span onClick={this.handleShopping}><i className='scoreshopping'/>积分商城</span>
                          <span><i className='scoredetail'/>积分榜</span>
                      </div>
                      <div className="scoreTask">
                         <span>积分任务</span>
                          <i className='detail'/>
                      </div>
                      <div className="task">
                          <span>签到</span>
                          <Button>签到</Button>
                          <span className="desc">+5积分/次</span>
                      </div>
                      <div className="task">
                          <span>普法小奖状</span>

                          <Button>查看</Button>
                          <span className="desc">+5积分/次</span>
                      </div>
                      <div className="task">
                          <span>加分包</span>
                          <Button>查看</Button>
                          <span className="desc">+5能量/次</span>
                      </div>
                </div>
            </div>
        );
    }
}
export default MyScore;