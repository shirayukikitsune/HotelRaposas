<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isErrorPage="true" import="java.io.PrintWriter" import="java.io.StringWriter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set scope="request" var="hasException" value="true" />
<jsp:include page="templates/header.jsp" />

<div class="page-content container">
	<h2>Erro</h2>
	
	<p>Um erro sem tratamento ocorreu. Por favor, volte à pagina inicial e tente novamente.</p>
	<p>Se continuar ocorrendo, entre em contato com o nosso suporte, informando todo o texto abaixo e o procedimento que gerou o erro.</p>
	
	<h4>Informações de Desenvolvedor</h4>
	<p>Mensagem: <%= exception.getLocalizedMessage() %></p>
	<p>Pilha de execução:</p>
	<pre>
	<%
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		exception.printStackTrace(printWriter);
		out.println(stringWriter);
		printWriter.close();
		stringWriter.close();
	%>
	</pre>
</div>

<jsp:include page="templates/footer.jsp" />
