<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
			    Boolean auth = (Boolean) session.getAttribute("authenticated");
			    if (auth == null || !auth) {
					response.sendRedirect("index.jsp");
			    }
			    session.removeAttribute("message");
			    session.removeAttribute("identityFound");
			    session.removeAttribute("previousSearch");
			%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<link rel="stylesheet" href="resources/css/style.css">
			<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
			
	</head>
	<%
			if(session.getAttribute("notification") != null){
		%>
	<script text="text/javascript">
		function onload(){
			alert('<%=session.getAttribute("notification")%>');
		}
		
	</script>
	<body onload="onload()">
	<%
		session.removeAttribute("notification");
			} else {	
	%>
	<body>
	<% } %>
		<div id="header">
		<h1>Welcome to the IAM System</h1>
		</div>
		
		
		<section>
			<div class="menu">
				<p>Thanks to this action, you can create a brand new Identity, you can click on the button below to begin</p>
				<a href="create.jsp">Create!</a>
			</div>
			<div class="menu">
				<p>Thanks to this action, you can search for an existing, you can click on the button below to begin</p>
				<a href="search.jsp">Search!</a>
			</div>
		</section>
	</body>
</html>