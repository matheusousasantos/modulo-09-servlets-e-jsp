<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Declara��o JSTL -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 33 - Login com JDBC e Servlets</title>
</head>
<body>

	<h1>Cadastro usu�rio</h1>
	<form action="LoginServlet" method="post">
	
		Login: <input type="text" id="login" name="login"><br/>
		Senha: <input type="text" id="senha" name="senha"><br/>
			   <input type="submit" value="Logar">
	
	</form>
</body>	
</html>