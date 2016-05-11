<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="se.bank.src.Transaction"%>
<%@ page import="se.bank.src.Notification"%>
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
	<%! @SuppressWarnings("unchecked") %>
	<div class="container-fluid ">
		<div class="row">
			<!-- header area start -->
			<div class="headerStyle">
				<span>
					<h1>
						<img src="Images/globe.png" style="height: 60px; width: 60px;">
						World Wide Bank
					</h1>
				</span> <span style="float: right;"> <strong>Welcome <%=session.getAttribute("user_name").toString() %></strong>
					&nbsp; <a href="Login.jsp"><span
						class="label label-primary btn-lg">Log out</span></a>
				</span>

			</div>

			<!-- header area end -->

			<!-- menubar area start -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- navbar for mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>

					<!-- navbar for desktop display -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
			<!-- menubar area end -->
		</div>

		<div class="row" style="margin: 10px;">
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

			<% ArrayList<Transaction> trans = (ArrayList<Transaction>)request.getAttribute("transactions"); %>
			<% ArrayList<Notification> notifi = (ArrayList<Notification>)request.getAttribute("notifications"); %>



			<textarea rows="2" cols="40" name="email" id="noScrollbar"
				style="display: none;">
				<% for (int i = 0; i<trans.size();i++) { %>
                        <%String trans_type = trans.get(i).getTransaction_type(); %>
                        <%String acc_display = new String(); %>
                       
                           <%=trans.get(i).getTransaction_date()%>
                            <%=trans.get(i).getTransactionId()%>
                            <%=trans.get(i).getDescription()%>
                            <%=trans.get(i).getTransaction_type()%>
                            <%=trans.get(i).getAmount()%>
                            <%=trans.get(i).getBalance()%>
                       
                        <%} %>
			</textarea>

			
			<div class="col-md-8">
				<div class="panel-group" style="text-align:center;">
					<div class="panel panel-primary">
						<script type="text/javascript">
					function change() {
						document.getElementById("what_too").value = "update_notifi"; 
					}
					</script>
						<div class="panel-heading"><strong>
								Auto Payment Suggestion(s)</strong></div><div class="panel-body">
						<% //ArrayList<Transaction> trans = (ArrayList<Transaction>)request.getAttribute("transactions"); %>
						<% //ArrayList<Notification> notifi = (ArrayList<Notification>)request.getAttribute("notifications"); %>
						<%for (int j = 0;j<notifi.size();j++) { %>
						<%=notifi.get(j).getContent()%>
						<%String check_auto = notifi.get(j).getAuto(); %>
						<%String display_auto = null; %>
						<%String display_button = null; %>
						<% if (check_auto.equals("Yes")) { display_auto = "No"; display_button = "Stop Auto Payment";} %>
						<%if (check_auto.equals("No")) { display_auto = "Yes"; display_button = "Start Auto Payment";} %>
						
						<form class="form" role="form" style="text-align: center"
							action="Autopayservlet" method="post">
							<div class="form-group">
							<input type="hidden" name="what_too" value="update_notifi"
								id="what_too"> <input type="hidden" id="unique_id"
								name="unique_id" value=<%=notifi.get(j).getUniqueID()%>> </div>
								<div class="form-group">
							<input type="hidden" name="account_number"
								value="<%=notifi.get(j).getAccountNumber()%>"> <input
								type="hidden" name="auto" value="<%=display_auto%>"> <input
								type="submit" class="btn btn-primary"
								value="<%=display_button%>" onclick="change()"></div>
						</form>
						<%} %></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
