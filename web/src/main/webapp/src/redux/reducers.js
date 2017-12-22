import { SHOW_LOADING, SHOW_ERROR } from './actionTypes'

function reducers(state = {}, action) {
    switch (action.type) {
        case SHOW_LOADING:
            return Object.assign({}, state, {showLoading: action.flag});
        case SHOW_ERROR:
            return Object.assign({}, state, {showError: action.flag});
        default:
            return state
    }
}

export default reducers