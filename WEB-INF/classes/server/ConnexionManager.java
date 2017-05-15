package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ressources.User;

@SuppressWarnings("serial")
public class ConnexionManager extends HttpServlet {
	PrintWriter out;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
		String[] parses = request.getRequestURL().toString().split("/"); //On parse l'URL
		String action = parses[parses.length - 1]; //On récupère l'action demandé dans l'URL
		
		if(action == "inscription")
			inscrire(request, response);
		else if(action == "authentification");

		
	}
	
	private void inscrire(HttpServletRequest request,  HttpServletResponse response){
		if(!controleNom(request.getParameter("nom"))){
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
		
		User user = new User(request.getParameter("nom"), request.getParameter("pseudo"), request.getParameter("mdp"), request.getParameter("email"));
	}
	
	private boolean controleNom(String nom){
		if(nom == null)
			return false;
		if(nom.equals(""))
			return false;
		if(nom.length() < 3)
			return false;
		return true;
		
	}
	
	
}
