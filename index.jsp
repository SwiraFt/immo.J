<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="res/style/bootstrap.css"/> 
    <title>Accueil</title>
  </head>
  <body>
  
	<jsp:include page="res/includes/header.jsp" />
	
	 <div class="container">
	 
      <form class="form-signin" action="authentification" method="POST">
        <h2 class="form-signin-heading">Se connecter</h2>
        <label for="inputPseudo" class="sr-only">Identifiant</label>
        <input type="text" id="inputPseudo" name="pseudo" class="form-control" placeholder="Identifiant" required autofocus>
        <label for="inputPassword" class="sr-only">Mot de passe</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Mot de passe" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div>
	
	<div class="container">
	 
      <form class="form-signin" action="inscription" method="POST">
        <h2 class="form-signin-heading">S'inscrire</h2>
		
		<label for="inputNom" class="sr-only">Nom</label>
        <input type="text" id="inputNom" name="nom" class="form-control" placeholder="Nom" required autofocus>
		
        <label for="inputPseudo" class="sr-only">Identifiant</label>
        <input type="text" id="inputPseudo" name="pseudo" class="form-control" placeholder="Identifiant" required autofocus>
		
        <label for="inputPassword" class="sr-only">Mot de passe</label>
        <input type="password" id="inputPassword" name="mdp" class="form-control" placeholder="Mot de passe" required>
		
		<label for="inputEmail" class="sr-only">Email</label>
        <input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email" required autofocus>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div>



  </body>
</html>
