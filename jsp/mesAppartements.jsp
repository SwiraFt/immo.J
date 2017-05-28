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

	<%
		AppartementDataModel appartementDataModel = new AppartementDataModel();
    ArrayList<Appartement> appartements = null;

      appartements = appartementDataModel.getAll();

		if(appartements.size() == 0){
			out.println("Aucune selection pour l'instant.");
		}

    else{

        for(int i = 0; i < appartements.size(); ++i){
          if(appartements.get(i).getLoginProp().equals((String) request.getSession().getAttribute("login"))){
          out.println("<nav class=\"navbar\">");
          out.println("<div class=\"alert alert-warning\" role=\"alert\" style=\"display:block\">");
          out.println("<span class=\"label label-primary\">Numero:</span>" + appartements.get(i).getNumero()+ "</br>");
          out.println("<span class=\"label label-primary\">Type:</span>" + appartements.get(i).getTypeAppart()+ "</br>");
          out.println("<span class=\"label label-success\">Prix de vente:</span>" + appartements.get(i).getMontantVente()+ " euros </br>");
          out.println("<span class=\"label label-danger\">Adresse:</span>" + appartements.get(i).getAdresse() + "</br>");
          out.println("<span class=\"label label-default\">Proprietaire:</span>" + appartements.get(i).getLoginProp());
          out.println("<form class=\"navbar-form navbar-left\" action=\"supprimerappart\" method=\"POST\">");
          out.println("</br>");
                out.println("<input type=\"text\" class=\"form-control\" name=\"numero\" value=\"" + appartements.get(i).getNumero() + "\"style=\"display: none\" >");
                  out.println("<button type=\"submit\" class=\"btn btn-default\" ><span class=\"glyphicon glyphicon-minus\" aria-hidden=\"true\">Supprimmer ce logement</span></button>");
              out.println("</form>");
          out.println("</div>");
          out.println("</nav>");
          }
        }
      }

	%>


  </body>
</html>
