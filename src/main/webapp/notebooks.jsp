<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.*"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Notebooks</title>
	<link rel="stylesheet" href="css/form.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous" />
	<style>
		
		
		.email {
		  background: #DEDBDF;
		  border-radius: 16px;
		  height: 32px;
		  overflow: hidden;
		  position: relative;
		  width: 162px;
		  -webkit-tap-highlight-color: transparent;
		  transition: width 300ms cubic-bezier(0.4, 0.0, 0.2, 1),
		    height 300ms cubic-bezier(0.4, 0.0, 0.2, 1),
		    box-shadow 300ms cubic-bezier(0.4, 0.0, 0.2, 1),
		    border-radius 300ms cubic-bezier(0.4, 0.0, 0.2, 1);
		}
		.email:not(.expand) {
		  cursor: pointer;
		}
		.email:not(.expand):hover {
		  background: #C2C0C2;
		}
		.from {
		  position: absolute;
		  transition: opacity 200ms 100ms cubic-bezier(0.0, 0.0, 0.2, 1);
		}
		.from-contents {
		  display: flex;
		  flex-direction: row;
		  transform-origin: 0 0;
		  transition: transform 300ms cubic-bezier(0.4, 0.0, 0.2, 1);
		}
		.to {
		  opacity: 0;
		  position: absolute;
		  transition: opacity 100ms cubic-bezier(0.4, 0.0, 1, 1);
		}
		.to-contents {
		  transform: scale(.55);
		  transform-origin: 0 0;
		  transition: transform 300ms cubic-bezier(0.4, 0.0, 0.2, 1);
		}
		.name {
		  font-size: 14px;
		  line-height: 32px;
		  margin-left: 40px;
		}
		.top {
		  background: #6422EB;
		  display: flex;
		  flex-direction: row;
		  height: 70px;
		  transition: height 300ms cubic-bezier(0.4, 0.0, 0.2, 1);
		  width: 300px;
		}
		
		.name-large {
		  color: #efd8ef;
		  font-size: 16px;
		  line-height: 70px;
		  margin-left: 100px;
		}
		.x-touch {
		  align-items: center;
		  align-self: center;
		  cursor: pointer;
		  display: flex;
		  height: 50px;
		  justify-content: center;
		  margin-left: auto;
		  width: 50px;
		}
		.x {
		  background: #BA87F9;
		  border-radius: 10px;
		  height: 20px;
		  position: relative;
		  width: 20px;
		}
		.x-touch:hover .x {
		  background: #CB9AFB;
		}
		.line1 {
		  background: #6422EB;
		  height: 12px;
		  position: absolute;
		  transform: translateX(9px) translateY(4px) rotate(45deg);
		  width: 2px;
		}
		.line2 {
		  background: #6422EB;
		  height: 12px;
		  position: absolute;
		  transform: translateX(9px) translateY(4px) rotate(-45deg);
		  width: 2px;
		}
		.bottom {
		  background: #FFF;
		  color:  #444247;
		  font-size: 14px;
		  height: 200px;
		  padding-top: 5px;
		  width: 300px;
		}
		.email.expand {
		  border-radius: 6px;
		  box-shadow: 0 10px 20px rgba(0,0,0,0.10), 0 6px 6px rgba(0,0,0,0.16);
		  height: 200px;
		  width: 300px;
		}
		.expand .from {
		  opacity: 0;
		  transition: opacity 100ms cubic-bezier(0.4, 0.0, 1, 1);
		}
		.expand .from-contents {
		  transform: scale(1.91);
		}
		.expand .to {
		  opacity: 1;
		  transition: opacity 200ms 100ms cubic-bezier(0.0, 0.0, 0.2, 1);
		}
		.expand .to-contents {
		  transform: scale(1);
		}
	</style>
	</head>
	<%! String driverName = "com.mysql.cj.jdbc.Driver";%>
<%!String url = "jdbc:mysql://localhost:3307/notifier";%>
<%!String user = "root";%>
<%!String psw = "root";%>
	
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
	<%
            
	int userid = (int) session.getAttribute("userid");
int count=0;
                       Connection con = null;
                       PreparedStatement ps = null;
                      try
                       {
                       Class.forName(driverName);
                       con = DriverManager.getConnection(url,user,psw);
                       String sql = "SELECT * FROM notebook where userid = ?";
                       ps = con.prepareStatement(sql);
                       ps.setInt(1, userid);
                       ResultSet rs = ps.executeQuery();
                      
                     %>
	
	<div class="content-container">
	<div class="container-fluid">
	
	
	<br>
	<h2 style="color:#4da6ff; margin-left:2%"><strong>Notebooks</strong></h2>
	<div class="email" style="float:right" onclick="this.classList.add('expand')">
		    <div class="from">
		      <div class="from-contents">
		        <div class="name">Add Notebook</div>
		      </div>
		    </div>
		    <div class="to">
		      <div class="to-contents">
		        <div class="top">
		          <div class="name-large" >Add Notebook</div>
		          <div class="x-touch" onclick="document.querySelector('.email').classList.remove('expand');event.stopPropagation();">
		            <div class="x">
		              <div class="line1"></div>
		              <div class="line2"></div>
		            </div>
		          </div>
		        </div>
		        <div class="bottom">
		          
		          <form action="<%=request.getContextPath() %>/addnotebook" method="post" autocomplete="off">
		          		<div style="margin-left:4%;margin-right:4%">
							<input type="text" name="nbname" placeholder="Enter Notebook name" required>
							<br>
							<input type="submit" class="submit" value="Add">
						</div>
					</form>
		        </div>
		      </div>
		    </div>
		  </div>
	<br><br>
		     <!--<c:forEach var="nb" items="${notebooks}">-->
	       <%
                         while(rs.next())
                         {
                         String nbname = rs.getString("nbname"); 
                         int id=rs.getInt("nbid");
                         ps = con.prepareStatement("SELECT count(*) as cm FROM note where nbid="+id);
                         ResultSet rss = ps.executeQuery();
                         rss.next();
                         count = rss.getInt("cm") ;
                         //String emailid = rs.getString("emailid"); 
               %>
			<div class="jumbotron">
	      	
		      <h3><b><a href="notes?nb=<c:out value="<%=nbname%>" />"><c:out value="<%=nbname%>"></c:out></a></b></h3>
		
		       <a style="margin-left:85%" href="editnotebook?nb=<c:out value="<%=nbname%>" />"><i class="fa fa-edit">Edit</i></a>
		        &nbsp;&nbsp;&nbsp;&nbsp; 
		        <a style="color:red" href="deletenotebook?nb=<c:out value="<%=nbname%>" />"><i class="fa fa-trash-alt">Delete</i></a>
		        <p><c:out value="<%=count%>"/>&nbsp;&nbsp;Notes</p>
		        <!-- <td><button (click)="updateNotebook(nb)" class="btn btn-success">Update</button>
		                 <button (click)="deleteNotebook(nb)" class="btn btn-warning">Delete</button></td> -->
	     	 </div>
	     	 	 			 <% 
                         }
                     %>
	     	 
	     <!--</c:forEach>-->
	     	 					
            					
	</div>
	<%
                       }
                        catch(SQLException sqe)
                       { 
                       out.println(sqe);
                       }
                       %>
	
	</div>
	</body>
</html>