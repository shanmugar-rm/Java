<%@page import="se.bank.src.dbconnection"%>
<%@page  import="java.sql.*" %>
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
        
        <div class="row" style="margin:10px;text-align:center">
            <div class="col-md-4"></div>
            <div class="col-md-4">
		<form action="checklogin" method="post" name="f1" onsubmit="return checkdata()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Login</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    <div class="form-group" style="text-align:left">
                    <label for="name" style="text-align:left">LoginID:</label>
                <input type="text" class="form-control" name="username">
            </div>
        
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Password:</label>
                <input type="password" class="form-control" name="password">
            </div>
                
            <button type="submit" class="btn btn-default" >login</button>
                  
                  
              </div>
            </div>
            </form>
            <a href="registration.jsp" id="forgotpassword">Register</a><br>
            <a href="checklogin?action=forgotpassword" id="forgotpassword" align="centre">Forgot your password?</a> 
            </div>    
            <div class="col-md-4"></div>
            
            
    
</div>
   
    <!-- jQuery -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <script>
    function checkdata()
    {
    	 var login=document.f1.username.value;
         var pass=document.f1.password.value;
         
         if(login=="")
         {
              alert("Enter Loginid");
              return false;
         }
         if(pass=="")
         {
              alert("Enter Password");
              return false;
         }
         	
    }
    
    </script>
</body>
</html>
