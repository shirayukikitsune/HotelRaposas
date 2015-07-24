var buildReservations = function (response) {
	// Constroi o gráfico para o histórico
	var dataReservations = [];
	var dataRooms = [];
	for (var i = 0; i < response.date.length; ++i) {
		dataReservations.push({ x: response.date[i].date, y: response.date[i].count });
		dataRooms.push({ x: response.date[i].date, y: response.date[i].roomCount });
	}
	
	$('#chart-reservation-history').highcharts({
		chart: {
			zoomType: 'x'
		},
		title: {
			text: 'Histórico de Reservas'
		},
		subtitle: {
			text: document.ontouchstart === undefined ?
                'Clique e arraste na área de plotagem para aproximar' : 'Pince o gráfico para aproximar'
		},
		tooltip: {
			shared: true
		},
		xAxis: {
			type: 'datetime'
		},
		yAxis: {
			title: {
				text: 'Quantidade de Reservas'
			}
		},
		legend: {
			enabled: true
		},
		plotOptions: {
			area: {
				fillColor: {
					linearGradient: {
						x1: 0,
						y1: 0,
						x2: 0,
						y2: 1
					},
					stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
					]
				},
				marker: {
					radius: 2
				},
				lineWidth: 1,
				states: {
					hover: {
						lineWidth: 1
					}
				},
				threshold: null
			}
		},
		series: [{
			type: 'area',
			name: 'Reservas',
			data: dataReservations
		},
		{
			type: 'line',
			name: 'Quartos Reservados',
			data: dataRooms
		}]
	});
	
	// Constroi o gráfico para situação
	var dataSituations = [];
	var situations = [];
	for (var i = 0; i < response.situation.length; ++i) {
		situations.push(response.situation[i].situation);
		dataSituations.push(response.situation[i].count);
	}
	
	$('#chart-reservation-situation').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Reservas por Situação'
		},
		xAxis: {
			categories: situations,
			crosshair: true
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Quantidade de Reservas'
			}
		},
		legend: {
			enabled: false
		},
		plotOptions: {
			area: {
				fillColor: {
					linearGradient: {
						x1: 0,
						y1: 0,
						x2: 0,
						y2: 1
					},
					stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
					]
				},
				marker: {
					radius: 2
				},
				lineWidth: 1,
				states: {
					hover: {
						lineWidth: 1
					}
				},
				threshold: null
			}
		},
		series: [{
			name: 'Reservas',
			data: dataSituations
		}]
	});
}
