package test;

import static org.junit.Assert.*;

import org.junit.Test;

import agencedata.AppartementDataModel;
import exceptions.DataBaseException;
import ressources.Appartement;

public class testAppartement {

	
	@Test
	public void ajoutAppartement() throws DataBaseException{
		@SuppressWarnings("deprecation")
		Appartement appart = new Appartement("T2", "3 bis avenue de la gare, 75005 Paris", 180000.0f, new java.sql.Date(25, 05, 2017), "amin");
		AppartementDataModel appartementDataModel = new AppartementDataModel();
		appartementDataModel.add(appart);
	}

}
