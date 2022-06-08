<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserimento Annuncio</title>
</head>
<body class="d-flex flex-column h-100">

	<c:if test="${errorMessage != null}">
		<div class="notification is-warning">
			<button class="delete"></button>
			${errorMessage}
		</div>
	</c:if>
	
	
	<div class="container">
		<div class="notification is-primary has-text-centered">
			<h1 class="title is-1">Inserisci Nuovo Annuncio</h1>
		</div>
		
		<div class="notification is-primary">
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
						    	<input class="input" type="text" name="testo" placeholder="Cognome">
						  	</div>
						</div>

						<c:forEach items="${mappa_categorie}" var="categoria" >
							<div class="field">
								<label class="checkbox">
									<input type="checkbox" value="${categoria.id}" name="categorie">
									${categoria.descrizione}
								</label>
							</div>
						</c:forEach>
						
					</div>
				</div>		
				
				<div align="center">
					<input class="button is-info" type="submit" value="Inserisci Annuncio">
				</div>

			</form>
			
		</div>
	</div>
			
	<jsp:include page="footer.jsp" />
</body>
</html>