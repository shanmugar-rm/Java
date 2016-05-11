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
      <div class="headerStyle">
          <span>
          <h1><img src="Images/UAlbany.png" style="height:60px;width:60px;">
				 UAlbany CS department site - Registration</h1>
            </span>    
      </div>
			</div>        
        <div class="row" style="margin:10px;">
            <div class="col-md-4"></div>
            <div class="col-md-4">
		<form action="RegistrationServlet" method="post" name="f2" onSubmit="return checkdata()">
		<h1><p style="color:red">${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Registration</h4></div>                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    
                    <div class="form-group" style="text-align:left">
                    <label for="First Name" style="text-align:left">First Name:</label>
                	<input type="text" class="form-control" name="First_name" maxlength="30" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Last Name" style="text-align:left">Last Name:</label>
                	<input type="text" class="form-control" name="Last_name" maxlength="30" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Email ID" style="text-align:left">Email ID:</label>
                	<input type="text" class="form-control" name="Email_id" maxlength="40" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Advisor Name" style="text-align:left">Advisor Name:</label>
                	<input type="text" class="form-control" name="Advisor_name" maxlength="30" required>
            		</div>
            		
            		<div class="form-group" style="text-align:left">
                    <label for="Program Taken" style="text-align:left">Program Taken:</label>
                	<select name="Program_taken" class="form-control" onChange="display_year()" required>
                        <option value="None">--Select one--</option>
                        <option value="BS">Bachelors's</option>
                        <option value="MS">Master's</option>
                        <option value="PhD">PhD</option>
                        <option value="Faculty">Faculty</option>
                        <option value="Staff">Staff</option>
                	</select>
            		</div>
            
            <div class="form-group" style="text-align:left">
                <label for="user_name" id="year_joined_label" style="text-align:left">Year Joined Program(Fall 2*** or Spring 2***):</label>
                <input type="text" class="form-control" name="year_joined" id="year_joined" maxlength="20">
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="user_name" id="major_label" style="text-align:left">Major:</label>
                <input type="text" class="form-control" name="major" id="major" maxlength="20">
            </div>                       
              
        	<div class="form-group" style="text-align:left">
                <label for="user_name" style="text-align:left">NET ID:</label>
                <input type="text" class="form-control" name="user_name" maxlength="15" required>
            </div>
        
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Password:</label>
                <input type="password" class="form-control" name="password" maxlength="15" required>
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="confirm pwd" style="text-align:left">Confirm Password:</label>
                <input type="password" class="form-control" name="passwordconfirm" maxlength="15" required>
            </div>
                
            <p style="color:red">All Fields are mandatory</p>
            <button type="submit" class="btn btn-default" >Register</button> 
                  
                  
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
    function checkdata()
    {                     	
         if(document.f2.password.value!=document.f2.passwordconfirm.value)
    	 {
    	 	alert("Password did not match");
    	 	return false;
    	 }                  
         return true;
    }        
    
    function display_year () {
    	if(document.f2.Program_taken.value=="BS" | document.f2.Program_taken.value=="MS" | document.f2.Program_taken.value=="PhD") {
       	 	document.getElementById("year_joined").style.display = 'block';
       	 	document.getElementById("year_joined_label").style.display = 'block';
       	 	document.getElementById("major").style.display = 'block';
    	 	document.getElementById("major_label").style.display = 'block';
        }
    	if(document.f2.Program_taken.value=="None" | document.f2.Program_taken.value=="Faculty" | document.f2.Program_taken.value=="Staff") {
       	 	document.getElementById("year_joined").style.display = 'none';
       	 	document.getElementById("year_joined_label").style.display = 'none';
       	 document.getElementById("major").style.display = 'none';
 	 	document.getElementById("major_label").style.display = 'none';
        }    	
    }
    
    window.onload = function() {
    	display_year();
    }
    
    </script>
    
</body>
</html>