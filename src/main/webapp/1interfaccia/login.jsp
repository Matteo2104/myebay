<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">

	<head>
		<meta charset="utf-8">
		<title>Accedi</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	
	
		 <!-- Custom styles for login -->
	    <link href="./1interfaccia/assets/css/signin.css" rel="stylesheet">
	
	</head>
	
	
	<body class="text-center">
	    
		<main class="form-signin">
		  <form action="${idAnnuncio!=null?'ExecuteLoginByAnnuncioServlet':'LoginServlet'}" method="post" novalidate="novalidate">
	  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		 		 ${errorMessage}
			</div>
		  
		    <img class="mb-4" src="1interfaccia/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
		    <h1 class="h3 mb-3 fw-normal">Inserire credenziali d'accesso</h1>
			
			<div class="columns">
				<div class="column">
					<div class="form-floating">
				    	<input type="text" name="inputUsername" class="form-control" id="inputUsername" placeholder="username">
				    	<label for="inputUsername">Nome utente</label>
				    </div>
				</div>
				
				<div class="column">
					<div class="form-floating">
				    	<input type="password" name="inputPassword" class="form-control" id="inputPassword" placeholder="Password">
				    	<label for="inputPassword">Password</label>
				    </div>
				</div>
			</div>
				
		    <div class="mb-3">
		    	<c:if test="${idAnnuncio != null}">
		      		<a href="${pageContext.request.contextPath}/PrepareRegisterUserByAnnuncioServlet?idAnnuncio=${idAnnuncio}">Registrati</a>
		    	</c:if>
		    	
		    	<c:if test="${idAnnuncio == null}">
		      		<a href="${pageContext.request.contextPath}/PrepareRegisterUserServlet">Registrati</a>
		    	</c:if>
		    </div>

		    <button class="w-100 btn btn-lg btn-primary" type="submit" name="idAnnuncio" value="${idAnnuncio}">Accedi</button>
		   
		   
		    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		  </form>
		</main>
	
	    
	</body>


</html>