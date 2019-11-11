<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="css/spring-start.css">
  <script src="js/bootstrap.min.js"></script>
</head>

<body>

  <div class="container main">
    <div class="container-fluid caption">
      <p class="h2">Spring Boot Application</p>
    </div>

    <div class="card-deck">

      <div class="card">
        <div class="card-header">
          Sign In
        </div>

        <div style="padding-top: 30px" class="card-body">
          <!-- Params: error and logout -->
          <c:if test="${param.error != null}">
            <h5 class="text-danger card-title"> You entered invalid username or password!</h5>
          </c:if>
          <c:if test="${param.logout != null}">
            <h5 class="text-success card-title"> You have been logged out.</h5>
          </c:if>

          <form:form name='loginForm' action="authenticate" method='POST'>
            <!-- Login -->
            <div class="form-group">
              <label for="username" class="col-sm-2 col-form-label sr-only">Login</label>

              <div class="input-group">
                <div class="input-group-prepend">
                  <img src="images/user-icon.png" width="15" height="15" style="margin:5px;">
                </div>
                <input type="text" name="username" placeholder="username" class="form-control" required>
              </div>

            </div>
            <!-- Password -->
            <div class="form-group">
              <label for="password" class="col-sm-3 col-form-label sr-only">Password</label>

              <div class="input-group">
                <div class="input-group-prepend">
                  <img src="images/lock.png" width="15" height="15" style="margin:5px;">
                </div>
                <input type="password" name="password" placeholder="password" class="form-control" required>
              </div>

            </div>
            <!-- Submit Button -->
            <button type="submit" class="btn btn-outline-success" style="margin-left:25px;">Login</button>
          </form:form>
        </div>
      </div>

      <div class="card">
        <div class="card-header">
          Registration
        </div>

        <div style="padding-top: 30px" class="card-body">
          <form:form name="registrationForm" modelAttribute="theUser" action="registrationProcess" method="POST">

            <!-- Login -->
            <div class="form-group">
              <form:label path="userName" cssClass="col-sm-2 col-form-label sr-only">Login</form:label>
                <div class="input-group">
                  <div class="input-group-prepend">
                    <img src="images/user-icon.png" width="15" height="15" style="margin:5px;">
                  </div>
                  <form:input type="text" path="userName" placeholder="username" cssClass="form-control"
                    required="required" />
                </div>
            </div>

            <!-- Password -->
            <div class="form-group">
              <form:label path="userPass" cssClass="col-sm-3 col-form-label sr-only">Password</form:label>
              <div class="input-group">
                <div class="input-group-prepend">
                  <img src="images/lock.png" width="15" height="15" style="margin:5px;">
                </div>
                <form:input type="password" path="userPass" placeholder="password" cssClass="form-control"
                  required="required" />
              </div>
            </div>

            <!-- Login/Submit Button -->
            <button type="submit" class="btn btn-outline-success" style="margin-left:25px;">Save</button>

          </form:form>
  
        </div>
      </div>

    </div>
</body>

</html>