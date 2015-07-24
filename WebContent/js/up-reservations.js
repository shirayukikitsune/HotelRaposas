$(function () {
	orderByField = "data";
	order = "desc";
	
	var table = $('#reservations');
	var loader = $('#loader');

	loadPage = function (orderByField, order) {
		table.hide();
		loader.show();
		$.post($('#reservations').data('url'), { orderBy: orderByField, order: order }, function (response) {
			var tbody = table.find('tbody').empty();
			for (var i = 0; i < response.length; ++i) {
				var reservation = response[i];
				tbody.append('<tr><td>' + reservation.id + '</td><td>' + reservation.date + '</td><td>' + reservation.situation.description + '</td></tr>');
			}
			loader.hide();
			table.show();
		}, 'json');
	}

	table.find('.sortable-column').on('click', function () {
		if ($(this).hasClass('sort-asc') || $(this).hasClass('sort-desc')) {
			$(this).toggleClass('sort-asc sort-desc');
			order = $(this).hasClass('sort-asc') ? "asc" : "desc";
		}
		else {
			$(this).siblings().removeClass('sort-asc sort-desc');
			$(this).addClass('sort-asc');
			order = "asc";
		}
		orderByField = $(this).data('field');
		
		loadPage(orderByField, order);
	});
	
	loadPage(orderByField, order);
});
