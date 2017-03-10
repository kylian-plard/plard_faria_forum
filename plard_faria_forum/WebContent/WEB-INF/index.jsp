<div id="menu">
    <p><a href="<c:url value="/createUser"/>">Créer un compte</a></p>
</div>
<h1>Bienvenu ${u.prenom} ${u.nom}</h1>
<c:choose>
	<c:when test="${ error }"><h2 class="error">Vous devez remplir tout les champs !</h2></c:when>
	<c:otherwise><h2 class="success">${ msg }</h2></c:otherwise>
</c:choose>
<form action="" method="POST">
	<label>Identifiant</label>
	<input type="text" name="id"/>
	<label>Mot de passe</label>
	<input type="password" name="mdp"/>
	<input type="submit"/>
</form>
<p>${ !empty param.id ? param.id : '' } &nbsp; ${ !empty param.mdp ? param.mdp : '' }</p>
<p>${date}</p>