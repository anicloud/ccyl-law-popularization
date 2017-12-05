import React from 'react';
import {Switch, Route, Redirect} from 'react-router-dom';
import App from '../App';
import Home from '../views/Home';
import AsyncCounter from '../views/AsyncCounter';
import AsyncZen from '../views/AsyncZen';
import AsyncElapse from '../views/AsyncElapse';
import AsyncRoute from '../views/AsyncRoute';
import AsyncPageNotFound from '../views/AsyncPageNotFound';

const Routes = () => (
    <Switch>
        <Route exact path='/' component={App} />
        <Route exact path='/home' component={Home} />
        <Route path='/counter' component={AsyncCounter} />
        <Route path='/zen' component={AsyncZen} />
        <Route path='/elapse' component={AsyncElapse} />
        <Route path='/route/:id' component={AsyncRoute} />
        <Route path='/404' component={AsyncPageNotFound} />

        <Redirect exact from='/home/index' to='/' />
        <Redirect exact from='/wechat/redirect' to='/' />
        <Redirect from='*' to='/404' />
    </Switch>
);

export default Routes;