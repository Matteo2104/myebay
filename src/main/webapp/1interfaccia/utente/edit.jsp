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
				        <h5>Modifica Utente</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteEditUserServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome: </label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${edit_utente_attr.nome}" >
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome: </label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${edit_utente_attr.cognome}" >
								</div>
								
								<div class="col-md-6">
									<label for="username" class="form-label">Username: </label>
									<input type="text" name="username" id="username" class="form-control" placeholder="Inserire lo username" value="${edit_utente_attr.username}" >
								</div>
								
								<select class="form-select" aria-label="Default select example" name="stato" >
  									<option ${edit_utente_attr.stato == 'CREATO'?'selected':''} value="CREATO">Creato</option>
  									<option ${edit_utente_attr.stato == 'ATTIVO'?'selected':''} value="ATTIVO">Attivo</option>
  									<option ${edit_utente_attr.stato == 'DISABILITATO'?'selected':''} value="DISABILITATO">Disabilitato</option>
								</select>
								
								<select class="form-select" aria-label="Default select example" id="ruolo" name="ruolo">
								  <option ${edit_utente_attr.stato == 'ROLE_ADMIN'?'selected':''} value="ROLE_ADMIN">ADMIN</option>
								  <option ${edit_utente_attr.stato == 'ROLE_CLASSIC_USER'?'selected':''} value="ROLE_CLASSIC_USER">CLASSIC USER</option>
								</select>
								
								<div class="col-12">
									<button type="submit" name="idUser" value="${edit_utente_attr.id}" class="btn btn-primary">Conferma</button>
									<a class="btn btn-outline-primary ml-2" href="PrepareInsertUserServlet">Add new</a>
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