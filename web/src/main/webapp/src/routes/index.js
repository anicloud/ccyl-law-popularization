import React from 'react';
import {Switch, Route, Redirect} from 'react-router-dom';
import App from '../App';

import Home from '../views/Home';
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

const Routes = () => (
    <Switch>
        <Route exact path='/' component={App} />
        <Route exact path='/regist' component={Regist} />
        <Route path='/answer' component={AnswerQuestion} />
        <Route path='/description' component={Description} />

        <Route exact path='/home' component={Home} />
        <Route path='/counter' component={AsyncCounter} />
        <Route path='/zen' component={AsyncZen} />
        <Route path='/elapse' component={AsyncElapse} />
        <Route path='/route/:id' component={AsyncRoute} />

        <Redirect from='*' to='/' />
    </Switch>
);

export default Routes;