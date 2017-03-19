<nav id="menu">
	<p><a href="<c:url value="/deleteUser"/>">Supprimer le compte</a></p>
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h2 class="welcome">${ param.libelle }</h2>
<c:forEach var="message" items="${ messages }">
	<div class="message">
		<c:if test="${ user.level==1 || user.level==3 }">
			<form class="formDelete inline-block" action="" method="POST" >
				<input type="hidden" name="action" value=1 />
				<input type="hidden" name="idMessage" value="${message.id}" />
				<input type="image" src="img/bin.png" alt="Supprimer" height="30" />
			</form>
		</c:if>
		<p><c:out value="${message.msg}"/></p>
		<hr>
		<p>${ message.auteur==null ? 'Compte supprimé' : message.auteur } ${ message.date }</p>
	</div>
</c:forEach>
<br>
<form action="" method="POST">
	<textarea rows="10" name="msg" required></textarea>
	<input type="submit" value="Répondre">
</form>