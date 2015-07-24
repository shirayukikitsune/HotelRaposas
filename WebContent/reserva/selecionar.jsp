<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${sucesso eq true and not empty lista}">
		<h2>Selecione um Quarto</h2>
		
		<form method="post" action="reserva" id="form-reserva">
			<input type="hidden" name="acao" value="SelecionarQuarto" />
			<input type="hidden" id="tipoid" name="tipo" value="" />
	
			<div class="accordion" data-role="accordion" data-close-any="true">
				<c:forEach items="${lista}" var="disponivel">
					<div class="frame" data-tipoid="${disponivel.tipo.id}">
						<div class="heading">${disponivel.tipo.titulo}</div>
						<div class="content">
							<p>${disponivel.tipo.descricao}</p>
							<h4 class="align-right">
								Preço: <small><fmt:formatNumber value="${disponivel.tipo.diaria}" type="currency" currencySymbol="R$" /></small><br/><br/>
								<button class="button primary" type="submit">Reservar</button>
							</h4>
						</div>
					</div>
				</c:forEach>
			</div>
		</form>
		
		<script>
			$(function() {
				$('#form-reserva').on('submit', function (e) {
					if ($('.accordion .frame.active').length != 1)
						return false;
		
					$('#tipoid').val($('.accordion .frame.active').data('tipoid'));
				});
			});
		</script>
	</c:when>
	<c:otherwise>
		<jsp:useBean id="now" class="java.util.Date" scope="request" />

		<h2>Selecione as Datas</h2>

		<form method="post" action="reserva">
			<input type="hidden" name="acao" value="Disponibilidade" />
			<div class="grid">
				<div class="row cells3 no-margin">
					<div class="cell">
						<h5>Data de Entrada</h5>
					</div>
					<div class="cell">
						<h5>Data de Saída</h5>
					</div>
					<div class="cell">&nbsp;</div>
				</div>
				<div class="row cells3 no-margin">
					<div class="cell">
			    		<div class="input-control text" data-role="datepicker" data-format="yyyy-mm-dd" data-min-date='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />'>
							<input type="text" name="entrada">
							<button class="button"><span class="mif-calendar"></span></button>
						</div>
					</div>
					<div class="cell">
			    		<div class="input-control text" data-role="datepicker" data-format="yyyy-mm-dd" data-min-date='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />'>
							<input type="text" name="saida">
							<button class="button"><span class="mif-calendar"></span></button>
						</div>
					</div>
					<div class="cell">
						<button class="button primary place-right">Reservar!</button>
					</div>
				</div>
			</div>
		</form>
	</c:otherwise>
</c:choose>
