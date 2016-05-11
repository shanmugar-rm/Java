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
                  <div class="panel-heading" style="text-align:center;"><h4>Make Announcement</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
					
					<p style="color:Black">Post Announcements</p> 				
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Announcement Type" style="text-align:left">Announcement Type:</label>
                	<select name="Announcement_type" class="form-control" required>
                        <option value="None">--Select one--</option>
                        <option value="Job">Jobs</option>
                        <option value="Events">Events</option>
                        <option value="News">News</option>
                	</select>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Announcement Heading" style="text-align:left">Announcement Heading:</label>
                	<input type="text" class="form-control" name="Announcement_heading" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Announcement Content" style="text-align:left">Announcement Content:</label>
                	<textarea class="form-control" name="Announcement_content" rows="3" cols="25" required ></textarea>
            		</div>
            		
            		<button type="submit" class="btn btn-default" >Post</button>
					<br>
					<p style="color:Black">Existing Announcements</p>					
					
					<table class="table table-striped">
					<tr> 
						<td>Announced BY</td>
						<td>Announcement type</td>
						<td>Announcement Date</td>
						<td>Announcement Heading</td>
						<td>Announcement Content</td>
						<td>Edit Content</td>
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
						<td><button type="button" class="btn btn-primary" onClick="return edit_confirm()"><a href='Announcement_edit.jsp?item=<%=i%>' style="color:white">Edit</a></button></td>
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
