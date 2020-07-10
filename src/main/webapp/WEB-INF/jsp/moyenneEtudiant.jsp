<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	
<!DOCTYPE html>
<html html lang="en" xml:lang="en">
<head>
  	<link rel="stylesheet" href="css/styleGraph.css">
  	<link rel="stylesheet" href="css/style.css">
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<meta charset="ISO-8859-1">
<title>Moyenne étudiants</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="accueil">Gestion Etudiants</a>
	  	<div class="collapse navbar-collapse" id="navbarNavDropdown">
    		<ul class="navbar-nav">
    			<li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Menu étudiant
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <sec:authorize access='hasRole("ROLE_DIRECTEUR")'>
			          	<a class="dropdown-item" href="listeEtudiants">Lister étudiants</a>
			          </sec:authorize>
			          <a class="dropdown-item" href="getFormLireEtudiant">Info étudiant</a>
			          <a class="dropdown-item" href="getFormSupprimerEtudiant">Supprimer un étudiant</a>
			          <a class="dropdown-item" href="getFormModifierEtudiant">Modifier un étudiant</a>
			          <a class="dropdown-item" href="getFormAjoutEtudiant">Ajouter un étudiant</a>
			          <a class="dropdown-item" href="getFormAjoutEtudiantCours">Rajouter un étudiant à un cours</a>
			          <sec:authorize access='hasRole("ROLE_ENSEIGNANT")'>
			          	<a class="dropdown-item" href="getFormNoterEtudiant">Noter un étudiant</a>
			          </sec:authorize>
			          <sec:authorize access='hasRole("ROLE_DIRECTEUR")'>
			          	<a class="dropdown-item" href="getMoyenneEtudiants">Afficher la moyenne des étudiants</a>
			          </sec:authorize>
			        </div>
			    </li>
				<li class="nav-item active" style="padding-right: 10px">
		       		<a class="nav-link" href="logout">Déconnexion <span class="sr-only">(current)</span></a>
		   		</li>
    		</ul>
  		</div>
	</nav>
<div class="container">
  <h2>Graphique des moyennes des étudiants<h2>
  <div>
    <canvas id="canvas"></canvas>
  </div>
</div>


</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>

<script>
var lineChartData = {
		${listeMoyenneEtudiants}
	}
	Chart.defaults.global.animationSteps = 50;
	Chart.defaults.global.tooltipYPadding = 16;
	Chart.defaults.global.tooltipCornerRadius = 0;
	Chart.defaults.global.tooltipTitleFontStyle = "normal";
	Chart.defaults.global.tooltipFillColor = "rgba(0,160,0,0.8)";
	Chart.defaults.global.animationEasing = "easeOutBounce";
	Chart.defaults.global.responsive = true;
	Chart.defaults.global.scaleLineColor = "black";
	Chart.defaults.global.scaleFontSize = 16;
	var ctx = document.getElementById("canvas").getContext("2d");
	var LineChartDemo = new Chart(ctx).Line(lineChartData, {
	    pointDotRadius: 10,
	    bezierCurve: false,
	    scaleShowVerticalLines: false,
	    scaleGridLineColor: "black"
	});
</script>
</html>
