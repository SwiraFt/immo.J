package agence;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AgenceManager extends HttpServlet{
	
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
		
		redirect(request, response, "listeappartements");
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
