<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>	
<%@ page import="se.bank.src.Transaction" %>
<%@ page import="se.bank.src.Notification" %>
<%@ page import="se.bank.src.RegistrationModel" %>	    	    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>World Wide Bank</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/CustomStyle.css" rel="stylesheet">

</head>
<body>

    <div class="container-fluid ">
		<div class="row">		
			<!-- header area start -->
      <div class="headerStyle">
          <span>
          <h1><img src="Images/globe.png" style="height:60px;width:60px;">
				 World Wide Bank</h1>
            </span>    
              
                <span style="float:right;">
                 <strong >Welcome <%=session.getAttribute("user_name").toString() %></strong> &nbsp;
                 <a href="Login.jsp"><span class="label label-primary btn-lg">Log out</span></a>
                </span>
            
      </div>
            
			<!-- header area end -->
			
			<!-- menubar area start -->
			<nav class="navbar navbar-default">
			  <div class="container-fluid">
				<!-- navbar for mobile display -->
				<div class="navbar-header">
				  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				  </button>
				</div>

				<!-- navbar for desktop display -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				  <ul class="nav navbar-nav">
					
				  </ul>				 
				</div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
			<!-- menubar area end -->
			</div>
        
        <div class="row" style="margin:10px;">
		 <div class="col-md-3" style="border:solid 0px">
          <ul class="nav nav-pills nav-stacked" >
           <li class="active"><a href="viewrecipient.jsp?x=<%=session.getAttribute("acct_number").toString()%>">View Beneficiary</a></li>
           <li class="active"><a href="addrecipient.jsp?x=<%=session.getAttribute("acct_number").toString()%>">Add Beneficiary</a></li>
           <li class="active"><a href="tranfertoexisting.jsp?x=<%=session.getAttribute("acct_number").toString()%>">Funds Transfer</a></li>
           <%session.setAttribute("what_to", "get_act_details");%>
           <li class="active"><a href="AccountDetails">Account Details</a></li>
           <%session.setAttribute("what_too", "update_notifii");%>
           <li class="active"><a href="Autopayservlet?x=<%=session.getAttribute("acct_number").toString()%>">Auto Payment</a></li>
           <li class="active"><a href="fundtransfer.jsp?x=<%=session.getAttribute("acct_number").toString()%>">Make Payment</a></li>                
          <li class="active"><a href="RewardsPoint.jsp?x=<%=session.getAttribute("acct_number").toString()%>">Rewards</a></li>
          <li class="active"><a href="echeque.jsp?x=<%=session.getAttribute("acct_number").toString()%>">Echeque</a></li>
           <li class="active"><a href="EditUserDetails?x=<%=session.getAttribute("acct_number").toString()%>">Edit User Details</a></li>
           <li class="active"><a href="Contact.jsp?x=<%=session.getAttribute("acct_number").toString()%>">Contact Us</a></li>
          </ul>
        </div>

<div class="col-md-1"></div>
            <div class="col-md-5">
            <%RegistrationModel user_details = (RegistrationModel) session.getAttribute("user_detail");%>
            
		<form action="EditUserDetails?x=<%=session.getAttribute("acct_number").toString()%>" method="post" name="f1" onsubmit="return checkdata()">
		 <input type="hidden" name="what_to" value="<%=session.getAttribute("acct_number").toString()%>">		 
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Edit Details</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    <div class="form-group" style="text-align:left">
                    <label for="name" style="text-align:left">Account Number:</label>
                <input type="text" class="form-control" name="accno" maxlength="10" value="<%=user_details.getAcct_number()%>" readonly>
            </div>
        
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">First Name:</label>
                <input type="text" class="form-control" name="firstname" value="<%=user_details.getname()%>" required>
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Last name:</label>
                <input type="text" class="form-control" name="lastname" value="<%=user_details.getlast_name()%>" required>
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Email ID:</label>
                <input type="text" class="form-control" name="email_id" value="<%=user_details.getemail_id()%>" required>
            </div>   
            
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Phone Number:</label>
                <input type="text" class="form-control" name="phone_number" maxlength="10" value="<%=user_details.getphone()%>" required>
            </div>            
            
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Address:</label>
                <input type="text" class="form-control" name="address" value="<%=user_details.getaddress()%>" required>
            </div>                     
            
            <div class="form-group" style="text-align:left">
                    <label for="syllabus" style="text-align:left">Change Password ?</label>
               		<input type="radio" name="Same_syllabus" id="Same_syllabus" value="same_syllabus" checked="checked" onClick="radio_disable()">No &nbsp;<input type="radio" name="Same_syllabus" id="New_syllabus" value="new_syllabus" onClick="radio_enable()">Yes
               		<label for="syllabus" style="text-align:left" id="lable1" >Password</label>
               		<input type="password" class="form-control" name="password" id="password" maxlength="45">
               		<label for="syllabus" style="text-align:left" id="lable" >Confirm Password</label>
               		<input type="password" class="form-control" name="password1" id="password1"  maxlength="45">
            </div>
            <button type="submit" class="btn btn-default" >Save</button>                  
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
    function checkdata() {
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
            if (document.getElementById("New_syllabus").checked) {
            	if(pass=="")
                {
                     alert("Enter Password");
                     return false;
                }else if(pass!=confirmpass)
               	 {
               	 	alert("Password didn't match");
               	 	return false;
               	 }
            }            
    }
    </script>
</body>
</html>
