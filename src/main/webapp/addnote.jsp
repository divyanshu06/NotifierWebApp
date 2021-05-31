<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="ISO-8859-1">
	<title>Dashboard</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
rel="Stylesheet"type="text/css"/>
	
	<link rel="stylesheet" href="css/form.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
	<script>
$(document).ready(function()
{
	var minDate = new Date();
	var maxDate = new Date();
	$("#start").datepicker({
		showAnim: 'drop',
		numberOfMonth: 1,
		minDate: minDate,
		maxDate: maxDate,
		dateFormat: 'yy-mm-dd',
		onClose: function (selectedDate){
			$('#end').datepicker("option","minDate",selectedDate);
		}
	});
	
	$("#end").datepicker({
		showAnim: 'drop',
		numberOfMonth: 1,
		dateFormat: 'yy-mm-dd',
		onClose: function (selectedDate){
			$('#remind').datepicker("option","maxDate",selectedDate);
		}
	});
	
	$("#remind").datepicker({
		showAnim: 'drop',
		numberOfMonth: 1,
		minDate: minDate,
		maxDate: maxDate,
		maxDate: $("#end").datepicker,
		dateFormat: 'yy-mm-dd',
		onClose: function (selectedDate){
			$('#end').datepicker("option","minDate",selectedDate);
		}
	});
});
</script>    
	
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
			<form action="<%=request.getContextPath() %>/insertnote" method="post" class="appointment-form" autocomplete="off">
				<h2 class="text-center">Add Note</h2>
				<br>
					<div class="form-group-1">
						
							<label>Notebook Name : </label><input type="text"  name="nb" value='${nbname}' readonly>
					
							<label>Note Name : </label><input type="text"  name="nname" placeholder="Note Name" required>
							
							<label>Start Date : </label><input type="text" name="startDate" id="start" placeholder="Start Date" required>
					
							<label>End Date : </label><input type="text"  name="endDate" id="end" placeholder="End Date" required>
						
							<label>Reminder Date : </label><input type="date" name="reminderDate" id="remind" placeholder="Reminder Date" required>
						
							<label>Tag : </label><input type="text" name="tag" placeholder="Tag" required>
						
							<label>Description : </label><br><textarea rows=5  name="description" placeholder="Description" required></textarea>
							<br>
							<label>Status</label>
							<select class="select-list" name="status" required>
								<option value="Started">Started</option>
								<option value="Not Started">Not Started</option>
								<option value="Completed">Completed</option>
							</select>
						
						
						<input type="submit" class="submit" value="Add">
						<br>
						<a href="notes?nb=<c:out value='${nbname}' />"><button type="button" class="form-submit btn btn-outline-info bn-block" style="float:right">Cancel</button></a>
					</div>
						</form>
						
			</div>
		</div>
</body>
</html>