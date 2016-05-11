
    
    
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
        
        <div class="row" style="margin:10px;">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                
                
	<form action="resetpassword" method="post" id="form">
        <div class="panel panel-primary">
            <div class="panel-heading" style="text-align:center;"><h4>Reset Password</h4></div>
		     <div class="panel-body" style="text-align:center" >
            <div class="form-group" style="text-align:left">
                <label for="name" style="text-align:left">Enter Password:</label>
                <input type="hidden" name="action" value="resetpassword" />  
                <input type="password" name="password1" class="form-control">
            </div>  
             <div class="form-group" style="text-align:left">
                <label for="name" style="text-align:left">Confirm Password: </label>
                <input type="password" name="password2" class="form-control">
            </div>     
                 
                
		<textarea rows="2" cols="40" name="username" id="noScrollbar"
			style="display: none;"><%=(String) request.getAttribute("username")%></textarea>
            
		<textarea rows="2" cols="40" name="email" id="noScrollbar"
			style="display: none;"><%=(String) request.getAttribute("email")%></textarea>
		<button type="submit" class="btn btn-default" >submit</button>
        <h4><%= request.getAttribute("Message") %></h4>
            </div></div>
	</form>
 
            <div class="col-md-4"></div>
    
</div>
   
    <!-- jQuery -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>


