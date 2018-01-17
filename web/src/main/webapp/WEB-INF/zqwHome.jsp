<%@ page import="com.ani.ccyl.leg.commons.dto.Top20Dto" %>
<%@ page import="com.ani.ccyl.leg.commons.dto.ProvinceInfoDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: sunminggui
  Date: 2018/1/17
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Map<String,Object> map = (Map<String,Object>)request.getAttribute("infoMap");
  List<Top20Dto> topList =  (List<Top20Dto>)map.get("top20");
  List<ProvinceInfoDto> provinceList =  (List<ProvinceInfoDto>)map.get("province");
  List<ProvinceInfoDto> provinceNewList = new ArrayList<ProvinceInfoDto>();
  ProvinceInfoDto noLocation = null;
  for(ProvinceInfoDto infoDto : provinceList){
    if (!"未定位".equals(infoDto.getProvince())){
      provinceNewList.add(infoDto);
    }else{
      noLocation = infoDto;
    }
  }
  if(noLocation!=null){
    provinceNewList.add(noLocation);
  }
%>

<html>
<head>
  <meta charset=utf-8>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no,shrink-to-fit=no"/>
  <meta name="theme-color" content="#000000">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/build/assets/images/favicon.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/build/assets/css/bootstrap.min.css">
  <title>第十四届全国青少年学法用法网上知识竞赛</title>
  <style>
    .bkg{
      height:8rem;
      width:100%;
      background-color: #f1f9fc;
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
      font-size: .2rem;
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
      background-color: #fff;
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
      width:50%;
      float:left;
      font-size:.15rem;
      margin-left:.15rem;
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
      background-color: #fff;
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
  </style>
<head>
  <title></title>
</head>
<body>
<div class="bkg">
  <div class="paihangbang">
    <div class="paihangbang1">
      <div class="phb1-title"></div>
      <div class="phb1-detail" id="top20Rank">
        <div class='clearfix info'>
          <div class='first'>
            得分
          </div>
          <div class='second'>
            全国排名
          </div>
        </div>
        <%
          for(int i=0;i<topList.size();i++){
        %>
        <div class='clearfix top-ranking'>
          <div class='pull-left rank-first'>
            <img src='<%=topList.get(i).getPortrat()%>' alt=''/>
          </div>
          <div class='pull-left rank-second'>
            <div><%=topList.get(i).getScore()%></div>
            <div><%=topList.get(i).getName()%></div>
          </div>
          <div class='pull-right rank-third'><%=i+1%></div>
        </div>
        <%
          }
        %>
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
        <%
          for(int j=0;j<provinceNewList.size();j++){
        %>
        <div class='clearfix top-ranking'>
          <div class='rank-first'><%=j+1%></div>
          <div class='rank-second'><%=provinceNewList.get(j).getProvince()%></div>
          <div class='rank-third'><%=provinceNewList.get(j).getAverageScore()%></div>
          <div class='rank-four'><%=provinceNewList.get(j).getTotalScore()%></div>
          <div class='rank-five'><%=provinceNewList.get(j).getPeopleNumber()%></div>
        </div>
        <%
          }
        %>
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/build/assets/js/rem.js"></script>
<script src="${pageContext.request.contextPath}/build/assets/js/jquery-3.1.0.min.js"></script>
</body>
</html>

