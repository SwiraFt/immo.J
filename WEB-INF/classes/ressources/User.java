package ressources;

public class User {
	private String nom;
	
	private String login;
	
	private String mdp;
	
	private String email;
	
	

	public User(String nom, String login, String mdp, String email) {
		super();
		this.nom = nom;
		this.login = login;
		this.mdp = mdp;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
