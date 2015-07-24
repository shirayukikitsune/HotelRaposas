<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="templates/header.jsp" />

<div class="container page-content">
	<c:choose>
		<c:when test="${empty sessionScope.reservaTipoQuarto}">
			<c:set scope="page" var="step" value="1" />
			<c:set scope="page" var="thePage" value="reserva/selecionar.jsp" />
		</c:when>
		<c:when test="${empty sessionScope.usuario}">
			<c:set scope="page" var="step" value="2" />
			<c:set scope="page" var="thePage" value="reserva/identificar.jsp" />
		</c:when>
		<c:when test="${empty sessionScope.reservaConfirmacao}">
			<c:set scope="page" var="step" value="3" />
			<c:set scope="page" var="thePage" value="reserva/confirmacao.jsp" />
		</c:when>
		<c:when test="${empty sessionScope.reserva}">
			<c:set scope="page" var="step" value="4" />
			<c:set scope="page" var="thePage" value="reserva/pagamento.jsp" />
		</c:when>
		<c:otherwise>
			<c:redirect url="index.jsp" />
		</c:otherwise>
	</c:choose>
	
	<div class="stepper" data-role="stepper" data-steps="5" data-start="${step}" data-clickable="false"></div>
	
	<jsp:include page="${thePage}" />
</div>

<c:if test="${not empty requestScope.msgSucesso}">
	<div data-role="dialog" data-type="success" data-close-button="true" data-windows-style="true" id="success-dialog">
		<div class="container">
			<h1>Sucesso</h1>
			<p>
				<c:choose>
					<c:when test="${requestScope.msgSucesso eq 1}">
						Seu cadastro foi realizado com sucesso!<br/>
					</c:when>
				</c:choose>
			</p>
		</div>
	</div>
	
	<script>
	$(function () {
		$('#success-dialog').data('dialog').open();
	});
	</script>
</c:if>

<c:if test="${not empty requestScope.msgErro}">
	<div data-role="dialog" data-type="alert" data-close-button="true" data-windows-style="true" id="alert-dialog">
		<div class="container">
			<h1>Erro</h1>
			<p>
				<c:choose>
					<c:when test="${requestScope.msgErro eq 1}">
						Credenciais inválidas.<br/>
						Por favor, insira uma combinação de nome de usuário ou e-mail e senha válida.
					</c:when>
					<c:when test="${requestScope.msgErro eq 2}">
						Datas inválidas.<br/>
						Por favor, insira datas de entrada e saída válidas.
					</c:when>
					<c:when test="${requestScope.msgErro eq 3}">
						Datas inválidas.<br/>
						A data de saída deve ser superior à data de entrada.
					</c:when>
					<c:when test="${requestScope.msgErro eq 3}">
						Lotado.<br/>
						Desculpe, estamos sem quartos disponíveis para as datas selecionadas <em>:(</em>
					</c:when>
				</c:choose>
			</p>
		</div>
	</div>
	
	<script>
	$(function () {
		$('#alert-dialog').data('dialog').open();
	});
	</script>
</c:if>

<jsp:include page="templates/footer.jsp" />
