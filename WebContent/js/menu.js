$(function() {
	$('#form-menu-login .form-actions button').on('click', function (e) {
		e.preventDefault();
		
		var form = $('#form-menu-login');
		
		$.post(form.attr('action'), form.serialize(), function (response) {
			switch (response.status) {
			case 0:
				window.location.href = response.redirectUrl;
				break;
			default:
				alert('Combinação usuário/senha inválida.');
				break;
			}
		}, 'json');
	});

	$('#app-bar-logout').on('click', function() {
		var form = $('#form-menu-logout');
		
		$.post(form.attr('action'), form.serialize(), function (response) {
			window.location.href = response.redirectUrl;
		});
	});
});