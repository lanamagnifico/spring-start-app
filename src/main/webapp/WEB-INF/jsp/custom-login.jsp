<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <title>Login Page</title>
	<meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <script	src="js/bootstrap.min.js"></script>
</head>
<body>
    	<div>
            <div id="loginbox" style="margin-top: 50px;"
            			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

            	<div class="panel panel-info">

            				<div class="panel-heading">
            					<div class="panel-title">Sign In</div>
            				</div>

            			<div style="padding-top: 30px" class="panel-body">
                           <form name='loginForm'
                    		  action="<c:url value='authenticate' />" method='POST'>
                                  <div style="margin-bottom: 25px" class="input-group">
                    							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>

                    							<input type="text" name="username" placeholder="username" class="form-control">
                    			  </div>

                    						<!-- Password -->
                    						<div style="margin-bottom: 25px" class="input-group">
                    							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>

                    							<input type="password" name="password" placeholder="password" class="form-control" >
                    						</div>

                    						<!-- Login/Submit Button -->
                    						<div style="margin-top: 10px" class="form-group">
                    							<div class="col-sm-6 controls">
                    								<button type="submit" class="btn btn-success">Login</button>
                    							</div>
                    						</div>
                           </form>


                        </div>
                </div>
            </div>
    		    <div>
    				<a href="" class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
    		    </div>

        </div>
</body>
</html>