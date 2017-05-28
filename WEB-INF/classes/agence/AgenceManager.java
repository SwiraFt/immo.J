package agence;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import agencedata.AppartementDataModel;
import exceptions.DataBaseException;
import ressources.Appartement;
import ressources.Outils;

@SuppressWarnings("serial")
public class AgenceManager extends HttpServlet{
	private final static String MSG_ERREUR = "msgerreur";
	private final static String SPECIAL_CHAR = "- ";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String[] parses = request.getRequestURL().toString().split("/"); //On parse l'URL
		String action = parses[parses.length - 1]; //On récupère l'action demandé dans l'URL
		
		if(action.equals("appartByNum"))
			request.setAttribute("action", "getByNum");

		else if(action.equals("appartByType"))
			request.setAttribute("action", "getByType");

		else if(action.equals("ajoutAppartListe"))
			ajouterAppartementListe(request);

		else if(action.equals("supprimerAppartListe")){
			supprimerAppartementListe(request);
			redirect(request, response, "listeselection");
			return;
		}
		else if(action.equals("creerappart")){
			ajouterAppartementDB(request);
			redirect(request, response, "vendreappart");
			return;
		}
		else if(action.equals("supprimerappart")){
			supprimerAppartmentDB(request);
			redirect(request, response, "mesappartements");
			return;
		}
		
		else if(action.equals("envoyerselection")){
			envoyerSelection(request);
			
		}
		
		redirect(request, response, "listeappartements");
	}
	
	
	private void envoyerSelection(HttpServletRequest request) {
		if(request.getSession().getAttribute("listeApparts") == null || request.getParameter("email") == null)
			return;
		StringBuilder sb = new StringBuilder();
		AppartementDataModel appartementDataModel = new AppartementDataModel();
		String[] liste = ((String) request.getSession().getAttribute("listeApparts")).split(",");
		if(liste == null)
			return;
		
		for(int i= 0; i < liste.length; i++){
			Appartement appart;
			try {
				appart = appartementDataModel.get(Integer.valueOf(liste[i]));
			} catch (NumberFormatException e) {
				continue;
			}
			if(appart == null)
				continue;
			sb.append("Appartement numéro : " + appart.getNumero()+ "\n");
			sb.append("Adresse : " + appart.getAdresse()+ "\n");
			sb.append("Prix de Vente : " + appart.getMontantVente() + "\n");
			sb.append("Proprietaire : " + appart.getLoginProp() + "\n");
			sb.append("    ---------------------------------------    ");
		}
		
		Outils.envoyerMail(request.getParameter("email") , sb.toString());
		
		
		
	}

	private void supprimerAppartmentDB(HttpServletRequest request) {
		int numéro = Integer.valueOf(request.getParameter("numero"));
		AppartementDataModel appartementDataModel = new AppartementDataModel();
		appartementDataModel.delete(numéro);		
	}


	private void ajouterAppartementDB(HttpServletRequest request) {
		try {
			Integer.valueOf(request.getParameter("montant"));
		} catch (NumberFormatException e) {
			request.setAttribute(MSG_ERREUR, SPECIAL_CHAR +"Montant invalide.");
			return;
		}
		
		if(Integer.valueOf(request.getParameter("montant")) <= 0)
			request.setAttribute(MSG_ERREUR, SPECIAL_CHAR +"Montant invalide.");
		else if(request.getParameter("adresse") == null)
			request.setAttribute(MSG_ERREUR, SPECIAL_CHAR +"Adresse invalide.");
		else{
			Date date = new Date();
			AppartementDataModel appartementDataModel = new AppartementDataModel();
			try {
				appartementDataModel.add(new Appartement(-1, request.getParameter("type"), request.getParameter("adresse"), Float.valueOf(request.getParameter("montant")), new java.sql.Date(date.getTime()), (String) request.getSession().getAttribute("login")));
				request.setAttribute(MSG_ERREUR, SPECIAL_CHAR +"L'appartement a bien été enregistré au numéro : "+ appartementDataModel.getNumOfLastAddedBy( (String) request.getSession().getAttribute("login")) );
			} catch (NumberFormatException | DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}


	private void ajouterAppartementListe(HttpServletRequest request) {
		String numero = request.getParameter("numero");
		HttpSession session = request.getSession();
		if(session.getAttribute("listeApparts") == null){
			session.setAttribute("listeApparts", numero);
		}
		else{
			String liste = (String) session.getAttribute("listeApparts");
			liste += "," + numero;
			session.setAttribute("listeApparts", liste);
		}
	}
	
	private void supprimerAppartementListe(HttpServletRequest request){
		String numero = request.getParameter("numero");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("listeApparts") == null)
			return;
		
		String[] apparts = ((String) session.getAttribute("listeApparts")).split(",");
		String res = "";
		
		for(int i = 0; i < apparts.length; ++i)
			if(!apparts[i].equals(numero))
				res += "," + apparts[i];
		
		session.setAttribute("listeApparts", res);
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
