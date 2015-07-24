<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../admin_header.jsp" />

<h1 class="text-light">Indicadores</h1>
<hr class="thin bg-grayLighter" />
<form id="filters" method="get">
<jsp:include page="../controls/date_filter.jsp" />
</form>
<hr class="thin bg-grayLighter" />

<div class="flex-grid">
	<div class="row">
		<div class="cell" style="flex: 0 0 320px;">
			<div class="tile-container" data-level="0">
				<div class="tile-wide bg-darkCobalt fg-white" data-role="tile" data-function="changepage" data-arg="reservations">
					<div class="tile-content iconic">
						<span class="icon mif-stack"></span>
						<span class="tile-label">Reservas</span>
					</div>
				</div>
				
				<div class="tile-wide bg-darkTeal fg-white" data-role="tile" data-function="changepage" data-arg="rooms">
					<div class="tile-content iconic">
						<span class="icon mif-hotel"></span>
						<span class="tile-label">Quartos</span>
					</div>
				</div>
			</div>
		</div>
		
		<div id="bi-container" class="cell auto-size">
			<div class="margin50 align-center">
				<h4>Selecione uma seção à esquerda para exibir os gráficos</h4>
			</div>
		
			<div class="margin50" style="margin-left: auto; margin-right: auto; width: 40px; display: none" id="loader">
				<div data-role="preloader" data-type="ring" data-style="dark"></div>
				<br/>
				<h6><small>Carregando</small></h6>
			</div>
		
			<div data-function="receiver" data-for="reservations" style="display: none">
				<div class="flex-grid">
					<div class="row">
						<div class="cell colspan12" id="chart-reservation-history" style="height: 400px"></div>
					</div>
					
					<div class="row">
						<div class="cell colspan6" id="chart-reservation-situation" style="height: 300px"></div>
					</div>
				</div>
			</div>
		</div>  
	</div>
</div>

<script src="js/highcharts/highcharts.js"></script>
<script src="js/admin-bi.js"></script>
<script>
$(function () {
	var filtersForm = $('#filters');
	var loader = $('#loader');
	var container = $('#bi-container');
	
	var getData = function(what) {
		var filtros = {};
		if (filtersForm.find('input[name=inicio]').val().length > 0)
			filtros.inicio = filtersForm.find('input[name=inicio]').val();
		if (filtersForm.find('input[name=termino]').val().length > 0)
			filtros.termino = filtersForm.find('input[name=termino]').val();
		
		container.children('div').hide();
		loader.show();
		
		$.post('admin/bi/' + what, filtros, function (response) {
			loader.hide();
			container.children('[data-for='+what+']').show();
			if (what === 'reservations') {
				buildReservations(response);
			}
		}, 'json');
	};
	
	filtersForm.find('button[type=submit]').on('click', function (e) {
		e.preventDefault();
		e.stopPropagation();
		
		if (location.hash.length > 1) {
			getData(location.hash.substr(1));
		}
		
		return false;
	});
	
	$('div[data-function=changepage]').on('click', function() {
		getData($(this).data('arg'));
		location.hash = $(this).data('arg');
	});
	
	if (location.hash.length > 1) {
		getData(location.hash.substr(1));
	}
});
</script>

<jsp:include page="../admin_footer.jsp" />
