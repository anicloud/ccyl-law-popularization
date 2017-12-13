/**
 * Created by wxw on 2017/12/13.
 */
import React, {Component} from 'react';
import '../media/styles/answer.less';
import ChoiceQuestion from "./questiontype/ChoiceQuestion"
import TrueFalseQuestion from "./questiontype/TrueFalseQuestion"

class AnswerQuestion extends Component {
    render() {
        return (
            <div className="answer main-bg">
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