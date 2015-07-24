package br.com.hoteldasraposas.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static ConnectionFactory getInstance() {
		if (instance == null) {
			instance = new ConnectionFactory();
		}
		
		return instance;
	};
	
	private ConnectionFactory() {
	}
	
	private Connection connection = null;
	public Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/hotel_raposas", "progweb", "progweb");
			} catch (SQLException e) {
				throw new RuntimeException("Falha ao conectar ao banco de dados: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Falha ao encontrar a classe do banco de dados: " + e.getMessage());
			}
		}
		
		return connection;
	}
	
	private static ConnectionFactory instance = null; 
}
