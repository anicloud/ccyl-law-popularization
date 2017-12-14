/**
 * Created by sunminggui on 2017/12/13.
 */
/**
 * 签到界面
 */
import React,{Component} from "react";
import {Button} from 'react-weui';
import '../media/styles/signin.less';
import main from '../media/images/signin_main.png';

class SignIn extends Component{
    constructor(props){
        super(props);
    }
    getDayStr(){
        var weekDay = this.getDayOfWeek();
        var beforeMouthLastDay = this.getBeforeMouthLastDay().getDate();
        for(var i=1;i<weekDay;i++){
            beforeMouthLastDay--;
        }
        var htmlStr = "";
        var currentMouthLastDay = this.getCurrentMonthLastDay().getDate();
        var boforeMouthDay = beforeMouthLastDay;
        var currentMouthDay = 1;
        var nextMouthDay = 1;
        for(var i=0;i<6;i++){
            htmlStr += "<tr>";
            for(var j=0;j<7;j++){
                if(i==0&&j==weekDay){
                    htmlStr +="<td>"+currentMouthDay+"</td>";
                    currentMouthDay++;
                }else if(i==0&&j>weekDay){
                    htmlStr +="<td>"+currentMouthDay+"</td>";
                    currentMouthDay++;
                }else if(i==0&&j<weekDay){
                    htmlStr +="<td style='color:grey;'><span class=\"signIcon\">"+boforeMouthDay+"</span></td>";
                    boforeMouthDay++;
                }
                else if(i>0&&currentMouthDay>currentMouthLastDay){
                    htmlStr +="<td style='color:grey;'>"+nextMouthDay+"</td>";
                    nextMouthDay++;
                }else{
                    htmlStr +="<td>"+currentMouthDay+"</td>";
                    currentMouthDay++;
                }

            }
            htmlStr +="</tr>";
        }
        return htmlStr;
    }
    getDayOfWeek(){
        var date = this.getCurrentMonthFirstDay();
        var week = date.getDay();
        return week;
    }


    getNextMouthFirstDay(){
        var date=new Date();
        var currentMonth=date.getMonth();
        var nextMonth=++currentMonth;
        var nextMonthFirstDay=new Date(date.getFullYear(),nextMonth,1);
        return nextMonthFirstDay;
    }
    getBeforeMouthLastDay(){
        var currentMouthFirstDay = this.getCurrentMonthFirstDay();
        var oneDay=1000*60*60*24;
        return new Date(currentMouthFirstDay-oneDay);
    }
    getCurrentMonthLastDay(){
        var nextMonthFirstDay = this.getNextMouthFirstDay();
        var oneDay=1000*60*60*24;
        return new Date(nextMonthFirstDay-oneDay);
    }
    getCurrentMonthFirstDay(){
        var date=new Date();
        date.setDate(1);
        return date;
    }
    render(){
        return(
            <div className="signin main-bg">
                <h2 className="text-center h2 title">
                    <span>我的签到</span>
                </h2>
                <div className="sign_main">
                    <table>
                        <tbody dangerouslySetInnerHTML={{__html: this.getDayStr()}} >
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}
export default SignIn;