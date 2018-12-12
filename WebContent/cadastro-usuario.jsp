<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
<link href="resourses/css/cadastro.css" rel="stylesheet" type="text/css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"
	type="text/javascript"></script>

<meta name="viewport" content="width=device-width">

</head>
<body>
	<center>
		<h1>Cadastro de Usuário</h1>
		<h3 style="color: orange">${msg}</h3>
 

		<form action="salvarUsuario" method="post" id="formUser">
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
							<!-- Mostra na hora que formos editar -->
						</tr>

						<tr>
							<td>Senha:</td>
							<td><input type="password" id="senha" name="senha"
								value="${user.senha}"></td>
						</tr>

						<tr>
							<td>Nome:</td>
							<td><input type="text" id="nome" name="nome"
								value="${user.nome}"></td>
						</tr>
						
						<tr>
							<td>Telefone:</td>
							<td><input type="text" id="telefone" name="telefone"
								value="${user.telefone}"></td>
						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="Salvar"> <input
								type="submit" value="Cancelar"
								onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
							<td></td>
						</tr>
					</table>
				</li>
			</ul>
		</form>

	</center>

	<div class="container">
		<table class="responsive-table">

			<caption>Usuários Cadastrado</caption>
			<tr>
				<!-- Linha de título da tabela -->
				<th>#</th>
				<th>LOGIN</th>
				<th>NOME</th>
				<th>TELEFONE</th>
				<th>EXCLUIR</th>
				<th>EDITAR</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					<td><c:out value="${user.login}"></c:out></td>
					<td><c:out value="${user.nome}"></c:out></td>
					<td><c:out value="${user.telefone}"></c:out></td>
					

					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							src="resourses/img/botao-excluir.jpg" alt="Excluir"
							title="excluir" width="20px"></a></td>

					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							alt="Editar" src="resourses/img/botao-editar.png" title="editar"
							width="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- container -->
</body>
</html>















