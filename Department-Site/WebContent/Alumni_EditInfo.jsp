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
        <div class="col-md-1"></div>           
        <div class="col-md-6">
		<form action="StaffMainServlet?x=View_post_updateAlumniinfo1" method="post" name="f1" onSubmit="return checkdata()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ArrayList<Alumni_info> alumni_list = (ArrayList<Alumni_info>) session.getAttribute("alumni_list");					%>
                  <%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
                  <%int i = Integer.parseInt(request.getParameter("item")) ;%>
                  <div class="panel-heading" style="text-align:center;"><h4>Edit Alumni Info</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>										
            		
            		<div class="form-group" style="text-align:left">
                    <label for="fname" style="text-align:left">First Name:</label>
                    <input type="text" class="form-control" name="fname" value="<%=alumni_list.get(i).getfname()%>" readonly>                	
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="lname" style="text-align:left">Last Name:</label>
                	<input type="text" class="form-control" name="lname" value="<%=alumni_list.get(i).getlname()%>" readonly>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="year_passout" style="text-align:left">Year Pass-Out:</label>
                	<input type="text" class="form-control" name="year_passout" id="year_passout" maxlength="4" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="current_address" style="text-align:left">Current Address:</label>
                	<input type="text" class="form-control" name="current_address" id="current_address" maxlength="30" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="current_job" style="text-align:left">Current Job:</label>
                	<input type="text" class="form-control" name="current_job" id="current_job" maxlength="30" required>
            		</div>
            		
            		<input type="hidden" name="unique_id" value="<%=alumni_list.get(i).getUnique_id()%>">
            		
            		<button type="submit" class="btn btn-default" >Save</button>																		 
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
    
    function checkdata()
    {               
    	var p=document.f1.year_passout.value;         	       
        var i;        
        var flag=0;
        for(i=0;i<p.length;i++)
        {
           var ch=p.charAt(i);
           if(ch>='0' && ch<='9')
           {
                 
           }
           else
           {
             flag=1;
           }          
        }            
        if(flag==1)
        {
           alert("Year: Alphabet not allowed");
           return false;
        }                    
    }
    </script>
    
    </body>
    </html>    
