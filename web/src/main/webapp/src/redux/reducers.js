import { SHOW_LOADING } from './actionTypes'

function reducers(state = {}, action) {
    switch (action.type) {
        case SHOW_LOADING:
            return Object.assign({}, state, {showLoading: action.flag});
        default:
            return state
    }
}

export default reducers