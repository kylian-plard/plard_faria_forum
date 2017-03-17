<nav id="menu">
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h1>Bienvenu ${ user.identifiant }</h1>
<c:forEach var="salon" items="${ salons }">
	<div class="salon">
		<p><a href="/salon?id=${salon.id}" ><c:out value="${salon.libelle}"/></a></p>
		<p><c:out value="${salon.description}"/></p>
		<c:set var="libelleSalon" scope="session" value="${salon.libelle}"/>
	</div>
</c:forEach>