<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
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
	
	<h2 style="color:#4da6ff; margin-left:2%"><strong>${nbname }</strong></h2>
<a href="addnote?nbname=<c:out value='${nbname}' />"><button type="button" class="btn btn-outline-info" style="float:right"><i class="fa fa-plus">Add Note</i></button></a>

	<br><br>
	 
	     <c:forEach var="note" items="${notes}">
			<div class="jumbotron">
				<h3 style="margin-left:3%"><b><a href="details?note=<c:out value='${note.nname}' />"><c:out value="${note.nname }"></c:out></a></b></h3>
		       	<a style="margin-left:85%" href="editnote?note=<c:out value='${note.nname}' />"><i class="fa fa-edit">Edit</i></a>
		        &nbsp;&nbsp;&nbsp;&nbsp; 
		        <a style="color:red" href="deletenote?note=<c:out value='${note.nname}' />"><i class="fa fa-trash-alt" >Delete</i></a>
			</div>
	       <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
	                 <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
	      
	     </c:forEach>
	
	</div>
	</div>
	</body>
</html>