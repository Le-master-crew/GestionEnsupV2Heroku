<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="eu.ensup.gestionscolairespringboot.domaine.Etudiant" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/style.css">
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<meta charset="ISO-8859-1">
	<title>Profil</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="accueil">Gestion Etudiants</a>
	  	<div class="collapse navbar-collapse" id="navbarNavDropdown">
    		<ul class="navbar-nav">
    			<li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Menu �tudiant
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <sec:authorize access='hasRole("ROLE_DIRECTEUR")'>
			          	<a class="dropdown-item" href="listeEtudiants">Lister �tudiants</a>
			          </sec:authorize>
			          <a class="dropdown-item" href="getFormLireEtudiant">Info �tudiant</a>
			          <a class="dropdown-item" href="getFormSupprimerEtudiant">Supprimer un �tudiant</a>
			          <a class="dropdown-item" href="getFormModifierEtudiant">Modifier un �tudiant</a>
			          <a class="dropdown-item" href="getFormAjoutEtudiant">Ajouter un �tudiant</a>
			          <a class="dropdown-item" href="getFormAjoutEtudiantCours">Rajouter un �tudiant � un cours</a>
			          <sec:authorize access='hasRole("ROLE_ENSEIGNANT")'>
			          	<a class="dropdown-item" href="getFormNoterEtudiant">Noter un �tudiant</a>
			          </sec:authorize>
			          <sec:authorize access='hasRole("ROLE_DIRECTEUR")'>
			          	<a class="dropdown-item" href="getMoyenneEtudiants">Afficher la moyenne des �tudiants</a>
			          </sec:authorize>
			        </div>
			    </li>
				<li class="nav-item active" style="padding-right: 10px">
		       		<a class="nav-link" href="logout">D�connexion <span class="sr-only">(current)</span></a>
		   		</li>
    		</ul>
  		</div>
	</nav>
	<div class="jumbotron">
	  <h1>Recherche �tudiant</h1>
	</div>
	
	<meta http-equiv="refresh" content="3;URL=accueil.jsp">
	<div class="container">
        <div class="card card-container">
        	<h2>Erreur lors de la recherche</h2>
            <h4>L'�tudiant n'existe pas</h4>
            <h6>Redirection</h6>
            <div class="spinner-border" role="status">
			  <span class="sr-only">Loading...</span>
			</div>
        <div class="countdown" data-toggle="circularcountdown" data-color="belize-hole" data-to="12/25/2016"></div>
        </div><!-- /card-container -->
    </div><!-- /container -->
</body>
</html>