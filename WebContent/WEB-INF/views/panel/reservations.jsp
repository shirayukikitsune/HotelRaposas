<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/templates/header.jsp" />

<div class="container page-content">
	<h2>Minhas Reservas</h2>
	<p>Aqui está disponível o histórico das suas reservas.</p>
	<hr/>
	<br/>
	
	<div class="grid">
		<div class="row cells5">
			<div class="cell">
				<jsp:include page="sidebar.jsp" />
			</div>
			
			<div class="cell colspan4">
				<table class="table striped hovered" data-url="${pageContext.request.contextPath}/panel/reservations" id="reservations">
					<thead>
						<tr>
							<th class="sortable-column" data-field="idreserva">Identificador</th>
							<th class="sortable-column sort-desc" data-field="data">Data</th>
							<th class="sortable-column" data-field="situacao">Situação</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
				<div class="margin50" style="margin-left: auto; margin-right: auto;" data-role="preloader" data-type="ring" data-style="dark" id="loader"></div>
			</div>
		</div>
	</div>
</div>

<script src="js/up-reservations.js"></script>

<jsp:include page="/templates/footer.jsp" />
