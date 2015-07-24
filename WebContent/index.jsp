<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/templates/header.jsp" />
<jsp:useBean id="now" class="java.util.Date" scope="request" />

<div class="page-content make-relative">
	<div class="carousel home-carousel" data-role="carousel" data-height="480">
		<div class="slide" style="background-image: url('images/fox-01.jpg'); background-position: center center;"></div>
		<div class="slide" style="background-image: url('images/fox-02.jpg'); background-position: center center;"></div>
		<div class="slide" style="background-image: url('images/fox-03.jpg'); background-position: center center;"></div>
	</div>
	
	<div class="box-reservation">
		<div class="container">
			<div class="panel" data-role="panel">
				<div class="heading">
					<span class="icon mif-home"></span>
					<span class="title">Faça sua reserva</span>
				</div>
				<div class="content">
					<form method="post" action="reserva">
						<input type="hidden" name="acao" value="Disponibilidade" />
						<div class="grid">
							<div class="row cells2">
								<div class="cell">
									<h5>Data de Entrada</h5>
						    		<div class="input-control text full-size" data-role="datepicker" data-format="yyyy-mm-dd" data-min-date='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />'>
										<input type="text" name="entrada">
										<button class="button"><span class="mif-calendar"></span></button>
									</div>
								</div>
								<div class="cell">
									<h5>Data de Saída</h5>
						    		<div class="input-control text full-size" data-role="datepicker" data-format="yyyy-mm-dd" data-min-date='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />'>
										<input type="text" name="saida">
										<button class="button"><span class="mif-calendar"></span></button>
									</div>
								</div>
							</div>
							<div class="row cells1">
								<div class="cell">
									<button class="button primary place-right">Reservar!</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/templates/footer.jsp" />
