<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />	
	<title>Disabilita Utente</title>
</head>
<body>
	
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	
	<div class="container">
		<div class="notification is-primary">
			
			<div class="box">
				<h2 class="title is-2">Sei sicuro di voler l'utente con ID : ${delete_utente_attr.id}?</h2>
				
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Nome:</h5>
				  	</div>
				  	<div class="column">
				    	${delete_utente_attr.nome}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Cognome:</h5>
				  	</div>
				  	<div class="column">
				    	${delete_utente_attr.cognome}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Username:</h5>
				  	</div>
				  	<div class="column">
				    	${delete_utente_attr.username}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Data di Creazione:</h5>
				  	</div>
				  	<div class="column">
				    	<fmt:formatDate type = "date" value = "${delete_utente_attr.dateCreated}" />
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Stato:</h5>
				  	</div>
				  	<div class="column">
				    	${delete_utente_attr.stato}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Ruolo:</h5>
				  	</div>
				  	<div class="column">
				    	${delete_utente_attr.ruolo}
				  	</div>
				</div>

			</div>
			
			<div>
				         	            	        
				
				<form action="ExecuteDeleteUserServlet" method="post">
					<a href="ExecuteListUserServlet" class="button" style='width:80px'>Indietro</a>  
					
					
		    		<input type="hidden" name="idUser" value="${delete_utente_attr.id}">
			    	<button type="submit" name="submit" id="submit" class="button is-danger">Disabilita</button>
				
				</form> 
			</div>

		</div>
	</div>
	
	
	<jsp:include page="../footer.jsp" />  
						
</body>
</html>