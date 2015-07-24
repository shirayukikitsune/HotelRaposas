<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="admin_header.jsp" />

<h1 class="text-light">Lista de Usuários</h1>
<hr class="thin bg-grayLighter" />

<div id="usuarios">
	<table class="table striped hovered">
		<thead>
			<tr>
				<th class="sortable-column" data-field="cpf">CPF</th>
				<th class="sortable-column" data-field="nome,sobrenome">Nome</th>
				<th class="sortable-column" data-field="email">E-mail</th>
				<th class="sortable-column sort-desc" data-field="create_time">Data de Cadastro</th>
				<th class="sortable-column" data-field="ativo">Ativo</th>
				<th class="sortable-column" data-field="admin">Administrador</th>
				<th>Ações</th>
			</tr>
			<!-- Template utilizada para os dados recebidos por AJAX -->
			<tr style="display: none" data-role="template">
				<!-- usar "data-receive='var'" na tag que vai receber, em texto o valor da variavel 'var' de cada dado na lista, no javascript -->
				<td data-receive="cpf"></td>
				<td data-receive="fullName"></td>
				<td data-receive="email"></td>
				<!-- usar "data-type='...'" em uma tag com "data-receive" para formatar o dado de uma maneira mais amigável -->
				<td data-receive="createTime" data-type="date"></td>
				<td data-receive="active" data-type="boolean"></td>
				<td data-receive="admin" data-type="boolean"></td>
				<td>
					<a><span class="mif-user-minus" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Desativar|Bloqueia o acesso deste usuário"></span></a>
					<a><span class="mif-user-check" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Ativar|Libera o acesso deste usuário"></span></a>
					<a><span class="mif-arrow-up-right" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Promover|Promove o usuário para administrador"></span></a>
					<a><span class="mif-arrow-down-right" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Revogar|Revoga a administração para o usuário"></span></a>
				</td>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	
	<div class="pagination" style="margin-left: auto; margin-right: auto;">
		<span class="item" data-page="first" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Primeira|Navega para a primeira página">&laquo;</span>
		<span class="item" data-page="prev" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Anterior|Navega para a página anterior">&lt;</span>
		
		<span class="item" data-page="next" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Próxima|Navega para a próxima página">&gt;</span>
		<span class="item" data-page="last" data-role="hint" data-hint-background="bg-blue" data-hint-color="fg-white" data-hint-mode="2" data-hint="Primeira|Navega para a última página">&raquo;</span>
	</div>
</div>
<div class="margin50" style="margin-left: auto; margin-right: auto;" data-role="preloader" data-type="ring" data-style="dark" id="loader"></div>

<script>
var userCount = ${userCount};
</script>
<script src="js/admin-users.js"></script>

<jsp:include page="admin_footer.jsp" />
