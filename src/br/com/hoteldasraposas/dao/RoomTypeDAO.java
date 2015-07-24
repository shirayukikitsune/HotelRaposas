package br.com.hoteldasraposas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.RoomType;

public class RoomTypeDAO {
	private Connection connection;

	public RoomTypeDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	private RoomType getRoomTypeFields(ResultSet rs) throws SQLException {
		RoomType type = new RoomType();
		type.setId(rs.getInt("idquarto_tipo"));
		type.setDailyPrice(rs.getFloat("diaria"));
		type.setTitle(rs.getString("titulo"));
		type.setDescription(rs.getString("descricao"));
		return type;
	}
	
	public Map<Integer, RoomType> getMap() {
		Map<Integer, RoomType> types = new HashMap<Integer, RoomType>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from quarto_tipo");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RoomType type = getRoomTypeFields(rs);
				
				types.put(type.getId(), type);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return types;
	}

	public List<RoomType> getList() {
		List<RoomType> types = new ArrayList<RoomType>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from quarto_tipo order by idquarto_tipo");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				types.add(getRoomTypeFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return types;
	}
	
	public RoomType getTypeById(int id) {
		RoomType tipo = null;
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from quarto_tipo where idquarto_tipo = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tipo = getRoomTypeFields(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return tipo;
	}
}
