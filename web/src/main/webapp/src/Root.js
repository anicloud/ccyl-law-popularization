import React, {Component} from 'react';
import { Provider } from 'react-redux';
import {HashRouter} from 'react-router-dom';
import store from './redux/index';
import Routes from './routes/index';

class Root extends Component {
    render() {
        return (
            <Provider store={store}>
                <HashRouter basename='/leg'>
                    <Routes></Routes>
                </HashRouter>
            </Provider>
        )
    }
}

export default Root;