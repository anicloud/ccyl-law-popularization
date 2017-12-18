import { SHOW_LOADING } from './actionTypes';

export function showLoading(flag) {
    return {
        type: SHOW_LOADING,
        flag
    };
}