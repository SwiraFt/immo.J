package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ressources.User;

public class ConnexionManager extends HttpServlet {
	private final static String MSG_ERREUR = "msgerreur";
	private final static String CONNEXION_FILE = "index.jsp";
	PrintWriter out;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
		String[] parses = request.getRequestURL().toString().split("/"); //On parse l'URL
		String action = parses[parses.length - 1]; //On récupère l'action demandé dans l'URL
		
		if(action.equals("inscription")) //En fonction de cette action on sait si on inscrit ou authentifie l'utilisateur
			inscrire(request, response);
		else if(action.equals("authentification"));

		
	}
	
	private void inscrire(HttpServletRequest request,  HttpServletResponse response){
		if(validationDonnées(request, response)){
			System.out.println("Inscription ok");
			request.setAttribute(MSG_ERREUR, "Vous vous êtes bien inscrit, vous pouvez vous connecter");
			//TODO renvoyer vers la page mon compte et connecter l'user automatiquement
		}
		
		RequestDispatcher view = request.getRequestDispatcher(CONNEXION_FILE);
	    try {
			view.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private boolean controleNom(String nom, StringBuilder messageErreur){
		if(nom == null || nom.equals("")){
			messageErreur.append("Le nom doit être renseigné  </br>");
			return false;
		}
		if(nom.length() < 3){
			messageErreur.append("Le nom doit comporter au moins 3 carctères </br>");
			return false;
		}
			
		return true;
		
	}
	
	private boolean controlePseudo(String pseudo, StringBuilder messageErreur){
		if(pseudo == null || pseudo.equals("")){
			messageErreur.append("L'identifiant doit être renseigné </br>");
			return false;
		}
		if(pseudo.length() < 3){
			messageErreur.append("L'identifiant doit comporter au moins 3 caractères. </br>");
			return false;
		}

		return true;
		
	}
	
	private boolean controleMdp(String mdp, StringBuilder messageErreur){
		if(mdp == null || mdp.equals("")){
			messageErreur.append("Mot de passe vide. </br>");
			return false;
		}
		if(mdp.length() < 3){
			messageErreur.append("Le mot de passe doit comporter au moins 5 caractères. </br>");
			return false;
		}

		return true;
		
	}
	
	private boolean controleEmail(String eMail, StringBuilder messageErreur){ 
		if(eMail == null || eMail.equals("")){
			messageErreur.append("Adresse Email vide. </br>");
			return false;
		}
		String[] mail = eMail.split("@");//TODO Ajouter les contrôle de caractères spéciaux de la partie gauche
		if(mail.length != 2){
			messageErreur.append("Adresse Email Invalide. </br>");
			return false;
		}
		//TODO Ajouter le ctrl sur la partie droite de l'email
		return true;
		
	}
	
	private boolean validationDonnées(HttpServletRequest request, HttpServletResponse response){
		StringBuilder messageErreur = new StringBuilder("");
		
		if (controleNom(request.getParameter("nom"), messageErreur) & controlePseudo(request.getParameter("pseudo"), messageErreur)
				& controleMdp(request.getParameter("mdp"), messageErreur) & controleEmail(request.getParameter("email"), messageErreur) )
			return true;
		
		
		request.setAttribute(this.MSG_ERREUR, messageErreur.toString());
		return false;
	}
	
	
	
	
}
