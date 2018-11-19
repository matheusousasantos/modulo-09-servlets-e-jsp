<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Declaração JSTL -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aula 25 - Servlets</title>
</head>
<body>
<!-- (1)Primeiros Passos -->
	<c:out value="${'bem vindo ao JSTL'}"></c:out>
	<%-- <c:import var = "data" url="http://www.google.com.br"/>  --%>
	<c:out value="${data}"/>
	
	<c:set var="data1" scope="page" value="${500*6}"/>
	<c:out value="${data1}"/>
	
	<c:remove var="data"/>
	
<!-- (2)Erros -->
	<c:catch var="erro">
		<% int var = 100/0; %><!-- erro -->
	</c:catch>
	<c:if test="${erro != null}"><!-- Se acontecer o erro -->
		${erro.message}<!-- mostrando o erro -->
	</c:if>

<!-- (3)Condição -->
	<c:set var="num" value="${100/4}"/>
	
	<c:choose>
		<c:when test="${num > 50}">
			<c:out value="${'Maior que 50'}"/>
		</c:when>
		
		<c:when test="${num < 50}">
			<c:out value="${'Menor que 50'}"/>
		</c:when>
		
		<c:otherwise>
			<c:out value="${'Igual a 50'}"/>
		</c:otherwise>
	
	</c:choose>
	
<!-- (4)Repetição -->
	<c:forEach var="n" begin="1" end="${num}">
	<br/>
	Item : ${n}
	</c:forEach>

<!-- (5)Quebrando String -->	
	<c:forTokens items="Matheus-Sousa-Santos" delims="-" var="nome">
		<br>
		Nome: ${nome}
	</c:forTokens>

<!-- (6)Url - Mostra toda url como está sendo montada -->	
	<c:url value="/acessoliberado.jsp" var="acesso">
	<c:param name="para1" value="111"/>
	<c:param name="para2" value="222"/>
	</c:url>
	
	${acesso}
	
<!-- (7)Redircionamento -->
	<c:if test="${num < 50}">
	<c:redirect url="http://www.google.com.br"/>
	
	</c:if>
	
	<p/>
	<p/>
	<p/>
	<p/>

	<h1>Cadastro usuário</h1>
	<form action="LoginServlet" method="post">
	
		Login: <input type="text" id="login" name="login"><br/>
		Senha: <input type="text" id="senha" name="senha"><br/>
			   <input type="submit" value="Logar">
	
	</form>
</body>	
</html>