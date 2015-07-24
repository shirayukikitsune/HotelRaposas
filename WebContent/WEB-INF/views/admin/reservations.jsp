<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="admin_header.jsp" />

<h1 class="text-light">Lista de Reservas de Quartos</h1>
<hr class="thin bg-grayLighter" />

<form method="get">
	<jsp:include page="controls/date_filter.jsp" />
</form>
<hr class="thin bg-grayLighter" />

<table class="table striped hovered">
	<thead>
		<tr>
			<th>ID Reserva</th>
			<th>Situação da Reserva</th>
			<th>Nº Quarto</th>
			<th>Tipo do Quarto</th>
			<th>Data da Reserva</th>
			<th>Data de Entrada</th>
			<th>Data de Saída</th>
			<th>Valor da Diária</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="reservaQuarto" items="${lista}">
		<tr data-id="${reservaQuarto.reservationId}">
			<td>${reservaQuarto.reservationId}</td>
			<td>${reservaQuarto.reservation.situation.description}</td>
			<td>${reservaQuarto.roomId}</td>
			<td>${reservaQuarto.room.type.title}</td>
			<td>${reservaQuarto.reservation.date}</td>
			<td>${reservaQuarto.start}</td>
			<td>${reservaQuarto.end}</td>
			<td>${reservaQuarto.dailyPrice}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<div data-role="dialog" id="details-dialog" data-overlay="true" data-close-button="true">
	<h4>Reserva #<span data-receive="reservationId"></span></h4>
	<div class="flex-grid">
		<div class="row">
			<div class="cell colspan6">
				<strong>Situação: </strong>
			</div>
		</div>
	</div>
</div>

<script>
$(function() {
	$('table tbody tr').on('click', function(e) {
		// Quando clicar em uma linha da tabela, mostra uma popup com os detalhes da reserva
		$('')
	});
});
</script>

<jsp:include page="admin_footer.jsp" />
