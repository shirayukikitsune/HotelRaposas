<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="templates/header.jsp" />
<jsp:useBean id="titleDao" class="br.com.hoteldasraposas.dao.UserTitleDAO" />
<c:set var="listaTitulos" value="${titleDao.list}" />

<script src="js/select2.min.js"></script>

<div class="container page-content">
	<h1>Cadastro</h1>
	
	<p>Para reservar um quarto, é necessário possuir um cadastro.</p>
	<p style="margin-bottom: 2rem;">Todos os dados abaixo são necessários.</p>
	
	<c:if test="${not empty requestScope.erroCadastro}">
		<div class="panel alert">
			<div class="heading">
				<span class="icon mif-notification"></span>
				<span class="title">Erro</span>
			</div>
			<div class="content">
				<p>Ocorreu o seguinte erro ao tentar concluir seu registro:</p>
				<c:choose>
					<c:when test="${requestScope.erroCadastro == 1}">
						<p>O nome está em branco</p>
					</c:when>
					<c:when test="${requestScope.erroCadastro == 2}">
						<p>O nome de usuário está em branco</p>
					</c:when>
					<c:when test="${requestScope.erroCadastro == 3}">
						<p>O nome de usuário está em uso</p>
					</c:when>
					<c:when test="${requestScope.erroCadastro == 4}">
						<p>A senha é muito curta</p>
					</c:when>
					<c:when test="${requestScope.erroCadastro == 5}">
						<p>CPF inválido</p>
					</c:when>
					<c:when test="${requestScope.erroCadastro == 6}">
						<p>Já existe um cadastro para este CPF</p>
					</c:when>
				</c:choose>
			</div>
		</div>
	</c:if>
	
	<form method="post" action="usuario" onsubmit="return validaSenha();" name="cadastro" data-role="validator">
		<input type="hidden" name="acao" value="Cadastro" />
		<div class="grid">
			<div class="row cells12">
				<div class="cell colspan2">
					<div class="input-control select full-size">
						<label>Título</label>
						<select name="titulo">
							<option value="0">Nenhum</option>
							<c:forEach items="${listaTitulos}" var="titulo">
							<option value="${titulo.id}">${titulo.descricao}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="cell colspan4">
					<div class="input-control text full-size">
						<label>Nome</label>
						<input type="text" name="nome" data-validate-func="minlength" data-validate-arg="3" data-validate-hint="Mínimo de 10 caracteres!" /> 
						<span class="input-state-error mif-warning"></span>
						<span class="input-state-success mif-checkmark"></span>
					</div>
				</div>
				<div class="cell colspan6">
					<div class="input-control text full-size">
						<label>Sobrenome</label>
						<input type="text" name="sobrenome" data-validate-func="minlength" data-validate-arg="5" data-validate-hint="Mínimo de 10 caracteres!" /> 
						<span class="input-state-error mif-warning"></span>
						<span class="input-state-success mif-checkmark"></span>
					</div>
				</div>
			</div>
			<div class="row cells2">
				<div class="cell">
					<div class="input-control text full-size">
						<label>CPF</label>
						<input type="text" value="${empty requestScope.cpf ? param.cpf : requestScope.cpf}" name="cpf" data-validate-func="pattern" data-validate-arg="^\d{3}\.?\d{3}\.?\d{3}\-?\d{2}$" data-validate-hint="Insira um CPF válido!" /> 
						<span class="input-state-error mif-warning"></span>
						<span class="input-state-success mif-checkmark"></span>
					</div>
				</div>
				<div class="cell">
					<div class="input-control text full-size">
						<label>E-mail</label>
						<input type="text" value="${empty requestScope.email ? param.email : requestScope.email}" name="email" data-validate-func="email" data-validate-hint="Coloque um e-mail válido!" /> 
						<span class="input-state-error mif-warning"></span>
						<span class="input-state-success mif-checkmark"></span>
					</div>
				</div>
			</div>
			<div class="row cells2">
				<div class="cell">
					<div class="input-control password full-size">
						<label>Senha</label>
						<input type="password" id="password" name="password" data-validate-func="minlength" data-validate-arg="6" /> 
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
				<button type="submit" class="button primary" onclick="return validaSenha();">Cadastrar</button>
			</div>
		</div>
	</form>
</div>

<script>
validaSenha = function() {
	var valid = document.getElementById("password").value === document.getElementById("password2").value; 
	return valid; 
};
</script>

<jsp:include page="templates/footer.jsp" />
