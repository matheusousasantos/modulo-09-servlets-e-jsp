<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resposta enviada!</title>
</head>
<body>
	<%= "Nome recebido: " + request.getParameter("nome") %> <!-- Teg Expressão -->
	
	<%= request.getContextPath() %>
	<%= request.getLocalPort() %>
	
	<% response.sendRedirect("http://www.google.com.br"); %><!--response - Define a resposta da requisição -->
	<!-- sendRedirect - Direciona aquela resposta -->

</body>
</html>