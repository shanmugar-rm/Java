<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.Login_sys" %>        
<%@page  import="java.sql.*" %>
<%@ page import="se.term_project.Announcement" %>
<%@page  import="java.util.*" %>
<%@page  import="java.io.*" %>
<%! @SuppressWarnings("unchecked") %>  
<!DOCTYPE html>
<html lang="en">
<head>    
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UAlbany CS department</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/CustomStyle.css" rel="stylesheet">
    <jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="SideBar.jsp"></jsp:include>
</head>
<body>   
    <div class="container-fluid ">        
        <div style="margin:10px;">        
            <div class="col-md-1"></div>
            <div class="col-md-9">
		<form action="StaffMainServlet?x=MakeAnnouncement_post" method="post" name="f1">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Announcement> announcements = (ArrayList<Announcement>) session.getAttribute("announcements");
					%>
					<%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
                  <div class="panel-heading" style="text-align:center;"><h4>Delete Announcements</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
					
					<p style="color:Black">Existing Announcements</p>					
					
					<table class="table table-striped">
					<tr> 
						<td>Announced BY</td>
						<td>Announcement type</td>
						<td>Announcement Date</td>
						<td>Announcement Heading</td>
						<td>Announcement Content</td>
						<td>Delete Content</td>
					</tr>
					<%String display_only;
					for (int i = 0; i< announcements.size();i++) { 						
					%>
					<tr>
						<td><%=announcements.get(i).getUser_name()%></td>
						<td><%=announcements.get(i).getAnnouncement_type()%></td>
						<td><%=announcements.get(i).getAnnouncement_date()%></td>
						<td><%=announcements.get(i).getAnnouncement_heading()%></td>						
						<td><%=announcements.get(i).getAnnouncement_content()%></td>
						<%if (announcements.get(i).getUser_name().equals(user_details.getFirst_name())) { %>
						<td><button type="button" class="btn btn-primary" onClick="return del_confirm()"><a href='StaffMainServlet?unique_id=<%=announcements.get(i).getUnique()%>&x=DeleteAnnouncement_list' style="color:white">Delete</a></button></td><td>
						<%}
						else { %>
						<td></td>
						<%} %>						
					</tr>
					<%} %>
					</table>													 
              </div>
            </div>
            </form>     
            </div>
            </div>    
</div>
    <script src="js/jquery-1.11.2.min.js"></script>    
    <script src="js/bootstrap.min.js"></script>
    <script>
    function del_confirm() {
    	var r = confirm("Are you sure you want to Delete ??");
    	if (r==true) {
    		return true;
    	}
    	else {
    		return false;
    	}    	
    }
    
    function edit_confirm() {
    	var r = confirm("Are you sure you want to Edit ??");
    	if (r==true) {
    		return true;
    	}
    	else {
    		return false;
    	}    	
    }
    </script>
    
    </body>
    </html>