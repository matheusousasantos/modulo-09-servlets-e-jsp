<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Telefones</title>
<link href="resourses/css/cadastro.css" rel="stylesheet" type="text/css">
</head>
<body>
	<a href="acessoliberado.jsp"
		style="text-decoration: none; color: black;">Inicio</a>
	<a href="index.jsp" style="text-decoration: none; color: black;">Sair</a>

	<center>
		<h1>Cadastro de Telefones</h1>
		<h3 style="color: orange">${msg}</h3>


		<form action="salvarTelefones" method="post" id="formUser"
			onsubmit="return validarCampos()? true : false;">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<!-- Linha -->
							<td>USER:</td>
							<td><input type="text" id="id" readonly="readonly" name="id" 
							value="${usuarioEscolhido.id}"/></td>
							
							<td><input type="text" id="nome" readonly="readonly" name="nome" 
							value="${usuarioEscolhido.nome}"/></td>
								
						</tr>
						
						<tr>
							<td>NÚMERO:</td>
							<td><input type="text" id="numero" name="numero"/></td>
							<td><select	id="tipo" name="tipo">
								<option>Casa</option>
								<option>Contato</option>
								<option>Celular</option>
							</select></td>
						
						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="Salvar"> 
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
				<th>NÚMERO</th>
				<th>TIPO</th>
				<th>EXCLUIR</th>
			</tr>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td><c:out value="${fone.id}"></c:out></td>
					<td><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>

					<td><a href="salvarTelefones?acao=deleteFone&foneId=${fone.id}"><img
							src="resourses/img/botao-excluir.jpg" alt="Excluir"
							title="excluir" width="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- container -->

	<!-- Comandos de JS -->
	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("numero").value == "") {
				alert('Informe o número');
				return false;
			} else
				
			if (document.getElementById("tipo").value == "") {
				alert('Informe o tipo');
				return false;
			}
			
			return true;
		}

		
	</script>

</body>
</html>















