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
 <% 
        
        dbconnection obj=new dbconnection();
        
        ResultSet rs=obj.selectdata("select *from registration where accountnumber='"+ session.getAttribute("id").toString() +"'");
        rs.next();
       %>  
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
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About Us</a></li>
					<li class="dropdown">
					  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Categories<span class="caret"></span></a>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#">sample 1</a></li>
						<li><a href="#">sample 2</a></li>
						<li><a href="#">sample 3</a></li>
						<li class="divider"></li>
						<li><a href="#">sample 4</a></li>
						<li><a href="#">sample 5</a></li>
						<li><a href="#">sample 6</a></li>
						
					  </ul>
					</li>
					<li><a href="#">Contact Us</a></li>
				  </ul>				 
				</div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
			<!-- menubar area end -->
			</div>
        
        <div class="row" style="margin:10px;">
            <div class="col-md-4"></div>
            <div class="col-md-4">
		<form action="recepientadd" method="post" name="f1" onsubmit="return checkdata()">
		 <input type="hidden" name="id" value='<%= rs.getInt(8) %>'>
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Add Recepient</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    <div class="form-group" style="text-align:left">
                    <label for="name" style="text-align:left">Account Number:</label>
                <input type="text" class="form-control" name="accno">
            </div>
        
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Firstname:</label>
                <input type="text" class="form-control" name="firstname">
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Lastname:</label>
                <input type="text" class="form-control" name="lastname">
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Nickname:</label>
                <input type="text" class="form-control" name="nickname">
            </div>
                
            <button type="submit" class="btn btn-default" >Add</button>
                  
                  
              </div>
            </div>
            </form>     
            <div class="col-md-4"></div>
    
</div>
</div>
   
    <!-- jQuery -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <script>
    function checkdata()
    {
    	 var name=document.f1.firstname.value;
         var lname=document.f1.lastname.value;
         var nick=document.f1.nickname.value;
         var accno=document.f1.accno.value;
         
         if(name=="" || name.length<3)
   	  {
   	  		alert("Name must be minimum 3 characters long");
   	  		return false;
   	  }
     if(accno=="" || accno.length<9)
   	  {
   	  alert("Account number must be 9 digit long");
   	  return false;
   	  }
         
    }
    
    
    </script>
</body>
</html>
