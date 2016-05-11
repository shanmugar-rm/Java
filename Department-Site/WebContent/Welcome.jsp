<%@page  import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UAlbany CS department site</title>    
    <link href="css/bootstrap.min.css" rel="stylesheet">    
    <link href="css/CustomStyle.css" rel="stylesheet">
</head>
 
<body>
    <div class="container-fluid ">
		<div class="row">		
			<!-- header area start -->
      <div class="headerStyle">
          <span>
          <h1><img src="Images/UAlbany.png" style="height:60px;width:60px;">
				 UAlbany CS department site - Login</h1>
            </span>                
      </div>            
	  </div>
	  </div>        
        <div class="row" style="margin:10px;">
            <div class="col-md-4"></div>
            <div class="col-md-4">
		<form action="LoginServlet" method="post" name="f1" onSubmit="return checkdata()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Login</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    <div class="form-group" style="text-align:left">
                    <label for="name" style="text-align:left">LoginID:</label>
                <input type="text" class="form-control" name="user_name" id="username" required>
            </div>
        
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Password:</label>
                <input type="password" class="form-control" name="password" id="password" required>
            </div>
            
            <div class="form-group" style="text-align:left">
                    <label for="type">Login type:</label>
                <select name="type" class="form-control" id="type" required>
                        <option value="None">--Select one--</option>
                        <option value="Student">Student</option>
                        <option value="Faculty">Faculty</option>
                        <option value="Staff">Staff</option>
                </select>
            </div>      
                
            <button type="submit" class="btn btn-default" >Login</button> <a href="Registration.jsp"> Sign Up ?</a>                                   
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
         if(document.f1.username.value =="")
         {
              alert("Enter Loginid");
              return false;
         }
         if(document.f1.password.value =="")
         {
              alert("Enter Password");
              return false;
         }
         if(document.f1.type.value =="None") {
        	 alert("Select Login type");
        	 return false;
         }
         return true;
    }        
    </script>
    
</body>
</html>