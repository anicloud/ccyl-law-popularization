/**
 * Created by sunminggui on 2018/1/3.
 */
/** 兑换成功界面 **/
import React,{Component} from 'react';
import { Msg, Footer, FooterLinks, FooterLink, FooterText,Page} from 'react-weui';
import {connect} from 'react-redux';

class ExchangeSuccess extends Component{
    constructor(props){
        super(props);
    }



    render() {
        let props = this.props;
        {/*const SuccessFooter = ()=>(
            <Footer>
                <FooterLinks>
                    <FooterLink href="#">Footer Link</FooterLink>
                </FooterLinks>
                <FooterText>
                    Copyright © 2008-2016 weui.io
                </FooterText>
            </Footer>
        );*/}
        return (
            <Page className="msg_success">
                <Msg
                    type="success"
                    title="兑换成功"
                    description="请到我的奖品页查看奖品"
                    buttons={[{
                        type: 'primary',
                        label: 'Ok',
                        onClick: props.history ? props.history.goBack : false
                    }, {
                        type: 'default',
                        label: 'Cancel',
                        onClick: props.history ? props.history.goBack : false
                    }]}
                />
            </Page>
        );
    }
}

function mapStateToProps(state) {
    return {
        host: state.host,
        showLoading: state.showLoading,
        showError: state.showError
    }
}

export default connect(mapStateToProps)(ExchangeSuccess);
