<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%!Usuario usuario = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Price Stalker</title>
</head>
<body>
	<ul>
		<li><a href="Principal">Price Stalker</a></li>
		<%
		usuario = (Usuario) request.getAttribute("usuario");
		if(usuario != null){
		%>
		<li><%=usuario.getNombre() %></li>
		<li><a href="Lista">Tu lista de productos</a></li>
		<li><a href="Logout">Cerrar sesión</a></li>
		<li><a href="AdminUser">Administrar usuario</a></li>
		<%}else{ %>
		<li><a href="Registro">Registrarse</a></li>
		<li><a href="Login">Iniciar sesión</a></li>
		<%} %>
	</ul>
	
</body>
</html>