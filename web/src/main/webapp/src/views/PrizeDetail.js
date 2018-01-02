/**
 * Created by sunminggui on 2017/12/29.
 */
/**
 * 奖品详情
 **/
import React,{Component} from "react";
import "../media/styles/prizedetail.less";
import Back from './Back';
import {connect} from 'react-redux';

class PrizeDetail extends Component {
    constructor(props){
        super(props);
        this.state = {
            location: this.props.location.state.path? this.props.location.state.path : '/home',
        };
    }

    render() {
        return (
            <div className="myprize main-bg">
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history}/>
                </div>
                <h2 className="text-center h2 title">
                    <span>奖品详情</span>
                </h2>
                <div>我的兑换码:{this.props.location.state.myAward.codeSecret}</div>
            </div>
        );
    }

}


function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    }
}

export default connect(mapStateToProps)(PrizeDetail);