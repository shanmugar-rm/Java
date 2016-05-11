<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.*" %>        
<%@page  import="java.sql.*" %>
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
		<form action="StaffMainServlet?x=view_post_discussion_reply" method="post" name="f1" onsubmit="return check_data()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Discussion> discussion_content = (ArrayList<Discussion>) session.getAttribute("discussion_content");
					%>
					<%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
                  <div class="panel-heading" style="text-align:center;"><h4>View / Post in Discussion Board</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
					<%if (!user_details.getStudent_employee().equals("Student")) {%>
					<p style="color:Black">Post in Discussion Board</p>					
					
				    <div class="form-group" style="text-align:left">
                    <label for="post_question" style="text-align:left">Post Question:</label>
                	<input type="text" class="form-control" name="new_question" maxlength="200">
            		</div>
            		            		
					<button type="submit" class="btn btn-default" onClick="change()">Post Question</button> <br> <br>
										
					<%} %>
					<input type = "hidden" name = "post_reply" id="post/value">
					<input type = "hidden" name = "id_feed" id="id/feed">
					<p style="color:Black">Reply in Discussion Board</p>
					
					<% for (int i = 0; i<discussion_content.size();i++) { %>		
					
					<div class="form-group" style="text-align:left">
                    <label for="post_question" style="text-align:left">Post Created by: <%=discussion_content.get(i).getUsername() %> - <%=discussion_content.get(i).getContent() %></label>
            		</div>
            							
					<% for (int j = 0; j<discussion_content.get(i).getChildcontent().size();j++) { %>
					
					<p style="text-align:left">Replied by: <%=discussion_content.get(i).getPostMadeBy().get(j) %> - <%=discussion_content.get(i).getChildcontent().get(j) %></p>
					
					<%} %>		
					
					<div class="form-group" style="text-align:left">
                    <label for="post_question" style="text-align:left">Reply :</label>
                	<input type="text" name="reply<%=discussion_content.get(i).getId()%>" maxlength="200" size="50"> 
                	<button type="submit" class="btn btn-default" onclick="change1(<%=discussion_content.get(i).getId()%>)">Reply</button>
            		</div>
										
					<%} %>														 
              </div>
            </div>
            </form>                 
            </div>    
</div>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>                
    function change() {
    	document.getElementById("post/value").value = "create_post"; 
    }
    function change1(id) {
    	document.getElementById("post/value").value = "reply_post";
    	document.getElementById("id/feed").value = id;
    }
    </script>
    
    </body>
    </html>