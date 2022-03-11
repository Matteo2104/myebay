<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Disabilita Utente</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Sei sicuro di voler eliminare questo annuncio?</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo Annuncio:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Creazione:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.data}</dd>
					    	</dl>
					    	
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Categorie
							  </a>
							</p>
					    	
					    </div>
					    
							<!-- info ruoli -->
							<div class="collapse" id="collapseExample">
							<div class="card card-body">
								<c:forEach items="${delete_annuncio_attr.categorie}" var="categoria" >
									<dl class="row">
								  	<dt class="col-sm-3 text-right">Codice:</dt>
								  	<dd class="col-sm-9">${categoria.codice}</dd>
							   		</dl>
							   		<dl class="row">
								 	 <dt class="col-sm-3 text-right">Descrizione:</dt>
								  	<dd class="col-sm-9">${categoria.descrizione}</dd>
							   		</dl>
								</c:forEach>  
							  </div>
							 </div>
							 
					<!-- end card -->
					</div>	
					    
					    <div class='card-footer'>
					    	<form action="ExecuteDeleteAnnuncioServlet" method="post">
					    		<input type="hidden" name="idAnnuncio" value="${delete_annuncio_attr.id}">
						    	<button type="submit" name="submit" id="submit" class="btn btn-danger">Conferma</button>
						        <a href="ExecuteListUserServlet" class='btn btn-outline-secondary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Back
						        </a>
					        </form>
					    </div>
						
			  	
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>