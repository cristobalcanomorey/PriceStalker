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
	<h1>Iniciar sesión</h1>
	<form method="POST" action="Login">
		<p>Correo electrónico</p>
		<input name="correo" type="email">
		<p>Contraseña</p>
		<input name="password" type="password">
		<input type="submit" value="Iniciar sesión">
	</form>
	<%
	error = (String) request.getAttribute("error");
	if(error != null){
		%>
		<div class="error">
		<%
		if(error.equals("1")){
			out.print("Correo electrónico o contraseña incorrectas.");
		}
		%>
		</div>
		<%
	}
	%>
</body>
</html>