import React, {Component} from 'react';
import { Provider } from 'react-redux';
import {HashRouter, BrowserRouter} from 'react-router-dom';
import store from './redux/index';
import Routes from './routes/index';

class Root extends Component {
    render() {
        return (
            <Provider store={store}>
                <BrowserRouter basename='/leg'>
                    <Routes></Routes>
                </BrowserRouter>
            </Provider>
        )
    }
}

export default Root;