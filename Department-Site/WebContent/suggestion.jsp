<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.term_project.Login_sys" %>
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
                  <%ArrayList<Suggestion> suggestion = (ArrayList<Suggestion>) session.getAttribute("suggestion");					%>
                  <div class="panel-heading" style="text-align:center;"><h4>Suggested Courses For Your Concentration</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
     				<p style="color:Black"></p> 		
     				
     				<table class="table table-striped">
					<tr>
						<td>Concentration</td> 
						<td>Suggested Courses</td>												
					</tr>
					<%for (int i = 0; i< suggestion.size();i++) { %>
					<tr>					
						<td><%=suggestion.get(i).getconcentration()%></td>
						<td><%=suggestion.get(i).getSuggested_courses()%></td>															
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