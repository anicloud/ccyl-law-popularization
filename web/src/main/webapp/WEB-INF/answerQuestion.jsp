<%@ page import="com.ani.ccyl.leg.commons.dto.ThumbUpDto" %>
<%--
  Created by IntelliJ IDEA.
  User: sunminggui
  Date: 2018/1/5
  Time: 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String nickName = (String)request.getAttribute("nickName");
  Boolean content = (Boolean)request.getAttribute("isThumbUp");//true为从点赞页面跳转，false为已经点赞过，直接进入这个页面
%>
<html>
<head>
  <title>第十四届全国青少年学法用法网上知识竞赛</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/build/assets/css/bootstrap.min.css">
  <style>
    .common-bg {
      background: #aee644 url("${pageContext.request.contextPath}/build/static/media/bkg1.png") center top no-repeat;
      background-attachment: fixed;
      background-size: 100%;
    }
    .thumb {
      width: 100%;
      height: 100%;
      padding: .8rem .3rem 0;
      position: relative;
      overflow: auto;
    }
    .thumb .share {
      margin-top: 1.5rem;
      margin-bottom: 1.5rem;
    }
    .thumb .share button.btn {
      font-size: .36rem;
      padding-right: .5rem;
      padding-left: .5rem;
    }
    .thumb .share button.btn:first-child {
      margin-right: .8rem;
    }
    .thumb .score-ques span {
      color: #f60;
    }
    .thumb .header img {
      width: 6rem;
      height: 2.6rem;
    }
    .thumb .wrapper {
      position: absolute;
      top: 4rem;
      bottom: 0;
      left: .4rem;
      right: .4rem;
      overflow: auto;
      background-color: rgba(255, 255, 255, 0.8);
      border-top-left-radius: .1rem;
      border-top-right-radius: .1rem;
    }
    .thumb .wrapper .wrapper-thumb {
      margin: 2rem auto;
    }
    .thumb .wrapper .wrapper-thumb .first {
      padding-top: .6rem;
      padding-left: 1rem;
    }
    .thumb .wrapper .wrapper-thumb .first img {
      width: 1.58rem;
      height: 1.58rem;
      border-radius: 50%;
    }
    .thumb .wrapper .wrapper-thumb .first p {
      line-height: 1rem;
      color: #4a4b4c;
      font-size: .48rem;
    }
    .thumb .wrapper .wrapper-thumb .second {
      padding-right: 1rem;
    }
    .thumb .wrapper .wrapper-thumb .second div {
      width: 4.4rem;
      height: 4.4rem;
      background-color: #fdf5a2;
      border-radius: .7rem;
      padding: .4rem;
    }
    .thumb .wrapper .wrapper-thumb .second div p {
      font-size: .4rem;
      color: #823c25;
      font-weight: bold;
    }
    .thumb .wrapper .wrapper-thumb .third p {
      margin: 0 auto;
      width: 4rem;
      text-align: center;
      line-height: 1rem;
      color: #303030;
      font-weight: bold;
      font-size: .4rem;
      letter-spacing: .02rem;
    }
    .thumb .wrapper .wrapper-thumb .four{
      margin: .4rem auto;
      text-align: center;
      color: #303030;
      font-size: .4rem;
      width: 5rem;
    }
    .thumb .wrapper .thumb-btn .right-now {
      margin: 0 auto;
      width: 3.2rem;
      line-height: 1rem;
      text-align: center;
      font-size: .4rem;
      color: #fff;
      font-weight: bold;
      background-color: #6173b7;
      border-radius: .3rem;
      border: .06rem solid #fff;
      box-shadow: 0.02rem 0.02rem 0.2rem rgba(0, 0, 0, 0.3);
      margin-top: .4rem;
    }

    .thumb .wrapper .thumb-btn img {
      margin: 0 auto;
      width: 2.2rem;
      text-align: center;
      font-size: .4rem;
      display:block;
    }

  </style>
  <script src="<%=request.getContextPath() %>/build/assets/js/bootstrap.min.js" ></script>
  <script src="<%=request.getContextPath() %>/build/assets/js/jquery-3.1.0.min.js" ></script>
  <script src="<%=request.getContextPath() %>/build/assets/js/rem.js"></script>
</head>
<body>
  <div class='thumb common-bg'>
    <div class='text-center header'>
      <img src="${pageContext.request.contextPath}/build/assets/images/header_thumbup.png" alt=""/>
    </div>
    <div class='wrapper'>
      <div class='wrapper-thumb'>
        <% if(content==true){%>
        <div class='third'>
          <p>恭喜你已为好友</p>
          <p><%=nickName%></p>
          <p>点赞成功</p>
        </div>
        <%}else{%>
          <div class='third'>
            <p>你今日已为好友</p>
            <p><%=nickName%></p>
            <p>点过赞了</p>
          </div>
        <%}%>
        <div class='four'>
          <p>我也要争当普法先锋，与好友PK赢奖品</p>
        </div>
      </div>
      <div class='text-center thumb-btn'>
        <div class='right-now' onclick="handleAnswer()">马上答题</div>
      </div>
    </div>
</div>
<script>
  function handleAnswer(){
    var _CTX = '<%=request.getContextPath() %>';
    window.location.href= _CTX+"/share/goToAnswerQuestion";
  }

</script>
</body>
</html>
