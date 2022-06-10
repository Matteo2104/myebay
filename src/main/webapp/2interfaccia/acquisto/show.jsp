<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />		
	<title>Dettaglio annuncio</title>   
</head>
<body>
	
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<div class="container">
		
		<div class="notification is-primary">
			<div class="box">
				<h2 class="title is-2">Dettaglio Acquisto</h2>
				
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Titolo Annuncio:</h5>
				  	</div>
				  	<div class="column">
				    	${show_acquisto_attr.descrizione}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Prezzo:</h5>
				  	</div>
				  	<div class="column">
				    	${show_acquisto_attr.prezzo}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Data Pubblicazione:</h5>
				  	</div>
				  	<div class="column">
				    	<fmt:formatDate type = "date" value = "${show_acquisto_attr.data}" />
				  	</div>
				</div>
				
			</div>
			
			<div>
				<a href="ExecuteListAcquistiServlet" class="button" style='width:100px'>Indietro</a>          	            	        
			</div>
			
		</div>
		
	</div>
	
	<jsp:include page="../footer.jsp" />
</body>
</html>