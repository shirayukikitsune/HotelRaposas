<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Confirme sua Reserva</h2>

<h3 class="margin20 no-margin-left">Tipo do Quarto: <small>${sessionScope.reservaTipoQuarto.titulo}</small></h3>
<h3 class="margin20 no-margin-left">Preço da Diária: <small><fmt:formatNumber value="${sessionScope.reservaTipoQuarto.diaria}" type="currency" currencySymbol="R$" /></small></h3>

<div class="place-right">
	<form method="post" action="reserva" id="form-cancelar">
		<input type="hidden" name="acao" value="SelecionarQuarto" />
		<input type="hidden" name="tipo" value="0" />
	</form>

	<form method="post" action="reserva" id="form-confirmar">
		<input type="hidden" name="acao" value="ConfirmarQuarto" />
	</form>

	<button class="button danger" type="button" id="btn-cancelar">Cancelar</button>
	<button class="button primary" type="button" id="btn-confirmar">Confirmar</button>
</div>

<script>
	$(function() {
		$('#btn-cancelar').click(function() {
			$('#form-cancelar').submit();
		});
		$('#btn-confirmar').click(function() {
			$('#form-confirmar').submit();
		});
	});
</script>
