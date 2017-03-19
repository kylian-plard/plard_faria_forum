<nav id="menu">
	<p><a href="<c:url value="/accueil"/>">Retour à l'accueil</a></p>
	<p><a href="<c:url value="/deleteUser"/>">Supprimer le compte</a></p>
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h1 class="welcome">Bienvenu ${ user.identifiant }</h1>
<h2><c:out value="${ param.libelle }"/></h2>
<c:forEach var="sujet" items="${ sujets }">
	<div class="sujet">
		<c:if test="${ user.level==1 || user.level==3 }">
			<form class="formDelete inline-block" action="" method="POST" >
				<input type="hidden" name="action" value=1 />
				<input type="hidden" name="idSujet" value="${sujet.id}" />
				<input type="image" src="img/bin.png" alt="Supprimer" height="30" />
			</form>
		</c:if>
		<p class="inline-block"><a href="sujet?id=${sujet.id}&libelle=${sujet.libelle}" ><c:out value="${sujet.libelle}"/></a></p>
		<hr>
		<p>${ sujet.auteur==null ? 'Compte supprimé' : sujet.auteur } ${ sujet.date }</p>
	</div>
</c:forEach>
<br>
<form action="" method="POST">
	<input type="hidden" name="action" value=0 />
	<label>Titre du sujet</label>
	<input type="text" name="titre" required />
	<br>
	<label>Message</label>
	<textarea rows="10" name="msg" required></textarea>
	<input type="submit" value="Créer un nouveaux sujet" />
</form>