import React from 'react';
import {Switch, Route, Redirect} from 'react-router-dom';
import App from '../App';
/*引导页*/
import Main from '../views/Main'

import AsyncCounter from '../views/AsyncCounter';
import AsyncZen from '../views/AsyncZen';
import AsyncElapse from '../views/AsyncElapse';
import AsyncRoute from '../views/AsyncRoute';

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

const Routes = () => (
    <Switch>
        <Route exact path='/' component={Main} />
        <Route exact path='/home' component={App} />
        <Route exact path='/regist' component={Regist} />
        <Route path='/home/index' component={Options} />
        <Route path='/signin' component={SignIn}/>
        <Route path='/answer' component={AnswerQuestion} />
        <Route path='/description' component={Description} />
        <Route path='/error' component={Error} />

        <Route path='/counter' component={AsyncCounter} />
        <Route path='/zen' component={AsyncZen} />
        <Route path='/elapse' component={AsyncElapse} />
        <Route path='/route/:id' component={AsyncRoute} />

        <Redirect from='*' to='/' />
    </Switch>
);

export default Routes;