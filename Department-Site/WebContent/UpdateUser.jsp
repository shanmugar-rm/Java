<%@page  import="java.sql.*" %>
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
            <div class="col-md-2"></div>
            <div class="col-md-4">            
		<form action="StaffMainServlet?x=UpdateUser" method="post" name="f2" onSubmit="return checkdata()">
		<h1><p style="color:black">${message}</p></h1>
            <div class="panel panel-default">            
                  <div class="panel-heading" style="text-align:center;"><h4>Update User Details</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    <%Login_sys user_details = (Login_sys) session.getAttribute("user_details_db"); %>
                    <div class="form-group" style="text-align:left">
                    <label for="First Name" style="text-align:left">First Name:</label>
                	<input type="text" class="form-control" name="First_name" maxlength="30" value="<%=user_details.getFirst_name()%>" readonly>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Last Name" style="text-align:left">Last Name:</label>
                	<input type="text" class="form-control" name="Last_name" maxlength="30" value="<%=user_details.getLast_name()%>" readonly>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Email ID" style="text-align:left">Email ID:</label>
                	<input type="text" class="form-control" name="Email_id" maxlength="40" required value="<%=user_details.getemail_id()%>">
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Advisor Name" style="text-align:left">Advisor Name:</label>
                	<input type="text" class="form-control" name="Advisor_name" maxlength="30" required value="<%=user_details.getAdvisor_name()%>">
            		</div>            		
            
            <div class="form-group" style="text-align:left">
                <label for="phone_number" id="year_joined_label" style="text-align:left">Phone number:</label>
                <input type="text" class="form-control" name="phone_number" id="phone_number" maxlength="10" value="<%=user_details.getphone()%>">
            </div>
                          
        	<div class="form-group" style="text-align:left">
                <label for="user_name" style="text-align:left">NET ID:</label>
                <input type="text" class="form-control" name="user_name" maxlength="15" required value="<%=user_details.getUser_name()%>" readonly>
            </div>
        
            <div class="form-group" style="text-align:left">
                    <label for="syllabus" style="text-align:left">Change Password ?</label>
               		<input type="radio" name="Same_syllabus" id="Same_syllabus" value="same_syllabus" checked="checked" onClick="radio_disable()">No &nbsp;<input type="radio" name="Same_syllabus" id="New_syllabus" value="new_syllabus" onClick="radio_enable()">Yes
               		<label for="syllabus" style="text-align:left" id="lable1" >Password</label>
               		<input type="password" class="form-control" name="password" id="password" maxlength="15">
               		<label for="syllabus" style="text-align:left" id="lable" >Confirm Password</label>
               		<input type="password" class="form-control" name="password1" id="password1"  maxlength="15">
            </div>
            		            		            		
            <button type="submit" class="btn btn-default" >Save</button>                                    
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
    
    window.onload = function() {
    	radio_disable();
    }
    
    function radio_disable() {
    	document.getElementById("password").style.display = 'none';
    	document.getElementById("password1").style.display = 'none';
    	document.getElementById("lable").style.display = 'none';
    	document.getElementById("lable1").style.display = 'none';
    	document.getElementById("password").value = "";
    	document.getElementById("password1").value = "";
    }
    function radio_enable() {    	
    	document.getElementById("password").style.display = 'block';
    	document.getElementById("password1").style.display = 'block';
    	document.getElementById("lable").style.display = 'block';
    	document.getElementById("lable1").style.display = 'block';
    }
    
    function checkdata(k)
    {               
    	var p=document.f1.phone_number.value;     
    	var pass=document.f1.password.value;
    	var confirmpass=document.f1.password1.value;
       
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
                 alert("Phone: Alphabet not allowed");
                return false;
            }                    
            
    	 if(pass=="")
         {
              alert("Enter Password");
              return false;
         }
    	 else if(pass!=confirmpass)
         {
         	alert("Password didn't match");
         	return false;
         }
    	 return true;
    }              
    
    </script>
    
</body>
</html>