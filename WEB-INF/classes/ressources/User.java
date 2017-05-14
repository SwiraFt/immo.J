package ressources;

public class User {
	private String nom;
	
	private String login;
	
	private String mdp;
	
	

	public User(String nom, String login, String mdp) {
		super();
		this.nom = nom;
		this.login = login;
		this.mdp = mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
