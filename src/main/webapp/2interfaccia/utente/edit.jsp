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
					</div>
					
					<div class="column">
						<div class="field">
							<label class="label has-text-centered">Cognome</label>
							<div class="control">
						    	<input class="input" type="text" name="cognome" placeholder="Cognome" value="${edit_utente_attr.cognome}">
						  	</div>
						</div>
					</div>
					
					<div class="column">
						<div class="field">
							<label class="label has-text-centered">Username</label>
							<div class="control">
						    	<input class="input" type="text" name="username" placeholder="Username" value="${edit_utente_attr.username}">
						  	</div>
						</div>
					</div>
				</div>
				
				
				
				<div class="columns">
					<div class="column">
						<div class="field has-text-centered">
							<div class="select">
						    	<select id="stato" name="stato">
								    <option ${edit_utente_attr.stato == 'CREATO'?'selected':''} value="CREATO">Creato</option>
								    <option ${edit_utente_attr.stato == 'ATTIVO'?'selected':''} value="ATTIVO">Attivo</option>
									<option ${edit_utente_attr.stato == 'DISABILITATO'?'selected':''} value="DISABILITATO">Disabilitato</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="column">
						<div class="field has-text-centered">
							<div class="select">
						    	<select id="ruolo" name="ruolo">
								    <option ${edit_utente_attr.ruolo == 'ROLE_ADMIN'?'selected':''} value="ROLE_ADMIN">Admin</option>
								    <option ${edit_utente_attr.ruolo == 'ROLE_CLASSIC_USER'?'selected':''} value="ROLE_CLASSIC_USER">Classic User</option>
								</select>
							</div>
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