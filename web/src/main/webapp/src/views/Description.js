/**
 * Created by wxw on 2017/12/13.
 */
import React, {Component} from 'react';
import '../media/styles/description.less';
import Back from './Back';
import {connect} from 'react-redux';
import {Tab,TabBody,NavBar,NavBarItem,Article} from "react-weui";

class Description extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tab:0
        };
    }
    render() {
        return (
            <div className="description main-bg">
                <div className='clearfix'>
                    <Back location='/home' history={this.props.history} />
                </div>
                <div className="description-main">
                    <div className="title">
                        <span>赛事说明</span>
                    </div>
                    <div className="info">
                        <Tab>
                            <NavBar>
                                <NavBarItem active={this.state.tab == 0} onClick={e=>this.setState({tab:0})}>活动简介</NavBarItem>
                                <NavBarItem active={this.state.tab == 1} onClick={e=>this.setState({tab:1})}>奖品设置</NavBarItem>
                                <NavBarItem active={this.state.tab == 2} onClick={e=>this.setState({tab:2})}>积分规则</NavBarItem>
                            </NavBar>
                            <TabBody>
                                <Article style={{display: this.state.tab == 0 ? null : 'none'}}>
                                    <ul>
                                        <li>由共青团中央、司法部、全国普法办联合主办，团中央维护青少年权益部、司法部法制宣传司、中国少年儿童发展服务中心、中国青少年宫协会联合承办，中国青年网、青年之声互动社交平台、青少年维权在线联合协办的第14届全国青少年学法用法网上知识竞赛旨在：</li>
                                        <li>1.突出“学用结合、以用促学”，在宣传法律知识的同时，更多地去考查青少年在各种情景中如何去看待问题、解决问题，引导青少年增强自觉守法、遇事找法、解决问题靠法的法治观念。</li>
                                        <li>2.培养尊法学法守法用法的现代公民，促进青少年健康成长。</li>
                                        <br/>
                                        <li>参赛时间：2018.1.15 - 2018.2.28</li>
                                        <li>参赛对象：不限年龄</li>
                                        <li>全国青少年们不服来战！争当普法先锋！赛出好成绩！赢取丰富奖品！</li>
                                    </ul>
                                </Article>
                                <Article style={{display: this.state.tab == 1 ? null : 'none'}}>

                                    <ul>
                                        <li>参赛者每天通过答题、签到、分享、拉好友答题和点赞都可增加自己的参赛积分，每天积分前20名可领取以下奖品：</li>
                                        <li>第1名：奖励100元京东购物卡</li>
                                        <li>第2名：奖励50元京东购物卡</li>
                                        <li>第3名：奖励30元京东购物卡</li>
                                        <li>第4-20名：奖励20元腾讯视频会员月卡</li>
                                        <li>幸运奖：每天随机抽取20名当天参赛答题网友，可获得摩拜单车骑行券5张</li>
                                        <br/>
                                        <li>当天未获奖的参赛者也可随时前往积分商城用积分兑换奖品：</li>
                                        <li>1000个积分：兑换20元腾讯视频会员月卡</li>
                                        <li>500个积分：兑换ofo共享单车用车券10张1元券</li>
                                        <li>200个积分：兑换10元优惠券（购物券99元减10元）</li>
                                        <li>100个积分：兑换5元购物优惠券（购物券49元减5元）</li>
                                    </ul>
                                </Article>
                                <Article style={{display: this.state.tab == 2 ? null : 'none'}}>
                                    <ul>
                                        <li>参赛者每天可通过多种方式增涨积分，获取的积分可累计。</li>
                                        <li>1.答题积分：每答对一题增加2个积分</li>
                                        <li>2.打卡积分：每天打卡一次，增加5个积分</li>
                                        <li>3.分享积分：答题后每分享一次朋友圈或者微信群，增加5个积分，每天最多分享5次</li>
                                        <li>4.拉好友点赞：每位好友每天可点赞一次，每被点赞一次增加2个积分</li>
                                        <li>5.拉好友答题：每位好友通过分享的网页进行答题，分享者可增加5个积分</li>
                                        <li>注：参赛者的积分一旦达到TOP20，微信公众号会在次日自动通知该参赛者领取奖品，同时将该参赛者的积分清零。</li>
                                    </ul>
                                </Article>
                            </TabBody>
                        </Tab>
                    </div>
                </div>
            </div>
        );
    }
}

export default Description;