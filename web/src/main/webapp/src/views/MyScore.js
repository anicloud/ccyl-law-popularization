/**
 * Created by sunminggui on 2017/12/15.
 */
/*我的积分页面*/
import React,{Component} from "react";
import "../media/styles/myscore.less";
import {Button} from 'react-weui';

class MyScore extends Component{
    constructor(props){
        super(props);
    }

    render() {
        return(
            <div>
              <div className="MyScore score-bg">
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
                          <Button>123</Button>
                          <Button>456</Button>
                      </div>
                      <div className="scoreTask">
                         <span>积分任务</span>
                      </div>
                      <div className="task">
                          <span>签到</span>
                          <span className="desc">+5积分/次</span>
                          <Button>签到</Button>
                      </div>
                      <div className="task">
                          <span>普法小奖状</span>
                          <span className="desc">+5积分/次</span>
                          <Button>查看</Button>
                      </div>
                      <div className="task">
                          <span>加分包</span>
                          <span className="desc">+5能量/次</span>
                          <Button>查看</Button>
                      </div>
                </div>
            </div>
        );
    }
}
export default MyScore;