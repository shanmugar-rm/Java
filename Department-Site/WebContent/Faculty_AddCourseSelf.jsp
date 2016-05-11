<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.*" %>
<%@page  import="java.sql.*" %>
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
    <%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
    <%ArrayList<String> resource_list = (ArrayList<String>) session.getAttribute("resource_list"); %>
    <div class="container-fluid ">		        
        <div class="row" style="margin:10px;">        
            <div class="col-md-1"></div>
            <div class="col-md-6">
		<form action="FacultyMainServlet?x=EditCourse1" method="post" name="f1" enctype="multipart/form-data">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">

                  <div class="panel-heading" style="text-align:center;"><h4>Edit Course Information</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
					<%ArrayList<Course_information> faculty_course = (ArrayList<Course_information>) session.getAttribute("all_course");
					int i = Integer.parseInt(request.getParameter("item")) ;%>
					<input type="hidden" id="item" value="<%=i%>" name="item" >
					<div class="form-group" style="text-align:left">
                    <label for="Course_ID" style="text-align:left">Course ID:</label>
               		<input type="text" class="form-control" name="course_id" maxlength="35" value="<%=faculty_course.get(i).getCourse_id()%>" readonly="readonly">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Course_Title" style="text-align:left">Course Title:</label>
               		<input type="text" class="form-control" name="course_title" maxlength="35" value="<%=faculty_course.get(i).getCourse_title()%>">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Taken By" style="text-align:left">Taken By:</label>
               		<input type="text" class="form-control" name="taken_by" maxlength="20" value="<%=faculty_course.get(i).getUser_name()%>">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Office_hours" style="text-align:left">Office Hours:</label>
               		<input type="text" class="form-control" name="office_hours" maxlength="40" value="<%=faculty_course.get(i).getOffice_hours()%>">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Office_location" style="text-align:left">Office Location:</label>
               		<input type="text" class="form-control" name="office_location" maxlength="40" value="<%=faculty_course.get(i).getOffice_location()%>">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="email_id" style="text-align:left">Email ID:</label>
               		<input type="text" class="form-control" name="email_id" maxlength="45" value="<%=user_details.getemail_id()%>">
            		</div>            		
            		 
            		<div class="form-group" style="text-align:left">
                    <label for="syllabus" style="text-align:left">Use Same Syllabus or new: (*PDF Only)</label>
               		<input type="radio" name="Same_syllabus" id="Same_syllabus" value="same_syllabus" checked="checked" onClick="radio_disable()">Same Syllabus &nbsp;<input type="radio" name="Same_syllabus" id="New_syllabus" value="new_syllabus" onClick="radio_enable()">New Syllabus
               		<input type="file" class="form-control" name="syllabus" id="syllabus" disabled="disabled">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="TA_names" style="text-align:left">TA Names:</label>
               		<textarea class="form-control" rows="3" cols="25" name="TA_name"><%=faculty_course.get(i).getTA_name()%></textarea>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="TA_email_id" style="text-align:left">TA Email IDs:</label>
               		<textarea class="form-control" rows="3" cols="25" name="TA_email_id"><%=faculty_course.get(i).getTA_email_id()%></textarea>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="TA_office_location" style="text-align:left">TA Office Location and timings:</label>
               		<textarea class="form-control" rows="3" cols="25" name="TA_office_location"><%=faculty_course.get(i).getTA_office_location()%></textarea>
            		</div>
            		
            		<input type="hidden" name="user_id" value="<%=user_details.getUser_name()%>">
            		<input type="hidden" name="fname" value="<%=user_details.getFirst_name()%>">
            		
					<button type="submit" class="btn btn-primary">Save Course Information</button>			
                  
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
    function radio_disable() {
    	document.getElementById("syllabus").disabled = true;
    }
    function radio_enable() {
    	document.getElementById("syllabus").disabled = false;
    }
    </script>
    
    </body>
    </html>