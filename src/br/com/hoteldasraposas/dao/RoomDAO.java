package br.com.hoteldasraposas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.Room;
import br.com.hoteldasraposas.model.RoomType;

public class RoomDAO {
	private Connection connection;

	public RoomDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	private Room getRoomFields(ResultSet rs) throws SQLException {
		Room room = new Room();
		room.setId(rs.getInt("idquarto"));
		room.setTypeId(rs.getInt("tipo"));
		return room;
	}
	
	public List<Room> getAvailableList(Date start, Date end) {
		RoomTypeDAO typesDao = new RoomTypeDAO();
		Map<Integer, RoomType> types = typesDao.getMap();
		
		List<Room> rooms = new ArrayList<Room>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from quarto left join reserva_quarto on reserva_quarto.idquarto = quarto.idquarto and (? between reserva_quarto.data_inicio and reserva_quarto.data_fim or reserva_quarto.data_inicio between ? and ? or reserva_quarto.data_fim between ? and ?) where idreserva is null");
			stmt.setDate(1, new java.sql.Date(start.getTime()));
			stmt.setDate(2, new java.sql.Date(start.getTime()));
			stmt.setDate(3, new java.sql.Date(end.getTime()));
			stmt.setDate(4, new java.sql.Date(start.getTime()));
			stmt.setDate(5, new java.sql.Date(end.getTime()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Room room = getRoomFields(rs);
				room.setType(types.get(room.getTypeId()));
				rooms.add(room);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rooms;
	}

	public List<Room> getList() {
		List<Room> rooms = new ArrayList<Room>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from quarto order by idquarto");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				rooms.add(getRoomFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rooms;
	}
	
	public Room getRoomById(int id) {
		Room room = null;
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from quarto where idquarto = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				room = getRoomFields(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return room;
	}
}
