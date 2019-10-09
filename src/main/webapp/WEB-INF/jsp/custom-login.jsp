<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
      <html lang="en">

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

          <div style="padding-top: 20px" class="container-fluid">
              <form name='loginForm' action="<c:url value='authenticate' />" method='POST' class="form-inline">
                  <!-- Login -->
                  <div class="form-group">
                    <label for="username" class="col-sm-2 col-form-label">Login</label>
                    <div class="col-sm-10">
                      <input type="text" name="username" placeholder="username" class="form-control" required>
                    </div>
                  </div>

                  <!-- Password -->
                  <div class="form-group">
                    <label for="password" class="col-sm-3 col-form-label">Password</label>
                    <div class="col-sm-9">
                      <input type="password" name="password" placeholder="password" class="form-control" required>
                    </div>
                  </div>

                  <!-- Login/Submit Button -->
                  <div class="form-group">
                    <button type="submit" class="btn btn-success">Login</button>
                  </div>
                  <div class="form-group" style="margin-left:50px;">
                    <a href="" class="btn btn-primary">New User</a>
                  </div>
              </form>
            </div>
          </div>
      </body>

      </html>