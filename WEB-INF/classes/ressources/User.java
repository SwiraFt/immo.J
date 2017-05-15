package ressources;

public class User {
	private String nom;
	
	private String login;
	
	private String mdp;
	
	private String eMail;
	
	

	public User(String nom, String login, String mdp, String eMail) {
		super();
		this.nom = nom;
		this.login = login;
		this.mdp = mdp;
		this.eMail = eMail;
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
	

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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
