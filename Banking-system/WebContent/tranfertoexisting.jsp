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
        
        ResultSet rs=obj.selectdata("select *from registration where accountnumber='"+ session.getAttribute("acct_number").toString() +"'");
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
            <h4>YOUR BENEFICIARY</h4>
             <%
              
                ResultSet rs3=obj.selectdata("select * from recepient where user_accno='"+ session.getAttribute("acct_number").toString() +"' ");
                               
                              %>
                              
                              
                            <table align="centre" class="table table-striped">
                                  <tr>
                                      
                                      
                                      <Td align="center">Nickname</td>
                                  </tr>
                                  <%
                                       while(rs3.next())
                                       {
                                  %>
                                  <tr>
                                      
                                      <td align="center"><a href='sendfunds.jsp?x=<%= rs3.getInt(6) %>'><%=rs3.getString(5) %></a></td>
                                     
                                  </tr>
                                  <%
                                       }
                                           %>
                              </table>
         
</div>
</div>
</div>
</body>
</html>