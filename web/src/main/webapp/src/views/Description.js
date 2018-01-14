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
            <div className="description common-bg">
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
                                        <li className="danwei">
                                            <div>
                                                <div>
                                                    <span className="label">主办单位:</span>
                                                </div>
                                                <div>
                                                    <div className="gong-qing-tuan"></div><div className="si-fa-bu"></div><div className="pu-fa-ban"></div>
                                                </div>
                                                <div>
                                                    <span className="gqt-name">共青团中央</span><span className="sfb-name">司法部</span><span className="pfb-name">全国普法办</span>
                                                </div>
                                                <div>
                                                    <span className="label1">承办单位:</span>
                                                </div>
                                                <div>
                                                    <span className="secondName">团中央维护青少年权益部</span>
                                                    <span className="secondName">司法部法制宣传司</span>
                                                    <span className="secondName">中国少年儿童发展服务中心</span>
                                                    <span className="secondName">中国青少年宫协会</span>
                                                </div>
                                                <div>
                                                    <span className="label1">协办单位:</span>
                                                </div>
                                                <div>
                                                    <span className="secondName">中国青年网</span>
                                                    <span className="secondName">青少年维权在线</span>
                                                    <span className="secondName">青年之声互动社交平台</span>
                                                </div>
                                            </div>
                                        </li>
                                        <br/>
                                        <li>为引导广大青少年深入学习贯彻十九大精神，落实“七五”普法规划要求和部署，深入开展青少年法治宣传教育工作，切实提高广大青少年的法治观念和法治素养，共青团中央、司法部、全国普法办共同举办第14届全国青少年学法用法网上知识竞赛。</li>
                                        <br/>
                                        <li className="competitionTime">参赛时间:2018.1.15 - 2018.2.28</li>
                                    </ul>
                                </Article>
                                <Article style={{display: this.state.tab == 1 ? null : 'none'}}>

                                    <ul>
                                        <li>争当普法先锋！赢取丰富奖品！</li>
                                        <br/>
                                        <li>每天答题、签到、分享、拉好友答题和点赞都可增加积分哦，每天积分前20名可领取相应奖品。</li>
                                        <li>NO.1:荣获100元京东购物卡</li>
                                        <li>NO.2:荣获50元京东购物卡</li>
                                        <li>NO.3:荣获30元京东购物卡</li>
                                        <li>NO.4-NO.20:荣获20元腾讯视频会员月卡</li>
                                        <li>幸运奖:每天随机抽取20名当天参赛答题网友，可荣获摩拜单车骑行券5张</li>
                                        <br/>
                                        <li>当天未获奖的参赛者也可随时前往积分商城用积分兑换奖品：</li>
                                        <li>1000个积分:兑换20元腾讯视频会员月卡</li>
                                        <li>500个积分:兑换ofo共享单车用车券10张1元券</li>
                                        <li>200个积分:兑换5元购物优惠券（购物券49元减5元）</li>
                                        <li>100个积分:兑换10元优惠券（购物券99元减10元）</li>
                                    </ul>
                                </Article>
                                <Article style={{display: this.state.tab == 2 ? null : 'none'}}>
                                    <ul>
                                        <li>每天可通过多种方式增涨积分，获取的积分可累计。</li>
                                        <li>1.答题积分:每答对一题增加2个积分</li>
                                        <li>2.打卡积分:每天打卡一次，增加5个积分</li>
                                        <li>3.分享积分:答题后每分享一次朋友圈或者微信群，增加5个积分，每天最多分享5次</li>
                                        <li>4.拉好友点赞:每位好友每天可点赞一次，每被点赞一次增加1个积分</li>
                                        <li>5.拉好友答题:每位好友通过分享的网页进行答题，分享者可增加5个积分</li>
                                        <li>注:参赛者的积分一旦达到TOP20，微信公众号会在次日自动通知该参赛者领取奖品，同时将该参赛者的积分清零。</li>
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