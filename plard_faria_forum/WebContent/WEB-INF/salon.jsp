<nav id="menu">
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h1>Bienvenu ${ user.identifiant }</h1>
<h2>${ libelleSalon }</h2>
<c:forEach var="sujet" items="${ sujetss }">
	<div class="sujet">
		<p><a href="/sujet?id=${sujet.id}" ><c:out value="${sujet.libelle}"/></a></p>
		<c:set var="libelleSujet" scope="session" value="${sujet.libelle}"/>
	</div>
</c:forEach>