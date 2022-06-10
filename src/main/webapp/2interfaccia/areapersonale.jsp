<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>	 
	<jsp:include page="header.jsp" />
	<title>Area Personale</title>
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	
	<div class="container">
		<c:if test="${errorMessage != null}">
			<div class="notification is-warning">
				<button class="delete"></button>
				${errorMessage}
			</div>
		</c:if>
		
		<c:if test="${operationResult != null}">
			<div class="notification is-info">
				<button class="delete"></button>
				${operationResult}
			</div>
		</c:if>
	
		<div class="notification is-primary has-text-centered">
			<h1 class="title is-1">Benvenuto ${userInfo.nome}!</h1>
			<h4 class="title is-4">Questa è la tua area personale</h4>
			
			<c:if test="${userInfo.ruolo == 'ROLE_ADMIN'}">
				<a class="button is-info" href="${pageContext.request.contextPath}/utente/PrepareSearchUserServlet">Gestione Utenze</a>
			</c:if>
			<c:if test="${userInfo.ruolo == 'ROLE_CLASSIC_USER'}">
				<a class="button is-info"  href="${pageContext.request.contextPath}/annuncio/PrepareSearchAnnunciPersonaliServlet">Gestione Annunci</a>
				<a class="button is-info"  href="${pageContext.request.contextPath}/acquisto/ExecuteListAcquistiServlet">I tuoi Acquisti</a>
				<a class="button is-info"  href="${pageContext.request.contextPath}/credito/PrepareRicaricaServlet">Effettua una ricarica</a>
			</c:if>
		</div>
	
	</div>
	
	<jsp:include page="footer.jsp" />
			
</body>
</html>