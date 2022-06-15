<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserimento Annuncio</title>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<c:if test="${errorMessage != null}">
		<div class="notification is-warning">
			<button class="delete"></button>
			${errorMessage}
		</div>
	</c:if>
	
	
	<div class="container">
		<div class="notification is-primary has-text-centered">
			<h2 class="title is-2">Inserisci Nuovo Annuncio</h2>
		
		
			<form method="post" action="ExecuteInsertAnnuncioServlet">
				
				<div class="columns">
					<div class="column">

						<div class="field">
							<label class="label has-text-centered">Titolo</label>
							<div class="control">
						    	<input class="input" type="text" name="titolo" placeholder="Titolo">
						  	</div>
						</div>
						
						<div class="field">
							<label class="label has-text-centered">Prezzo</label>
							<div class="control">
						    	<input class="input" type="number" name="prezzo" placeholder="Prezzo">
						  	</div>
						</div>
						
					</div>
					
					<div class="column">
					
						<div class="field">
							<label class="label has-text-centered">Descrizione</label>
							<div class="control">
						    	<input class="textarea" type="text" name="testo" placeholder="Descrizione">
						  	</div>
						</div>
						
					</div>
				</div>		
				
				<c:forEach items="${mappa_categorie}" var="categoria" >
					<div class="field">
						<label class="checkbox">
							<input type="checkbox" value="${categoria.key.id}" name="categorie">
							${categoria.key.descrizione}
						</label>
					</div>
				</c:forEach>
						
				<div style="text-align:center">					
					<input class="button is-info" type="submit" value="Inserisci Annuncio">
				</div>

			</form>
			
		</div>
	</div>
			
	<jsp:include page="../footer.jsp" />
</body>
</html>