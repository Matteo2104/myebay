<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>Accedi</title>
	<jsp:include page="./header.jsp" />
</head>
<body>
	<div class="container has-text-centered">
		<c:if test="${errorMessage != null}">
			<div class="notification is-warning">
				<button class="delete"></button>
				${errorMessage}
			</div>
		</c:if>
		
		<div class="notification is-primary">
			<h1 class="title is-1">Login</h1>
		</div>
		
		<div class="notification is-primary">
			<form class="box" action="${idAnnuncio!=null?'ExecuteLoginByAnnuncioServlet':'LoginServlet'}" method="post">
				<div class="columns">
					<div class="column">
						<div class="field">
					    	<label class="label">Nome Utente</label>
					    	<div class="control">
					      		<input class="input" name="inputUsername" type="text" placeholder="Username">
					    	</div>
					  	</div>
					</div>
					
					<div class="column">
						<div class="field">
					    	<label class="label">Password</label>
					    	<div class="control">
					      		<input class="input" name="inputPassword" type="password" placeholder="Password">
					    	</div>
					  	</div>
					</div>
				</div>
			  	
			  
			  	<div class="field">
					<c:if test="${idAnnuncio != null}">
						<a href="${pageContext.request.contextPath}/PrepareRegisterUserByAnnuncioServlet?idAnnuncio=${idAnnuncio}">Registrati</a>
					</c:if>
				
					<c:if test="${idAnnuncio == null}">
						<a href="${pageContext.request.contextPath}/PrepareRegisterUserServlet">Registrati</a>
					</c:if>
				</div>
				
				<div>
			  		<button class="button is-primary" type="submit" name="idAnnuncio" value="${idAnnuncio}">Accedi</button>
				</div>
			</form>
		</div>
	</div>
</body>


</html>