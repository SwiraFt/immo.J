package agencedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionSingleton {
	private static Connection connect;
	
	public static Connection getConnection(){
		if(connect != null)
			return connect;
		else{
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.err.println("Impossible de trouver le pilote de connexion � la base de donn�es.");
				e.printStackTrace();
			}
			
			try {
				connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GHELISA", "GHELISA");
			} catch (SQLException e) {
				System.err.println("Impossible de se connecter � la base de donn�es.");
				e.printStackTrace();
			}
			return connect;
		}
	}
	
}
