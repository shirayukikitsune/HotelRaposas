<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<c:set var="req" value="${pageContext.request}" />
		<c:set var="url">${req.requestURL}</c:set>
		<c:set var="uri" value="${req.requestURI}" />
		<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Hotel das Raposas</title>
		<link href="css/metro.min.css" rel="stylesheet" />
		<link href="css/metro-icons.min.css" rel="stylesheet" />
		<link href="css/style.css" rel="stylesheet" />
		<script src="js/jquery-2.1.4.min.js"></script>
		<script src="js/metro.min.js"></script>
    </head>
	<body>
		<jsp:include page="menu.jsp" />
