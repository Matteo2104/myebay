<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>	 
	<jsp:include page="./header.jsp" />   
	<title>My Ebay</title>
</head>
<body>
 
	<jsp:include page="./navbar.jsp"></jsp:include>
	    
	<div class="container">
	
		<div class="notification is-primary has-text-centered">
			<h1 class="title is-1">Benvenuto in MyEbay!</h1>
			<h4 class="title is-4">Qui puoi trovare annunci di prodotti di tutti i tipi</h4>
  		</div>
  		
  		<form method="post" action="${pageContext.request.contextPath}/ExecuteSearchAnnunciServlet">
	  		<div class="notification is-primary has-text-centered">
	  		
				<div class="field">
					<label class="label">Titolo</label>
					<div class="control">
				    	<input class="input" type="text" name="titolo" placeholder="Titolo Annuncio">
				  	</div>
				</div>
				
				<div class="field">
					<label class="label">Prezzo (a partire da)</label>
					<div class="control">
				    	<input class="input" type="number" name="prezzo" placeholder="Prezzo">
				  	</div>
				</div>
				
				<c:forEach items="${list_categorie_attr}" var="categoria" >
					<div class="field">
						<label class="checkbox">
							<input type="checkbox" value="${categoria.id}" name="categorie">
							${categoria.descrizione}
						</label>
					</div>
				</c:forEach>
				
				
				<div class="field" align="center">
					<input class="button" type="submit" value="Cerca tra gli annunci">
				</div>
				
				
	  		</div>
  		</form>
  		
	</div>
	

	<jsp:include page="./footer.jsp" />
</body>
</html>