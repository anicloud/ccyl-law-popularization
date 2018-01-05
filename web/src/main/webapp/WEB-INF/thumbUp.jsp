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
  ThumbUpDto thumbUpDto = (ThumbUpDto)request.getAttribute("thumbUpDto");
  String head = thumbUpDto.getToPortrait();
  String nickName = thumbUpDto.getToNickName();
  Integer totalScore = thumbUpDto.getTotalScore();
  Boolean isThumbUp = thumbUpDto.getIsThumbUp();
  Integer accountId = thumbUpDto.getAccountId();
%>
<html>
<head>
    <title>点赞</title>
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
      margin: 1rem auto;
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
      line-height: .7rem;
      color: #303030;
      font-weight: bold;
      font-size: .4rem;
      letter-spacing: .02rem;
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
        <div class='clearfix wrapper-thumb'>
          <div class='pull-left first'>
            <img src=<%=head%> alt=""/>
            <p><%=nickName%></p>
          </div>
          <div class='pull-right second'>
            <div>
              <p>我正在争当普法小先锋，目前积分<span><%=totalScore%></span>，快来为我点赞，帮我增加积分赢取奖品哦~</p>
            </div>
          </div>
        </div>
        <div class='text-center thumb-btn'>
          <img src="${pageContext.request.contextPath}/build/assets/images/btn_thumbup.png" onclick="handleThumb()" alt=""/>
        </div>
      </div>
    </div>
  <script>
      function handleThumb(){
        var _CTX = '<%=request.getContextPath() %>';
        $.ajax({
          url: _CTX + "/share/thumbUp?toAccountId=<%=accountId%>",
          type: "GET",
          cache:false,
          success: function (response) {
            var res = JSON.parse(response);
            if(res.state===0){
              alert("点赞成功!");
            }
          },
          error:function(e){
            console.log(e);
          }
        });
      }
  </script>
</body>
</html>

