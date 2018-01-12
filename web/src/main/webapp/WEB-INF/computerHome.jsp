<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset=utf-8>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no,shrink-to-fit=no"/>
  <meta name="theme-color" content="#000000">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/build/favicon.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/build/assets/css/bootstrap.min.css">
  <title>第十四届全国青少年学法用法网上知识竞赛</title>
  <style>
    .subscribe {
      width: 100%;
      height: 100%;
      text-align: center;
      background: #aee644 url("${pageContext.request.contextPath}/build/assets/images/computerBkg.jpg") center top no-repeat;
      background-attachment: fixed;
      background-size: 100%,100%;
      position: relative;
    }
    span{
      font-size:.3rem;
      display:inline-block;
      margin-bottom:0rem;
      font-weight: 600;
    }
    img {
      width: 124px;
      height: 119px;
    }
    .info {
      margin-top: .5rem;
      font-size: .36rem;
      letter-spacing: 1px;
    }
    .msg {
      position: absolute;
      font-size: .32rem;
      bottom: 1rem;
      width: 100%;
    }
  </style>
</head>
<body>
<noscript>
  You need to enable JavaScript to run this app.
</noscript>
<div class="subscribe">
</div>
<script src="${pageContext.request.contextPath}/build/assets/js/rem.js"></script>
</body>
</html>