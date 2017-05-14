package agencedata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DataBaseException;
import ressources.Appartement;

public class AppartementDataModel {
	private Connection connect;
	
	public AppartementDataModel(){
		this.connect = ConnectionSingleton.getConnection();
	}
	
	public Appartement get(int num�ro) {
		PreparedStatement statement = null;
				
		try {
			statement = connect.prepareStatement("SELECT * FROM APPARTEMENTS WHERE numero = ?");
			
			statement.setInt(1, num�ro);
			
			ResultSet res = statement.executeQuery();
			
 
			if( res.next()){
				return new Appartement(res.getString("TypeAppart"), res.getString("Adresse"), res.getFloat("MontantVente"), res.getDate("DatePublication"), res.getString("LoginProp"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void add(Appartement appartement) throws DataBaseException{
		PreparedStatement statement = null;		
		
		try {
			statement = connect.prepareStatement("INSERT INTO APPARTEMENTS VALUES(NUMERO_APPART.NEXTVAL, ? , ? , ? , ?, ? )");

			statement.setString(1, appartement.getTypeAppart());
			statement.setString(2, appartement.getAdresse());
			statement.setFloat(3, appartement.getMontantVente());
			statement.setDate(4, appartement.getDatePublication());
			statement.setString(5, appartement.getLoginProp());
			
			statement.executeUpdate();
			
			connect.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataBaseException("Impossible d'ajouter l'appartement � la base de donn�es." );
		}


	}
}
