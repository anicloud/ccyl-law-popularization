/**
 * Created by sunminggui on 2017/12/15.
 */
/*我的积分页面*/
import React,{Component} from "react";
import "../media/styles/myscore.less";

class MyScore extends Component{
    constructor(props){
        super(props);
    }

    render() {
        return(
          <div className="MyScore score-bg">
              <div className="cloud">
                <h2 className="text-center h2 title">
                    <span>我的积分</span>
                </h2>
                <h3 className="text-center h3 title">
                    <span>第一天第三场</span>
                </h3>
              </div>
          </div>
        );
    }
}
export default MyScore;