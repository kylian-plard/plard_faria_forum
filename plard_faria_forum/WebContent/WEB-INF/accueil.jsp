<nav id="menu">
	<c:if test="${ user.level==1 }"><p><a href="<c:url value="/changeLevel"/>">Changer les droits d'aministration</a></p></c:if>
	<p><a href="<c:url value="/deleteUser"/>">Supprimer le compte</a></p>
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h1 class="welcome">Bienvenue ${ user.identifiant }</h1>
<c:forEach var="salon" items="${ salons }">
	<div class="salon">
		<c:if test="${ user.level==1 || user.level==3 }">
			<form class="formDelete inline-block" action="" method="POST" >
				<input type="hidden" name="action" value=1 />
				<input type="hidden" name="idSalon" value="${salon.id}" />
				<input type="image" src="img/bin.png" alt="Supprimer" height="30" />
			</form>
		</c:if>
		<p class="inline-block"><a href="salon?id=${salon.id}&libelle=${salon.libelle}" ><c:out value="${salon.libelle}"/></a></p>
		<p><c:out value="${salon.description}"/></p>
	</div>
</c:forEach>
<c:if test="${ user.level==1 || user.level==3}">
	<br>
	<form action="" method="POST">
		<label>Titre du salon</label>
		<input type="text" name="titre" required />
		<br>
		<label>Description</label>
		<textarea rows="10" name="msg" required></textarea>
		<input type="hidden" name="action" value=0 />
		<input type="submit" value="Créer un nouveau salon" />
	</form>
</c:if>