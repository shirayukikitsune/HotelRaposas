$(function () {
	// Usado para o paginador
});

$(function () {
	var orderByField = "create_time";
	var order = "desc";
	
	var table = $('#usuarios');
	var loader = $('#loader');
	var template = table.find('thead tr[data-role=template]');
	
	var page = 0;

	loadPage = function (thePage, orderByField, order) {
		table.hide();
		loader.show();
		$.post('admin/users/list', { page: thePage, orderBy: orderByField, order: order }, function (response) {
			var tbody = table.find('tbody').empty();
			for (var i = 0; i < response.length; ++i) {
				var reservation = response[i];
				var row = template.clone().show().appendTo(tbody);
				for (var prop in reservation) {
					if (reservation.hasOwnProperty(prop)) {
						var field = row.find('[data-receive='+prop+']');
						var value = reservation[prop];
						if (field.data('type') === 'datetime')
							value = (new Date(value)).toLocaleString();
						else if (field.data('type') === 'date')
							value = (new Date(value)).toLocaleDateString();
						else if (field.data('type') === 'boolean')
							value = value == true ? 'Sim' : 'Não';
						field.text(value);
					}
				}
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
		
		page = 0;
		loadPage(page, orderByField, order);
	});
	
	loadPage(page, orderByField, order);
});
