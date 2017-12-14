/**
 * Created by sunminggui on 2017/12/13.
 */
/**
 * 判断题组件
 */
import React,{Component} from "react";
import {Form,FormCell,CellHeader,CellBody,Radio,Button} from 'react-weui';
import "../../media/styles/question.less"

class TrueFalseQuestion extends Component {
    render() {
        return (
            <div className="question">
                <h3 className="text-center question title">
                    <span>第一题</span>
                </h3>
                <p>
                    我国的刑罚分为主刑和附加刑，其中附加刑包括罚金、拘役和没收财产
                    dknoisdhngoinsdfibso
                    sdgosidhgoais
                    sdgfosidbgoasibdg;oasidb
                </p>
                <Form checkbox>
                    <FormCell checkbox>
                        <CellHeader>
                            <Radio name="radio1" value="A"/>
                        </CellHeader>
                        <CellBody>Option 1</CellBody>
                    </FormCell>
                    <FormCell checkbox>
                        <CellHeader>
                            <Radio name="radio1" value="B" defaultChecked/>
                        </CellHeader>
                        <CellBody>Option 2</CellBody>
                    </FormCell>
                </Form>

                <Button className='questionButton' type="primary" plain>下一题</Button>
            </div>
        )
    }
}
export default TrueFalseQuestion;
