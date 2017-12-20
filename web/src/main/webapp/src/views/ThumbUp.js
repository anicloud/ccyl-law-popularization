import React, {Component} from 'react';
import {connect} from 'react-redux';
import axios from 'axios';
import {Toast} from 'react-weui';
import '../media/styles/thumb.less';
import icon from '../media/images/signin_icon.png';

class ThumbUp extends Component {
    constructor(props) {
        super(props);
        console.log(this.props);
        this.state = {
            scoreInfo: null
        };
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        const userId = _this.props.location.state;
        axios.get(`${host}/score/findDailyTotalScore?id=${userId}`).then(function (response) {
            if (response.data.state === 0) {
                _this.setState({
                    scoreInfo: response.data.data
                })
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    render() {
        return (
            <div className='thumb main-bg'>
                <h2 className="text-center title">
                    <span>wxw的荣誉</span>
                </h2>
                <div className='flower'>
                    <img src={icon} alt=""/>
                </div>
                <p className='text-center score-ques'>
                    答题获得积分：<span>15 积分</span>
                </p>
                <p className='text-center share'>
                    <button className='btn btn-success'>
                        <i className='glyphicon glyphicon-thumbs-up'/>
                        <span className='thumb-up'>点赞</span>
                    </button>
                    <button className='btn btn-success'>
                        <i className='glyphicon glyphicon-share-alt'/>
                        <span className='thumb-up'>去答题</span>
                    </button>
                </p>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading
    };
}

export default connect(mapStateToProps)(ThumbUp);