<%@page import="java.util.ArrayList" %>
<%@page import="ressources.*" %>
<%@page import="agencedata.AppartementDataModel"%>


<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="res/style/bootstrap.css"/>
    <title>immoJ</title>
  </head>
  <body>

	<jsp:include page="res/includes/header.jsp" />


    <nav  class="navbar navbar-default">

	<form class="navbar-form navbar-left" action="envoyerSelection" method="POST">
        <button type="submit" class="btn btn-default">Valider ma selection</button>
      </form>
	</nav>

	<%
		AppartementDataModel appartementDataModel = new AppartementDataModel();
    ArrayList<Appartement> appartements = null;

      appartements = appartementDataModel.getAll();

		if(appartements.size() == 0){
			out.println("Aucune selection pour l'instant.");
		}

		else{
      String[] selection = null;
      if(request.getSession().getAttribute("listeApparts") != null){
        String liste = (String) request.getSession().getAttribute("listeApparts");
        selection = liste.split(",");
      }

      if(selection == null || (selection != null && selection.length == 0) ){
        out.println("Aucune selection pour l'instant.");
      }
      else{
        System.out.println();
        for(int i = 0; i < appartements.size(); ++i){
          if(Outils.contient(selection, Integer.toString(appartements.get(i).getNumero()))){
          out.println("<nav class=\"navbar\">");

          out.println("<div class=\"alert alert-warning\" role=\"alert\">");
          out.println("<span class=\"label label-primary\">Type:</span>" + appartements.get(i).getTypeAppart()+ "</br>");
          out.println("<span class=\"label label-success\">Prix de vente:</span>" + appartements.get(i).getMontantVente()+ " euros </br>");
          out.println("<span class=\"label label-danger\">Adresse:</span>" + appartements.get(i).getAdresse() + "</br>");
          out.println("<span class=\"label label-default\">Proprietaire:</span>" + appartements.get(i).getLoginProp());
          out.println("<form class=\"navbar-form navbar-left\" action=\"supprimerAppartListe\" method=\"POST\">");
                out.println("<input type=\"text\" class=\"form-control\" name=\"numero\" value=\"" + appartements.get(i).getNumero() + "\"style=\"display: none\" >");
                  out.println("<button type=\"submit\" class=\"btn btn-default\" ><span class=\"glyphicon glyphicon-minus\" aria-hidden=\"true\"></span></button>");
              out.println("</form>");
          out.println("</div>");
          out.println("</nav>");
          }
        }
      }
		}
	%>


  </body>
</html>
