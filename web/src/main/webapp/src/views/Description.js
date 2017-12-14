/**
 * Created by wxw on 2017/12/13.
 */
import React, {Component} from 'react';
import '../media/styles/description.less';
import Back from './Back';

class Description extends Component {
    render() {
        return (
            <div className="description main-bg">
                <div className='clearfix'>
                    <Back location='/home' history={this.props.history} />
                </div>
                <h2 className="text-center title">
                    <span>赛事说明</span>
                </h2>
                <div className="info">
                    <ul>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                        <li>赛事主要内容赛事主要内容赛事主要内容赛事主要内容赛事主要内容</li>
                    </ul>
                </div>
            </div>
        );
    }
}

export default Description;