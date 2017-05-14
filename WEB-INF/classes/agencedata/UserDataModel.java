package agencedata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DataBaseException;
import ressources.User;

public class UserDataModel {
		
	private Connection connect;
	
	public UserDataModel(){
			this. connect = ConnectionSingleton.getConnection();
	}

	public User get(String login) {
		PreparedStatement statement = null;
				
		try {
			statement = connect.prepareStatement("SELECT * FROM PROPRIETAIRES WHERE login = ?");
			
			statement.setString(1, login);
			
			ResultSet res = statement.executeQuery();
			
 
			if( res.next()){
				return new User(res.getString("Nom"), res.getString("Login"), res.getString("Mdp"));
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
			statement = connect.prepareStatement("INSERT INTO PROPRIETAIRES VALUES( ? , ? , ? )");

			statement.setString(1, user.getNom());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getMdp());
			
			statement.executeUpdate();
			
			connect.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataBaseException("Impossible d'ajouter l'utilisateur " + user.getNom() + " à la base de données." );
		}


	}

}
