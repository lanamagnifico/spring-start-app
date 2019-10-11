<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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

    <div style="padding-top: 20px" class="container-fluid">

      <form:form name="registrationForm"
                 modelAttribute="theUser"
                 action="registrationProcess"
                 method="POST"
                 cssClass="form-inline">
        <!-- Login -->
        <div class="form-group">
          <form:label path="userName" cssClass="col-sm-2 col-form-label">Login</form:label>
            <div class="col-sm-10">
              <form:input type="text" path="userName" placeholder="username" cssClass="form-control" required="required" />
            </div>
        </div>

        <!-- Password -->
        <div class="form-group">
          <form:label path="userPass" cssClass="col-sm-3 col-form-label">Password</form:label>
            <div class="col-sm-9">
              <form:input type="password" path="userPass" placeholder="password" cssClass="form-control" required="required" />
            </div>
        </div>

        <!-- Login/Submit Button -->
        <div class="form-group">
          <button type="submit" class="btn btn-success">Save</button>
        </div>
        </form:form>
    </div>
  </div>
</body>
</html>