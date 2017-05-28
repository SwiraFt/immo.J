package agencedata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.DataBaseException;
import ressources.Appartement;

public class AppartementDataModel {
	private Connection connect;
	
	public AppartementDataModel(){
		this.connect = ConnectionSingleton.getConnection();
	}
	
	public Appartement get(int numéro) {
		PreparedStatement statement = null;
				
		try {
			statement = connect.prepareStatement("SELECT * FROM APPARTEMENTS WHERE numero = ?");
			
			statement.setInt(1, numéro);
			
			ResultSet res = statement.executeQuery();
			
 
			if( res.next()){
				return new Appartement(res.getInt("numero"), res.getString("TypeAppart"), res.getString("Adresse"), res.getFloat("MontantVente"), res.getDate("DatePublication"), res.getString("LoginProp"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public ArrayList<Appartement> getAll(){
		PreparedStatement statement = null;
		ArrayList<Appartement> appartements = new ArrayList<Appartement>();
		
		try {
			statement = connect.prepareStatement("SELECT * FROM APPARTEMENTS WHERE numero is not null");
			
			ResultSet res = statement.executeQuery();
			
			while(res.next()){
				appartements.add(new Appartement(res.getInt("numero"), res.getString("TypeAppart"), res.getString("Adresse"), res.getFloat("MontantVente"), res.getDate("DatePublication"), res.getString("LoginProp")));
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return appartements;
	
	}
	
	public ArrayList<Appartement> getbyType(String type){
		PreparedStatement statement = null;
		ArrayList<Appartement> appartements = new ArrayList<Appartement>();
		
		try {
			statement = connect.prepareStatement("SELECT * FROM APPARTEMENTS WHERE typeappart = ?");
			
			statement.setString(1, type);
			
			ResultSet res = statement.executeQuery();
			
			while(res.next()){
				appartements.add(new Appartement(res.getInt("numero"), res.getString("TypeAppart"), res.getString("Adresse"), res.getFloat("MontantVente"), res.getDate("DatePublication"), res.getString("LoginProp")));
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return appartements;
	
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
			throw new DataBaseException("Impossible d'ajouter l'appartement à la base de données." );
		}
	}
	
	public int getNumOfLastAddedBy(String login) {
		PreparedStatement statement = null;
				
		try {
			statement = connect.prepareStatement("SELECT MAX(numero) FROM APPARTEMENTS WHERE loginprop = ?");
			
			statement.setString(1, login);
			
			ResultSet res = statement.executeQuery();
			
 
			if( res.next()){
				return res.getInt("numero");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return -1;
	}
	
}
