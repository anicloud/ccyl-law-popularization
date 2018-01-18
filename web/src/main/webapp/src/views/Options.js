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
                window.sessionStorage.setItem('option', window.location.href.split('#')[0]);
                this.props.history.push('/');
                break;
            case 'LOGIN_FAILURE':
                window.sessionStorage.setItem('option', window.location.href.split('#')[0]);
                this.props.history.push('/error');
                break;
            case 'THUMB_UP':
                window.sessionStorage.setItem('option', window.location.href.split('#')[0]);
                this.props.history.push({
                    pathname: '/thumb',
                    state: getSearchString(search, 'id')
                });
                // window.sessionStorage.setItem('option', `THUMB_UP&id=${getSearchString(search, 'id')}`);
                break;
            case 'ACCESS_DENIED':
                console.log('123');
                window.sessionStorage.setItem('option', window.location.href.split('#')[0]);
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