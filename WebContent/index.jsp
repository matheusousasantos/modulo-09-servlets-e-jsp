<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 08 - Passando Par�mentros</title>
</head>
<body>
	
	<%= "Seu sucesso Garantido!!"%>

	<form action="receber-nome.jsp"> <!-- action - define qual ser� a a��o ap�s o click do bot�o, nesse caso, a p�gina recever-nome-->
		<input type="text" id="nome" name ="nome"/>
		<input type="submit" value="Enviar"/> 
	</form>
</body>
</html>