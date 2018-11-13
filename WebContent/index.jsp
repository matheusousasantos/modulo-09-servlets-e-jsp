<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 08 - Passando Parâmentros</title>
</head>
<body>
	
	<h1>Formulário Aula</h1>
	<form action="receber-nome.jsp">
		<input type="text" id="nome" name="nome"/>
		<input type="submit" value="Enviar"/>
			
	</form>
	<% session.setAttribute("curso", "curso de jsp"); %> <!-- Sessão inserida, pode ser resgatada em qualquer página -->
		
</body>
</html>