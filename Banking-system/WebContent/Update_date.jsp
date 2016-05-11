<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.bank.src.system_date" %>
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
                                                     <span style="float:right;">
                 <strong >Welcome Admin</strong> &nbsp;
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
           <li class="active"><a href="#">Update System Date</a></li>
          </ul>
        </div>
            <div class="col-md-1"></div>
            <div class="col-md-4">
              <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Update System Date</h4></div>
                   <div class="panel-body" style="text-align:center;" >
                <% system_date bank_date = (system_date)request.getAttribute("db_date");%>
				<form method = "post" action="UpdateDateServlet"> 
				
				<input type = "hidden" name = "select_update" value="update_date">

                
                <div class="form-group" style="text-align:left">
                <label for="bank_date" style="text-align:left">System Date:</label>
                <input type="text" class="form-control" name="database_date" value ="<%=bank_date.getSystem_date() %>">
            	</div>
            	
            	<button type="submit" class="btn btn-default" >Update date</button>
                <br>${display_msg}
            
                   </form> </div>
                </div>
            </div>
            <div class="col-md-4"></div>
</div>
    </div>      
</body>
</html>