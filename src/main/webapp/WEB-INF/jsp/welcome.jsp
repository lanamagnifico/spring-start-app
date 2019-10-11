<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
          <div class="container">
            <div class="row">
              <div class="col-sm-6 ">
                <!--   py-3 px-lg-5 border feature   -->
                <div class="card feature">
                  <div class="card-body">
                    <h5 class="card-title">${message_mvc}</h5>
                    <p class="card-text">${message_rest}</p>
                    <a class="card-link" href="<c:url value='/api'/>" target="_blank">api</a>
                  </div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="card feature">
                  <div class="card-body">
                    <h5 class="card-title">Bootstrap v4.3.1</h5>
                    <p class="card-text">customize</p>
                    <a class="card-link" href="https://getbootstrap.com/" target="_blank">bootstrap</a>
                  </div>
                </div>
              </div>
            </div>
            <div class="w-100">
            </div>
            <div class="row">
              <div class="col-sm-6">
                <div class="card feature">
                  <div class="card-body">
                    <h5 class="card-title">H2 database <strong>mem:startdb</strong></h5>
                    <p class="card-text">check </p>
                    <a class="card-link" href="<c:url value='/console'/>" target="_blank">console</a>
                  </div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="card feature">
                  <div class="card-body">
                    <h5 class="card-title">Spring Security </h5>
                    <sec:authorize access="isAnonymous()">
                      <a class="card-link" href="<c:url value='/customLoginPage'/>">Sign in</a>
                      <a class="card-link" href="<c:url value='/registrationForm'/>">Registration</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                      <form method="post" action="logout">
                        Welcome,
                        <strong><sec:authentication property="principal.username"/></strong>
                        <p>You have authorities:</p>
                        <sec:authentication property="principal.authorities" var="authorities" />
                        <c:forEach items="${authorities}" var="authority" varStatus="vs">
                        <p>${authority.authority}</p>
                        </c:forEach>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <p>This content will only be visible to users who have
                                the ADMIN role in their list of GrantedAuthorities</p>
                        </sec:authorize>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input name="submit" type="submit" value="Logout" / class="btn btn-outline-secondary">
                      </form>
                    </sec:authorize>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </body>

      </html>