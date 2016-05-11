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
       <%
      String recipientid=request.getParameter("x");
             
       ResultSet rss=obj.selectdata("select *from  recepient where recepient_id='"+ recipientid +"'");
       rss.next();    
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
           <li class="active"><a href="EditUserDetails?x=<%=session.getAttribute("acct_number").toString()%>">Edit User Details</a></li>
           <li class="active"><a href="Contact.jsp?x=<%=session.getAttribute("acct_number").toString()%>">Contact Us</a></li>
          </ul>
        </div>
            <div class="col-md-1"></div>
            <div class="col-md-4">
		<form action="fundtransfer" method="post" name="f1" onsubmit="return checkdata()">
		 <input type="hidden" name="id" value='<%= request.getParameter("x") %>'>
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Transfer Funds</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
                    <div class="form-group" style="text-align:left">
                    <label for="name" style="text-align:left">Name:</label>
                <input type="text" class="form-control" name="username" value='<%= rss.getString(3) %>'>
            </div>
        
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Account number:</label>
                <input type="text" class="form-control" name="accno" maxlength="9" value='<%= rss.getInt(2) %>'>
            </div>
            <%
            ResultSet rs1=obj.selectdata("select * from transaction where account_number='"+ session.getAttribute("acct_number").toString() +"'" + "and transaction_id = (select max(transaction_id) from transaction where account_number ='"+ session.getAttribute("acct_number").toString() +"')");
            rs1.next();
            %>
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Amount:</label>
                <input type="text" class="form-control" name="amount">Your Balance:<%=rs1.getFloat(8) %>
            </div>
            
            <div class="form-group" style="text-align:left">
                <label for="pwd" style="text-align:left">Description:</label>
                <input type="text" class="form-control" name="desc">
            </div>
            <textarea rows="2" cols="40" name="email" id="noScrollbar"
			style="display: none;"><%=session.getAttribute("email").toString()%></textarea>
                
            <button type="submit" class="btn btn-default" >Transfer</button>
           
            
                  
                  
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
    var name=document.f1.username.value;
    var accno=document.f1.accno.value;
    var amount=document.f1.amount.value;
    
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
    if(amount=="")
	  {
	  alert("Enter amount");
	  return false;
	  }
    } 
    
    </script>
</body>
</html>
