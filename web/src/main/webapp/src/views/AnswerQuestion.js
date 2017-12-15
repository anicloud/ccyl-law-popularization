/**
 * Created by wxw on 2017/12/13.
 */
import React, {Component} from 'react';
import '../media/styles/answer.less';
// import ChoiceQuestion from "./questiontype/ChoiceQuestion";
import TrueFalseQuestion from "./questiontype/TrueFalseQuestion";
import Back from './Back';

class AnswerQuestion extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '/home'
        }
    }
    componentWillMount() {
        const {state} = this.props.location;
        if (state === '/tasks') {
            this.setState({
                location: state
            });
        }
    }
    render() {
        return (
            <div className="answer main-bg">
                <div className='clearfix'>
                    <Back location={this.state.location} history={this.props.history} />
                </div>
                <h2 className="text-center h2 title">
                    <span>今日必答</span>
                </h2>
                <h3 className="text-center h3 title">
                    <span>第一天第三场</span>
                </h3>
                <TrueFalseQuestion></TrueFalseQuestion>
            </div>
        )
    }
}

export default AnswerQuestion;