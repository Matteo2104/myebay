<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Pagina dei Risultati</title>
	 </head>
	 
	<body class="d-flex flex-column h-100">
	 
		<!-- Fixed navbar -->
		<jsp:include page="../navbar.jsp"></jsp:include>
	 
	
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-success alert-dismissible fade show  ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
				  Esempio di operazione fallita!
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
				  Aggiungere d-none nelle class per non far apparire
				   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Lista dei risultati</h5> 
				    </div>
				    <div class='card-body'>
				    	<a class="btn btn-primary " href="PrepareInsertUserServlet">Aggiungi nuovo utente</a>
				    	<a class="btn btn-primary " href="PrepareSearchUserServlet">Indietro</a>
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				            	<caption style="text-align:center">${users_list_attr.size()} elementi trovati</caption>  
				                <thead>
				                    <tr>
			                         	<th>Nome</th>
				                        <th>Cognome</th>
				                        <th>Username</th>
				                        <th>Data di Creazione</th>
				                        <th>Azioni</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${users_list_attr}" var="userItem">
				                		
				                			<tr>
												<td>${userItem.nome }</td>
												<td>${userItem.cognome }</td>
												<td>${userItem.username }</td>
												<td><fmt:formatDate type = "date" value = "${userItem.dateCreated}" /></td>
												<td>
													<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/utente/ExecuteVisualizzaUserServlet?idUser=${userItem.id}">Visualizza</a>
													<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/utente/PrepareEditUserServlet?idUser=${userItem.id}">Modifica</a>
													<c:if test="${userItem.stato == 'DISABILITATO'}">
														<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/utente/PrepareActionUserServlet?idUser=${userItem.id}">Abilita</a>
							                		</c:if>
							                		<c:if test="${userItem.stato != 'DISABILITATO'}">
							                			<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/utente/PrepareDeleteUserServlet?idUser=${userItem.id}">Disabilita</a>
							                		</c:if>
												</td>
											</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
				   
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