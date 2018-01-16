<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String jsonUrl = (String)request.getAttribute("jsonFileUrl");
%>
<html>
<head>
  <meta charset=utf-8>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no,shrink-to-fit=no"/>
  <meta name="theme-color" content="#000000">
  <link rel="shortcut icon" href="./favicon.ico">
  <link rel="stylesheet" href="./bootstrap.min.css">
  <title>第十四届全国青少年学法用法网上知识竞赛</title>
  <style>
    .title {
      width: 100%;
      height: 100%;
      text-align: center;
      background: #aee644 url("${pageContext.request.contextPath}/build/assets/images/computerBkg.png") center top no-repeat;
      background-size: cover;
    }
    .middle{
      width:100%;
      height:21rem;
      background-color: #9bbd4b;
    }
    .tubiao{
      height:2.2rem;
      width:80%;
      margin:0 auto;
      background-color: #eaf1d9;
      border-radius:5px;
      text-align:center;
    }
    .tb-title{
      height:2.2rem;
      width:40%;
      background: #eaf1d9 url("${pageContext.request.contextPath}/build/assets/images/gifts_title.png") center no-repeat;
      padding-top:.2rem;
      float:left;
      display:inline-block;
      border-radius:5px;
    }
    .tengxun{
      height:2.2rem;
      width:18%;
      display:inline-block;

    }
    .tx-title{
      height:1.3rem;
      width:90%;
      margin-bottom:.3rem;
      background: #eaf1d9 url("${pageContext.request.contextPath}/build/assets/images/logo_tencent_v.png") bottom no-repeat;
    }
    .tx-centent{
      text-align:center;
      height:.4rem;
      width:90%;
    }
    .jingdong{
      height:2.2rem;
      width:18%;
      display:inline-block;
    }
    .jd-title{
      height:1.3rem;
      width:90%;
      margin-bottom:.3rem;
      background: #eaf1d9 url("${pageContext.request.contextPath}/build/assets/images/jingdong.png") bottom no-repeat;
    }
    .jd-centent{
      text-align:center;
      height:.4rem;
      width:90%;
    }
    .mobai{
      height:2.2rem;
      width:18%;
      display:inline-block;
    }
    .mb-title{
      height:1.3rem;
      width:90%;
      margin-bottom:.3rem;
      background: #eaf1d9 url("${pageContext.request.contextPath}/build/assets/images/mobai.png") bottom no-repeat;
    }
    .mb-centent{

      height:.4rem;
      width:90%;
    }
    .shuoming{
      height:3.5rem;
      width:80%;
      margin:0.2rem auto 0;
      border-radius:5px;
      background-color: #eaf1d9;
    }
    .sm-img{
      height:32%;
      padding-top:.1rem;
      margin-bottom:.1rem;
      margin-left:.4rem;
      background:#eaf1d9 url("${pageContext.request.contextPath}/build/assets/images/sssm.png") bottom left no-repeat;
    }
    .sm-content{
      height:68%
    }
    .sm-detail{
      height:100%;
      width:100%;
      clear:both;
    }
    .detail1{
      margin-left:.3rem;
      margin-top:0rem;
      float:left;
      width:14%;
      height:100%
    }
    .detail2{
      margin-top:0rem;
      float:left;
      width:14%;
      height:100%
    }
    .detail3{
      margin-top:0rem;
      float:left;
      width:14%;
      height:100%
    }
    .detail4{
      margin-left:5%;
      float:left;
      width:50%;
      height:100%
    }
    .paihangbang{
      height:8rem;
      width:80%;
      margin:0.2rem auto 0;
      border-radius:5px;
      clear:both;
    }
    .paihangbang1{
      height:100%;
      width:49%;
      border-radius:5px;
      float:left;
      background-color: #eaf1d9;
    }
    .phb1-title{
      background:#eaf1d9 url("${pageContext.request.contextPath}/build/assets/images/glory_title.png") bottom center no-repeat;
      height:20%;
      margin-left:.4rem;
    }
    .phb1-detail{
      overflow:auto;
      height:80%;
      width:100%;
    }
    .phb1-detail .info {
      background-color: #fff;
      color: #000000;
      line-height: .64rem;
      margin-top: .3rem;
      letter-spacing: .02rem;
      font-size: .26rem;
      border-radius: .08rem;
      margin-left:.1rem;
      margin-right:.1rem;
    }
    .phb1-detail .info .first{
      float:left;
      width:40%;
      text-align:center;
    }
    .phb1-detail .info .second {
      float:right;
      width:30%;
      text-align:center;
    }

    .phb1-detail .top-ranking{
      margin-top: .2rem;
      margin-bottom: .2rem;
      margin-left:.1rem;
      margin-right:.1rem;
      font-size: .1rem;
      background-color: #aee644;
      height: .7rem;
      border-radius: .08rem;
      box-shadow: .02rem .02rem .1rem rgba(0, 0, 0, .5);
    }
    .phb1-detail .top-ranking .rank-first{
      width:15%;
      float:left;
      text-align:center;
    }
    .phb1-detail .top-ranking .rank-first img{
      padding-top:.01rem;
      width: .68rem;
      height:.68rem;
      border-radius: 50%;
    }
    .phb1-detail .top-ranking .rank-second{
      padding-top:.15rem;
      width:55%;
      float:left;
      text-align:left;
    }
    .phb1-detail .top-ranking .rank-third{
      padding-top:.1rem;
      font-size: .3rem;
      width:30%;
      text-align:center;
      font-weight: bold;
    }
    .paihangbang2{
      margin-left:1.4%;
      height:100%;
      width:49%;
      border-radius:5px;
      background-color: #eaf1d9;
      float:left;
    }
    .phb2-title{
      background:#eaf1d9 url("${pageContext.request.contextPath}/build/assets/images/national_rank.png") bottom center no-repeat;
      height:20%;
      margin-left:.4rem;
    }
    .phb2-detail{
      overflow:auto;
      height:80%;
      width:100%;
    }

    .phb2-detail .info {
      background-color: #fff;
      color: #000000;
      line-height: .64rem;
      margin-top: .3rem;
      letter-spacing: .02rem;
      font-size: .26rem;
      border-radius: .08rem;
      margin-left:.1rem;
      margin-right:.1rem;
    }
    .phb2-detail .info .first{
      float:left;
      width:12.5%;
      text-align:center;
    }
    .phb2-detail .info .second {
      float:left;
      width:20%;
      text-align:center;
    }
    .phb2-detail .info .third {
      float:left;
      width:22.5%;
      text-align:center;
    }
    .phb2-detail .info .four {
      float:left;
      width:22.5%;
      text-align:center;
    }
    .phb2-detail .info .five {
      float:left;
      width:22.5%;
      text-align:center;
    }

    .phb2-detail .top-ranking{

      margin-top: .2rem;
      margin-bottom: .2rem;
      margin-left:.1rem;
      margin-right:.1rem;
      font-size: .1rem;
      background-color: #aee644;
      height: .7rem;
      border-radius: .08rem;
      box-shadow: .02rem .02rem .1rem rgba(0, 0, 0, .5);
    }
    .phb2-detail .top-ranking .rank-first{
      font-size:.3rem;
      float:left;
      width:12.5%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }

    .phb2-detail .top-ranking .rank-second{
      font-size:.2rem;
      float:left;
      width:20%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }
    .phb2-detail .top-ranking .rank-third{
      font-size:.2rem;
      float:left;
      width:22.5%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }
    .phb2-detail .top-ranking .rank-four{
      font-size:.2rem;
      float:left;
      width:22.5%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }

    .phb2-detail .top-ranking .rank-five{
      font-size:.2rem;
      float:left;
      width:22.5%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }
    .guize{
      height:6rem;
      width:80%;
      margin:0.2rem auto 0;
      border-radius:5px;
      clear:both;
    }
    .guize1{
      height:100%;
      width:49%;
      border-radius:5px;
      background-color: #eaf1d9;
      float:left;
    }
    .guize1-detail{
      margin-top:.3rem;
      margin-left:.3rem;
    }
    .guize2{
      margin-left:1.4%;
      height:100%;
      width:49%;
      border-radius:5px;
      background-color: #eaf1d9;
      float:left;
    }
    .guize2-detail{
      margin-top:.3rem;
      margin-left:.3rem;
    }
  </style>
