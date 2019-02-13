<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
<link href="resourses/css/cadastro.css" rel="stylesheet" type="text/css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"
	type="text/javascript"></script>

<meta name="viewport" content="width=device-width">

</head>
<body>
	<a href="acessoliberado.jsp"
		style="text-decoration: none; color: black;">Inicio</a>
	<a href="index.jsp" style="text-decoration: none; color: black;">Sair</a>

	<center>
		<h1>Cadastro de Usuário</h1>
		<h3 style="color: orange">${msg}</h3>


		<form action="salvarUsuario" method="post" id="formUser"
			onsubmit="return validarCampos()? true : false;" enctype="multipart/form-data">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<!-- Linha -->
							<td>Código:</td>
							<!-- célula     Id: somente leitura-->
							<td><input type="text" id="id" readonly="readonly" name="id"
								value="${user.id}" class="field-long"></td>

							<td>CEP:</td>
							<td><input type="text" id="cep" name="cep"
								onblur="consultarCep();" value="${user.cep}" 
								placeholder="Informe um CEP válido"></td>
						</tr>

						<tr>
							<!-- Linha -->
							<td>Login:</td>
							<!-- célula -->
							<td><input type="text" id="login" name="login"
								value="${user.login}"></td>
							<!-- Mostra na hora que formos editar -->

							<td>Rua:</td>
							<td><input type="text" id="rua" name="rua"
								value="${user.rua}"></td>

						</tr>

						<tr>
							<td>Senha:</td>
							<td><input type="password" id="senha" name="senha"
								value="${user.senha}"></td>

							<td>Bairro:</td>
							<td><input type="text" id="bairro" name="bairro"
								value="${user.bairro}"></td>
						</tr>

						<tr>
							<td>Nome:</td>
							<td><input type="text" id="nome" name="nome"
								value="${user.nome}" placeholder="Informe o nome do Usuário"></td>

							<td>Cidade:</td>
							<td><input type="text" id="cidade" name="cidade"
								value="${user.cidade}" placeholder="Informe o nome da Cidade"></td>
						</tr>

						<tr>
							<td>Telefone:</td>
							<td><input type="text" id="telefone" name="telefone"
								value="${user.telefone}"></td>

							<td>Estado:</td>
							<td><input type="text" id="estado" name="estado"
								value="${user.estado}"></td>
						</tr>

						<tr>
							<td>IBGE:</td>
							<td><input type="text" id="ibge" name="ibge" value="${user.ibge}"></td>
						</tr>
						
						<tr>
						
							<td>Foto:</td>
							<td><input type="file" name="foto" value="Foto"></td>
				
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
				<th>FONES</th>
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
							
					<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img
							alt="Telefones" src="resourses/img/phone.png" title="Telefones"
							width="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- container -->

	<!-- Comandos de JS -->
	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("login").value == "") {
				alert('Informe o login');
				return false;
			}

			else if (document.getElementById("senha").value == "") {
				alert('Informe o senha');
				return false;
			}

			else if (document.getElementById("nome").value == "") {
				alert('Informe o nome');
				return false;
			}

			else if (document.getElementById("telefone").value == "") {
				alert('Informe o telefone');
				return false;
			}

			return true;
		}

		function consultarCep() {

			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {

							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);

						}

						else {

							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');

							alert("CEP não existe!");
						}
					});
		}
	</script>

</body>
</html>















