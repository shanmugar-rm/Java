<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.Login_sys" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UAlbany CS department site</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/CustomStyle.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
<body>
<div style="margin:10px;">
        <div class="col-md-3" style="border:solid 0px">
          <ul class="nav nav-pills nav-stacked" >
           <li class="active"><a href="/SE_Term_Project/FacultyMainServlet?x=Home">Home</a></li>
           <%if (user_details.getStudent_employee().equals("Faculty")) { %>
              <li class="active"><a href="/SE_Term_Project/FacultyMainServlet?x=Post_Information">Post Course Information</a></li>
           <% }%>
           <%if (user_details.getStudent_employee().equals("Staff")) { %>           
              <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=AddResource">Add Resource</a></li>
           <% }%>           
           <%if (user_details.getStudent_employee().equals("Staff") || user_details.getStudent_employee().equals("Faculty")) { %>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=ReserveResourse">Reserve Resources</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=CancelResourse">Cancel Resources</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=MakeAnnouncement">Post job opportunities, events and news</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=DeleteAnnouncement">Delete job opportunities, events and news</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=DeleteInappropriateContent">Delete Inappropriate Content</a></li>           
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=ViewDepartmentResults">Post / View / Edit Department Results</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=View_post_updatePhdStudentStatus">View / Post / Update PhD Student Status</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=View_post_DiscussionBoard">View / Post in Discussion Board</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=View_post_updateAlumniinfo">View / Post / Update Alumni info</a></li>
           <li class="active"><a href="UpdateUser.jsp">Update User Info</a></li>
           <%} %>
           <%if (user_details.getStudent_employee().equals("Student")) { %>
           <li class="active"><a href="/SE_Term_Project/StudentMainServlet?x=ViewOfficeHours">View Professor And TA office Hours</a></li>
           <li class="active"><a href="/SE_Term_Project/StudentMainServlet?x=SendEmail">Send Email to Professor and TA</a></li>
           <li class="active"><a href="/SE_Term_Project/FacultyMainServlet?x=Post_Information">View Course Information</a></li>
           <li class="active"><a href="/SE_Term_Project/StudentMainServlet?x=ApplyJob">Apply for jobs</a></li>     
           <li class="active"><a href="/SE_Term_Project/StudentMainServlet?x=ProgramReq">View Program requirements and status</a></li>
           <li class="active"><a href="/SE_Term_Project/StudentMainServlet?x=SuggestedCourses">Suggested Courses</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=ViewDepartmentResults">Sign Up / View Department Results</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=View_post_DiscussionBoard">View / Post in Discussion Board</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=ReserveResourse">Reserve Resources</a></li>
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=CancelResourse">Cancel Resources</a></li>           
           <li class="active"><a href="/SE_Term_Project/StaffMainServlet?x=View_post_updateAlumniinfo">View Alumni info</a></li>           
           <li class="active"><a href="UpdateUser.jsp">Update User Info</a></li>
           <%} %>          
          </ul>
          <br>
        </div>
        </div>