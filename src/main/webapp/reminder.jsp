<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
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
	<br><br>
		<h2 style="color:#4da6ff;margin-left:40%"><b>Reminders</b></h2>
		<div class="row">
			<div class="col-md-12">
		  		<div class="table-responsive">
		        	<table class="table table-striped" style="width:80%;margin-left:auto;margin-right:auto">
						<tbody>
						<c:forEach var="note" items="${note}">
							<tr>
								<th>${note}</th>
							</tr>
							</c:forEach>
						</tbody>
					</table>
		      	</div>
		 	</div>
		</div>
	</div>
	</div>
	</body>
</html>