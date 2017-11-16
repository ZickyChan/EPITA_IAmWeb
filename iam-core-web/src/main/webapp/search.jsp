<%@page import="fr.epita.iamtesting.datamodel.Identity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
			    Boolean auth = (Boolean) session.getAttribute("authenticated");
			    if (auth == null || !auth) {
					response.sendRedirect("index.jsp");
			    }
			%>
<link rel="stylesheet" href="resources/css/style.css">
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	function checkNullInput(){
		var inputs = document.getElementsByTagName('input');
		var checked = false;
		
		for(var i = 0; i < inputs.length; i++) {
		    if(inputs[i].type.toLowerCase() == 'radio' && inputs[i].checked) {
		        return true;
		    }
		}
		
		if(!checked){
			alert('There is no selected identity!');
			return false;
		}
	}
	function deleteConfirm(){
		if(checkNullInput()){
			return confirm("Do you want to delete this identity?");
		}
		else{
			return false;
		}
	}
	
	function deselected(){
		var inputs = document.getElementsByTagName('input');

		for(var i = 0; i < inputs.length; i++) {
		    if(inputs[i].type.toLowerCase() == 'radio') {
		        inputs[i].checked = false;
		    }
		}
	}
</script>

</head>
<body>
	<a href="mainPage.jsp">Back to Home!</a>
	<h2>Search</h2>
	<div class="message"><%=session.getAttribute("message")%></div>
	<div class="container">
		<form xmlns="http://www.w3.org/1999/xhtml" class="form-horizontal"
			role="form" action="SearchingIdentity" method="get">
			<div class="form-group">
				<label for="displayName" class="col-sm-2 control-label">Display
					Name </label>
				<div class="col-sm-10">
					<input type="text" name="displayName" class="form-control"
						id="firstName" placeholder="Display Name" <% 
							if(session.getAttribute("previousSearch") != null && !session.getAttribute("previousSearch").equals("all") ){
						%>  value="<%=session.getAttribute("previousSearch")%>" <% } %>/>
				</div>
			</div>
			<!-- TODO : complete criteria-->

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Search</button>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" name="showAll" value="showAll" class="btn btn-default">List All</button>
				</div>
			</div>
		</form>

		<!-- Search results -->
		<div>

					<%
						List<Identity> identityList = (List<Identity>) session.getAttribute("identityFound");
						if (identityList == null || identityList.size()==0) {
					%>
							<table xmlns="http://www.w3.org/1999/xhtml" class="table">
						<thead>
							<tr>
								<th>Selection</th>
								<th>UID</th>
								<th>Name</th>
		
								<th>Email</th>
								<th>Birthdate</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td colspan="5">No result</td>
						</tr>
							</tbody>
						</table>

					<%
						} else {%>
						<form xmlns="http://www.w3.org/1999/xhtml" class="form-horizontal"
			role="form" action="SearchingIdentity" method="post">
						<table xmlns="http://www.w3.org/1999/xhtml" class="table">
						<thead>
							<tr>
								<th>Selection</th>
								<th>UID</th>
								<th>Name</th>
		
								<th>Email</th>
								<th>Birthdate</th>
							</tr>
						</thead>
						<tbody>
						<% 
							for (Identity identity : identityList) {
						%>
					<tr>
						<td><input name="selection" type="radio" value="<%=identity.getUid()%>" /></td>
						<td><%=identity.getUid()%></td>
						<td><%=identity.getDisplayName()%></td>
						<td><%=identity.getEmail()%></td>
						<td><%=identity.getBirthdate()%></td>
					</tr>
					<%
							}
					%>
						</tbody>
						</table>
						<input type="hidden" name="displayName" value="<%=session.getAttribute("previousSearch")%>"/>
						
						<div xmlns="http://www.w3.org/1999/xhtml" class=" col-sm-offset-2 col-sm-10 text-right">
		                    <button onclick="return checkNullInput()"type="submit" class="btn btn-primary" name="action" value="modify">Modify</button>
		                    <button onclick="return deleteConfirm()" class="btn btn-primary" name="action" value="delete">Delete</button>
		                    <button type="button" onclick="deselected()" class="btn btn-default">Cancel</button>
		                </div>
						</form>
					
					<%	
						}
					%>
			
			
				
		</div>
	</div>
</body>
</html>
