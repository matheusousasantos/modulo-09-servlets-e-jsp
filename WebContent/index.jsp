<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Declaração JSTL -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 33 - Login com JDBC e Servlets</title>
<link href="resourses/css/style.css" type="text/css" rel="stylesheet">
</head>
<body>

	<div class="login-page">
		<center><h3>Projeto Didático</h3></center><br>
		<center><h1>JSP + Servlet + JDBC</h1></center><br>
		<center><span><b>USUÁRIO:</b> admin <br> <b>SENHA:</b> admin</span></center><br>
		<div class="form">
			<form action="login-servlet" method="post" class="login-form">

				Login:<input type="text" id="login" name="login">
				Senha:<input type="text" id="senha" name="senha">
				<button type="submit" value="Logar">Logar</button>

			</form>
		</div>
		
		<center><h3><a style="text-decoration: none;" href="https://www.google.com.br">Formação Java Web</a></h3></center>
		
	</div>
</body>
</html>