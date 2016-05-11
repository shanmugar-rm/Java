<%@page  import="java.sql.*" %>
<%@ page import="se.term_project.Login_sys" %>
<%@ page import="se.term_project.ResourseStatus" %>
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
            <div class="col-md-2"></div>
            <div class="col-md-4">
            <%ArrayList<ResourseStatus> list_res = (ArrayList<ResourseStatus>)session.getAttribute("list_res");%>
		<form action="StaffMainServlet?x=AddResource1" method="post" name="f2" onSubmit="return checkdata(<%=list_res.size()%>)">
		<h1><p style="color:black">${message}</p></h1>
            <div class="panel panel-default">            
                  <div class="panel-heading" style="text-align:center;"><h4>Add Resource</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    
                    <div class="form-group" style="text-align:left">
                    <label for="Resource Name" style="text-align:left">Resource Name(*Unique):</label>
                	<input type="text" class="form-control" name="resource_name" id="resource_name" maxlength="20" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Resource Details" style="text-align:left">Resource Details:</label>
                	<input type="text" class="form-control" name="resource_details" id="resource_details" maxlength="60" required>
            		</div>
            		            		
            		<%for(int i =0;i<list_res.size(); i++) {%>
            			<input type="hidden" name="res_list<%=i%>" id="res_list<%=i%>" value="<%=list_res.get(i).getResourse_name()%>">
            		<%} %>
            <button type="submit" class="btn btn-default" >Add</button>                                    
              </div>
            </div>
            </form>     
            <div class="col-md-4"></div>
    </div>
    </div>
</div>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
    function checkdata(k)
    {               
    	var i =0;    	
    	for (i = 0;i<k;i++){
    		var temp = "res_list" + i;    		
    	 	if(document.getElementById(temp).value == document.getElementById("resource_name").value)
       	 	{
	       	 	alert("Resource Name is not unique");
    	   	 	return false;
       		}
    	}                          
        return true;
    }              
    
    </script>
    
</body>
</html>