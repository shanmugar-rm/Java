<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="se.term_project.Course_information" %>        
<%@page  import="java.sql.*" %>
<%@page  import="java.util.*" %>
<%@page  import="java.io.*" %>
<%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%ArrayList<Course_information> faculty_course = (ArrayList<Course_information>) session.getAttribute("faculty_course");
ArrayList<Course_information> all_course = (ArrayList<Course_information>) session.getAttribute("all_course");
int i = Integer.parseInt(request.getParameter("item")) ;
int which = Integer.parseInt(request.getParameter("which")) ;
Blob syllabus = null;
if (which == 1) {
	syllabus =  faculty_course.get(i).getSyllabus();
}
else {
	syllabus =  all_course.get(i).getSyllabus();
}


						byte[] syllabus_byte = null;
						try {
							syllabus_byte = syllabus.getBytes(1, (int)syllabus.length());
						}
						catch(Exception ex) {
							System.out.println("Something went wrong");
						}
						response.setContentType("application/pdf");
					    response.setHeader("Content-Disposition", "inline");
					    response.setContentLength(syllabus_byte.length);
					    OutputStream syllabus_stream = response.getOutputStream();
					    syllabus_stream.write(syllabus_byte);
					    syllabus_stream.flush();
						%>
</body>
</html>