import React, {Component} from 'react';
import '../media/styles/prize.less';
import Back from './Back';
import icon from '../media/images/signin_icon.png';

class SharePrize extends Component {
    render() {
        return (
            <div className='share-prize main-bg'>
                <div className='clearfix'>
                    <Back location='/answer' history={this.props.history} />
                </div>
                <h3 className='text-center'>荣誉奖状</h3>
                <div className='flower'>
                    <img src={icon} alt=""/>
                </div>
                <p className='text-center share'>
                    <button className='btn btn-success' >立即分享</button>
                </p>
            </div>
        )
    }
}

export default SharePrize;