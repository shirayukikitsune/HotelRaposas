package br.com.hoteldasraposas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.ReservationSituation;

public class ReservationSituationDAO {
	private Connection connection;

	/**
	 * Instancia um novo ReservaSituacaoDAO, utilizando uma conexão gerada pela instância padrão da classe {@link ConnectionFactory}
	 */
	public ReservationSituationDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	/**
	 * Popula os dados de um {@link ReservationSituation} a partir de um {@link ResultSet}
	 * @param rs {@link ResultSet} de um select * from reserva_situacao
	 * @return Um novo {@link ReservationSituation} populado
	 * @throws SQLException
	 */
	private ReservationSituation getReservationSituationFields(ResultSet rs) throws SQLException {
		ReservationSituation reservationSituation = new ReservationSituation();
		reservationSituation.setId(rs.getInt("idreserva_situacao"));
		reservationSituation.setDescription(rs.getString("descricao"));
		return reservationSituation;
	}

	/**
	 * Retorna uma lista (usando {@link ArrayList}) contendo todos os {@link ReservationSituation} do banco de dados
	 */
	public List<ReservationSituation> getList() {
		List<ReservationSituation> situations = new ArrayList<ReservationSituation>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from reserva_situacao order by idreserva_situacao");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				situations.add(getReservationSituationFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return situations;
	}
	
	/**
	 * Procura no banco de dados por um {@link ReservationSituation} com o id especificado
	 * @param id Identificador do {@link ReservationSituation}
	 * @return null caso não exista ou uma instância da classe {@link ReservationSituation} caso o identificador seja válido
	 */
	public ReservationSituation getReservationSituationById(int id) {
		ReservationSituation situation = null;
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from reserva_situacao where idreserva_situacao = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				situation = getReservationSituationFields(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return situation;
	}
}
