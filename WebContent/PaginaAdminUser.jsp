<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%!Usuario usuario = null; %>
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
	<h1>Editar cuenta</h1>
	<%
		usuario = (Usuario) request.getAttribute("usuario");
		if(usuario != null){
	%>
	<form method="POST" action="AdminUser">
		<p>Nombre de usuario</p>
		<input name="nombre" type="text" value="<%=usuario.getNombre() %>" required>
		<p>Correo electrónico</p>
		<input name="correo" type="email" value="<%=usuario.getCorreo() %>" required>
		<p>Contraseña actual</p>
		<input name="password" type="password" required>
		<p>Nueva contraseña</p>
		<input name="password2" type="password" required>
		<p>Repite la nueva contraseña</p>
		<input name="confirmaPassword2" type="password" required>
		<input type="submit" value="Editar usuario">
	</form>
	<a href="DelUser">Eliminar usuario</a>
	<%
		}else{
	%>
	<h3>Algo ha salido mal...</h3>
	<a href="Principal">volver a la página principal</a>
	<%} %>
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