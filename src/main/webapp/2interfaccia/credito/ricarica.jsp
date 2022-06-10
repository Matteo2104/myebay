<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>My Ebay</title>
</head>
<body>

	<div class="container">
		<c:if test="${errorMessage != null}">
			<div class="notification is-warning">
				<button class="delete"></button>
				${errorMessage}
			</div>
		</c:if>

		<div class="notification has-text-centered">
			<h2 class="title is-2">Effettua una ricarica</h2>
			<h4 class="title is-4">Scegli l'importo che desideri ricaricare</h4>

			<div align="center">
				<form method="post" action="${pageContext.request.contextPath}/credito/ExecuteRicaricaServlet">
				  
					  <div class="field">
						  <label for="credito" class="form-label">Credito</label>
						  <input type="number" class="form-control w-25" name="credito" id="credito" min="1" value="1">
					  </div>
					  
					  <div>
						  <button type="submit" name="submit" value="submit" id="submit" class="button">Conferma</button>
					  </div>

			  </form>
			</div>
		</div>
	</div>
	
	<jsp:include page="../footer.jsp" />

</body>
</html>