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
    <title>UAlbany CS department</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/CustomStyle.css" rel="stylesheet">
    <jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="SideBar.jsp"></jsp:include>
</head>
<body>    
    <div class="container-fluid ">		        
        <div style="margin:10px;">                    
        <div class="col-md-9">
		<form action="#" method="post" name="f1">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Phd_status> student_list = (ArrayList<Phd_status>) session.getAttribute("phd_student_list");					%>
                  <div class="panel-heading" style="text-align:center;"><h4>PhD Students of CS department</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
     				<p style="color:Black"></p> 		
     				
     				<table class="table table-striped">
					<tr>
						<td>First Name</td> 
						<td>Last Name</td>
						<td>Year Joined</td>
						<td>Current Semester</td>
						<td>Is Candidate ?</td>
						<td>Edit ?</td>						
					</tr>
					<%for (int i = 0; i< student_list.size();i++) { %>
					<tr>
					<%String display = "";
					String temp = student_list.get(i).getis_candidate();
					if (temp.equals("0")) {
						display = "No";
					}
					if (temp.equals("1")) {
						display = "Yes";
					}
					String year_joined = student_list.get(i).getyear_joined();
					String[] break_down  = year_joined.split(" ",2);
					String semester = break_down[0];
					String year = break_down[1];
					int number_of_semester = 0;
					if (semester.equals("Fall")) {
						number_of_semester = 2* (2016 - Integer.valueOf(year));
					}
					if (semester.equals("Spring")) {
						number_of_semester = 1 +  2* (2016 - Integer.valueOf(year));
					}
					%>
						<td><%=student_list.get(i).getfirst_name()%></td>
						<td><%=student_list.get(i).getlast_name()%></td>
						<td><%=student_list.get(i).getyear_joined()%></td>
						<td><%=number_of_semester%></td>	
						<td><%=display%></td>	
						<td><button type="button" class="btn btn-primary" onClick="return edit_confirm()"><a href='PhD_EditInfo.jsp?item=<%=i%>' style="color:white">Edit</a></button></td>									
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