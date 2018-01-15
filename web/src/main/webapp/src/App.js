import React, {Component} from 'react';
import './App.less';
import {connect} from 'react-redux';
import {Dialog,Toast} from 'react-weui';
import axios from 'axios';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showTishi:false,
            tishiButtons: [
                {
                    label: '确定',
                    onClick: this.hideTishiDialog.bind(this)
                }
            ],
            ifTop20:false,
            ifShow:false,
            top20Time:'',
        };
        this.handleTouch = this.handleTouch.bind(this);
        this.handleSign = this.handleSign.bind(this);
    }
    componentDidMount() {
        let _this = this;
        const {host} = _this.props;
        axios.get(`${host}/score/findIsTop20`).then(function (response) {
            if (response.data.state === 0) {
                if(response.data.data!==null){
                    var day1 = new Date();
                    day1.setTime(day1.getTime());
                    let month = day1.getMonth();
                    if(month.length===1){
                        month = "0"+month;
                    }
                    var s1 = day1.getFullYear()+"-" + month + "-" + day1.getDate();
                    var ifShow = _this.getCookie("ifShow");
                    if(ifShow==="true"){
                        _this.setState({
                            ifShow:true
                        })
                    }
                    if(response.data.data.date === s1&&_this.state.ifShow===false){
                        _this.setState({
                            ifTop20:true,
                            top20Time:s1,
                            showTishi:true
                        });
                    }
                }
            }
        }).catch(function (errors) {
            console.log(errors);
        });
    }

    hideTishiDialog() {
        this.setState({
            showTishi: false,
        });
        this.setCookie("ifShow","true",1);
    }
    setCookie(c_name,value,expiredays)
    {
        var exdate=new Date();
        exdate.setDate(exdate.getDate()+expiredays);
        document.cookie=c_name+ "=" +escape(value)+
            ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
    }
    getCookie(name){
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }
    handleTouch(num) {
        switch (num) {
            case 0:
                this.props.history.push('/description');
                break;
            case 1:
                this.props.history.push('/answer');
                break;
            case 2:
                this.props.history.push('/myprize');
                break;
            case 3:
                this.props.history.push('/tasks');
                break;
            case 4:
                this.props.history.push('/scoreshopping');
                break;
            case 5:
                this.props.history.push('/score');
                break;
            case 6:
                this.props.history.push('/regist');
                break;
            default:
                return;
        }
    }
    handleSign() {
        this.props.history.push('/signin');
    }
    render() {
        let _this = this;
        return (
            <div className="app main-bg">
                <div className='text-center right-now'>
                    <div onClick={() => {this.handleTouch(1)}}>每日答题</div>
                </div>
                <div className='list'>
                    <div className='list-first' onClick={() => {this.handleTouch(5)}}>荣耀榜</div>
                    <div className='list-second' onClick={() => {this.handleTouch(0)}}>竞赛介绍</div>
                    <div className='list-third' onClick={() => {this.handleTouch(3)}}>我的积分</div>
                    <div className='list-four' onClick={() => {this.handleTouch(3)}}>每日签到</div>
                </div>
                <Dialog type="ios" title="提示" buttons={this.state.tishiButtons} show={this.state.showTishi}>
                    <br/>
                    <p className='detail-info'>恭喜！你在{_this.state.top20Time}获得Top前20，请在"我的奖品"中查看奖品，积分已被自动清零一次，欢迎继续每日答题赢积分兑换奖品</p>
                </Dialog>
                <Toast icon="loading" show={this.props.showLoading}>Loading...</Toast>
            </div>
        );
    }
}

function mapStateToProps (state) {
    return {
        host: state.host,
        showLoading: state.showLoading
    }
}

export default connect(mapStateToProps)(App);