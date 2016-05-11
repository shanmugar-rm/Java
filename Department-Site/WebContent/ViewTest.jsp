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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script>
   		$(function() {
     	$( "#datepicker" ).datepicker({  minDate: 0, // your min date
        });
   		});
  	</script>
</head>
<body>
    <%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>    
    <div class="container-fluid ">		        
        <div style="margin:10px;">                    
        <div class="col-md-9">
		<form action="StaffMainServlet?x=ViewDepartmentResults1" method="post" name="f1">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Create_test_M> test_list = (ArrayList<Create_test_M>) session.getAttribute("test_list");%>                  
                  <%ArrayList<Student_test> student_list = (ArrayList<Student_test>) session.getAttribute("student_list");%>
                  <div class="panel-heading" style="text-align:center;"><h4>Test</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
     				
     				<%if(!user_details.getStudent_employee().equals("Student")) {%>
     				
     				<div class="form-group" style="text-align:left">
                    <label for="test_name" style="text-align:left">Test Name:</label>
               		<input type="text" class="form-control" name="test_name" maxlength="20" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Course_ID" style="text-align:left">Test Date:</label>
               		<input type="Text" id="datepicker" name="datepicker"  placeholder="Select date" style="text-align:center;background: transparent;" name="datepicker" class="searchBar" readonly="readonly" >
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="test_details" style="text-align:left">Test Details:</label>
               		<input type="text" class="form-control" name="test_details" maxlength="30" required>
            		</div>
     				
     				<button type="submit" class="btn btn-default" >Add Test</button> <br><br>
     				
     				<%} %>
     				
     				<p style="color:Black">Test List</p> 		
     				<%ArrayList<String> exams_enrolled = new ArrayList<String>();%>
     				<%for (int j = 0; j<student_list.size(); j++) {%>
     				<%if (student_list.get(j).getstudent_fname().equals(user_details.getFirst_name()) && student_list.get(j).getstudent_lname().equals(user_details.getLast_name()) ) {%>
     				<%
     				exams_enrolled.add(student_list.get(j).getparentid()); %>
     				<%}} %>
     				
     				<table class="table table-striped">
					<tr>
						<td>Test Name</td> 
						<td>Test Created by</td>
						<td>Test Date</td>
						<td>Test Link</td>
						<%if (!user_details.getStudent_employee().equals("Student")){ %>
						<td>Post Results</td>
						<%} 
						else { %>
						<td>Register?</td>						
						<% }%>
						<td>View Results</td>
					</tr>
					<%for (int i = 0; i< test_list.size();i++) { %>
					<tr>
						<td><%=test_list.get(i).gettest_name()%></td>
						<td><%=test_list.get(i).gettested_created_by()%></td>
						<td><%=test_list.get(i).gettest_date()%></td>
						<td><a href='<%=test_list.get(i).gettest_details()%>' target="_blank">Links</a></td>
						<%if (!user_details.getStudent_employee().equals("Student")){ %>
						<td><button type="button" class="btn btn-primary"><a href='ResultView.jsp?item=<%=test_list.get(i).getUnique_id()%>&type=edit' style="color:white">Post</a></button></td>
						<%} 
						else {%>
						<%if (!exams_enrolled.contains(test_list.get(i).getUnique_id())) {%>
						<td><button type="button" class="btn btn-primary"><a href='StaffMainServlet?x=RegisterTest&item=<%=test_list.get(i).getUnique_id()%>' style="color:white">Register</a></button></td>
						<%} 
						else { %>							
						<td>Already Registered</td>
						<% }}%>						
						<td><button type="button" class="btn btn-primary"><a href='ResultView.jsp?item=<%=test_list.get(i).getUnique_id()%>&type=view' style="color:white">View</a></button></td>																 					
					</tr>
					<%}%>					
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