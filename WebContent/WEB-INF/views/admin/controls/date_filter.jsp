<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="flex-grid" style="margin-top: 10px;">
	<div class="row">
		<div class="cell colspan6 align-right padding10">
			<h4>Filtrar por Datas</h4>
		</div>
		<div class="cell colspan2 padding10">
    		<div class="input-control text full-size" data-role="datepicker" data-format="yyyy-mm-dd">
    			<label>Data de Início</label>
				<input type="text" name="inicio" value="${inicio}">
				<button class="button"><span class="mif-calendar"></span></button>
			</div>
		</div>
		<div class="cell colspan2 padding10">
    		<div class="input-control text full-size" data-role="datepicker" data-format="yyyy-mm-dd">
    			<label>Data de Término</label>
				<input type="text" name="termino" value="${termino}">
				<button class="button"><span class="mif-calendar"></span></button>
			</div>
		</div>
		<div class="cell padding10">
			<button class="button primary" style="margin-top: 6px;" type="submit">Filtrar</button>
		</div>
	</div>
</div>