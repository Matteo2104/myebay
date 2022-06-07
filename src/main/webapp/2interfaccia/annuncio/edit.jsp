<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Utente</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica Annuncio</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteEditAnnuncioServlet" class="row g-3" >
								
								<div class="col-md-6">
									<label for="titolo" class="form-label">Titolo: </label>
									<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Modifica il titolo" value="${edit_annuncio_attr.titolo}" >
								</div>
							
								<div class="col-md-6">
									<label for="testo" class="form-label">Descrizione: </label>
									<input type="text" name="testo" id="testo" class="form-control" placeholder="Modifica il nome" value="${edit_annuncio_attr.testoAnnuncio}" >
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Prezzo: </label>
									<input type="text" name="prezzo" id="prezzo" class="form-control" placeholder="Modifica il prezzo" value="${edit_annuncio_attr.prezzo}" >
								</div>
								
								<c:forEach items="${mappa_categorie}" var="categoria" >
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="${categoria.key.id}"
										id="flexCheckDefault" name="categorie" ${categoria.value?'checked':''}> <label class="form-check-label"
										for="flexCheckDefault"> ${categoria.key.codice} </label>
									</div>
								</c:forEach>
								
								<div class="col-12">
									<button type="submit" name="idAnnuncio" value="${edit_annuncio_attr.id}" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci"/>
									<a href="${pageContext.request.contextPath}/annuncio/ExecuteListAnnunciPersonaliServlet" class='btn btn-outline-secondary' style='width:100px'>
					            		<i class='fa fa-chevron-left'></i> Indietro
					       			</a>
								</div>
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>