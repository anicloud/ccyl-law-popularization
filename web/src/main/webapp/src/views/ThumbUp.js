import React, {Component} from 'react';
import {connect} from 'react-redux';
import axios from 'axios';
import {Toast} from 'react-weui';
import '../media/styles/thumb.less';

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
                    <span>我的荣誉</span>
                </h2>
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