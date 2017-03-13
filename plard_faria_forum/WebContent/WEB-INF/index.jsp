<div id="menu">
	<p><a href="<c:url value="/createUser"/>">Créer un compte</a></p>
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</div>
<c:if test="${ !empty error }">
	<c:if test="${ error }"><h2 class="error">Vous devez remplir tout les champs !</h2></c:if>
	<c:if test="${ !error }">
		<%-- Vérification de la présence d'un objet utilisateur en session --%>
		<c:if test="${!empty sessionScope.user}">
		    <%-- Si l'utilisateur existe en session, alors on affiche --%>
		    <p class="succes">Bienvenu ${sessionScope.user.identifiant}</p>
		</c:if>
		<c:if test="${empty sessionScope.user}">
		    <%-- Si l'utilisateur n'existe pas en session, alors erreur --%>
		    <p class="error">Mauvais identifiant ou mot de passe !</p>
		</c:if>
	</c:if>
</c:if>
<form action="" method="POST">
	<label>Identifiant</label>
	<input type="text" name="id"/>
	<label>Mot de passe</label>
	<input type="password" name="mdp"/>
	<input type="submit"/>
</form>
<p>${ !empty param.id ? param.id : '' } &nbsp; ${ !empty param.mdp ? param.mdp : '' }</p>
<p>${date}</p>