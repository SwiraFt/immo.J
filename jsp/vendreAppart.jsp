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

  <div class="container">

      <form class="form-signin" action="creerappart" method="POST">
        <h2 class="form-signin-heading">Vendre mon appartements</h2>

        <div class="form-group">
        <label for="inputType" class="">Type du logement</label>
        <SELECT name="type" size="1">
        <OPTION> Studio </OPTION>
        <OPTION> T1 </OPTION>
        <OPTION> T2 </OPTION>
        <OPTION> T3 </OPTION>
        </SELECT>
        </div>

        <label for="inputAdresse" class="">Adresse</label>
        <input type="text" id="inputAdresse" name="adresse" class="form-control" placeholder="Ex : 25 rue du Junit Test Case" required autofocus>

        <label for="inputMontant" class="">Prix de Vente</label>
        <input type="text" id="inputMontant" name="montant" class="form-control" placeholder="Ex : 80000" required>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Mettre en vente</button>
      </form>
    <font color="red" style="text-align:center">
    <%
  String msg = (String) request.getAttribute("msgerreur");
  if(msg != null)
    out.println(msg);
  %>
  </font>

    </div>


  </body>
</html>
