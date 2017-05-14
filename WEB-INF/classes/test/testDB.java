package test;

import org.junit.Assert;

import agencedata.*;
import exceptions.DataBaseException;
import ressources.User;

import org.junit.Test;

public class testDB {
	
	@Test
	public void testAddUser(){
		UserDataModel userDataModel = new UserDataModel();
		User user = new User("testUser","testUser","testUser");
		try {
			userDataModel.add(user);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUser(){
		UserDataModel userDataModel = new UserDataModel();
		User user = userDataModel.get("testUser");
		Assert.assertTrue(user != null);
		
	}

}