</head>
<body>
<noscript>
  You need to enable JavaScript to run this app.
</noscript>
<div class="title"></div>
<div class="middle">
  <div class="tubiao">
    <div class="tb-title"></div>
    <div class="tengxun">
      <div class="tx-title"></div>
      <div class="tx-centent">京东购物卡</div>
    </div>
    <div class="jingdong">
      <div class="jd-title"></div>
      <div class="jd-centent">腾讯视频会员</div>
    </div>
    <div class="mobai">
      <div class="mb-title"></div>
      <div class="mb-centent">摩拜骑行劵</div>
    </div>
  </div>
  <div class="shuoming">
    <div class="sm-img"></div>
    <div class="sm-content">
      <div class="sm-detail">
        <div class="detail1">
          <p style="font-weight: bold;font-size:.25rem;">主办单位</p>
          <p>工青团中央</p>
          <p>司法部</p>
          <p>全国普法办</p>
        </div>
        <div class="detail2">
          <p style="font-weight: bold;font-size:.25rem;">承办单位</p>
          <p>团中央维护青少年权益部</p>
          <p>司法部法制宣传司</p>
          <p>中国少年儿童发展服务中心</p>
          <p>中国青少年宫协会</p>

        </div>
        <div class="detail3">
          <p style="font-weight: bold;font-size:.25rem;">协办单位</p>
          <p>中国青年网</p>
          <p>青少年维权在线</p>
          <p>青年之声互动社交平台</p>
        </div>
        <div class="detail4">
          <p>为引导广大青少年深入学习贯彻十九大精神，落实“七五”普法规划要</p>
          <p>求和部署，深入开展青少年法治宣传教育工作，切实提高广大青少年的法</p>
          <p>治观念和法治素养，共青团中央、司法部、全国普法办共同举办第14届全国青少</p>
          <p>年学法用法网上知识竞赛</p>
          <br/>
          <p style="font-weight: bold;">举办时间:2018年1月15日 - 2018年2月28日</p>
        </div>
      </div>
    </div>
  </div>
  <div class="paihangbang">
    <div class="paihangbang1">
      <div class="phb1-title"></div>
      <div class="phb1-detail" id="top20Rank">
        <div class='clearfix info'>
          <div class='first'>
            今日得分
          </div>
          <div class='second'>
            全国排名
          </div>
        </div>
        <div class='clearfix top-ranking'>
          <div class='pull-left rank-first'>
            <img src='http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0' alt=''/>
          </div>
          <div class='pull-left rank-second'>
            <div>10000</div>
            <div>secty</div>
          </div>
          <div class='pull-right rank-third'>1</div>
        </div>
        <div class='top-ranking clearfix'>
          <div class='pull-left rank-first'>
            <img src='http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqEacia8yO1dRwiclJiawFyt8PQsbibVld9PmCcyaGZlR2gCR8RNTojKFkVdePUdpw7FhiacjzOMtZNFHQ/0' alt=''/>
          </div>
          <div class='pull-left rank-second'>
            <div>10000</div>
            <div>secty</div>
          </div>
          <div class='pull-right rank-third'>2</div>
        </div>
      </div>
    </div>
    <div class="paihangbang2">
      <div class="phb2-title"></div>
      <div class="phb2-detail" id="provinceRank">
        <div class='clearfix info'>
          <div class='first'>
            排名
          </div>
          <div class='second'>
            省份
          </div>
          <div class='third'>
            平均得分
          </div>
          <div class='four'>
            总得分
          </div>
          <div class='five'>
            参赛人数
          </div>
        </div>
        <div class='clearfix top-ranking'>
          <div class='rank-first'>1</div>
          <div class='rank-second'>北京市</div>
          <div class='rank-third'>370</div>
          <div class='rank-four'>37,000,000</div>
          <div class='rank-five'>100,000</div>
        </div>
      </div>
    </div>
  </div>
  <div class="guize">
    <div class="guize1">
      <div class="guize1-detail">
        <p style="font-weight: bold;font-size:.25rem;">奖品设置</p>
        <br/>
        <p>每天答题、签到、分享、拉好友答题和点赞都可增加积分哦，每天积分前20名可领取相应奖品。</p>
        <p>NO.1:荣获100元京东购物卡</p>
        <p>NO.2:荣获50元京东购物卡</p>
        <p>NO.3:荣获30元京东购物卡</p>
        <p>NO.4-NO.20:荣获20元腾讯视频会员月卡</p>
        <p>幸运奖:每天随机抽取20名当天参赛答题网友，可荣获摩拜单车骑行券5张</p>
        <br/>
        <p>当天未获奖的参赛者也可随时前往积分商城用积分兑换奖品：</p>
        <p>1000个积分:兑换20元腾讯视频会员月卡</p>
        <p>500个积分:兑换摩拜单车骑行券10张1元券</p>
        <p>200个积分:兑换5元购物优惠券（购物券49元减5元）</p>
        <p>100个积分:兑换10元优惠券（购物券99元减10元）</p>
      </div>
    </div>
    <div class="guize2">
      <div class="guize2-detail">
        <p style="font-weight: bold;font-size:.25rem;">积分规则</p>
        <br/>
        <p>每天可通过多种方式增涨积分，获取的积分可累计。</p>
        <p>1.答题积分:每答对一题增加2个积分</p>
        <p>2.打卡积分:每天打卡一次，增加5个积分</p>
        <p>3.分享积分:答题后每分享一次朋友圈或者微信群，增加5个积分，每天通过转发最多可累积5个积分</p>
        <p>4.拉好友点赞:每位好友每天可点赞一次，每被点赞一次增加1个积分</p>
        <p>5.拉好友答题:每位好友通过分享的网页进行答题，分享者可增加5个积分</p>
        <p>注:参赛者的积分一旦达到TOP20，微信公众号会在次日自动通知该参赛者领取奖品，同时将该参赛者的积分清零。</p>
      </div>
    </div>
  </div>

