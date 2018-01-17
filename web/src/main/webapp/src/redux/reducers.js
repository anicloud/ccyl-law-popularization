import { SHOW_LOADING, SHOW_ERROR, CHANGE_COUNTSDK } from './actionTypes'

function reducers(state = {}, action) {
    switch (action.type) {
        case SHOW_LOADING:
            return Object.assign({}, state, {showLoading: action.flag});
        case SHOW_ERROR:
            return Object.assign({}, state, {showError: action.flag});
        case CHANGE_COUNTSDK:
            return Object.assign({}, state, {countJsSdk: action.num});
        default:
            return state
    }
}

export default reducers