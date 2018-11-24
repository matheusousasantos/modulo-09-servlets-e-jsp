<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
<link href="resourses/css/cadastro.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>Cadastro de Usuário</h1>

	<form action="salvarUsuario" method="post">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<!-- Linha -->
						<td>Código:</td>
						<!-- célula     Id: somente leitura-->
						<td><input type="text" id="id" readonly="readonly" name="id"
							value="${user.id}" class="field-long"></td>
					</tr>

					<tr>
						<!-- Linha -->
						<td>Login:</td>
						<!-- célula -->
						<td><input type="text" id="login" name="login"
							value="${user.login}"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<table>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td><c:out value="${user.id}"></c:out></td>
				<!-- Mostrando o Id na tabela -->
				<td><c:out value="${user.login}"></c:out></td>
				<td><c:out value="${user.senha}"></c:out></td>

				<td><a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a></td>
				<td><a href="salvarUsuario?acao=editar&user=${user.login}">Editar</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>















