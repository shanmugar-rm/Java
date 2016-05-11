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
<style type="text/css">
#noScrollbar {
	overflow: auto;
}

#noScrollbar1 {
	overflow: auto;
	display: none;
}


</style>
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
         <div class="col-md-2"></div>   
            
		
		<div class="col-md-3">
                    <div class="panel panel-primary" style="text-align:center;" id="div2">
            
            <div class="panel-heading"><strong>Enter OTP</strong></div>
			<div class="panel-body">
		
		<form role="form" action="checkotp" method="post" id="form2">
 			 <div class="form-group">
 			 	<label for="email">OTP:</label>
		     	<input type="text" name="otp" class="form-control">
		     </div>
		     
		     <input type="hidden" name="action" value="otpsubmit" />
		     <textarea rows="2" cols="40" name="username" id="noScrollbar"
			style="display: none;"><%=(String) request.getAttribute("username")%></textarea>
			<textarea rows="2" cols="40" name="email" id="noScrollbar"
			style="display: none;"><%=(String) request.getAttribute("email")%></textarea>
        <button type="submit" class="btn btn-default">Verify</button>
		</form></div>
		<h4><%= request.getAttribute("Message") %></h4>    
</div>

		</div>
      <div class="col-md-2"></div>
    </div>
</div>
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
    <!-- jQuery -->
    
    <!-- Bootstrap Core JavaScript -->
    
    
   
</body>
</html>




