<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard</title>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous" />
	</head>
	<body>
	<%
	  response.setHeader("Location",request.getContextPath()+"/dashboard.jsp");
	  response.setHeader("Cache-Control","no-cache");
	  response.setHeader("Cache-Control","no-store");
	  response.setHeader("Pragma","no-cache");
	  response.setDateHeader ("Expires", 0);
	
	  if(session.getAttribute("userid")==null)
	      response.sendRedirect(request.getContextPath()+"/index.jsp");
	  %>
	 <jsp:include page="header.jsp"></jsp:include>
	<div class="content-container">
	
	  <div class="container-fluid">
	
	    <!-- Main component for a primary marketing message or call to action -->
	    <div class="jumbotron">
	      <h3><b>Welcome <span>${user} <%request.getAttribute("user"); %></span></b></h3>
	      
	    </div>
	
	  </div>
	</div>
	</body>
</html>