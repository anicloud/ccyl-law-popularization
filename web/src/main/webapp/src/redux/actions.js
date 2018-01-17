import { SHOW_LOADING, SHOW_ERROR, CHANGE_COUNTSDK } from './actionTypes';

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

export function changeCountJsSdk(num) {
    return {
        type: SHOW_ERROR,
        num
    };
}