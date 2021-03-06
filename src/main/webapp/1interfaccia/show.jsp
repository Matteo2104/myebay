<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	 	
	   <title>Dettaglio annuncio</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>${show_annuncio_attr.aperto?'':'VENDUTO - '}${show_annuncio_attr.titolo}</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo Annuncio:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Pubblicazione:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_annuncio_attr.data}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Proprietario dell'annuncio:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.utenteInserimento.username}</dd>
					    	</dl>
					    	
					    	<!-- info Regista -->
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Categorie
							  </a>
							</p>
							<div class="collapse" id="collapseExample">
							<div class="card card-body">
								
							   		<dl class="row">
								 	 <dt class="col-sm-3 text-right">Descrizione:</dt>
								  		<dd class="col-sm-9">
								  		<c:forEach items="${show_annuncio_attr.categorie}" var="categoria">
								  			${categoria.descrizione}
								  		</c:forEach>
								  		</dd>
								  	 
							   		</dl>
								 
							  </div>
							 </div>
					    	
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					        <a href="ExecuteListAnnunciServlet" class='btn btn-outline-secondary' style='width:100px'>
					            <em class='fa fa-chevron-left'></em> Indietro
					        </a>
					         
					        <c:if test="${userInfo==null }">
					        	<a href="${pageContext.request.contextPath}/PrepareLoginByAnnuncioServlet?idAnnuncio=${show_annuncio_attr.id}" class='btn btn-primary' style='width:200px'>Compra</a>
					        </c:if>
					        <c:if test="${userInfo!=null }">
					        	<a href="${pageContext.request.contextPath}/acquisto/ExecuteCompraAnnuncioServlet?idAnnuncio=${show_annuncio_attr.id}" class='btn btn-primary' style='width:200px'>Compra</a>
					        </c:if>
					           
					            
					        
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="./footer.jsp" />
	  </body>
</html>