<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.Login_sys" %>
<%@ page import="se.term_project.Phd_status" %>
<%@page  import="java.util.*" %>
<%! @SuppressWarnings("unchecked") %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UAlbany CS department site</title>    
    <link href="css/bootstrap.min.css" rel="stylesheet">  
    <link href="css/CustomStyle.css" rel="stylesheet">
    <jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="SideBar.jsp"></jsp:include>
</head>
<body>    
    <%ArrayList<Phd_status> student_list = (ArrayList<Phd_status>) session.getAttribute("phd_student_list");					%>
    <div class="container-fluid ">		        
        <div class="row" style="margin:10px;">        
            <div class="col-md-1"></div>
            <div class="col-md-6">
		<form action="StaffMainServlet?x=EditPhdStudentStatus" method="post" name="f1" onSubmit="return check_data()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Edit PhD Student's Information</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
					<%int i = Integer.parseInt(request.getParameter("item")) ;%>
					<input type="hidden" id="unique_id" value="<%=student_list.get(i).getUnique_id()%>" name="unique_id" >
					
					<div class="form-group" style="text-align:left">
                    <label for="First_name" style="text-align:left">First Name:</label>
               		<input type="text" class="form-control" name="first_name" maxlength="35" value="<%=student_list.get(i).getfirst_name()%>" readonly="readonly">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="last_name" style="text-align:left">Last Name:</label>
               		<input type="text" class="form-control" name="last_name" maxlength="35" value="<%=student_list.get(i).getlast_name()%>" readonly="readonly">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Year_joined" style="text-align:left">Year Joined:</label>
               		<input type="text" class="form-control" name="year_joined" maxlength="35" value="<%=student_list.get(i).getyear_joined()%>" readonly="readonly">
            		</div>
            		
            		<%String test = student_list.get(i).getis_candidate();
            		String display ="";
            		if (test.equals("0")) {
            			display = "No";
            		}
            		if (test.equals("1")) {
            			display = "Yes";
            		}            		
            		%>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="iscandidate" style="text-align:left">*Is Candidate(Yes / No):</label>
               		<input type="text" class="form-control" name="is_candidate" id="is_candidate" maxlength="3" value="<%=display%>">
            		</div>
            		
					<button type="submit" class="btn btn-primary" onClick="return edit_confirm()">Save PhD Student's Information</button>			
                  
              </div>
            </div>
            </form>     
            </div>
            </div>    
</div>
   
    <!-- jQuery -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <script>    
    function check_data() {
    	var to_check = document.getElementById("is_candidate").value
    	if (to_check == "Yes" || to_check == "No") {
    		return true;
    	}
    	alert("Is Candidate should be Yes or No, noting else!!!");
    	return false;
    }
    
     
    function edit_confirm() {
    	var r = confirm("Are you sure you want to Save ??");
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