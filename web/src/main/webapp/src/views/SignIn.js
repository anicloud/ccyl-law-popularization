/**
 * Created by sunminggui on 2017/12/13.
 */
/**
 * 签到界面
 */
import React, {Component} from "react";
import Back from './Back';
import {Button, Toast} from 'react-weui';
import {connect} from 'react-redux';
import axios from 'axios';
import '../media/styles/signin.less';

class SignIn extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '/home',
            signInfo: null,
            showToast: false
        };
        this.handleSignIn = this.handleSignIn.bind(this);
        this.toastTimer = null;
    }

    componentWillMount() {
        const {state} = this.props.location;
        if (state === '/tasks') {
            this.setState({
                location: state
            });
        }
    }

    componentDidMount() {
        this.getAllSignIn(1);
    }

    componentWillUnmount() {
        this.toastTimer && clearTimeout(this.toastTimer);
    }

    getAllSignIn(num) {
        let _this = this;
        let {host} = _this.props;
        axios.get(`${host}/score/findTotalSignIn`).then(function (response) {
            if (response.data.state === 0) {
                if (num === 2) {
                    _this.setState({
                        showToast: true,
                        signInfo: response.data.data
                    });
                    _this.toastTimer = setTimeout(function () {
                         _this.setState({
                            showToast: false
                        })
                    }, 2000);
                } else {
                    _this.setState({
                        signInfo: response.data.data
                    })
                }

            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }

    getDayStr() {
        let weekDay = this.getDayOfWeek();
        let beforeMouthLastDay = this.getBeforeMouthLastDay().getDate();
        for (let k = 1; k < weekDay; k++) {
            beforeMouthLastDay--;
        }
        let htmlStr = "";
        let currentMouthLastDay = this.getCurrentMonthLastDay().getDate();
        let boforeMouthDay = beforeMouthLastDay;
        let currentMouthDay = 1;
        let nextMouthDay = 1;
        for (let i = 0; i < 6; i++) {
            htmlStr += "<tr>";
            for (let j = 0; j < 7; j++) {
                if (i === 0 && j === weekDay) {
                    if (this.state.signInfo.signInDays.indexOf(currentMouthDay) > -1) {
                        htmlStr += "<td><span class='signIcon'>" + currentMouthDay + "</span></td>";
                        currentMouthDay++;
                    } else {
                        htmlStr += "<td>" + currentMouthDay + "</td>";
                        currentMouthDay++;
                    }
                } else if (i === 0 && j > weekDay) {
                    if (this.state.signInfo.signInDays.indexOf(currentMouthDay) > -1) {
                        htmlStr += "<td><span class='signIcon'>" + currentMouthDay + "</span></td>";
                        currentMouthDay++;
                    } else {
                        htmlStr += "<td>" + currentMouthDay + "</td>";
                        currentMouthDay++;
                    }
                } else if (i === 0 && j < weekDay) {
                    htmlStr += "<td style='color:grey;'>" + boforeMouthDay + "</td>";
                    boforeMouthDay++;
                }
                else if (i > 0 && currentMouthDay > currentMouthLastDay) {
                    htmlStr += "<td style='color:grey;'>" + nextMouthDay + "</td>";
                    nextMouthDay++;
                } else {
                    if (this.state.signInfo.signInDays.indexOf(currentMouthDay) > -1) {
                        htmlStr += "<td><span class='signIcon'>" + currentMouthDay + "</span></td>";
                        currentMouthDay++;
                    } else {
                        htmlStr += "<td>" + currentMouthDay + "</td>";
                        currentMouthDay++;
                    }
                }
            }
            htmlStr += "</tr>";
        }
        return htmlStr;
    }

    getDayOfWeek() {
        let date = this.getCurrentMonthFirstDay();
        return date.getDay();
    }

    getNextMouthFirstDay() {
        let date = new Date();
        let currentMonth = date.getMonth();
        let nextMonth = ++currentMonth;
        return new Date(date.getFullYear(), nextMonth, 1);
    }

    getBeforeMouthLastDay() {
        let currentMouthFirstDay = this.getCurrentMonthFirstDay();
        let oneDay = 1000 * 60 * 60 * 24;
        return new Date(currentMouthFirstDay - oneDay);
    }

    getCurrentMonthLastDay() {
        let nextMonthFirstDay = this.getNextMouthFirstDay();
        let oneDay = 1000 * 60 * 60 * 24;
        return new Date(nextMonthFirstDay - oneDay);
    }

    getCurrentMonthFirstDay() {
        let date = new Date();
        date.setDate(1);
        return date;
    }

    handleSignIn() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/signIn`).then(function (response) {
            if (response.data.state === 0) {
                _this.getAllSignIn(2);
            }
        }).catch(function (errors) {
            console.log(errors);
        })
    }

    render() {
        let signInfo = this.state.signInfo;
        return (
            <div className="signin main-bg">
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history}/>
                </div>
                <h2 className="text-center h2 title">
                    <span>我的签到</span>
                </h2>
                <div className="sign_main">
                    <div className="signdiv">
                        <Button className="signbutton" size="small" disabled={signInfo? !!signInfo.signIn : false} onClick={this.handleSignIn}>
                            {signInfo? signInfo.signIn? '已签到' : '签到' : '签到'}
                        </Button>
                    </div>
                    {
                        signInfo? (
                            <table>
                                <tbody dangerouslySetInnerHTML={{__html: this.getDayStr()}}>
                                </tbody>
                            </table>
                        ) : (<Toast icon="loading" show={true}>Loading...</Toast>)
                    }
                </div>
                <Toast icon="success-no-circle" show={this.state.showToast}>签到成功</Toast>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
                <Toast icon="warn" show={this.props.showError}>请求失败</Toast>
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

export default connect(mapStateToProps)(SignIn);