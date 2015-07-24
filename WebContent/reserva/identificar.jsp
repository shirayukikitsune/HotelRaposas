<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Identificação</h2>

<div class="grid">
	<div class="row cells2">
		<div class="cell">
			<h4 style="margin-bottom: 1.5em;">Já sou cadastrado</h4>

			<form method="post" action="usuario">
				<input type="hidden" name="acao" value="Login" />
				<input type="hidden" name="redirect" value="/reserva.jsp" />
				<div class="grid">
					<div class="row cells2"> 
						<div class="cell">
							<div class="input-control text full-size">
								<label>E-mail ou CPF</label>
								<span class="mif-user prepend-icon"></span>
								<input type="text" name="login" /> 
							</div>
						</div>
						<div class="cell">&nbsp;</div>
					</div>
					<div class="row cells2"> 
						<div class="cell">
							<div class="input-control password full-size">
								<label>Senha</label>
								<span class="mif-lock prepend-icon"></span>
								<input type="password" name="password" /> 
							</div>
						</div>
						<div class="cell">&nbsp;</div>
					</div>
					<div class="row cells2"> 
						<div class="cell">
							<div class="form-actions">
								<button class="button primary place-right" type="submit">Login</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="cell">
			<h4 style="margin-bottom: 1.5em;">Quero me cadastrar</h4>

			<form method="post" action="cadastro.jsp">
				<div class="grid">
					<div class="row cells2"> 
						<div class="cell">
							<div class="input-control text full-size">
								<label>CPF</label>
								<span class="mif-user prepend-icon"></span>
								<input type="text" name="cpf" /> 
								<button class="button helper-button clear"><span class="mif-cross"></span></button>
							</div>
						</div>
					</div>
					<div class="row cells2"> 
						<div class="cell">
							<div class="input-control text full-size">
								<label>E-mail</label>
								<span class="mif-envelop prepend-icon"></span>
								<input type="text" name="email" /> 
								<button class="button helper-button clear"><span class="mif-cross"></span></button>
							</div>
						</div>
					</div>
					<div class="row cells2"> 
						<div class="cell">
							<div class="form-actions">
								<button class="button primary place-right" type="submit">Cadastrar</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
