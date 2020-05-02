<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.modelo.pojo.ProductoSinPrecio" %>
<%!Usuario usuario = null; %>
<%!ProductoSinPrecio producto = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Price Stalker</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
</head>
<body>
	<%
		producto = (ProductoSinPrecio) request.getAttribute("producto");
		usuario = (Usuario) request.getAttribute("usuario");
	%>
	<ul>
		<li><a href="Principal">Price Stalker</a></li>
		<%
			if(usuario != null){
		%>
		<li><%=usuario.getNombre() %></li>
		<li><a href="Lista">Tu lista de productos</a></li>
		<li><a href="Logout">Cerrar sesión</a></li>
		<li><a href="AdminUser">Administrar usuario</a></li>
		<%} %>
	</ul>
	<h1>Eliminar producto</h1>
	<%
		if(producto != null){
	%>
	<div>
		<div><img src="<%=producto.getImgLink() %>" alt="imágen del producto"></div>
		<div>Nombre: <a href="<%= producto.getLink()%>"><%=producto.getNombre() %></a></div>
		<form action="Eliminar" method="POST">
			<p>¿Seguro que quieres eliminar este producto de tu lista?</p>
			<input type="hidden" name="idContenido" value="<%=producto.getId() %>">
			<input type="submit" value="Si, elimina el producto">
			<a href="Lista">No, volver a la lista.</a>
		</form>
	</div>
	<%
		}
	%>
	
</body>
</html>