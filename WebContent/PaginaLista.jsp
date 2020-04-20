<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.modelo.pojo.Producto" %>
<%!ArrayList<Producto> productos; %>
<%!Usuario usuario = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Price Stalker</title>
</head>
<body>
	<%
		productos = (ArrayList<Producto>) request.getAttribute("productos");
		usuario = (Usuario) request.getAttribute("usuario");
	%>
	<ul>
		<li><a href="Principal">Price Stalker</a></li>
		<%
			if(usuario != null){
		%>
		<li><%=usuario.getNombre() %></li>
		<li><a href="AddProducto">Añadir producto a lista</a></li>
		<li><a href="Logout">Cerrar sesión</a></li>
		<li><a href="AdminUser">Administrar usuario</a></li>
		<%} %>
	</ul>
	<h1>Lista de productos</h1>
	<%
		if(productos != null){
			if(!productos.isEmpty()){
				
				%>
				<table>
					<tr>
						<th>Nombre</th>
						<th>Precio</th>
						<th>Imágen</th>
						<th></th>
					</tr>
				<%
				
				for(Producto producto : productos){
					%>
						<tr>
							<td><a href="<%=producto.getLink()%>"><%=producto.getNombre()%></a></td>
							<td><%=producto.getCoste()%></td>
							<td><img alt="<%=producto.getNombre()%>" src="<%=producto.getImgLink()%>"></td>
							<td><a href="Grafica?producto=<%=producto.getId()%>">Gráfica</a></td>
						</tr>
					<%
				}
				
				%>
				</table>
				<%
				
			}
		}
	%>
</body>
</html>