<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
  <title>Welcome</title>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="css/spring-start.css">
</head>

<body>
  <div class="container main">
    <div class="container-fluid caption">
      <p class="h2">Spring Boot Application</p>
    </div>

    <div class="card-deck">

      <div class="card feature">
        <div class="card-body">
          <h5 class="card-title">${message_mvc}</h5>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
            <p class="small text-muted">This content will only be visible to users who have
              the ADMIN role in their list of GrantedAuthorities</p>
            <a class="card-link" href="<c:url value='/api'/>" target="_blank">api</a>
          </sec:authorize>
        </div>
      </div>

      <div class="card feature">
        <div class="card-body">
          <h5 class="card-title">Bootstrap v4.3.1</h5>
          <a class="card-link" href="https://getbootstrap.com/" target="_blank">bootstrap</a>
        </div>
      </div>
    </div>

    <div class="card-deck">

      <div class="card feature">
        <div class="card-body">
          <h5 class="card-title">H2 database <strong>mem:startdb</strong></h5>
          <p class="card-text">Server: <%= application.getServerInfo() %></p>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
            <p class="small text-muted">This content will only be visible to users who have
              the ADMIN role in their list of GrantedAuthorities</p>
            <a class="card-link" href="<c:url value='/console'/>" target="_blank">console H2</a>
          </sec:authorize>
        </div>
      </div>

      <div class="card feature">
        <div class="card-body">
          <h5 class="card-title">Spring Security </h5>

          <!-- isAnonymous -->
          <sec:authorize access="isAnonymous()">
            <p class="small text-muted">This content will only be visible to users who are not authenticated</p>
            <a class="card-link" href="<c:url value='/showCustomLoginPage'/>">Sign in</a>
            or <a class="card-link" href="<c:url value='/showCustomLoginPage'/>">Registration</a>
          </sec:authorize>

          <!-- isAuthenticated -->
          <sec:authorize access="isAuthenticated()">
            <form:form method="post" action="logout">
              Welcome, <strong>
                <sec:authentication property="principal.username" /></strong>
              <p>You have authorities:</p>
              <sec:authentication property="principal.authorities" var="authorities" />
              <c:forEach items="${authorities}" var="authority" varStatus="vs">
                <p>${authority.authority}</p>
              </c:forEach>
              <sec:authorize access="hasRole('ROLE_ADMIN')">
                <p class="small text-muted">This content will only be visible to users who have
                  the ADMIN role in their list of GrantedAuthorities</p>
              </sec:authorize>
              <button type="submit" class="btn btn-outline-secondary" style="margin-left:25px;">Logout</button>
            </form:form>
          </sec:authorize>
        </div>
      </div>

    </div>
  </div>
</body>

</html>