package br.com.hoteldasraposas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.ReservationRoom;

public class ReservationRoomDAO {
	private Connection connection;

	/**
	 * Instancia um novo ReservaQuartoDAO, utilizando uma conexão gerada pela instância padrão da classe {@link ConnectionFactory}
	 */
	public ReservationRoomDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	/**
	 * Popula os dados de um {@link ReservationRoom} a partir de um {@link ResultSet}
	 * @param rs {@link ResultSet} de um select * from reserva_quarto
	 * @return Um novo {@link ReservationRoom} populado
	 * @throws SQLException
	 */
	private ReservationRoom getReservationRoomFields(ResultSet rs) throws SQLException {
		ReservationRoom reservationRoom = new ReservationRoom();
		reservationRoom.setReservationId(rs.getInt("idreserva"));
		reservationRoom.setRoomId(rs.getInt("idquarto"));
		reservationRoom.setStart(rs.getDate("data_inicio"));
		reservationRoom.setEnd(rs.getDate("data_fim"));
		reservationRoom.setDailyPrice(rs.getFloat("diaria"));
		return reservationRoom;
	}

	/**
	 * Retorna uma lista (usando {@link ArrayList}) contendo todos os {@link ReservationRoom} do banco de dados
	 */
	public List<ReservationRoom> getList() {
		List<ReservationRoom> reservationRooms = new ArrayList<ReservationRoom>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from reserva_quarto order by idreserva, idquarto");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reservationRooms.add(getReservationRoomFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservationRooms;
	}
	
	/**
	 * Retorna uma lista (usando {@link ArrayList}) contendo todos os {@link ReservationRoom} do banco de dados filtrados por período
	 */
	public List<ReservationRoom> getListByPeriod(Date start, Date end) {
		List<ReservationRoom> reservationRooms = new ArrayList<ReservationRoom>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from reserva_quarto where data_inicio between ? and ? or data_fim between ? and ? or ? between data_inicio and data_fim or ? between data_inicio and data_fim order by idreserva, idquarto");
			java.sql.Date startSql = new java.sql.Date(start.getTime());
			java.sql.Date endSql = new java.sql.Date(end.getTime());
			stmt.setDate(1, startSql);
			stmt.setDate(2, endSql);
			stmt.setDate(3, startSql);
			stmt.setDate(4, endSql);
			stmt.setDate(5, startSql);
			stmt.setDate(6, endSql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reservationRooms.add(getReservationRoomFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservationRooms;
	}
	
	/**
	 * Procura no banco de dados por todas as {@link ReservationRoom}s da {@link Reservation} com o identificador especificado
	 * @param reservationId Identificador da {@link Reservation}
	 * @return Uma lista contendo todas as {@link ReservationRoom}s da {@link Reservation} especificada
	 */
	public List<ReservationRoom> getReservationRoomByReservationId(int reservationId) {
		List<ReservationRoom> reservationRooms = new ArrayList<ReservationRoom>();
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from reserva_quarto where idreserva = ?");
			stmt.setInt(1, reservationId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reservationRooms.add(getReservationRoomFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return reservationRooms;
	}
	
	/**
	 * Procura no banco de dados por todas as {@link ReservationRoom}s do {@link Room} com o identificador especificado
	 * @param roomId Identificador do {@link Room}
	 * @return Uma lista contendo todas as {@link ReservationRoom}s do {@link Room} especificado
	 */
	public List<ReservationRoom> getReservationRoomByRoomId(int roomId) {
		List<ReservationRoom> reservationRooms = new ArrayList<ReservationRoom>();
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from reserva_quarto where idquarto = ?");
			stmt.setInt(1, roomId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reservationRooms.add(getReservationRoomFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return reservationRooms;
	}
}
