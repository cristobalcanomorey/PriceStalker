<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.modelo.pojo.Producto" %>
<%@page import="aplicacion.modelo.pojo.ProductoSinPrecio" %>
<%!ArrayList<Producto> productos; %>
<%!ArrayList<ProductoSinPrecio> productosSinPrecio; %>
<%!Usuario usuario = null; %>
<%!String nombreLista = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Price Stalker</title>
</head>
<body>
	<%
		productos = (ArrayList<Producto>) request.getAttribute("productos");
		productosSinPrecio = (ArrayList<ProductoSinPrecio>) request.getAttribute("productosSinPrecio");
		usuario = (Usuario) request.getAttribute("usuario");
		nombreLista = (String) request.getAttribute("nombreLista");
		String opcionAddProducto = "Añadir producto a la lista";
		if(nombreLista == null){
			opcionAddProducto = "Crear lista de productos";
		}
	%>
	<ul>
		<li><a href="Principal">Price Stalker</a></li>
		<%
			if(usuario != null){
		%>
		<li><%=usuario.getNombre() %></li>
		<li><a href="AddProducto"><%=opcionAddProducto %></a></li>
		<li><a href="Logout">Cerrar sesión</a></li>
		<li><a href="AdminUser">Administrar usuario</a></li>
		<%} %>
	</ul>
	<h1>Lista de productos</h1>
	<%
		if(productos != null | productosSinPrecio != null){
			if(!productos.isEmpty() | !productosSinPrecio.isEmpty()){
				
				%>
				<h3><%=nombreLista %></h3>
				<table>
					<tr>
						<th>Nombre</th>
						<th>Precio actual</th>
						<th>Precio objetivo</th>
						<th>Imágen</th>
						<th></th>
						<th></th>
					</tr>
				<%
				if(!productosSinPrecio.isEmpty()){
					for(ProductoSinPrecio productoSinPrecio : productosSinPrecio){
						String enlace = productoSinPrecio.getLink();
						if(enlace.equals("ProductoNotFound")){
							enlace += "?idContenido="+productoSinPrecio.getId();
						}
						%>
							<tr>
								<td><a href="<%=enlace%>"><%=productoSinPrecio.getNombre()%></a></td>
								<td>Hasta que escaneemos el producto desconocemos el precio actual</td>
								<td><%=String.format("%.2f",productoSinPrecio.getPrecioObjetivo())%> €</td>
								<td><img alt="No se ha podido cargar la imágen" src="<%=productoSinPrecio.getImgLink()%>"></td>
								<td><a href="Grafica?producto=<%=productoSinPrecio.getId()%>">Gráfica</a></td>
								<td><a href="Eliminar?producto=<%=productoSinPrecio.getId()%>">Eliminar producto</a></td>
							</tr>
						<%
					}
				}
				if(!productos.isEmpty()){
					for(Producto producto : productos){
						%>
							<tr>
								<td><a href="<%=producto.getLink()%>"><%=producto.getNombre()%></a></td>
								<td><%=String.format("%.2f",producto.getCoste())%> €</td>
								<td><%=String.format("%.2f",producto.getPrecioObjetivo())%> €</td>
								<td><img alt="No se ha podido cargar la imágen" src="<%=producto.getImgLink()%>"></td>
								<td><a href="Grafica?producto=<%=producto.getId()%>">Gráfica</a></td>
								<td><a href="Eliminar?producto=<%=producto.getId()%>">Eliminar producto</a></td>
							</tr>
						<%
					}	
				}
				%>
				</table>
				<%
			} else{%>
				<h3>No tienes ninguna lista de productos</h3>
				<a href="AddProducto">Crear lista de productos</a>
			<%}
		}
	%>
</body>
</html>