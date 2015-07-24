<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.tab {
	display:none;
}
.tab.active {
	display: block;
}
</style>

<h2>Pagamento</h2>

<p>O pagamento é facilitado. Aceitamos cartões de crédito e débito, nacionais e internacionais, além de boleto bancário.</p>

<p>Por favor, selecione sua forma de pagamento abaixo:</p>

<form method="post" action="">
	<div class="grid">
		<div class="row cells4">
			<div class="cell">
				<ul class="v-menu">
					<li class="active"><a data-function="tabs" data-target="#area-cartao"><span class="mif-credit-card icon"></span> Cartão de crédito/Débito</a>
					<li><a data-function="tabs" data-target="#area-boleto"><span class="mif-barcode icon"></span> Boleto Bancário</a>
				</ul>
			</div>
			<div class="cell colspan3">
				<div class="tab active" id="area-cartao">
					<h3>Cartão de Crédito/Débito</h3>
				</div>
				<div class="tab" id="area-boleto">
					<h3>Boleto Bancário</h3>
				</div>
			</div>
		</div>
		
		<div class="row cells1">
			<div class="cell">
				<button type="submit" class="button primary place-right">Pagar</button>
			</div>
		</div>
	</div>
</form>

<script>
	$('a[data-function=tabs]').on('click', function(e) {
		e.preventDefault();
		
		$('.tab,.v-menu li').removeClass('active');
		$($(this).data('target')).addClass('active');
		$(this).parent().addClass('active');
	});
</script>
