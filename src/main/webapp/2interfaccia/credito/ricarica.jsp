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

		<div class="notification has-text-centered is-primary">
			<h2 class="title is-2">Effettua una ricarica</h2>
			<h4 class="title is-4">Scegli l'importo che desideri ricaricare</h4>

			<div style="text-align:center">
				<form method="post" action="${pageContext.request.contextPath}/credito/ExecuteRicaricaServlet">
				  
				  	<div class="columns">
				  		<div class="column is-half is-offset-one-quarter">
				  			<div class="field">
								<label for="credito" class="label">Credito</label>
								<input type="number" class="input" name="credito" id="credito" min="1" value="1">
						  </div>
				  		</div>
				  	</div>
					  
					  
					  <div>
					  		<a href="${pageContext.request.contextPath}/2interfaccia/areapersonale.jsp" class="button" style='width:100px'>Indietro</a>
				      		<button type="submit" name="submit" value="submit" id="submit" class="button is-info">Conferma</button>
					  </div>

			  </form>
			</div>
		</div>
	</div>
	
	<jsp:include page="../footer.jsp" />

</body>
</html>