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

	<form class="navbar-form navbar-left" action="appartByNum" method="POST">
        <div class="form-group">
          <input type="text" class="form-control" name="numero" placeholder="Saisir le numero">
        </div>
        <button type="submit" class="btn btn-default">Rechercher</button>
      </form>

	<form class="navbar-form navbar-left" action="appartByType" method="POST">
    <div class="form-group">
    <SELECT name="type" size="1">
    <OPTION> Studio </OPTION>
    <OPTION> T1 </OPTION>
    <OPTION> T2 </OPTION>
    <OPTION> T3 </OPTION>
    </SELECT>
    </div>
        <button type="submit" class="btn btn-default">Rechercher par type</button>
      </form>

      <form class="navbar-form navbar-left" action="listeappartements" method="POST">
          <button type="submit" class="btn btn-default">Reinitialiser</button>
      </form>

      <%
      if(request.getSession().getAttribute("login") != null){
        out.println("<form class=\"navbar-form navbar-right\" action=\"mesappartements\" method=\"POST\">");
        out.println("<button type=\"submit\" class=\"btn btn-primary\">Mes appartements</button>");
        out.println("</form>");
        out.println("<form class=\"navbar-form navbar-right\" action=\"vendreappart\" method=\"POST\">");
        out.println("<button type=\"submit\" class=\"btn btn-primary\">Vendre mon appartements</button>");
        out.println("</form>");
      }
      %>

	</nav>

	<%
		AppartementDataModel appartementDataModel = new AppartementDataModel();
    String action = (String) request.getAttribute("action");
    ArrayList<Appartement> appartements = null;
    if(action == null){
  		appartements = appartementDataModel.getAll();
    }
    else if(action.equals("getByNum") && request.getParameter("numero") != null){
      appartements = new ArrayList<Appartement>();
      int numero = -1;
      if( !((String) request.getParameter("numero")).equals("") )
        numero = Integer.valueOf((String)request.getParameter("numero"));

      Appartement appart = appartementDataModel.get(numero);
      if(appart != null)
        appartements.add(appart);

    }
    else if(action.equals("getByType")){
      appartements = appartementDataModel.getbyType((String) request.getParameter("type"));

    }
    else{
      appartements = appartementDataModel.getAll();
    }

		if(appartements.size() == 0){
			out.println("Aucun resultat pour l'instant.");
		}

		else{
      String[] selection = null;
      if(request.getSession().getAttribute("listeApparts") != null){
        String liste = (String) request.getSession().getAttribute("listeApparts");
        selection = liste.split(",");
      }


			for(int i = 0; i < appartements.size(); ++i){
        out.println("<nav class=\"navbar\">");

				out.println("<div class=\"alert alert-warning\" role=\"alert\">");
				out.println("<span class=\"label label-primary\">Type:</span>" + appartements.get(i).getTypeAppart()+ "</br>");
				out.println("<span class=\"label label-success\">Prix de vente:</span>" + appartements.get(i).getMontantVente()+ " euros </br>");
				out.println("<span class=\"label label-danger\">Adresse:</span>" + appartements.get(i).getAdresse() + "</br>");
				out.println("<span class=\"label label-default\">Proprietaire:</span>" + appartements.get(i).getLoginProp());
        out.println("<form class=\"navbar-form navbar-left\" action=\"ajoutAppartListe\" method=\"POST\">");
              out.println("<input type=\"text\" class=\"form-control\" name=\"numero\" value=\"" + appartements.get(i).getNumero() + "\"style=\"display: none\" >");
              if(!Outils.contient(selection, Integer.toString(appartements.get(i).getNumero())))
                out.println("<button type=\"submit\" class=\"btn btn-default\" ><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></button>");
            out.println("</form>");
				out.println("</div>");
        out.println("</nav>");

			}


		}
	%>


  </body>
</html>
