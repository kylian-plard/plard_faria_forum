<nav id="menu">
	<p><a href="<c:url value="/accueil"/>">Retour à l'accueil</a></p>
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h1 class="welcome">Bienvenu ${ user.identifiant }</h1>
<h2><c:out value="${ param.libelle }"/></h2>
<c:forEach var="sujet" items="${ sujets }">
	<div class="sujet">
		<p><a href="sujet?id=${sujet.id}&libelle=${sujet.libelle}" ><c:out value="${sujet.libelle}"/></a></p>
		<hr>
		<p>${ sujet.auteur } ${ sujet.date }</p>
	</div>
</c:forEach>