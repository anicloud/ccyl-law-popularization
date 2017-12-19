import React from 'react';
import {Switch, Route, Redirect} from 'react-router-dom';
/*首页*/
import App from '../App';
/*引导页*/
import Main from '../views/Main'
/*注册信息*/
import Regist from '../views/Regist';
/*每日答题*/
import AnswerQuestion from '../views/AnswerQuestion';
/*赛事说明*/
import Description from '../views/Description';
/*签到*/
import SignIn from '../views/SignIn';
/*前后端转接组件*/
import Options from '../views/Options';
/*错误页面*/
import Error from '../views/Error';
///*我的任务组件*/
//import Tasks from '../views/Tasks';
/*荣耀排行榜组件*/
import ScoreBoard from '../views/ScoreBoard';
/*我的积分*/
import MyScore from '../views/MyScore';
import SharePrize from '../views/SharePrize';

const Routes = () => (
    <Switch>
        <Route exact path='/' component={Main} />
        <Route exact path='/home' component={App} />
        <Route exact path='/regist' component={Regist} />
        <Route path='/myscore' component={MyScore}/>
        <Route path='/tasks' component={MyScore} />
        <Route path='/score' component={ScoreBoard} />
        <Route path='/prize' component={SharePrize} />
        <Route path='/home/index' component={Options} />
        <Route path='/signin' component={SignIn}/>
        <Route path='/answer' component={AnswerQuestion} />
        <Route path='/description' component={Description} />
        <Route path='/error' component={Error} />

        <Redirect from='*' to='/' />
    </Switch>
);

export default Routes;