/**
 * Created by sunminggui on 2017/12/19.
 */
/**
 * 积分商城
 */
import React,{Component} from "react";
import Back from './Back';
import "../media/styles/scoreshopping.less";

class ScoreShopping extends Component{
    constructor(props){
       super(props);
        this.state = {
            location: this.props.location.state? this.props.location.state : '/home'
        };
    }
    getCurrentScore(){
        return 1200;
    }
    render(){
        return (
          <div className="scoreshopping main-bg">
              <div className='clearfix'>
                  <Back location={this.state.location} history={this.props.history} />
              </div>
              <h2 className="text-center h2 title">
                  <span>积分商城</span>
              </h2>
              <p className='text-center current-score'>当前积分:{this.getCurrentScore()}</p>
              <div className="listdiv">
                  <div className="largest">
                    <img className="largestImg"/>
                      <div className="largestDetail">
                        <span>20元充值劵</span>
                        <span>1000积分</span>
                      </div>
                  </div>
                  <hr className="line"/>

                  <div className="larger">
                      <img className="largerImg"/>
                      <div className="largerDetail">
                          <span>10元充值劵</span>
                          <span>500积分</span>
                      </div>
                  </div>
                  <hr className="line"/>
                  <div className="normal">
                      <img className="normalImg"/>
                      <div className="normalDetail">
                          <span>5元充值劵</span>
                          <span>200积分</span>
                      </div>
                  </div>
                  <hr className="line"/>
                  <div className="smaller">
                      <img className="smallerImg"/>
                      <div className="smallerDetail">
                          <span>2元充值劵</span>\
                          <span>100积分</span>
                      </div>
                  </div>
              </div>
          </div>
        );
    }
}

export default ScoreShopping;