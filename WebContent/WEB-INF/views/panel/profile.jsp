<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="br.com.hoteldasraposas.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="titleDao" class="br.com.hoteldasraposas.dao.UserTitleDAO" />
<c:set var="titleList" value="${titleDao.list}" />
<jsp:include page="/templates/header.jsp" />

<div class="container page-content">
	<h2>Meu Perfil</h2>
	<p>Aqui estão disponíveis as informações sobre o seu perfil</p>
	<hr/>
	<br/>
	
	<div class="grid">
		<div class="row cells5">
			<div class="cell">
				<jsp:include page="sidebar.jsp" />
			</div>
			
			<div class="cell colspan4">
				<div class="grid">
					<form id="update-form" action="${pageContext.request.contextPath}/user/update" method="post">
						<div class="row cells12">
							<div class="cell colspan2">
								<div class="input-control select full-size">
									<label>Título</label>
									<select name="userTitleId">
										<option value="0" ${user.userTitleId eq 0 or empty user.userTitleId ? "selected" : ""}>Nenhum</option>
										<c:forEach items="${titleList}" var="title">
										<option value="${title.id}" ${user.userTitleId eq title.id ? "selected" : ""}>${title.description}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="cell colspan4">
								<div class="input-control text full-size">
									<label>Nome</label>
									<input type="text" name="name" value="${user.name}" data-validate-func="minlength" data-validate-arg="3" data-validate-hint="Mínimo de 3 caracteres!" /> 
									<span class="input-state-error mif-warning"></span>
									<span class="input-state-success mif-checkmark"></span>
								</div>
							</div>
							<div class="cell colspan6">
								<div class="input-control text full-size">
									<label>Sobrenome</label>
									<input type="text" name="familyName" value="${user.familyName}" data-validate-func="minlength" data-validate-arg="5" data-validate-hint="Mínimo de 5 caracteres!" /> 
									<span class="input-state-error mif-warning"></span>
									<span class="input-state-success mif-checkmark"></span>
								</div>
							</div>
						</div>
						<div class="row cells2">
							<div class="cell">
								<div class="input-control text full-size">
									<label>E-mail</label>
									<input type="text" value="${user.email}" name="email" data-validate-func="email" data-validate-hint="Coloque um e-mail válido!" /> 
									<span class="input-state-error mif-warning"></span>
									<span class="input-state-success mif-checkmark"></span>
								</div>
							</div>
							<div class="cell">&nbsp;</div>
						</div>
						<div class="row cells2">
							<div class="cell">
								<div class="input-control password full-size">
									<label>Senha</label>
									<input type="password" id="password" name="plainPassword" data-validate-func="minlength" data-validate-arg="6" /> 
								</div>
							</div>
							<div class="cell">
								<div class="input-control password full-size">
									<label>Confirmação da senha</label>
									<input type="password" id="password2" name="password2" data-validate-func="minlength" data-validate-arg="6" /> 
								</div>
							</div>
						</div>
						<div class="row cells1">
							<button type="submit" id="update-button" class="button primary place-right">Atualizar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="dialog" data-role="dialog" data-type="alert" data-overlay="true" data-close-button="true" data-windows-style="true">
	<div class="container">
		<h1>Titulo</h1>
		<p>Mensagem</p>
	</div>
</div>

<script>
$(function() {
	$('#update-button').on('click', function (e) {
		e.preventDefault();
		
		var valid = document.getElementById("password").value === document.getElementById("password2").value;
		if (!valid)
			alert("As senhas não conferem.");
		else {
			var form = $('#update-form');
			$.post(form.attr('action'), form.serialize(), function (response) {
				var dialog = $('#dialog').removeClass('success').addClass('alert');
				dialog.find('h1').text('Erro');
				
				switch (response.status) {
				case 0:
					dialog.removeClass('alert').addClass('success');
					dialog.find('h1').text('Sucesso');
					dialog.find('p').text("Atualização efetuada com sucesso.");
					break;
				case 1:
					dialog.find('p').text('O seu nome deve conter no mínimo 3 caracteres.');
					break;
				case 2:
					dialog.find('p').text('O e-mail está em branco.');
					break;
				case 3:
					dialog.find('p').text('O e-mail desejado já está em uso.');
					break;
				}
				
				dialog.data('dialog').open();
			}, 'json');
		}
	});
});
</script>

<jsp:include page="/templates/footer.jsp" />
