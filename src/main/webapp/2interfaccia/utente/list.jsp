<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei Risultati</title>
</head> 
<body>
	
	<jsp:include page="../navbar.jsp"></jsp:include>
	 	
	<div class="container has-text-centered">
		<div class="notification is-primary">
			<table class="table is-fullwidth">
				<caption style="text-align:center">${users_list_attr.size()} elementi trovati</caption>
	  			<thead> 
	    			<tr>
	      				<th style="text-align:center">Nome</th>
	      				<th style="text-align:center">Cognome</th>
	      				<th style="text-align:center">Username</th>
	      				<th style="text-align:center">Data Creazione</th>
	      				<th style="text-align:center"></th>
	    			</tr>
	  			</thead>
	  			
	  			<tbody>
	  				<c:forEach items="${users_list_attr}" var="userItem">
					    <tr>
					      	<td>${userItem.nome}</td>
							<td>${userItem.cognome}</td>
							<td>${userItem.username}</td>
							<td><fmt:formatDate type = "date" value = "${userItem.dateCreated}" /></td>
							<td>
								<a class="button" href="${pageContext.request.contextPath}/utente/ExecuteVisualizzaUserServlet?idUser=${userItem.id}">Dettaglio</a>
								<a class="button is-warning" href="${pageContext.request.contextPath}/utente/PrepareEditUserServlet?idUser=${userItem.id}">Modifica</a>
								
								<!-- I DUE BOTTONI ATTERRANO SULLA STESSA JSP MA SONO STATI DIVISI PER LEGGIBILITA -->
								<c:if test="${userItem.stato == 'DISABILITATO'}">
									<a class="button is-info" href="${pageContext.request.contextPath}/utente/PrepareActionUserServlet?idUser=${userItem.id}">Abilita</a>
		                		</c:if>
		                		<c:if test="${userItem.stato != 'DISABILITATO'}">
		                			<a class="button is-danger" href="${pageContext.request.contextPath}/utente/PrepareActionUserServlet?idUser=${userItem.id}">Disabilita</a>
		                		</c:if>    		
							</td>
					    </tr>
					</c:forEach>
	  			</tbody>
	  			
			</table>
			
			<div>
				<a href="${pageContext.request.contextPath}/utente/PrepareSearchUserServlet" class="button" style='width:100px'>Indietro</a>
			</div>
	
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
		
</body>
</html>