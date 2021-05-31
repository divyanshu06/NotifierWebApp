<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard</title>
	<link rel="stylesheet" href="css/form.css">
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
	<div class="content-container container col-md-8">
	<div class="container-fluid">
	<br><br>
		<form action="<%=request.getContextPath() %>/updatenotebook" method="post" class="appointment-form" autocomplete="off">
			<h2 class="text-center">Edit Your Details</h2>
				<br>
				<div class="form-group-1">
				<input type="text"  name="nbname" value="${nb}" readonly>
				<br>

				<input type="text" name="updatednbname" placeholder="Enter New NoteBook Name" required>
				<br>
				<div style="float:left">
              		<input type="submit" class="submit" value="Save Changes">
              	</div>
              	<div style="float:right">
              		<input type="submit" formaction="notebook"  class="submit" value="Cancel">
             	</div>
            </div>
		</form>
		</div>
		</div>

	</body>
</html>