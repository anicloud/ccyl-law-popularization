import { SHOW_LOADING, SHOW_ERROR } from './actionTypes';

export function showLoading(flag) {
    return {
        type: SHOW_LOADING,
        flag
    };
}

export function showError(flag) {
    return {
        type: SHOW_ERROR,
        flag
    };
}