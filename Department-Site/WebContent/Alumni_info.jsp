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
    <div class="container-fluid ">		        
        <div style="margin:10px;">                    
        <div class="col-md-9">
		<form action="#" method="post" name="f1">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Alumni_info> alumni_list = (ArrayList<Alumni_info>) session.getAttribute("alumni_list");					%>
                  <%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
                  <div class="panel-heading" style="text-align:center;"><h4>Alumni Details</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
     				<p style="color:Black"></p> 		
     				
     				<table class="table table-striped">
					<tr>
						<td>First Name</td> 
						<td>Last Name</td>
						<td>Year Pass-out</td>
						<td>Current Address</td>
						<td>Current Job</td>
						<%if (!user_details.getStudent_employee().equals("Student")) {%>
						<td>Edit ?</td>						
						<%} %>
					</tr>
					<%for (int i = 0; i< alumni_list.size();i++) { %>
					<tr>					
						<td><%=alumni_list.get(i).getfname()%></td>
						<td><%=alumni_list.get(i).getlname()%></td>
						<td><%=alumni_list.get(i).getyear_passout()%></td>
						<td><%=alumni_list.get(i).getcurrent_address()%></td>	
						<td><%=alumni_list.get(i).getcurrent_company()%></td>	
						<%if (!user_details.getStudent_employee().equals("Student")) {%>
						<td><button type="button" class="btn btn-primary" onClick="return edit_confirm()"><a href='Alumni_EditInfo.jsp?item=<%=i%>' style="color:white">Edit</a></button></td>
						<%} %>									
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