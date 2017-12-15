/**
 * Created by sunminggui on 2017/12/13.
 */
/**
 * 签到界面
 */
import React,{Component} from "react";
import '../media/styles/signin.less';
import Back from './Back';

class SignIn extends Component{
    constructor(props) {
        super(props);
        this.state = {
            location: '/home'
        }
    }
    componentWillMount() {
        const {state} = this.props.location;
        if (state === '/tasks') {
            this.setState({
                location: state
            });
        }
    }
    getDayStr(){
        let weekDay = this.getDayOfWeek();
        let beforeMouthLastDay = this.getBeforeMouthLastDay().getDate();
        for(let k=1;k<weekDay;k++){
            beforeMouthLastDay--;
        }
        let htmlStr = "";
        let currentMouthLastDay = this.getCurrentMonthLastDay().getDate();
        let boforeMouthDay = beforeMouthLastDay;
        let currentMouthDay = 1;
        let nextMouthDay = 1;
        for(let i=0;i<6;i++){
            htmlStr += "<tr>";
            for(let j=0;j<7;j++){
                if(i===0&&j===weekDay){
                    htmlStr +="<td>"+currentMouthDay+"</td>";
                    currentMouthDay++;
                }else if(i===0&&j>weekDay){
                    htmlStr +="<td>"+currentMouthDay+"</td>";
                    currentMouthDay++;
                }else if(i===0&&j<weekDay){
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
        let date = this.getCurrentMonthFirstDay();
        let week = date.getDay();
        return week;
    }
    getNextMouthFirstDay(){
        let date=new Date();
        let currentMonth=date.getMonth();
        let nextMonth=++currentMonth;
        let nextMonthFirstDay=new Date(date.getFullYear(),nextMonth,1);
        return nextMonthFirstDay;
    }
    getBeforeMouthLastDay(){
        let currentMouthFirstDay = this.getCurrentMonthFirstDay();
        let oneDay=1000*60*60*24;
        return new Date(currentMouthFirstDay-oneDay);
    }
    getCurrentMonthLastDay(){
        let nextMonthFirstDay = this.getNextMouthFirstDay();
        let oneDay=1000*60*60*24;
        return new Date(nextMonthFirstDay-oneDay);
    }
    getCurrentMonthFirstDay(){
        let date=new Date();
        date.setDate(1);
        return date;
    }
    render(){
        return(
            <div className="signin main-bg">
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
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