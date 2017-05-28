package ressources;

public class Outils {
	
	public static boolean contient(String[] s, String val){
		if(s == null)
			return false;
		
		for(int i = 0; i < s.length; ++i)
			if(s[i].equals(val))
				return true;

		return false;
	}
}
