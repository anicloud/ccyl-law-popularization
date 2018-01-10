import React, {Component} from 'react';
import axios from 'axios';
import {connect} from 'react-redux';

class UploadQuestion extends Component {
    handleChoice(e) {
        let _this = this;
        let file = e.target.files[0];
        let {host} = _this.props;
        let formData = new FormData();
        formData.append('type', 'CHOICE');
        formData.append('file', file);
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post(`${host}/question/upload`, formData, config).then(function (response) {
            if (response.data.state === 0) {
                console.log('上传成功');
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    handleJudge(e) {
        let _this = this;
        let file = e.target.files[0];
        let {host} = _this.props;
        let formData = new FormData();
        formData.append('type', 'JUDGEMENT');
        formData.append('file', file);
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post(`${host}/question/upload`, formData, config).then(function (response) {
            if (response.data.state === 0) {
                console.log('上传成功');
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }
    handleBig(e) {
        let _this = this;
        let file = e.target.files[0];
        let {host} = _this.props;
        let formData = new FormData();
        formData.append('type', 'NINETEEN');
        formData.append('file', file);
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post(`${host}/question/upload`, formData, config).then(function (response) {
            if (response.data.state === 0) {
                console.log('上传成功');
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }

    render() {
        return (
            <div className='upload'>
                <div>
                    选择 <input type="file" onChange={(e) => {this.handleChoice(e)}} />
                </div>
                <div>
                    判断 <input type="file" onChange={(e) => {this.handleJudge(e)}} />
                </div>
                <div>
                    19大 <input type="file" onChange={(e) => {this.handleBig(e)}} />
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        host: state.host
    }
}

export default connect(mapStateToProps)(UploadQuestion);