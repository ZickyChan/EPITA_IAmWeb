<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%
			    Boolean auth = (Boolean) session.getAttribute("authenticated");
			    if (auth != null && auth) {
					response.sendRedirect("mainPage.jsp");
			    }
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<link rel="stylesheet" href="resources/css/style.css">
			<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	</head>
	
	<body>
		<div id="header">
		<h1>Authentication</h1>
		</div>
		
		<div>
		<form name="authenticationForm" action="Login" method="post">
			<div class="form-group">
				<label>Login: </label>
				<input type="text" name="firstName" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Password: </label>
				<input type="password" name="password" class="form-control"/>
			</div>
			
			 <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Log In</button>
            </div>
        	</div>
		</form>
		</div>
		
	</body>
</html>
