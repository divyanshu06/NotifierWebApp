<style><%@include file="css/home.css"%></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.*"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.*"%>

<div class="sidebar-container">
  <div class="sidebar-logo">
    Notifier
  </div>
  <ul class="sidebar-navigation">
    <li class="header">Navigation</li>
    <li>
      <a href="home">
        <i class="fa fa-home" aria-hidden="true"></i> Home
      </a>
    </li>
    <li>
      <a href="notebook">
        <i class="fa fa-book-open" aria-hidden="true"></i> Notebooks
      </a>
    </li>
     <li>
      <a href="allnotes">
        <i class="fa fa-sticky-note" aria-hidden="true"></i> Notes
      </a>
    </li>
     <li>
      <a href="edituser">
        <i class="fa fa-user-edit" aria-hidden="true"></i> Edit User
      </a>
    </li>
    <li>
      <a href="logout">
        <i class="fa fa-sign-out-alt" aria-hidden="true"></i> Logout
      </a>
    </li>
    </ul>
</div>
	<div style="padding-left:220px">
	<nav class="navbar">
		<ul>
	<li>
		<a href="reminder">
			<i class="fa fa-bell" aria-hidden="true"></i>
		</a>				
	</li>
</ul> 
	</nav>
</div>

 