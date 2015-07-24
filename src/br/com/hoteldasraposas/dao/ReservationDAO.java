package br.com.hoteldasraposas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.Reservation;
import br.com.hoteldasraposas.model.ReservationSituation;
import br.com.hoteldasraposas.model.User;

public class ReservationDAO {
	private Connection connection;

	/**
	 * Instancia um novo ReservationDAO, utilizando uma conexão gerada pela instância padrão da classe {@link ConnectionFactory}
	 */
	public ReservationDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	/**
	 * Popula os dados de um {@link Reservation} a partir de um {@link ResultSet}
	 * @param rs {@link ResultSet} de um select * from reserva
	 * @return Um novo {@link Reservation} populado
	 * @throws SQLException
	 */
	private Reservation getReservationFields(ResultSet rs) throws SQLException {
		Reservation reservation = new Reservation();
		reservation.setId(rs.getInt("idreserva"));
		reservation.setCpf(rs.getLong("cpf"));
		reservation.setDate(rs.getDate("data"));
		reservation.setSituationId(rs.getInt("situacao"));
		return reservation;
	}

	/**
	 * Retorna uma lista (usando {@link ArrayList}) contendo todos os {@link Reservation} do banco de dados
	 */
	public List<Reservation> getList() {
		List<Reservation> reservations = new ArrayList<Reservation>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from reserva order by idreserva");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reservations.add(getReservationFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservations;
	}
	
	/**
	 * Procura no banco de dados por um {@link Reservation} com o id especificado
	 * @param id Identificador do {@link Reservation}
	 * @return null caso não exista ou uma instância da classe {@link Reservation} caso o identificador seja válido
	 */
	public Reservation getReservationById(int id) {
		Reservation reservation = null;
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from reserva where idreserva = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				reservation = getReservationFields(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return reservation;
	}
	
	/**
	 * Procura no banco de dados por todas as {@link Reservation}s efetuadas pelo {@link User} com o cpf especificado
	 * @param cpf CPF do {@link User}
	 * @return Uma lista (usando {@link ArrayList}) contendo todas as {@link Reservation}s filtradas pelo cpf
	 */
	public List<Reservation> getReservationsByCpf(Long cpf, int start, int count, String orderBy, String order) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			String sql = String.format("select * from reserva where cpf = ? order by %s %s limit ?,?", orderBy, order);
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, cpf);
			stmt.setInt(2, start);
			stmt.setInt(3, count);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reservations.add(getReservationFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservations;
	}
	
	/**
	 * Procura no banco de dados por todas as {@link Reservation}s que possuem a situação especificada
	 * @param situationId Identificador da {@link ReservationSituation}
	 * @return Uma lista (usando {@link ArrayList}) contendo todas as {@link Reservation}s filtradas por esta situação
	 */
	public List<Reservation> getReservationsBySituation(int situationId) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from reserva where situacao = ?");
			stmt.setInt(1, situationId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reservations.add(getReservationFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservations;
	}
}
