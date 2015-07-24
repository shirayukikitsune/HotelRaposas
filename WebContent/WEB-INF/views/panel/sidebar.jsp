<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="sidebar">
	<li><a href="panel/profile">
		<span class="mif-profile icon"></span>
		<span class="title">Meu Perfil</span>
	</a></li>
	<li><a href="panel/reservations">
		<span class="mif-calendar icon"></span>
		<span class="title">Minhas Reservas</span>
	</a></li>
</ul>

<script>
	// Adiciona a classe "active" ao LI que contiver um A cujo HREF seja para a página atual
	$('.sidebar li a').each(function() {
		if (window.location.href.indexOf($(this).attr('href')) >= 0)
			$(this).parents('li').addClass('active');
	});
</script>
