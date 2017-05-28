package ressources;

import java.sql.Date;

public class Appartement {
	
	private int num�ro;
	private String typeAppart;
	private String adresse;
	private float montantVente;
	private Date datePublication;
	private String loginProp;
	
	public Appartement(int num�ro, String typeAppart, String adresse, float montantVente, Date datePublication, String loginProp) {
		this.num�ro = num�ro;
		this.typeAppart = typeAppart;
		this.adresse = adresse;
		this.montantVente = montantVente;
		this.datePublication = datePublication;
		this.loginProp = loginProp;
	}
	
	public int getNumero() {
		return num�ro;
	}



	public void setNumero(int num�ro) {
		this.num�ro = num�ro;
	}

	public String getTypeAppart() {
		return typeAppart;
	}

	public void setTypeAppart(String typeAppart) {
		this.typeAppart = typeAppart;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public float getMontantVente() {
		return montantVente;
	}

	public void setMontantVente(float montantVente) {
		this.montantVente = montantVente;
	}

	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public String getLoginProp() {
		return loginProp;
	}

	public void setLoginProp(String loginProp) {
		this.loginProp = loginProp;
	}
	
	

	
}