</div>
<script src="${pageContext.request.contextPath}/build/assets/js/rem.js"></script>
<script src="${pageContext.request.contextPath}/build/assets/js/jquery-3.1.0.min.js"></script>
<script>
  var jsonUrl = '<%=jsonUrl%>';
  //全国json获取
  $.ajax({
    type: "POST",//请求方式
    url: "item.json",//地址，就是json文件的请求路径
    dataType: "json",//数据类型可以为 text xml json  script  jsonp
    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
      addBox(result);
    }
  });

  function addBox(result){
    //result是一个集合,所以需要先遍历
    $.each(result,function(index,obj){
      $("#provinceRank").append(
              "<div class='clearfix top-ranking'>"+
                "<div class='rank-first'>"+index+"</div>"+
                "<div class='rank-second'>"+obj['province']+"</div>"+
                "<div class='rank-third'>"+obj['averageScore']+"</div>"+
                "<div class='rank-four'>"+obj['totalScore']+"</div>"+
                "<div class='rank-five'>"+obj['peopleNumber']+"</div>"+
              "</div>"
      );
    });
  }

  //top20Json获取
  $.ajax({
    type: "POST",//请求方式
    url: "item.json",//地址，就是json文件的请求路径
    dataType: "json",//数据类型可以为 text xml json  script  jsonp
    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
      addBox(result);
    }
  });

  function addBox(result){
    //result是一个集合,所以需要先遍历
    $.each(result,function(index,obj){
      $("#top20Rank").append(
              "<div class='clearfix top-ranking'>"+
                "<div class='pull-left rank-first'>"+
                  "<img src='"+obj['portrat']+"' alt=''/>"+
                "</div>"+
                "<div class='pull-left rank-second'>"+
                    "<div>"+obj['score']+"</div>"+
                    "<div>"+obj['name']+"</div>"+
                    "</div>"+
                "<div class='pull-right rank-third'>"+index+"</div>"+
              "</div>"
      );
    });
  }

</script>
</body>
</html>
