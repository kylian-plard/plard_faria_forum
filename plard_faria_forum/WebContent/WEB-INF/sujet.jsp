<nav id="menu">
	<p><a href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
</nav>
<h2 class="welcome">${ param.libelle }</h2>
<c:forEach var="message" items="${ messages }">
	<div class="message">
		<p><c:out value="${message.msg}"/></p>
		<hr>
		<p>${ message.auteur } ${ message.date }</p>
	</div>
</c:forEach>

<form action="" method="POST">
	<input type="hidden" value="${ param.id }"/>
	<textarea rows="10" cols="100" name="msg"></textarea>
	<input type="submit" value="Répondre">
</form>