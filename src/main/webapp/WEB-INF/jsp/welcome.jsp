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
                <div class="col-sm-6 py-3 px-lg-5 border bg-light feature">
                        <p class="lead">${message_mvc}</h2>
                        <p class="text-muted ">${message_rest}</h2>
                </div>
                <div class="col-sm-6 py-3 px-lg-5 border bg-light feature">
                 <p class="lead">Bootstrap v4.3.1</p>
                        <p class="text-muted">customize  <a href="https://getbootstrap.com/" target="_blank">bootstrap</a>

              </div>
            </div>
  <div class="w-100">
    </div>
            <div class="row">
                <div class="col-sm-6 py-3 px-lg-5 border bg-light feature">

                        <p class="lead">H2 database <strong>mem:startdb</strong></p>
                        <p class="text-muted">check <a href="<c:url value="/console"/>" target="_blank">console</a></p>
                </div>
                <div class="col-sm-6 py-3 px-lg-5 border bg-light feature">

                        <p class="lead">Spring Security </p>
                        <sec:authorize access="isAnonymous()">
                            <a href="<c:url value="/customLoginPage"/>">Sign in</a> |
                            <a href="<c:url value=""/>">Registration</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            Welcome, <strong><sec:authentication property="principal.username"/></strong>
                            <form method="post" action="logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input name="submit" type="submit" value="Logout"/ class="btn btn-outline-secondary">
                            </form>
                        </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</body>
</html>