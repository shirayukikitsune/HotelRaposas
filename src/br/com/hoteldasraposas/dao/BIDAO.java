package br.com.hoteldasraposas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.bi.ReservationBySituation;
import br.com.hoteldasraposas.model.bi.ReservationCount;

/**
 * Objeto de acesso a dados para a seção Business Intelligence  
 */
public class BIDAO {
	private Connection connection;

	/**
	 * Instancia um novo BIDAO, utilizando uma conexão gerada pela instância padrão da classe {@link ConnectionFactory}
	 */
	public BIDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	/**
	 * Retorna uma lista (usando {@link ArrayList}) contendo todos os {@link ReservationCount} do banco de dados, filtrados por período
	 */
	public List<ReservationCount> getReservationHistory(Date inicio, Date termino) {
		List<ReservationCount> reservations = new ArrayList<ReservationCount>();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement stmt;
		try {
			String sql = "select reserva.data, count(distinct reserva.idreserva), count(distinct reserva_quarto.idquarto) from reserva inner join reserva_quarto on reserva.idreserva = reserva_quarto.idreserva where 1=1";
			if (inicio != null) {
				sql = sql + " and reserva.data >= '" + fmt.format(inicio) + "'";
			}
			if (termino != null) {
				sql = sql + " and reserva.data <= '" + fmt.format(termino) + "'";
			}
			sql = sql + " group by reserva.data";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ReservationCount count = new ReservationCount();
				count.setDate(rs.getDate(1).getTime());
				count.setCount(rs.getInt(2));
				count.setRoomCount(rs.getInt(3));
				reservations.add(count);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservations;
	}
	
	public List<ReservationBySituation> getReservationsBySituation(Date inicio, Date termino) {
		List<ReservationBySituation> reservations = new ArrayList<ReservationBySituation>();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement stmt;
		try {
			String sql = "select reserva_situacao.descricao, count(reserva.idreserva) from reserva_situacao left outer join reserva on reserva.situacao = reserva_situacao.idreserva_situacao";
			if (inicio != null) {
				sql = sql + " and reserva.data >= '" + fmt.format(inicio) + "'";
			}
			if (termino != null) {
				sql = sql + " and reserva.data <= '" + fmt.format(termino) + "'";
			}
			sql = sql + " group by reserva_situacao.idreserva_situacao";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ReservationBySituation r = new ReservationBySituation();
				r.setSituation(rs.getString(1));
				r.setCount(rs.getInt(2));
				reservations.add(r);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservations;
	}
}
