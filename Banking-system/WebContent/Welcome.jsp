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
					<li><a href="Login.jsp">Logout</a></li>
				  </ul>				 
				</div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
			<!-- menubar area end -->
			</div>
        
        <div class="row" style="margin:10px;">
            <div class="col-md-4"></div>
            <div class="col-md-4">
            <h1>WELCOME</h1>
            <a href="fundtransferoptions.jsp?x=<%=rs.getInt(8) %>">Fund Transfer</a>
</div>
</div>
</div>
</body>
</html>