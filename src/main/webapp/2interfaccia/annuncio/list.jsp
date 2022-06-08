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
		
		<c:if test="${errorMessage != null}">
			<div class="notification is-warning">
				<button class="delete"></button>
				${errorMessage}
			</div>
		</c:if>

		<c:if test="${successMessage != null}">
			<div class="notification is-info">
				<button class="delete"></button>
				${successMessage}
			</div>
		</c:if>

		<div class="notification is-primary">
			<table class="table is-fullwidth">
	  			<thead> 
	    			<tr>
	      				<th align="center">Titolo Annuncio</th>
	      				<th align="center">Prezzo</th>
	      				<th align="center">Data Pubblicazione</th>
	      				<th align="center"></th>
	    			</tr>
	  			</thead>
	  			
	  			<tbody>
	  				<c:forEach items="${annunci_list_attribute}" var="annuncio">
					    <tr>
					      	<td>${annuncio.aperto?'':'VENDUTO - '}${annuncio.titolo}</td>
							<td>${annuncio.prezzo }</td>
							<td><fmt:formatDate type = "date" value = "${annuncio.data}" /></td>
							<td>							
								<a class="btn  btn-sm btn-outline-secondary" style='width:100px' href="${pageContext.request.contextPath}/annuncio/ExecuteVisualizzaAnnunciPersonaliServlet?idAnnuncio=${annuncio.id}">Dettaglio</a>				
								<c:if test="${annuncio.aperto}">
									<a class="button is-warning" style='width:100px' href="${pageContext.request.contextPath}/annuncio/PrepareEditAnnuncioServlet?idAnnuncio=${annuncio.id}">Modifica</a>
									<a class="button is-danger" style='width:100px' href="${pageContext.request.contextPath}/annuncio/PrepareDeleteAnnuncioServlet?idAnnuncio=${annuncio.id}">Rimuovi</a>
								</c:if>
							</td>
					    </tr>
					</c:forEach>
	  			</tbody>
	  			
			</table>

			<div>
				<a href="${pageContext.request.contextPath}/annuncio/PrepareSearchAnnunciPersonaliServlet" class="button" style='width:100px'>Indietro</a>
			</div>
	
		</div>
	</div>
	
	
	<jsp:include page="../footer.jsp" />
		
</body>
</html>