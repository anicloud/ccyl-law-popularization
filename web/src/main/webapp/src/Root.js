import React, {Component} from 'react';
import { Provider } from 'react-redux';
import {HashRouter, BrowserRoute} from 'react-router-dom';
import store from './redux/index';
import Routes from './routes/index';

class Root extends Component {
    render() {
        return (
            <Provider store={store}>
                <BrowserRoute basename='/leg'>
                    <Routes></Routes>
                </BrowserRoute>
            </Provider>
        )
    }
}

export default Root;