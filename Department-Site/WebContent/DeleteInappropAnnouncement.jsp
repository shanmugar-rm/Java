<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page  import="java.sql.*" %>
<%@ page import="se.term_project.*" %>
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
        <div style="margin:10px;">        
            <div class="col-md-1"></div>
            <div class="col-md-9">
		<form action="StaffMainServlet?x=MakeAnnouncement_post" method="post" name="f1" onsubmit="return check_data()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Announcement> announcements = (ArrayList<Announcement>) session.getAttribute("announcements_inapprop");
                  ArrayList<Discussion> discussion_content = (ArrayList<Discussion>) session.getAttribute("discussion_content_inapprop");
					%>					
                  <div class="panel-heading" style="text-align:center;"><h4>Delete Inappropriate Content</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
					
					<p style="color:Black">Existing Announcements</p>					
					
					<table align="left" class="table table-striped">
					<tr> 
						<td>Announced BY</td>
						<td>Announcement type</td>
						<td>Announcement Date</td>
						<td>Announcement Heading</td>
						<td>Announcement Content</td>
						<td>Delete Content</td>
					</tr>
					<%
					for (int i = 0; i< announcements.size();i++) { 						
					%>
					<tr>
						<td><%=announcements.get(i).getUser_name()%></td>
						<td><%=announcements.get(i).getAnnouncement_type()%></td>
						<td><%=announcements.get(i).getAnnouncement_date()%></td>
						<td><%=announcements.get(i).getAnnouncement_heading()%></td>						
						<td><%=announcements.get(i).getAnnouncement_content()%></td>
						<td><button type="button" class="btn btn-primary" onClick="return del_confirm()"><a href='StaffMainServlet?unique_id=<%=announcements.get(i).getUnique()%>&x=DeleteInAppropriate&type=Announcement' style="color:white">Delete</a></button></td><td>
					</tr>
					<%} %>
					</table>				
					
					<p style="color:Black">Existing Discussions</p>	
					
					<table class="table table-striped">
					<tr> 
						<td>Posted BY</td>
						<td>Post / Reply</td>
						<td>Post Heading</td>
						<td>Reply Content</td>
						<td>Delete Content</td>
					</tr>
					<%
					for (int i = 0; i< discussion_content.size();i++) { 						
					%>
					<tr>
						<td><%=discussion_content.get(i).getUsername()%></td>
						<td>Post</td>
						<td><%=discussion_content.get(i).getContent()%></td>
						<td></td>						
						<td><button type="button" class="btn btn-primary" onClick="return del_confirm()"><a href='StaffMainServlet?unique_id=<%=discussion_content.get(i).getId()%>&x=DeleteInAppropriate&type=DissHead' style="color:white">Delete</a></button></td><td>						
					</tr>
					<% for (int j = 0; j<discussion_content.get(i).getChildcontent().size();j++ ) { %>
					<tr>
						<td><%=discussion_content.get(i).getPostMadeBy().get(j)%></td>
						<td>Reply</td>
						<td><%=discussion_content.get(i).getContent()%></td>
						<td><%=discussion_content.get(i).getChildcontent().get(j)%></td>						
						<td><button type="button" class="btn btn-primary" onClick="return del_confirm()"><a href='StaffMainServlet?unique_id=<%=discussion_content.get(i).getId()%>&x=DeleteInAppropriate&type=DissContent&content=<%=discussion_content.get(i).getChildcontent().get(j)%>' style="color:white">Delete</a></button></td><td>						
					</tr>															
					<%} %>					
					<%} %>
					</table>	
														 
              </div>
            </div>
            </form>                 
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