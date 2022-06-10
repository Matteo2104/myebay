<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica Utente</title>
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
			<h2 class="title is-2">Modifica Utente</h2>
			
			<form method="post" action="ExecuteEditUserServlet">
				
				<div class="columns">
					<div class="column">

						<div class="field">
							<label class="label has-text-centered">Nome</label>
							<div class="control">
						    	<input class="input" type="text" name="nome" placeholder="Nome" value="${edit_utente_attr.nome}">
						  	</div>
						</div>
						
						<div class="field">
							<label class="label has-text-centered">Username</label>
							<div class="control">
						    	<input class="input" type="text" name="username" placeholder="Username" value="${edit_utente_attr.username}">
						  	</div>
						</div>
						
					</div>
					
					<div class="column">
					
						<div class="field">
							<label class="label has-text-centered">Cognome</label>
							<div class="control">
						    	<input class="input" type="text" name="cognome" placeholder="Cognome" value="${edit_utente_attr.cognome}">
						  	</div>
						</div>
						
					</div>
				</div>
				
				<div class="dropdown is-active">
					<div class="dropdown-trigger">
						<button class="button" aria-haspopup="true" aria-controls="dropdown-menu">
							<span>Ruolo</span>
							<span class="icon is-small">
								<i class="fas fa-angle-down" aria-hidden="true"></i>
				      		</span>
				    	</button>
				  	</div>
					<div class="dropdown-menu" id="dropdown-menu" role="menu">
				    	<div class="dropdown-content">
				    		<a href="#" class="dropdown-item">
				        		Admin
				      		</a>
				      		<a class="dropdown-item">
				        		Classic User
				      		</a>
				    	</div>
					</div>
				</div>
				
				<div align="center">
					
					<button type="submit" name="idUser" value="${edit_utente_attr.id}" class="button is-info">Modifica Utente</button>
				</div>

			</form>
			
			
		</div>
	</div>
	
	<jsp:include page="../footer.jsp" />
	    
			
</body>
</html>