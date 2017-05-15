package test;

import java.sql.Date;

import org.junit.Assert;

import agencedata.*;
import exceptions.DataBaseException;
import ressources.Appartement;
import ressources.User;

import org.junit.Test;

public class testDB {
	
	@Test (expected = DataBaseException.class)
	public void testAddUser() throws DataBaseException{
		UserDataModel userDataModel = new UserDataModel();
		User user = new User("testUser","testUser","testUser", "testUser@immoJ.fr");

		userDataModel.add(user);

	}
	
	@Test
	public void testGetUser(){
		UserDataModel userDataModel = new UserDataModel();
		User user = userDataModel.get("testUser");
		Assert.assertTrue(user != null);
		
	}
	
	@Test
	public void testAddAppartement() throws DataBaseException{
		AppartementDataModel appartementDataModel = new AppartementDataModel();
		Appartement appartement = new Appartement("T1", "5 rue du Junit Test Case", 90500f , new Date(14, 05, 2017) , "testUser" );
		
		appartementDataModel.add(appartement);

	}
	
	@Test
	public void testGetAppartement(){
		AppartementDataModel appartementDataModel = new AppartementDataModel();
		Appartement appartement = appartementDataModel.get(0);
		Assert.assertTrue(appartement != null);
	}

}
