<div id="menu">
    <p><a href="<c:url value="/hello"/>">Connexion</a></p>
</div>
<h1>Cr�er un nouveau compte</h1>
<c:if test="${ error }"><h2 class="error">Vous devez remplir tout les champs !</h2></c:if>
<form action="" method="POST">
	<label>Identifiant</label>
	<input type="text" name="id"/>
	<label>Mot de passe</label>
	<input type="password" name="mdp"/>
	<input type="submit"/>
</form>