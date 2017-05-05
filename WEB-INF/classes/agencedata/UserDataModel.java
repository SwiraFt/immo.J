package agencedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DataBaseException;
import ressources.User;

public class UserDataModel implements UserData {
	
	private Connection connect;
	
	
	public UserDataModel(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de trouver le driver de connexion à la base de données.");
			e.printStackTrace();
		}
		
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL", "GHELISA", "GHELISA");
		} catch (SQLException e) {
			System.err.println("Impossible de se connecter à la base de données.");
			e.printStackTrace();
		}
		
	}

	public User get(String login) {
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM PROPRIETAIRES WHERE login = ?");
			
			statement.setString(1, login);
			
			ResultSet res = statement.executeQuery();
			
 
			if( res.next()){
				return new User(res.getString("Nom"), res.getString("Login"), res.getString("Mdp"), res.getString("Email"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void add(User user) throws DataBaseException{
		PreparedStatement statement = null;		
		
		try {
			statement = connect.prepareStatement("INSERT INTO TABLE PROPRIETAIRES VALUES( ? , ? , ? , ?");

			statement.setString(1, user.getNom());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getMdp());
			statement.setString(4, user.getEmail());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
