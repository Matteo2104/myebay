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
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/SwitchInterface">Cambia Interfaccia</a>
          </li>
          
					<c:if test="${userInfo.ruolo == 'ROLE_ADMIN'}">
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/utente/PrepareSearchUserServlet">Gestione Utenze</a></li>
					</c:if>
					<c:if test="${userInfo.ruolo == 'ROLE_CLASSIC_USER'}">
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/annuncio/PrepareInsertAnnuncioServlet">Inserisci un nuovo Annuncio</a></li>
					</c:if>

		</ul> 
			
				<c:if test="${empty userInfo}" >

            			<a class="nav-link active text-white" href="${pageContext.request.contextPath}/PrepareLoginServlet">Accedi o Registrati</a>

     			</c:if>
     			
     			<c:if test="${not empty userInfo}" >
					<span class="navbar-text">
            			Utente: ${userInfo.username} (${userInfo.nome } ${userInfo.cognome}) ${userInfo.ruolo=='ROLE_CLASSIC_USER'?'Credito:':''} ${userInfo.ruolo=='ROLE_CLASSIC_USER'?userInfo.creditoResiduo:''} ${userInfo.ruolo=='ROLE_CLASSIC_USER'?'Euro':''}
            		</span>
            		
            		


          				<a class="nav-link active text-white" href="${pageContext.request.contextPath}/1interfaccia/areapersonale.jsp">Area Personale</a>

          				
       					<a class="nav-link active text-white" href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>

					
				</c:if>
         
       </div> 
        
      <div class="form-inline mr-sm-2">
      		
      </div>
    </div>
  </nav>

  
  
</header>