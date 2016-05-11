<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.Login_sys" %>
<%@ page import="java.util.ArrayList" %>        
<%@page  import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UAlbany CS department site</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/CustomStyle.css" rel="stylesheet">
</head>
 
<body>
    <%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
    <div class="container-fluid ">
		<div class="row">		
			<!-- header area start -->
      <div class="headerStyle">
          <span>
          <h1><img src="Images/UAlbany.png" style="height:60px;width:60px;">
				UAlbany CS department</h1>
            </span>    
                 <span style="float:right;">
                 <strong >Welcome <%=user_details.getLast_name() %> , <%=user_details.getFirst_name() %></strong> &nbsp;
                 <a href="/SE_Term_Project/LogoutServlet" ><span class="label label-primary btn-lg">Log out</span></a>
                </span>                           
      </div>
			</div>
			</div>
			</body>
			</html>