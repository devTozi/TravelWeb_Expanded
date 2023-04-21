<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>TravelWeb</title>
		<meta name="description" content="Grid Loading and Hover Effect: Recreating Samsung's grid loading effect" />
		<meta name="keywords" content="grid loading, swipe, effect, slide, masonry, web design, tutorial" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component.css" />
		<script src="js/modernizr.custom.js"></script>
	</head>
	<body>
		<div class="container">
			
			<header class="codrops-header">
				<a href="${pageContext.request.contextPath}/index.board"><h1>TRAVELER<span>여행사진을 공유해보세요</span></h1></a>
				<nav class="codrops-demos">
				<a class="current-demo" href="${pageContext.request.contextPath}/index.board">&#128153;시작하기</a><br><br><br>
				
				<img src="${pageContext.request.contextPath}/img/20190709_180300.jpg" alt="dummy" style="width:314px; height:380px;">
				<img src="${pageContext.request.contextPath}/img/20190716_135435.jpg" alt="dummy" style="width:314px; height:380px;">
				<img src="${pageContext.request.contextPath}/img/20190716_205122.jpg" alt="dummy" style="width:314px; height:380px;">
				<img src="${pageContext.request.contextPath}/img/20190727_180831.jpg" alt="dummy" style="width:314px; height:380px;">
				
				</nav>
			</header>
<%@ include file="../include/footer.jsp"%>


