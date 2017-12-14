import React, {Component} from 'react';
import {getSearchString} from '../utils/index';

class Options extends Component {
    constructor(props) {
        super(props);
        console.log(this.props);
    }
    componentWillMount() {
        let opt = getSearchString(this.props.location.search, 'op');
        switch (opt) {
            case 'LOGIN_SUCCESS':
                this.props.history.push('/');
                break;
            case 'LOGIN_FAILURE':
                this.props.history.push('/error')
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