import React from 'react';
import {Switch, Route, Redirect} from 'react-router-dom';
/*首页*/
import App from '../App';
/*注册信息*/
import Regist from '../views/Regist';
/*每日答题*/
import AnswerQuestion from '../views/AnswerQuestion';
/*赛事说明*/
import Description from '../views/Description';
/*前后端转接组件*/
import Options from '../views/Options';
/*错误页面*/
import Error from '../views/Error';
/*任务公告*/
import Tasks from '../views/Tasks';
/*荣耀排行榜组件*/
import ScoreBoard from '../views/ScoreBoard';
/*我的积分*/
import MyScore from '../views/MyScore';
/*分享答题*/
import SharePrize from '../views/SharePrize';
/*积分商城*/
import ScoreShopping from '../views/ScoreShopping';
/*通过连接点赞*/
import ThumbUp from '../views/ThumbUp';
/*上传题库*/
import UploadQuestion from '../views/UploadQuestion';
/*展示邀请答题详情*/
import ShowAnsQuesDetail from '../views/ShowAnsQuesDetail';
/*展示点赞详情*/
import ShowThumbUpDetail from '../views/ShowThumbUpDetail';


const Routes = () => (
    <Switch>
        <Route exact path='/' component={App} />
        <Route exact path='/home' component={App} />
        <Route exact path='/regist' component={Regist} />
        <Route path='/scoreshopping' component={ScoreShopping}/>
        <Route path='/showAnswerDetail' component={ShowAnsQuesDetail}/>
        <Route path='/showThumbDetail' component={ShowThumbUpDetail}/>
        <Route path='/tasks' component={MyScore} />
        <Route path='/score' component={ScoreBoard} />
        <Route path='/announce' component={Tasks} />
        <Route exact path='/prize' component={SharePrize} />
        <Route path='/thumb' component={ThumbUp} />
        <Route exact path='/home/index' component={Options} />
        <Route exact path='/home/index/THUMB_UP/:id' component={Options} />
        <Route path='/answer' component={AnswerQuestion} />
        <Route path='/description' component={Description} />
        <Route path='/error' component={Error} />
        <Route path='/upload' component={UploadQuestion} />

        <Redirect from='*' to='/' />
    </Switch>
);

export default Routes;