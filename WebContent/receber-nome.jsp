<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resposta enviada!</title>
</head>
<body>
	<% out.print(request.getParameter("nome")); %> <!-- request - recebe um par�metro -->
	
	<h3>ou</h3>
	<%= "Nome recebido: " + request.getParameter("nome") %>
	
</body>
</html>