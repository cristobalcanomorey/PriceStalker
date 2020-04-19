<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!String error; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Price Stalker</title>
</head>
<body>
	<ul>
		<li><a href="Principal">Price Stalker</a></li>
	</ul>
	<h1>Eliminar cuenta</h1>
	<h3>¿Estás seguro que quieres eliminar tu cuenta? Se borrarán todos tus datos...</h3>
	<form method="POST" action="DelUser">
		<p>Contraseña</p>
		<input name="password" type="password" required>
		<input type="submit" value="Eliminar usuario">
	</form>
	<%
	error = (String) request.getAttribute("error");
	if(error != null){
		%>
		<div class="error">
		<%=error %>
		</div>
		<%
	}
	%>
</body>
</html>