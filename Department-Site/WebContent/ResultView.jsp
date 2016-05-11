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
		<form action="StaffMainServlet?x=SaveMark" method="post" name="f1">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Student_test> student_list = (ArrayList<Student_test>) session.getAttribute("student_list");%>                  
                  <%String item = request.getParameter("item"); %>                  
                  <%String type = request.getParameter("type"); %>                         
                                   
                  <div class="panel-heading" style="text-align:center;"><h4>Result</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>     			
     				
     				<p style="color:Black">Student List</p> 		
     				<input type="hidden" name = "item" value="" id="item">
     				<input type="hidden" name = "parentid" value ="<%=item%>">
     				<table class="table table-striped">
					<tr>
						<td>Student First Name</td> 
						<td>Student Last Name</td>
						<td>Mark</td>						
						<%if (type.equals("edit")){ %>
						<td>Post Results</td>
						<%} %>						
					</tr>
					<%for (int i = 0; i< student_list.size();i++) { %>					
					<%if (student_list.get(i).getparentid().equals(item)) {%>
					<tr>
						<td><%=student_list.get(i).getstudent_fname()%></td>
						<td><%=student_list.get(i).getstudent_lname()%></td>											
						<%if (type.equals("edit")){ %>
						<td><input type="text" name = "mark<%=student_list.get(i).getUnique_id()%>" value ="<%=student_list.get(i).getstudent_mark()%>"></td>
						<td><button type="submit" class="btn btn-primary" id="<%=student_list.get(i).getUnique_id()%>" onClick="set_value(this.id)">Save</button></td>
						<%} 
						else {%>
						<td><%=student_list.get(i).getstudent_mark()%></td>
						<%} %>
						<%} %>																								 					
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
    
    function set_value(k) {
    	document.getElementById("item").value = k;
    }
    </script>
    
</body>
</html>