<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />	
	<title>Elimina annuncio</title>
</head>
<body>
	
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<div class="container">
		<div class="notification is-primary">
			<div class="box">
				<h2 class="title is-2">Sei sicuro di voler eliminare questo annuncio?</h2>
				
				<div class="columns">
					<div class="column">
						<h5 class="title is-5">Testo Annuncio:</h5>
					</div>
				  	<div class="column">
				    	${delete_annuncio_attr.testoAnnuncio}
				  	</div>
				</div>
						
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Prezzo:</h5>
				  	</div>
				  	<div class="column">
				    	${delete_annuncio_attr.prezzo}
				  	</div>
				</div>
						
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Data Pubblicazione:</h5>
				  	</div>
				  	<div class="column">
				    	<fmt:formatDate type = "date" value = "${delete_annuncio_attr.data}" />
				  	</div>
				</div>
			
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Categorie:</h5>
				  	</div>
				  	<div class="column">
				  		<c:forEach items="${delete_annuncio_attr.categorie}" var="categoria">
				    		${categoria.descrizione}; 
				  		</c:forEach>
				  	</div>
				</div>
						
					
	
				<div>
					
					<form action="ExecuteDeleteAnnuncioServlet" method="post">
			    		<a href="ExecuteListAnnunciPersonaliServlet" class="button" style='width:80px'>Indietro</a>           	            	        
			    		
			    		
			    		<input type="hidden" name="idAnnuncio" value="${delete_annuncio_attr.id}">
				    	<button type="submit" name="submit" id="submit" class="button is-danger">Elimina</button>
					</form>           	            	        
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="../footer.jsp" />  
						
</body>
</html>