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

	public static void envoyerMail(String adresse, String message) {
		System.out.println("Mail de selection envoy� � l'adresse : "+ adresse + "\n Contenu : \n" + message);
		
	}
}
