package agencedata;

import exceptions.DataBaseException;
import ressources.User;

public interface UserData {
	
	public User get(String login); 
	
	public void add (User user) throws DataBaseException;
}
