<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.*" %>
<%@page  import="java.util.*" %> 
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
            <div class="col-md-1"></div>
            <div class="col-md-6">
		<form action="FacultyMainServlet?x=AddCourse" method="post" name="f1" enctype="multipart/form-data">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">

                  <div class="panel-heading" style="text-align:center;"><h4>Add Course Information</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
					
					<div class="form-group" style="text-align:left">
                    <label for="Course_ID" style="text-align:left">Course ID:</label>
               		<input type="text" class="form-control" name="course_id" maxlength="35" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Course_Title" style="text-align:left">Course Title:</label>
               		<input type="text" class="form-control" name="course_title" maxlength="35" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Office_hours" style="text-align:left">Instructor Office Hours:</label>
               		<input type="text" class="form-control" name="office_hours" maxlength="40">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Office_location" style="text-align:left">Instructor Office Location:</label>
               		<input type="text" class="form-control" name="office_location" maxlength="40">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="email_id" style="text-align:left">Instructor Email ID:</label>
               		<input type="text" class="form-control" name="email_id" maxlength="45">
            		</div>
            		
            		 
            		<div class="form-group" style="text-align:left">
                    <label for="syllabus" style="text-align:left">Syllabus: (*only PDF)</label>
               		<input type="file" class="form-control" name="syllabus" id="syllabus">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="TA_Names" style="text-align:left">TA Names:</label>
               		<textarea class="form-control" rows="3" cols="25" name="TA_name" ></textarea>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="TA_email_id" style="text-align:left">TA Email IDs:</label>
               		<textarea class="form-control" rows="3" cols="25" name="TA_email_id"></textarea>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="TA_email_id" style="text-align:left">TA Office Location and timings:</label>
               		<textarea class="form-control" rows="3" cols="25" name="TA_office_location"></textarea>
            		</div>
            		
					<button type="submit" class="btn btn-primary">Add Course</button>			
                  
              </div>
            </div>
            </form>     
            
            </div>
    </div>
</div>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>    
    </script>

    </body>
    </html>
