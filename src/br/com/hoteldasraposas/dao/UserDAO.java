package br.com.hoteldasraposas.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.User;

public class UserDAO {
	private Connection connection;

	public UserDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	public byte[] encodePassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedPassword = digest.digest((password + "_RAPOSA").getBytes("UTF-8"));
		return encodedPassword;
	}

	public void insert(User user) {
		String sql = "INSERT INTO usuario (cpf,email,password,idusuario_titulo,nome,sobrenome) VALUES (?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setLong(1, user.getCpf());
			stmt.setString(2, user.getEmail());
			stmt.setBytes(3, user.getPassword());
			if (user.getUserTitleId() == 0)
				stmt.setNull(4, Types.INTEGER);
			else
				stmt.setInt(4, user.getUserTitleId());
			stmt.setString(5, user.getName());
			stmt.setString(6, user.getFamilyName());
			// executa
			stmt.execute();
			
			user.setCreateTime(new Date());

			// fecha statement
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(User user) {
		String sql = "update usuario set idusuario_titulo=?, email=?, password=?, nome=?, sobrenome=?, ativo=? where cpf=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			if (user.getUserTitleId() == 0)
				stmt.setNull(1, Types.INTEGER);
			else
				stmt.setInt(1, user.getUserTitleId());
			stmt.setString(2, user.getEmail());
			stmt.setBytes(3, user.getPassword());
			stmt.setString(4, user.getName());
			stmt.setString(5, user.getFamilyName());
			stmt.setBoolean(6, user.getActive());
			stmt.setLong(7, user.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private User getUserFields(ResultSet rs) throws SQLException {
		User user = new User();
		user.setCpf(rs.getLong("cpf"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getBytes("password"));
		user.setCreateTime(rs.getDate("create_time"));
		user.setUserTitleId(rs.getInt("idusuario_titulo"));
		user.setName(rs.getString("nome"));
		user.setFamilyName(rs.getString("sobrenome"));
		user.setAdmin(rs.getBoolean("admin"));
		user.setActive(rs.getBoolean("ativo"));
		return user;
	}

	public List<User> getList() {
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from usuario order by create_time desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(getUserFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return users;
	}
	
	public User getUserByCpf(long cpf) {
		User user = null;
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from usuario where cpf = ? AND ativo = 1");
			stmt.setLong(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = getUserFields(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return user;
	}
	
	public boolean hasEmail(String email) {
		boolean exists = false;
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT EXISTS (SELECT * FROM usuario WHERE email = ? AND ativo = 1)");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				exists = count > 0;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return exists;
	}

	public User tryLogin(String name, byte[] password) {
		User user = null;
		
		PreparedStatement stmt;
		try {
			Long cpf;
			try {
				cpf = Long.parseLong(name);
			}
			catch (NumberFormatException e) {
				cpf = 0L;
			}
			
			stmt = connection.prepareStatement("select * from usuario where (email=? or cpf=?) and password=? and ativo = 1");
			stmt.setString(1, name);
			stmt.setLong(2, cpf);
			stmt.setBytes(3, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = getUserFields(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return user;
	}
	
	public Long getUserCount() {
		Long count = 0L;
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT COUNT(cpf) FROM usuario");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return count;
	}
}
