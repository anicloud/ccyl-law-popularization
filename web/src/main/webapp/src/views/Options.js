import React, {Component} from 'react';
import {getSearchString} from '../utils/index';

class Options extends Component {
    constructor(props) {
        super(props);
        console.log(this.props);
    }
    componentWillMount() {
        let search = this.props.location.search;
        let opt = getSearchString(search, 'op');
        switch (opt) {
            case 'UNSUBSCRIBE':
                this.props.history.push('/error');
                break;
            case 'LOGIN_SUCCESS':
                this.props.history.push('/');
                window.sessionStorage.setItem('option', 'LOGIN_SUCCESS')
                break;
            case 'LOGIN_FAILURE':
                this.props.history.push('/error');
                break;
            case 'THUMB_UP':
                this.props.history.push({
                    pathname: '/thumb',
                    state: getSearchString(search, 'id')
                });
                window.sessionStorage.setItem('option', `THUMB_UP&id=${getSearchString(search, 'id')}`);
                break;
            case 'ACCESS_DENIED':
                this.props.history.push('/');
                break;
            default:
                return;
        }
    }
    render() {
        return (
            <div>Options</div>
        )
    }
}

export default Options;