<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body>
    <div class="container">
        <div>
            <h1>Spring Boot JSP Example</h1>
            <h2>${message}</h2>
            <br>
            <p class="text-muted">Bootstrap v4.3.1 included</p>
            <br>
            <p class="text-muted">Spring Security included </p>
            <sec:authorize access="isAnonymous()">
              <a href="<c:url value="/customLoginPage"/>">Sign in</a> |
              <a href="<c:url value=""/>">Registration</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
               Welcome, <strong><sec:authentication property="principal.username"/></strong>
                 <form method="post" action="logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input name="submit" type="submit" value="Logout"/>
                 </form>
            </sec:authorize>

        </div>
    </div>
</body>
</html>