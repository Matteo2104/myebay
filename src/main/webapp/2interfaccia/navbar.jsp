<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>


<div class="tabs is-centered">
  <ul>
    <li><a href="${pageContext.request.contextPath}">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/SwitchInterface">Cambia Interfaccia</a></li>
    	
  	<c:if test="${userInfo.ruolo == 'ROLE_ADMIN'}">
		<li><a href="${pageContext.request.contextPath}/utente/PrepareInsertUserServlet">Crea Nuovo Utente</a></li>
	</c:if>
	<c:if test="${userInfo.ruolo == 'ROLE_CLASSIC_USER'}">
		<li><a href="${pageContext.request.contextPath}/annuncio/PrepareInsertAnnuncioServlet">Inserisci un nuovo Annuncio</a></li>
	</c:if>
	
	<c:if test="${empty userInfo}" >
    	<li><a href="${pageContext.request.contextPath}/PrepareLoginServlet">Accedi o Registrati</a></li>
	</c:if>
	
	<c:if test="${not empty userInfo}" >
		<span class="navbar-text">
 			Utente: ${userInfo.username} (${userInfo.nome } ${userInfo.cognome}) ${userInfo.ruolo=='ROLE_CLASSIC_USER'?'Credito:':''} ${userInfo.ruolo=='ROLE_CLASSIC_USER'?userInfo.creditoResiduo:''} ${userInfo.ruolo=='ROLE_CLASSIC_USER'?'Euro':''}
 		</span>

		<li><a href="${pageContext.request.contextPath}/2interfaccia/areapersonale.jsp">Area Personale</a></li>
		<li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>

		
	</c:if>
    	
  </ul>
</div>


 

  
  
</header>