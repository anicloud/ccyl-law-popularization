import { ADD_TODO } from './actionTypes'

function reducers(state = {}, action) {
    switch (action.type) {
        case ADD_TODO:
            console.log(state);
            return Object.assign({}, state, {todos: state.todos.concat({
                text: action.text,
                completed: false
            })});
        default:
            return state
    }
}

export default reducers