import React, {Component} from 'react';
import ChildRoute from './ChildRoute';
import {Route, Link} from 'react-router-dom';

class AsyncRoute extends Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.handleClick = this.handleClick.bind(this);
    }
    handleClick() {
        this.props.history.push('/counter?name=2&age=5');
    }
    render() {
        return (
            <div>
                AsyncRoute component
                <button onClick={this.handleClick}>change page</button>    
                <Route path={`${this.props.match.url}/child`} component={ChildRoute}></Route>
                <Link to='/zen'>连接跳转</Link>
                <Link to={{
                    pathname: '/elapse',
                    search: '?sort=name',
                    state: {
                        price: 18
                    }
                }}>链接跳转2</Link>
            </div>
        )
    }
}

export default AsyncRoute;