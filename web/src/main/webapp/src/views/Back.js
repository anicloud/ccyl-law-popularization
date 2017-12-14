import React, {Component} from 'react';

class Back extends Component {
    constructor(props) {
        super(props);
        this.handleTouch = this.handleTouch.bind(this);
    }
    handleTouch() {
        const {location, history} = this.props;
        history.push(location);
    }
    render() {
        return (
            <span style={{fontSize: '.36rem'}} className='pull-left' onClick={this.handleTouch}>
                <i className='glyphicon glyphicon-menu-left'></i>
                返回
            </span>
        )
    }
}

export default Back;