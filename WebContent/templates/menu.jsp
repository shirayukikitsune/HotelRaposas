<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="js/menu.js" async="async"></script>

<nav class="app-bar" data-role="appbar">
	<div class="container">
		<a href="index.jsp" class="app-bar-element logo">
			<img src="images/logo.png" alt="Hotel das Raposas" style="height: 32px;"/>
			Hotel das Raposas
		</a>
		
		<div class="app-bar-element place-right">
			<c:choose>
				<c:when test="${empty user}">
					<!-- Login Form -->
					<a class="dropdown-toggle fg-white"><span class="mif-enter"></span> Entrar</a>
					<div class="app-bar-drop-container bg-white fg-dark place-right" data-role="dropdown" data-no-close="true">
						<div class="padding20">
							<form action="${pageContext.request.contextPath}/user/login" id="form-menu-login">
								<c:choose>
									<%-- Se está em uma página de erro, então redireciona para a home --%>
									<c:when test="${not empty hasException}">
										<input type="hidden" name="redirect" value="${pageContext.request.contextPath}" />
									</c:when>
									<c:otherwise>
										<input type="hidden" name="redirect" value="${pageContext.request.contextPath}${pageContext.request.servletPath}" />
									</c:otherwise>
								</c:choose>
								<div class="input-control modern text iconic">
									<input type="text" name="login" /> 
									<span class="label">Nome de usuário</span>
									<span class="informer">E-mail ou CPF</span>
									<span class="placeholder">E-mail / CPF</span>
									<span class="icon mif-user"></span>
								</div>
								<div class="input-control modern password iconic" data-role="input">
									<input type="password" name="password" />
									<span class="label">Senha</span>
									<span class="informer">Insira a senha</span>
									<span class="placeholder">Senha</span>
									<span class="icon mif-lock"></span>
									<button class="button helper-button reveal"><span class="mif-looks"></span></button>
								</div>
								<div class="form-actions">
									<button class="button" type="submit">Login</button>
								</div>
							</form>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<!-- User options -->
					<a class="dropdown-toggle fg-white">Olá, ${user.title.description} ${user.name}!</a>
					<ul class="d-menu place-right" data-role="dropdown">
						<li><a href="panel/reservations"><span class="mif-calendar"></span> Minhas Reservas</a></li>
						<li><a href="panel/profile"><span class="mif-user"></span> Meu Perfil</a></li>
						<c:if test="${user.admin eq true}">
						<li class="divider"></li>
						<li><a href="admin"><span class="mif-security"></span> Administração</a>
						<li><a href="admin/reservations"><span class="mif-stack"></span> Reservas</a></li>
						<li><a href="admin/bi"><span class="mif-chart-dots"></span> Indicadores</a></li>
						</c:if>
						<li class="divider"></li>
						<li><a id="app-bar-logout"><span class="mif-exit"></span> Sair</a>
						<form action="${pageContext.request.contextPath}/user/login" id="form-menu-logout">
							<input type="hidden" name="redirect" value="${pageContext.request.contextPath}" />
						</form>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>