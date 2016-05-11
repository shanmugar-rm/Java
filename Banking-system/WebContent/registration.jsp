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
    <!-- <div class="container-fluid ">
		<div class="row">		
			header area start
      <div class="headerStyle">
          <span>
          <h1><img src="Images/globe.png" style="height:60px;width:60px;">
				 World Wide Bank</h1>
            </span>    
              
                
            
      </div> -->
            
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
              <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Sign Up</h4></div>
                   <div class="panel-body" style="text-align:center;" >
                <form role="form" action="registration" method="POST" name="f1" onsubmit="return checkdata()">
                <p style="color:red">${zc}</p>
                    <div class="form-group" style="text-align:left">
                    
                    <label for="name">Firstname:</label>
                <input type="text" class="form-control" name="firstname">
            </div>
            <div class="form-group" style="text-align:left">
                    
                    <label for="name">Lastname:</label>
                <input type="text" class="form-control" name="lastname" >
            </div>
            
            <div class="form-group" style="text-align:left">
                    <label for="type">Account type:</label>
                <select name="type" class="form-control">
                <option value="">Select Type</option>
                        <option value="I">Individual</option>
                        <option value="B">Business</option>
                </select>
                
            </div>      
            <div class="form-group" style="text-align:left">
                    <label for="email">Email address:</label>
                <input type="email" class="form-control" name="email">
            </div>
            <div class="form-group" style="text-align:left">
                    <label for="address">Address:</label>
                <input type="text" class="form-control" name="address">
            </div>     
            <div class="form-group" style="text-align:left">
                    <label for="phone">Phone:</label>
                <input type="text" class="form-control" name="phone" maxlength="10">
            </div>         
              
               <div class="form-group" style="text-align:left">
                <label for="loginid">LoginID:</label>
                <input type="text" class="form-control" onkeyup="checkloginid(this.value)" name="loginid" id="loginid">
                <div id="l" ></div> 
            </div>      
                   
            <div class="form-group" style="text-align:left">
                <label for="password">Password:</label>
                <input type="password" class="form-control" name="password">
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="password">Confirm Password:</label>
                <input type="password" class="form-control" name="password2">
            </div>
                
            <button type="submit" class="btn btn-default" >Register</button>
                   </form> </div>
                </div>
            </div>
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
         var p=document.f1.phone.value;
         var f=document.f1.firstname.value;
         var l=document.f1.lastname.value;
         var type=document.f1.type.value;
         var login=document.f1.loginid.value;
         var pass=document.f1.password.value;
         var add=document.f1.address.value;
         //var p=document.f1.password.value;
         var confirmpass=document.f1.password2.value;
  
         if(f=="")
         {
              alert("Enter Firstname");
              return false;
         }
         if(l=="")
         {
              alert("Enter lastname");
              return false;
         }
         if(type=="")
         {
              alert("Select Type");
              return false;
         }
         if(add=="")
         {
              alert("Enter Address");
              return false;
         }
        
         var i;
         if(p.length==10)
         {
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
             
         }
         else
         {
             alert("Invalid Phone Number");
             return false;
         }
         if(login=="")
         {
              alert("Enter Loginid");
              return false;
         }
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
     var xmlHttp  
     var xmlHttp
    
   function checkloginid(l)
   {
 
     if (typeof XMLHttpRequest != "undefined"){
     xmlHttp= new XMLHttpRequest();
     }
     else if (window.ActiveXObject){
     xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
     }
     if (xmlHttp==null){
     alert("Browser does not support XMLHTTP Request")
     return;
     } 
     var url="checklogin.jsp";
     url +="?l=" +l;
     xmlHttp.onreadystatechange = aftercheck;
     xmlHttp.open("GET", url, true);
     xmlHttp.send(null);


       
   }
   
   

     function aftercheck(){   
     if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){   
     document.getElementById("l").innerHTML=xmlHttp.responseText   
     }   
     }
    </script>
</body>
</html>
