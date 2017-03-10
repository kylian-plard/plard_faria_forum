<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Forum Plard Faria</title>
	</head>
	<body>
		<h1>Bienvenu ${u.prenom} ${u.nom}</h1>
		<h1>${ msg }</h1>
		<form action="" method="POST">
			<label>Identifiant</label>
			<input type="text" name="id"/>
			<label>Mot de passe</label>
			<input type="password" name="mdp"/>
			<input type="submit"/>
		</form>
		<p>
			${ !empty param.id ? param.id : '' } &nbsp; ${ !empty param.mdp ? param.mdp : '' }
		</p>${date}</p>
		<p>
	</body>
</html>