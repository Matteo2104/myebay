<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserimento Nuovo Utente</title>
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />

	<c:if test="${errorMessage != null}">
		<div class="notification is-warning">
			<button class="delete"></button>
			${errorMessage}
		</div>
	</c:if>
	
	
	<div class="container">
		<div class="notification is-primary has-text-centered">
			<h1 class="title is-1">Inserimento Nuovo Utente</h1>
		</div>
		
		<div class="notification is-primary">
			<form method="post" action="${pageContext.request.contextPath}/utente/ExecuteInsertUserServlet">
				
				<div class="columns">
					<div class="column">

						<div class="field">
							<label class="label has-text-centered">Nome</label>
							<div class="control">
						    	<input class="input" type="text" name="nome" placeholder="Nome">
						  	</div>
						</div>
						
						<div class="field">
							<label class="label has-text-centered">Username</label>
							<div class="control">
						    	<input class="input" type="text" name="username" placeholder="Username">
						  	</div>
						</div>
						
					</div>
					
					<div class="column">
					
						<div class="field">
							<label class="label has-text-centered">Cognome</label>
							<div class="control">
						    	<input class="input" type="text" name="cognome" placeholder="Cognome">
						  	</div>
						</div>
					
						
						
						<div class="field">
							<label class="label has-text-centered">Password</label>
							<div class="control">
						    	<input class="input" type="password" name="password" placeholder="Password">
						  	</div>
						</div>
						
					</div>
				</div>
				
				<div class="field has-text-centered">
					<div class="select">
				    	<select id="ruolo" name="ruolo">
						    <option value="ROLE_ADMIN">Admin</option>
						    <option value="ROLE_CLASSIC_USER">Classic User</option>
						</select>
					</div>
				</div>
				
				
				<div style="text-align:center">
					<input class="button is-info" type="submit" value="Inserisci Nuovo Utente">
				</div>

			</form>
			
		</div>
	</div>
			
	<jsp:include page="../footer.jsp" />
</body>
</html>