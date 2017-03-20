<nav id="menu">
	<p><a href="<c:url value="/accueil"/>">Retour à l'accueil</a></p>
	<p><a href="<c:url value="/deleteUser"/>">Supprimer le compte</a></p>
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h1 class="welcome">Changer les droits d'administration</h1>
<c:forEach var="user" items="${ users }">
	<div class="user">
		<p class="inline-block">${ user.identifiant } - niveau actuel: ${ user.level==2 ? 'Utilisateur basique' : 'Modérateur' }</p>
		<div class="formLevel inline-block">
			<form action="" method="POST" >
				<input type="hidden" name="idUser" value="${user.identifiant}" />
				<input type="hidden" name="level" value=${ user.level==2 ? 3 : 2 } />
				<input type="image" src=${ user.level==2 ? 'img/plus.png' : 'img/moins.png' } alt=${ user.level==2 ? 'Passer modérateur' : 'Passer Utilisateur basique' } height="30" />
			</form>
		</div>
	</div>
</c:forEach>