<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 08 - Passando Parâmentros</title>
</head>
<body>

	<%@ page import="java.util.Date" %> <!-- Importando objeto data -->	
	<%= "Data de Hoje: " + new Date() %>
	
	
	<%@ page errorPage = "receber-nome.jsp" %> <!-- Chamando a página de erro! -->
	<%= 100/0 %>

</body>	
</html>