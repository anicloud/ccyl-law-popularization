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
      if(infoDto.getProvince().contains("中华人民共和国")&&infoDto.getProvince().length()>7){
        String province = infoDto.getProvince().substring(7);
        infoDto.setProvince(province);
      }
      if(infoDto.getProvince().contains("广西省广西壮族自治区")){
        String province = infoDto.getProvince().substring(3);
        infoDto.setProvince(province);
      }
      provinceNewList.add(infoDto);
    }else{
      noLocation = infoDto;
    }
  }
  if(noLocation!=null){
    provinceNewList.add(noLocation);
  }
%>
<!DOCTYPE html>
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
      background-color: #fff;
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
      background-color: #45caef;
    }
    .phb1-title{
      background:#45caef url("${pageContext.request.contextPath}/build/assets/images/glory_title.png") bottom center no-repeat;
      height:15%;
      margin-left:.4rem;
      border-radius:5px;
    }
    .phb1-detail{
      overflow:auto;
      height:85%;
      width:100%;
      border-radius:5px;
    }
    .phb1-detail .info {
      background-color: #fff;
      color: #000000;
      line-height: .64rem;
      margin-top: .2rem;
      letter-spacing: .02rem;
      font-size: .26rem;
      border-radius: .08rem;
      margin-left:.2rem;
      margin-right:.2rem;
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
      font-size: .2rem;
    }

    .phb1-detail .top-ranking{
      margin-top: .2rem;
      margin-bottom: .2rem;
      margin-left:.2rem;
      margin-right:.2rem;
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
      background-color: #45caef;
      float:left;
    }
    .phb2-title{
      background:#45caef url("${pageContext.request.contextPath}/build/assets/images/national_rank.png") bottom center no-repeat;
      height:15%;
      margin-left:.4rem;
      border-radius:5px;
    }
    .phb2-detail{
      overflow:auto;
      height:85%;
      width:100%;
      border-radius:5px;
    }

    .phb2-detail .info {
      background-color: #fff;
      color: #000000;
      line-height: .64rem;
      margin-top: .2rem;
      letter-spacing: .02rem;
      font-size: .26rem;
      border-radius: .08rem;
      margin-left:.2rem;
      margin-right:.2rem;
    }
    .phb2-detail .info .first{
      float:left;
      width:12.5%;
      text-align:center;
      font-size: .2rem;
    }
    .phb2-detail .info .second {
      float:left;
      width:24.5%;
      text-align:center;
      font-size: .2rem;
    }
    .phb2-detail .info .third {
      float:left;
      width:21%;
      text-align:center;
      font-size: .2rem;
    }
    .phb2-detail .info .four {
      float:left;
      width:21%;
      text-align:center;
      font-size: .2rem;
    }
    .phb2-detail .info .five {
      float:left;
      width:21%;
      text-align:center;
      font-size: .2rem;
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
      width:24.5%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }
    .phb2-detail .top-ranking .rank-third{
      font-size:.2rem;
      float:left;
      width:21%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }
    .phb2-detail .top-ranking .rank-four{
      font-size:.2rem;
      float:left;
      width:21%;
      line-height: .7rem;
      vertical-align: middle;
      text-align:center;
    }

    .phb2-detail .top-ranking .rank-five{
      font-size:.2rem;
      float:left;
      width:21%;
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
          <div style="float:left;width:40%;text-align:center;font-size: .2rem;">
            得分
          </div>
          <div style="float:right;width:30%;text-align:center;font-size: .2rem;">
            全国排名
          </div>
        </div>
        <%
          for(int i=0;i<topList.size();i++){
        %>
        <div class='clearfix top-ranking'>
          <div style="width:15%;float:left;text-align:center;">
            <img src='<%=topList.get(i).getPortrat()%>' style="padding-top:.01rem;width: .68rem;height:.68rem;border-radius: 50%;" alt=''/>
          </div>
          <div style="padding-top:.15rem;width:50%;float:left;font-size:.15rem;margin-left:.15rem;text-align:left;">
            <div><%=topList.get(i).getScore()%></div>
            <div><%=topList.get(i).getName()%></div>
          </div>
          <div style="padding-top:.1rem;font-size: .3rem;width:30%;text-align:center;font-weight: bold;float:right;"><%=i+1%></div>
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
          <div style="float:left;width:12.5%;text-align:center;font-size: .2rem;">
            排名
          </div>
          <div style="float:left;width:24.5%;text-align:center;font-size: .2rem;">
            省份
          </div>
          <div style="float:left;width:21%;text-align:center;font-size: .2rem;">
            平均得分
          </div>
          <div style="float:left;width:21%;text-align:center;font-size: .2rem;">
            总得分
          </div>
          <div style="float:left;width:21%;text-align:center;font-size: .2rem;">
            参赛人数
          </div>
        </div>
        <%
          for(int j=0;j<provinceNewList.size();j++){
        %>
        <div class='clearfix top-ranking'>
          <div style="font-size:.3rem;float:left;width:12.5%;line-height: .7rem;vertical-align: middle;text-align:center;"><%=j+1%></div>
          <div style="font-size:.2rem;float:left;width:24.5%;line-height: .7rem;vertical-align: middle;text-align:center;"><%=provinceNewList.get(j).getProvince()%></div>
          <div style="font-size:.2rem;float:left;width:21%;line-height: .7rem;vertical-align: middle;text-align:center;"><%=provinceNewList.get(j).getAverageScore()%></div>
          <div style="font-size:.2rem;float:left;width:21%;line-height: .7rem;vertical-align: middle;text-align:center;"><%=provinceNewList.get(j).getTotalScore()%></div>
          <div style="font-size:.2rem;float:left;width:21%;line-height: .7rem;vertical-align: middle;text-align:center;"><%=provinceNewList.get(j).getPeopleNumber()%></div>
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

