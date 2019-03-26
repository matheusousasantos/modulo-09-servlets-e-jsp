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
	<a href="acessoliberado.jsp"><img title="Início" src="resourses/img/home.png" width="50px"></a>
	<a href="index.jsp"><img title="Início" src="resourses/img/sair.png" width="50px"></a>

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
								placeholder="Informe um CEP válido" maxlength="9"></td>
						</tr>

						<tr>
							<!-- Linha -->
							<td>Login:</td>
							<!-- célula -->
							<td><input type="text" id="login" name="login"
								value="${user.login}" maxlength="10"></td>
							<!-- Mostra na hora que formos editar -->

							<td>Rua:</td>
							<td><input type="text" id="rua" name="rua"
								value="${user.rua}" maxlength="20"></td>

						</tr>

						<tr>
							<td>Senha:</td>
							<td><input type="password" id="senha" name="senha"
								value="${user.senha}" maxlength="10"></td>

							<td>Bairro:</td>
							<td><input type="text" id="bairro" name="bairro"
								value="${user.bairro}" maxlength="50" maxlength="20"></td>
						</tr>

						<tr>
							<td>Nome:</td>
							<td><input type="text" id="nome" name="nome"
								value="${user.nome}" placeholder="Informe o nome do Usuário" maxlength="50"></td>

							<td>Cidade:</td>
							<td><input type="text" id="cidade" name="cidade"
								value="${user.cidade}" placeholder="Informe o nome da Cidade" maxlength="20"></td>
						</tr>

						<tr>

							<td>Estado:</td>
							<td><input type="text" id="estado" name="estado"
								value="${user.estado}" maxlength="2"></td>
						</tr>

						<tr>
							<td>IBGE:</td>
							<td><input type="text" id="ibge" name="ibge" value="${user.ibge}" maxlength="20"></td>
							
							<td>Ativo:</td>
							<td><input type="checkbox" id="ativo" name="ativo"></td>
							
						</tr>
						
						<tr>
						
							<td>Foto:</td>
							<td>
								<input type="file" name="foto" value="foto">
							</td>
						</tr>
						
						<tr>
							<td>Curriculo:</td>
							<td>
								<input type="file" name="curriculo" value="curriculo">
							</td>
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
				<th>FOTO</th>
				<th>CURRICULO</th>
				<th>NOME</th>
				<th>EXCLUIR</th>
				<th>EDITAR</th>
				<th>FONES</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					
					<c:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
						<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src="<c:out value="${user.fotoBase64Miniatura}"></c:out>" alt="Imagem User" title="Imagem User"
						width="50px" height="50px"></a></td>
					</c:if>
					
					<c:if test="${user.fotoBase64Miniatura == null}">
						<td><img alt="ImagemUser" src="resourses/img/user-img.png" width="50px" onclick="alert('Não pussue imagem')"></td>
					</c:if>
					
					<c:if test="${user.curriculoBase64.isEmpty() == false}">
						<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img alt="Curriculo" src="resourses/img/pdf.png" width="50px"/></a></td>
					</c:if>
					
					<c:if test="${user.curriculoBase64 == null}">
						<td><img alt="Curriculo" src="resourses/img/pdf.png" width="50px" onclick="alert('Não possue curriculo')"/></td>
					</c:if>
					
					<td><c:out value="${user.nome}"></c:out></td>

					<td><a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirmar a exclusão?');" ><img
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















