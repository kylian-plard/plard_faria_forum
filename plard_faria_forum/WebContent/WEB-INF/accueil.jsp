<nav id="menu">
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h1 class="welcome">Bienvenu ${ user.identifiant }</h1>
<c:forEach var="salon" items="${ salons }">
	<div class="salon">
		<p><a href="salon?id=${salon.id}&libelle=${salon.libelle}" ><c:out value="${salon.libelle}"/></a></p>
		<p><c:out value="${salon.description}"/></p>
	</div>
</c:forEach>