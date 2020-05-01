<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%!Usuario usuario = null; %>
<%!String nombreLista = null; %>
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
		nombreLista = (String) request.getAttribute("nombreLista");
		if(usuario != null){
		%>
		<li><%=usuario.getNombre() %></li>
		<li><a href="Lista">Tu lista de productos</a></li>
		<li><a href="Logout">Cerrar sesión</a></li>
		<li><a href="AdminUser">Administrar usuario</a></li>
		<%} %>
	</ul>
	<h1>
		Añadir producto
		<%
			if(nombreLista != null){
				out.print(" en la lista: " + nombreLista);
			}
		%>
	</h1>
	<form method="POST" action="AddProducto">
		<p>Enlace a la página de compra</p>
		<input name="enlace" type="text" required>
		<%
			if(nombreLista == null){
				out.print("<p>Nombre de la lista </p><input name='nombreLista' type='text' required>");
			}
		%>
		<p>Precio objetivo</p>
		<input name="precioObjetivo" type="number" step="0.01" min="0.01" required>
		<input type="submit" value="Añadir producto a la lista">
	</form>
</body>
</html>