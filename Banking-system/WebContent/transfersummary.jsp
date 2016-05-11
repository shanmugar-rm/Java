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
        //String transferid=request.getParameter("x");
        //ResultSet rs=obj.selectdata("select * from registration where accountnumber='"+ session.getAttribute("acct_number").toString() +"'");
        //ResultSet rs1=obj.selectdata("select * from fundtransfer where transfer_id='"+ transferid +"'");
        ResultSet rs2=obj.selectdata("select * from transaction where account_number='"+ session.getAttribute("acct_number").toString() +"'" + "and transaction_id = (select max(transaction_id) from transaction where account_number ='"+ session.getAttribute("acct_number").toString() +"')");
        //rs.next();
        //rs1.next();
        rs2.next();
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
            <div class="col-md-4">
              <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Transfer Complete</h4></div>
                   <div class="panel-body" style="text-align:center;" >
                <form role="form" action="Welcome.jsp" method="POST" name="f1">
                <input type="hidden" name="id" value='<%= rs2.getString(1)%>'>
                
                 <div class="form-group" style="text-align:left">
                    <label for="amount">TransactionID:</label><%= rs2.getString(2) %>
                
            </div>
                    <div class="form-group" style="text-align:left">
                    
                    <label for="name">Name:</label><%= session.getAttribute("name_user").toString() %>
            </div>
            
            <div class="form-group" style="text-align:left">
                    <label for="type">Account number:</label><%= rs2.getString(5) %>
                
                
            </div>      
            <div class="form-group" style="text-align:left">
                    <label for="amount">Amount:</label><%= rs2.getString(6) %>
                
            </div>
            
             <div class="form-group" style="text-align:left">
                    <label for="amount">Balance:</label><%= rs2.getString(8) %>
                
            </div>
            
            <div class="form-group" style="text-align:left">
                    <label for="desc">Description:</label><%= session.getAttribute("desc_transfer") %>
                    
            </div>
            
                   </form> </div>
                </div>
            </div>
            <div class="col-md-4"></div>
</div>
    </div>      
</body>
</html>