<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.*" %>
<%@page  import="java.util.*" %>
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
    <%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>    
    <div class="container-fluid ">		        
        <div style="margin:10px;">                    
        <div class="col-md-9">
		<form action="StaffMainServlet?x=ReserveResourse1" method="post" name="f1">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Announcement> announcements = (ArrayList<Announcement>) session.getAttribute("announcements");%>                  
                  <div class="panel-heading" style="text-align:center;"><h4>Apply for Jobs</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
     				<p style="color:Black">All Jobs Listed</p> 		
     				
     				<table class="table table-striped">
					<tr>						
						<td>Job Announcement Made By</td>
						<td>Job Announcement Made Date</td>
						<td>Job Announcement Heading</td>																		
						<td>Job Announcement Content</td>															
					</tr>
					<%for (int i = 0; i< announcements.size();i++) { %>
					<%if (announcements.get(i).getAnnouncement_type().equals("Job")) {%>
					<tr>						
						<td><%=announcements.get(i).getUser_name()%></td>
						<td><%=announcements.get(i).getAnnouncement_date()%></td>
						<td><%=announcements.get(i).getAnnouncement_heading()%></td>
						<td><a href="mailto:<%=announcements.get(i).getAnnouncement_content()%>"><%=announcements.get(i).getAnnouncement_content()%></a></td>																																														
					</tr>
					<%}} %>
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
    
    function add_confirm() {
    	var r = confirm("Are you sure you want to Add the course to you ??");
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