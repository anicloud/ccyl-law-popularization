import React from 'react';
import {Switch, Route, Redirect} from 'react-router-dom';
import App from '../App';

import Home from '../views/Home';
import AsyncCounter from '../views/AsyncCounter';
import AsyncZen from '../views/AsyncZen';
import AsyncElapse from '../views/AsyncElapse';
import AsyncRoute from '../views/AsyncRoute';

import Regist from '../views/Regist';
import AnswerQuestion from '../views/AnswerQuestion';

const Routes = () => (
    <Switch>
        <Route exact path='/' component={App} />
        <Route exact path='/regist' component={Regist} />
        <Route path='/answer' component={AnswerQuestion} />

        <Route exact path='/home' component={Home} />
        <Route path='/counter' component={AsyncCounter} />
        <Route path='/zen' component={AsyncZen} />
        <Route path='/elapse' component={AsyncElapse} />
        <Route path='/route/:id' component={AsyncRoute} />

        <Redirect from='*' to='/' />
    </Switch>
);

export default Routes;