<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 08 - Passando Parâmentros</title>
</head>
<body>
	
	<%= "Seu sucesso Garantido!!"%>

	<form action="receber-nome.jsp"> <!-- action - define qual será a ação após o click do botão, nesse caso, a página recever-nome-->
		<input type="text" id="nome" name ="nome"/>
		<input type="submit" value="Enviar"/> 
	</form>
</body>
</html>