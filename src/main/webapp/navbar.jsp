<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}">Home</a>
          </li>
         </ul> 
       </div> 
          <!--
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        
          
           
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/home">Home</a></li>
            --> 
            <div class="col-md-3 text-end">
				<c:forEach items="${userInfo.ruoli}" var="ruolo" >
					<c:if test="${ruolo.codice == 'ROLE_CLASSIC_USER'}">
						<li class="navbar-text"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/annuncio/PrepareInsertAnnuncioServlet">Inserisci un nuovo Annuncio</a></li>
					</c:if>
					<c:if test="${ruolo.codice == 'ROLE_ADMIN'}">
						<li class="navbar-text"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/utente/PrepareSearchUserServlet">Gestione Utenze</a></li>
					</c:if>
				</c:forEach>
			</div>
         
          

      <div class="col-md-4 text-end">
      		<c:if test="${empty userInfo}" >
     			<p class="navbar-text"><a href="login.jsp">Login</a></p>
     		</c:if>
     		<c:if test="${not empty userInfo}" >
       			<p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome})
       			<a href="${pageContext.request.contextPath}/areapersonale.jsp">Area Personale</a>
       			<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></p>
     		</c:if>
      </div>
    </div>
  </nav>

  
  
</header>