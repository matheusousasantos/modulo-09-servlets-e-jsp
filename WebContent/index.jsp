<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 08 - Passando Parāmentros</title>
</head>
<body>
	<h1>Index</h1>
	<jsp:forward page="receber-nome.jsp">
	<jsp:param value="www.google.com.br" name="paramforward"/>
	</jsp:forward>
</body>	
</html>