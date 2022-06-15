<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="./header.jsp" />
	<title>Pagina dei Risultati</title>
</head>
<body>
	<jsp:include page="./navbar.jsp"></jsp:include>
	
	<div class="container has-text-centered">
		<div class="notification is-primary">
			<table class="table is-fullwidth">
				<caption style="text-align:center">${annunci_list_attribute.size()} elementi trovati</caption>
	  			<thead> 
	    			<tr>
	      				<th style="text-align:center">Titolo Annuncio</th>
	      				<th style="text-align:center">Prezzo</th>
	      				<th style="text-align:center">Data Pubblicazione</th>
	      				<th style="text-align:center"></th>
	    			</tr>
	  			</thead>
	  			
	  			<tbody>
	  				<c:forEach items="${annunci_list_attribute}" var="annuncio">
					    <tr>
					      	<td>${annuncio.aperto?'':'VENDUTO - '}${annuncio.titolo}</td>
							<td>${annuncio.prezzo }</td>
							<td><fmt:formatDate type = "date" value = "${annuncio.data}" /></td>
							<td>
								<a class="button" style='width:400px' href="${pageContext.request.contextPath}/ExecuteVisualizzaAnnuncioServlet?idAnnuncio=${annuncio.id}">Dettaglio</a>
							</td>
					    </tr>
					</c:forEach>
	  			</tbody>
	  			
			</table>
	
			<div>
				<a href="${pageContext.request.contextPath}" class="button" style='width:100px'>Indietro</a>
			</div>
		
		</div>
	</div>
	
	
	<jsp:include page="./footer.jsp" />
		
</body>
</html>