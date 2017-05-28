	 <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Bienvenue sur ImmoJ</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="/immo.J/">Accueil</a></li>
							 <li class="active"><a href="listeappartements">Logements</a></li>
							 <%
							 String[] parses = request.getRequestURL().toString().split("/"); //On parse l'URL
							 String action = parses[parses.length - 1]; //On récupère l'action demandé dans l'URL
							 if(!action.equals("immo.J"))
							 	out.println("<li class=\"active\"><a href=\"listeselection\">Ma selection</a></li>");
							 %>
            </ul>
							<a class="navbar-right" href="deconnexion" >Se deconnecter</span></a>

          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
