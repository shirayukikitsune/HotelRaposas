<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/templates/header.jsp" />

<div class="flex-grid no-responsive-future" style="height: 100%">
	<div class="row" style="height: 100%">
		<div id="cell-sidebar" class="cell size-x200" style="background-color: #71b1d1; height: 100%">
			<ul class="sidebar">
				<li><a href="admin/home">
					<span class="mif-security icon"></span>
					<span class="title">Início</span>
				</a></li>
				<li><a href="admin/users">
					<span class="mif-users icon"></span>
					<span class="title">Usuários</span>
					<span class="counter">${userCount}</span>
				</a></li>
				<li><a href="admin/reservations">
					<span class="mif-stack icon"></span>
					<span class="title">Reservas</span>
				</a></li>
				<li><a href="admin/bi">
					<span class="mif-chart-dots icon"></span>
					<span class="title">Indicadores</span>
				</a></li>
			</ul>
		</div>
		<div class="cell padding20 auto-size bg-white">
		
<script>
	// Adiciona a classe "active" ao LI que contiver um A cujo HREF seja para a página atual
	$('.sidebar li a').each(function() {
		if (window.location.href.indexOf($(this).attr('href')) >= 0)
			$(this).parents('li').addClass('active');
	});
</script>
		