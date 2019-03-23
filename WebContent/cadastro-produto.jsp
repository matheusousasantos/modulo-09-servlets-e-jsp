	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="resourses/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resourses/javascript/jquery.maskMoney.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="resourses/css/cadastro.css"/>
</head>
<body>
	<a href="acessoliberado.jsp"><img title="Início" src="resourses/img/home.png" width="50px"></a>
	<a href="index.jsp"><img title="Início" src="resourses/img/sair.png" width="50px"></a>
	
	<h1>Produtos</h1>
	<h3>${msg}</h3>
	
	<form action="salvarProduto" method="post" id="formularioProdutos" onsubmit="return validarCampos() ? true : false">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" id="id" 
				name="id" value ="${x.id}" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td>NOME</td>
				<td><input type="text" id="nome" name="nome" value ="${x.nome}" maxlength="7"></td>
			</tr>
			
			<tr>
				<td>QUANTIDADE Uni.</td>
				<td><input type="text" id="quantidade" name="quantidade" value ="${x.quantidade}" maxlength="7"></td>
			</tr>
			
			<tr>
				<td>VALOR</td>
				<td><input type="text" id="valor" maxlength="8" data-thousands="." data-decimal="," 
				data-precision="2" name="valor" value ="${x.valorEmTexto}" ></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Enviar">  
				<input type="submit" value="Cancelar" 
				onclick="document.getElementById('formularioProdutos').action = 'salvarProduto?acao=reset'"></td>
				<td></td>
			</tr>
			
		</table>
	</form>

   <div class="container">
	<table class="responsive-table">
	
	<caption>Produtos</caption>
		
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>QUANTIDADE</th>
			<th>VALOR</th>
			<th>EXCLUIR</th>
			<th>EDITAR</th>
		</tr>
		
		<c:forEach items="${produtos}" var="x">
			<tr>
				<td><c:out value="${x.id}"/></td>
				<td><c:out value="${x.nome}"/></td>
				<td><c:out value="${x.quantidade}"/></td>
				
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${x.valor}"/></td>
				<td><a href="salvarProduto?acao=deletar&&id=${x.id}" title="Exclur">
				
				<img src="resourses/img/botao-excluir.jpg" width="20px"/></a></td>
				
				<td><a href="salvarProduto?acao=editar&&id=${x.id}" title="Editar">
				<img src="resourses/img/botao-editar.png" width="20px"/></a></td>
			</tr>
		</c:forEach>
	
	</table>
  </div>
  
  <script type="text/javaScript">
	function validarCampos(){
		
		if(document.getElementById("nome").value == ""){
			alert('Nome não informado!');
			return false;
		}
		
		if(document.getElementById("quantidade").value == ""){
			alert('Quantidade não informado!');
			return false;
		}
		
		if(document.getElementById("valor").value == ""){
			alert('Valor não informado!');
			return false;
		}
		
		return true;
		
	}
  	

  </script>
</body>

 <script type="text/javascript">

	$(function() {
	    $('#valor').maskMoney();
	 });
	 
	 $(document).ready(function() {
		 $("#quantidade").keyup(function(){
			 $("#quantidade").val(this.value.match(/[0-9]*/));
		 });
	 });

</script>

</html>