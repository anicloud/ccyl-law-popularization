import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import reducers from './reducers';
import initStore from './store';
let store = createStore(
    reducers,
    initStore,
    applyMiddleware(thunk)
);
export default store;