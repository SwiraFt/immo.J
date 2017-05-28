package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import agencedata.UserDataModel;
import exceptions.DataBaseException;
import ressources.User;

@SuppressWarnings("serial")
public class ConnexionManager extends HttpServlet {
	private final static String MSG_ERREUR = "msgerreur";
	private final static String CONNEXION_FILE = "index.jsp";
	private final static String CONNEXION_OK = "listeappartements";
	private final static String SPECIAL_CHAR = "- ";
	PrintWriter out;


	public void doPost(HttpServletRequest request, HttpServletResponse response){

		String[] parses = request.getRequestURL().toString().split("/"); //On parse l'URL
		String action = parses[parses.length - 1]; //On récupère l'action demandé dans l'URL

		if(action.equals("inscription")) //En fonction de cette action on sait si on inscrit ou authentifie l'utilisateur
			inscrire(request, response);
		else if(action.equals("authentification"))
			connexion(request, response);


	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){

		String[] parses = request.getRequestURL().toString().split("/"); //On parse l'URL
		String action = parses[parses.length - 1]; //On récupère l'action demandé dans l'URL

		if(action.equals("deconnexion")){
			request.getSession().invalidate();
			try {
				response.sendRedirect(CONNEXION_FILE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	private void inscrire(HttpServletRequest request,  HttpServletResponse response){
		if(validationDonnées(request, response)){
			UserDataModel userDataModel = new UserDataModel();

			if(userDataModel.get(request.getParameter("pseudo")) != null){
				request.setAttribute(MSG_ERREUR, SPECIAL_CHAR +"Le pseudo entré est déjà utilisé, merci d'en choisir un différent.");
			}
			else{
				try {
					userDataModel.add(new User(request.getParameter("nom"),request.getParameter("pseudo"), request.getParameter("mdp"), request.getParameter("email")));
				} catch (DataBaseException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur lors de l'ajout de l'utilisateur à la base de données");
					e.printStackTrace();
				}
				request.setAttribute(MSG_ERREUR, SPECIAL_CHAR + "Vous vous êtes bien inscrit, vous pouvez vous connecter");
				//TODO renvoyer vers la page mon compte et connecter l'user automatiquement
			}

		}

		redirect(request, response, CONNEXION_FILE);

	}

	private boolean controleNom(String nom, StringBuilder messageErreur){
		if(nom == null || nom.equals("")){
			messageErreur.append(SPECIAL_CHAR + "Le nom doit être renseigné  </br>");
			return false;
		}
		if(nom.length() < 3){
			messageErreur.append(SPECIAL_CHAR + "Le nom doit comporter au moins 3 caractères </br>");
			return false;
		}

		return true;

	}

	private boolean controlePseudo(String pseudo, StringBuilder messageErreur){
		if(pseudo == null || pseudo.equals("")){
			messageErreur.append(SPECIAL_CHAR + "L'identifiant doit être renseigné </br>");
			return false;
		}
		if(pseudo.length() < 3){
			messageErreur.append(SPECIAL_CHAR + "L'identifiant doit comporter au moins 3 caractères. </br>");
			return false;
		}

		return true;

	}

	private boolean controleMdp(String mdp, StringBuilder messageErreur){
		if(mdp == null || mdp.equals("")){
			messageErreur.append(SPECIAL_CHAR + "Mot de passe vide. </br>");
			return false;
		}
		if(mdp.length() < 3){
			messageErreur.append(SPECIAL_CHAR + "Le mot de passe doit comporter au moins 5 caractères. </br>");
			return false;
		}

		return true;

	}

	private boolean controleEmail(String eMail, StringBuilder messageErreur){
		if(eMail == null || eMail.equals("")){
			messageErreur.append(SPECIAL_CHAR + "Adresse Email vide. </br>");
			return false;
		}
		String[] mail = eMail.split("@");//TODO Ajouter les contrôle de caractères spéciaux de la partie gauche
		if(mail.length != 2){
			messageErreur.append(SPECIAL_CHAR + "Adresse Email Invalide. </br>");
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


		request.setAttribute(MSG_ERREUR, messageErreur.toString());
		return false;
	}


	private void connexion(HttpServletRequest request, HttpServletResponse response){
		User user;

		if(request.getParameter("pseudo") == null || request.getParameter("mdp") == null){
			request.setAttribute(MSG_ERREUR, SPECIAL_CHAR + "Pseudo ou mot de passe invalide");
			redirect(request, response, CONNEXION_FILE);
		}

		else{
			UserDataModel userDataModel = new UserDataModel();
			user = userDataModel.get(request.getParameter("pseudo"));
			if(user == null || !user.getMdp().equals(request.getParameter("mdp"))){
				request.setAttribute(MSG_ERREUR, SPECIAL_CHAR + "Combinaison Identifiant/Mot de passe invalide");
				redirect(request, response, CONNEXION_FILE);
			}
			else{
				try {
					HttpSession session = request.getSession();
					session.setAttribute("login", request.getParameter("pseudo"));
					response.sendRedirect(CONNEXION_OK);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}





	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String route) {
		RequestDispatcher view = request.getRequestDispatcher(route);
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





}
