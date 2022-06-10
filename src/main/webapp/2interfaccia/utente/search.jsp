<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
</head>
<body>
	   
	<jsp:include page="../navbar.jsp"></jsp:include>
	    
 
	<div class="container">
		<div class="notification is-primary has-text-centered">
			<h1 class="title is-1">Ricerca Utenti</h1>
			<h4 class="title is-4">Inserisci i parametri di ricerca</h4>
  		</div>
  		
  		<form method="post" action="${pageContext.request.contextPath}/utente/ExecuteSearchUserServlet">
	  		<div class="notification is-primary has-text-centered">
	  			
	  			<div class="columns">
	  			
	  				<div class="column">
	  					<div class="field">
							<label class="label">Nome</label>
							<div class="control">
						    	<input class="input" type="text" name="nome" placeholder="Nome" value="${search_utente_attr.nome}">
						  	</div>
						</div>
						
						<div class="field">
							<label class="label">Username</label>
							<div class="control">
						    	<input class="input" type="text" name="username" placeholder="Username" value="${search_utente_attr.username}">
						  	</div>
						</div>
	  				</div>
	  				
	  				<div class="column">
	  					
	  					<div class="field">
							<label class="label">Cognome</label>
							<div class="control">
						    	<input class="input" type="text" name="cognome" placeholder="Cognome" value="${search_utente_attr.cognome}">
						  	</div>
						</div>
						
						<div class="columns">
	  						<div class="column">
	  							<div class="field">
									<label class="label">Data di Creazione</label>
									<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value="${search_utente_attr.dateCreated}" />
									<div class="control">
								    	<input type="date" placeholder="dd/MM/yy" name="dateCreated" value="${parsedDate}" placeholder="dd/MM/yy">
								  	</div>
								</div>
				  			</div>
	  						<div class="column">
	  						
	  						<div class="field">
								<label class="checkbox">
									<input type="checkbox" value="ROLE_ADMIN" name="ruoli">
									Admin
								</label>
								<label class="checkbox">
									<input type="checkbox" value="ROLE_CLASSIC_USER" name="ruoli">
									Classic User
								</label>
							</div>
	  						
	  						
			  		
								
	  						</div>
	  					</div>
					
	  			</div>
				
	  		</div>
	  		
	  		<div class="field" align="center">
					<input class="button" type="submit" value="Cerca">
			</div>
			</div>
  		</form>
	</div>
	
	
	<jsp:include page="../footer.jsp" />
	
</body>
</html>