<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
</head>
<body>
	<h1>Cadastro de Usuário</h1>
	
	 <form action="salvarUsuario" method="post">
	 	<table>
	 	<tr><!-- Linha -->
	 		<td>Login:</td><!-- célula -->
	 		<td><input type="text" id="login" name="login"></td>
	 	</tr>
	 	
	 	<tr>
	 		<td>Senha:</td>
	 		<td><input type="password" id="senha" name="senha"></td>
	 	</tr>
	 	
	 	</table>
	 	<input type="submit" value="Salvar"> 	
	 </form>
	 
	 <table>
	 	<c:forEach items="${usuarios}" var="user">
	 		<tr>
	 			<td><c:out value="${user.login}"></c:out></td>
	 			<td><c:out value="${user.senha}"></c:out></td>
	 			
	 			<!--(1) Vou apontar pro Servlet passando um parâmentro e definindo o usuário -->
	 			<td><a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a></td>
	 		</tr>
	 	</c:forEach>
	 
	 </table>
	 
</body>
</html>















