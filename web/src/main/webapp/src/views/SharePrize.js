import React, {Component} from 'react';
import Back from './Back';
import icon from '../media/images/signin_icon.png';
import {connect} from 'react-redux';
import {jsSdkConfig} from "../utils/index";
import axios from 'axios';
import '../media/styles/prize.less';

class SharePrize extends Component {
    constructor(props) {
        super(props);
        this.handleShare = this.handleShare.bind(this);
    }
    componentDidMount() {
        const {host} = this.props;
        jsSdkConfig(axios, host);
    }
    handleShare() {
        window.wx.ready(function () {

        });
    }
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
                    <button className='btn btn-success' onClick={this.handleShare}>立即分享</button>
                </p>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host
    };
}

export default connect(mapStateToProps)(SharePrize);