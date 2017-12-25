/**
 * Created by sunminggui on 2017/12/19.
 */
/**
 * 积分商城
 */
import React,{Component} from "react";
import Back from './Back';
import "../media/styles/scoreshopping.less";
import {Button} from 'react-weui';
import axios from 'axios';

class ScoreShopping extends Component{
    constructor(props){
       super(props);
        this.state = {
            location: this.props.location.state? this.props.location.state : '/home'
        };
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findTotalScore`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    getCurrentScore(){
        let scoreInfo = this.state.scoreInfo;
        let score = scoreInfo?(scoreInfo.score? scoreInfo.score : 0):0;
        return score;
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
                    <span className="largestImg" />
                      <div className="largestDetail">
                        <span className="largestSpan">20元充值劵</span>
                        <span className="scoreSpan">1000积分</span>
                      </div>
                      <Button disabled={this.getCurrentScore()>1000?false:true}>{this.getCurrentScore()>1000?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>

                  <div className="larger">
                      <span className="largerImg" />
                      <div className="largerDetail">
                          <span className="largerSpan">10元充值劵</span>
                          <span className="scoreSpan">500积分</span>
                      </div>
                      <Button disabled={this.getCurrentScore()>500?false:true}>{this.getCurrentScore()>500?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>
                  <div className="normal">
                      <span className="normalImg" />
                      <div className="normalDetail">
                          <span className="normalSpan">5元充值劵</span>
                          <span className="scoreSpan">200积分</span>
                      </div>
                      <Button disabled={this.getCurrentScore()>200?false:true}>{this.getCurrentScore()>200?"立即兑换":"积分不足"}</Button>
                  </div>
                  <hr className="line"/>
                  <div className="smaller">
                      <span className="smallerImg" />
                      <div className="smallerDetail">
                          <span className="smallerSpan">2元充值劵</span>
                          <span className="scoreSpan">100积分</span>
                      </div>
                      <Button disabled={this.getCurrentScore()>100?false:true}>{this.getCurrentScore()>100?"立即兑换":"积分不足"}</Button>
                  </div>
              </div>
          </div>
        );
    }
}

export default ScoreShopping;