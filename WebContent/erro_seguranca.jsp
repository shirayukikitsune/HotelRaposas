<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isErrorPage="true" import="java.io.PrintWriter" import="java.io.StringWriter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set scope="request" var="hasException" value="true" />
<jsp:include page="templates/header.jsp" />

<div class="page-content container">
	<h2>Erro</h2>
	
	<p>Você não possui permissão para acessar esta página.</p>
	
	<p>Mensagem: <%= exception.getLocalizedMessage() %></p>
</div>

<jsp:include page="templates/footer.jsp" />
