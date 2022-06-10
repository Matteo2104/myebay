<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina degli Acquisti</title>
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
	  				<c:forEach items="${list_acquisti_attr}" var="acquisto">
					    <tr>
					      	<td>${acquisto.descrizione}</td>
							<td>${annuncio.prezzo }</td>
							<td><fmt:formatDate type = "date" value = "${acquisto.data}" /></td>
							<td>			
											
								<a class="button is-light" style="width:300px" href="ExecuteVisualizzaAcquistoServlet?idAcquisto=${acquisto.id}">Dettaglio</a>				
								
								
								
							</td>
					    </tr>
					</c:forEach>
	  			</tbody>
	  			
			</table>

			<div>
				<a href="${pageContext.request.contextPath}/2interfaccia/areapersonale.jsp" class="button" style='width:100px'>Indietro</a>
			</div>
	
		</div>
	</div>
	
	
	<jsp:include page="../footer.jsp" />
		
</body>
</html>