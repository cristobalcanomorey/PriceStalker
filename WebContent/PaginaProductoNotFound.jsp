<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!String idContenido = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Price Stalker</title>
</head>
<body>
	<%
		idContenido = (String) request.getAttribute("idContenido");
	%>
	<ul>
		<li><a href="Principal">Price Stalker</a></li>
	</ul>
	<h1>Enlace no funciona</h1>
	<p>Se ha producido un error que ha impedido que podamos obtener información de este enlace.</p>
	<p>Es posible que nosotros no conozcamos esa tienda o que el enlace que has introducido no sea correcto.</p>
	<a href="Eliminar?producto="+<%=idContenido %>>Eliminar producto de tu lista.</a>
</body>
</html>