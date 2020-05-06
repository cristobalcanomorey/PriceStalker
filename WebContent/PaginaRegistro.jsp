<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!String error; %>
<%!String nombre; %>
<%!String correo; %>
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
	<%
		nombre = (String) request.getAttribute("nombre");
		correo = (String) request.getAttribute("correo");
		if(nombre == null){
			nombre = "";
		}
		if(correo == null){
			correo = "";
		}
	%>
	<h1>Crear cuenta</h1>
	<form method="POST" action="Registro">
		<p>Nombre de usuario</p>
		<input name="nombre" type="text" value="<%=nombre %>" required>
		<p>Correo electrónico</p>
		<input name="correo" type="email" value="<%=correo %>" required>
		<p>Contraseña</p>
		<input name="password" type="password" required>
		<p>Repite la contraseña</p>
		<input name="confirmaPassword" type="password" required>
		<input type="submit" value="Crear usuario">
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