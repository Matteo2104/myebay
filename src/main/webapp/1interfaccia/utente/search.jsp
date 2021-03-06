<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Ricerca</title>
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
				        <h5>Ricerca utenti</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteSearchUserServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome </label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${search_utente_attr.nome}" >
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome </label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${search_utente_attr.nome}" >
								</div>
							
								<div class="col-md-6">
									<label for="username" class="form-label">Username </label>
									<input type="text" class="form-control" name="username" id="username" placeholder="Inserire lo username" value="${search_utente_attr.cognome}" >
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value="${search_utente_attr.dateCreated}" />
								<div class="col-md-3">
									<label for="dataCreazione" class="form-label">Data di Creazione </label>
                        			<input class="form-control" id="dataCreazione" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dateCreated" value="${parsedDate}"  >
								</div>	
								
								
								
								
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="ROLE_ADMIN"
										id="flexCheckDefault" name="ruoli"> <label class="form-check-label"
										for="flexCheckDefault"> Admin </label>
									</div>
									
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="ROLE_CLASSIC_USER"
										id="flexCheckDefault" name="ruoli"> <label class="form-check-label"
										for="flexCheckDefault"> Classic User </label>
									</div>
								
								
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
								<a class="btn btn-outline-primary ml-2" href="PrepareInsertUserServlet">Aggiungi nuovo utente</a>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
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