<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="./header.jsp" />		
	<title>Dettaglio annuncio</title>   
</head>
<body>
	
	<jsp:include page="./navbar.jsp"></jsp:include>
	
	<div class="container">
		
		<div class="notification is-primary">
			<div class="box">
				<h2 class="title is-2">${show_annuncio_attr.aperto?'':'VENDUTO - '}${show_annuncio_attr.titolo}</h2>
				
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Testo Annuncio:</h5>
				  	</div>
				  	<div class="column">
				    	${show_annuncio_attr.testoAnnuncio}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Prezzo:</h5>
				  	</div>
				  	<div class="column">
				    	${show_annuncio_attr.prezzo}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Data Pubblicazione:</h5>
				  	</div>
				  	<div class="column">
				    	<fmt:formatDate type = "date" value = "${show_annuncio_attr.data}" />
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Proprietario dell'annuncio:</h5>
				  	</div>
				  	<div class="column">
				    	${show_annuncio_attr.utenteInserimento.username}
				  	</div>
				</div>
				
				<div class="columns">
					<div class="column">
				    	<h5 class="title is-5">Categorie:</h5>
				  	</div>
				  	<div class="column">
				  		<c:forEach items="${show_annuncio_attr.categorie}" var="categoria">
				    		${categoria.descrizione}; 
				  		</c:forEach>
				  	</div>
				</div>
				
			</div>
			
			<div>
				<a href="ExecuteListAnnunciServlet" class="button" style='width:100px'>Indietro</a>
					         
		        <c:if test="${userInfo==null }">
		        	<a href="${pageContext.request.contextPath}/PrepareLoginByAnnuncioServlet?idAnnuncio=${show_annuncio_attr.id}" class="button is-info" style='width:200px'>Compra</a>
		        </c:if>
		        <c:if test="${userInfo!=null }">
		        	<a href="${pageContext.request.contextPath}/acquisto/ExecuteCompraAnnuncioServlet?idAnnuncio=${show_annuncio_attr.id}" class="button is-info" style='width:200px'>Compra</a>
		        </c:if>
					           	            	        
			</div>
			
		</div>
		
	</div>
	
	<jsp:include page="./footer.jsp" />
</body>
</html>