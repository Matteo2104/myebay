<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica Annuncio</title>
</head>
<body>
	   
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<div class="container">
		<c:if test="${errorMessage != null}">
			<div class="notification is-warning">
				<button class="delete"></button>
				${errorMessage}
			</div>
		</c:if>
		
		
		<div class="notification is-primary has-text-centered">
			<h2 class="title is-2">Modifica annuncio</h2>
			
			<form method="post" action="ExecuteEditAnnuncioServlet">
				
				<div class="columns">
					<div class="column">

						<div class="field">
							<label class="label has-text-centered">Titolo</label>
							<div class="control">
						    	<input class="input" type="text" name="titolo" placeholder="Titolo" value="${edit_annuncio_attr.titolo}">
						  	</div>
						</div>
						
						<div class="field">
							<label class="label has-text-centered">Prezzo</label>
							<div class="control">
						    	<input class="input" type="number" name="prezzo" placeholder="Prezzo" value="${edit_annuncio_attr.prezzo}">
						  	</div>
						</div>
						
					</div>
					
					<div class="column">
					
						<div class="field">
							<label class="label has-text-centered">Descrizione</label>
							<div class="control">
						    	<input class="textarea" type="text" name="testo" placeholder="Cognome" value="${edit_annuncio_attr.testoAnnuncio}">
						  	</div>
						</div>
						

						
						
					</div>
					
					
				</div>	
				
				<c:forEach items="${mappa_categorie}" var="categoria" >
						<div class="field">
							<label class="checkbox">
								<input type="checkbox" value="${categoria.key.id}" name="categorie" ${categoria.value?'checked':''}>
								${categoria.key.descrizione}
							</label>
						</div>
					</c:forEach>	
				
				<div align="center">
					<a href="${pageContext.request.contextPath}/annuncio/ExecuteListAnnunciPersonaliServlet" class="button" style='width:100px'>Indietro</a>
					<input class="button is-info" type="submit" value="Modifica Annuncio">
				</div>

			</form>
			
			
		</div>
	</div>
	
	<jsp:include page="../footer.jsp" />
	    
			
</body>
</html>