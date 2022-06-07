<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="./header.jsp" />
	<title>Errore</title>
</head>
<body>
	
	<jsp:include page="./navbar.jsp"></jsp:include>
	
	<div class="container">
		<div class="notification is-primary has-text-centered">
			<h1 class="title is-1">Ops!</h1>
			<h4 class="title is-4">${errorMessage}</h4>
			
			<c:if test="${insufficientCredit != null}">
				<a class="button" style="width:400px" href="${pageContext.request.contextPath}/credito/PrepareRicaricaServlet">Effettua una ricarica</a>
			</c:if>
		</div>
	</div>
	
	<jsp:include page="./footer.jsp" />
</body>
</html>