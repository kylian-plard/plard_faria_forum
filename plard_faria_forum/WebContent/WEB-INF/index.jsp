<nav id="menu">
	<p><a href="<c:url value="/inscription"/>">Cr�er un compte</a></p>
	<p><a href="<c:url value="/deconnexion"/>">D�connexion</a></p>
</nav>
<c:if test="${ error }"><h2 class="error">${ msg }</h2></c:if>
<form action="" method="POST">
	<label>Identifiant</label>
	<input type="text" name="id"/>
	<label>Mot de passe</label>
	<input type="password" name="mdp"/>
	<input type="submit"/>
</form>
<p>${date}</p>