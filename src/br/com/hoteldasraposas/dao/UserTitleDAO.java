package br.com.hoteldasraposas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hoteldasraposas.factory.ConnectionFactory;
import br.com.hoteldasraposas.model.UserTitle;

public class UserTitleDAO {
	private Connection connection;

	/**
	 * Instancia um novo UsuarioTituloDAO, utilizando uma conexão gerada pela instância padrão da classe {@link ConnectionFactory}
	 */
	public UserTitleDAO() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	/**
	 * Popula os dados de um {@link UserTitle} a partir de um {@link ResultSet}
	 * @param rs {@link ResultSet} de um select * from usuario_titulo
	 * @return Um novo {@link UserTitle} populado
	 * @throws SQLException
	 */
	private UserTitle getUserTitleFields(ResultSet rs) throws SQLException {
		UserTitle userTitle = new UserTitle();
		userTitle.setId(rs.getInt("idusuario_titulo"));
		userTitle.setDescription(rs.getString("descricao"));
		return userTitle;
	}

	/**
	 * Retorna uma lista (usando {@link ArrayList}) contendo todos os {@link UserTitle} do banco de dados
	 */
	public List<UserTitle> getList() {
		List<UserTitle> titles = new ArrayList<UserTitle>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from usuario_titulo order by idusuario_titulo");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				titles.add(getUserTitleFields(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return titles;
	}
	
	/**
	 * Procura no banco de dados por um {@link UserTitle} com o id especificado
	 * @param id Identificador do {@link UserTitle}
	 * @return null caso não exista ou uma instância da classe {@link UserTitle} caso o identificador seja válido
	 */
	public UserTitle getUserTitleById(int id) {
		UserTitle title = null;
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from usuario_titulo where idusuario_titulo = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				title = getUserTitleFields(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return title;
	}
}
