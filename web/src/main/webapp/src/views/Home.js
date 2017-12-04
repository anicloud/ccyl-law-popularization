import React, {Component} from 'react';
import {connect} from 'react-redux';
import {addTodo} from '../redux/actions';

class Home extends Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
    }
    handleClick() {
        this.props.onIncrement(11);
    }
    render() {
        return (
            <div className="Home">
                <button onClick={this.handleClick}>click</button>
                {JSON.stringify(this.props.value)}
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        value: state.todos
    };
}

function mapDispatchToProps(dispatch) {
    return {
        onIncrement: (value) => dispatch(addTodo(value))
    };
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Home);