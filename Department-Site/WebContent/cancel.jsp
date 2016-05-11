<%@page  import="java.sql.*" %>
<%@ page import="se.term_project.Login_sys" %>
<%@ page import="se.term_project.ResourseHold" %>
<%@page  import="java.util.*" %>
<%! @SuppressWarnings("unchecked") %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UAlbany CS department</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/CustomStyle.css" rel="stylesheet">
    <jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="SideBar.jsp"></jsp:include>
</head>
<body>    
    <%ArrayList<ResourseHold> resource_list = (ArrayList<ResourseHold>) session.getAttribute("booked_slots"); %>
    <div class="container-fluid ">		           
        <div style="margin:10px;">        
            <div class="col-md-1"></div>
            <div class="col-md-6">
		<form action="StaffMainServlet?x=CancelResourseDelete" method="post" name="f1" onSubmit="return check_data()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <div class="panel-heading" style="text-align:center;"><h4>Reserved Resource by you</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>
            	
            	<table class="table table-striped">
                                  <tr>
                                      
                                      <td>Resource Name</td>
                                      <Td>Date Booked</td>
                                      <Td>Slot Booked</td>
                                      <Td>Cancel Reservation</td>
                                  </tr>
                                  <%
                                       for(int i = 0;i<resource_list.size();i++)
                                       {
                                  %>
                                  <tr>                       
                                  <%String display = null;
                                  if(resource_list.get(i).getReserverSlot() == 1) display = "08:00 - 09:00";
                                  if(resource_list.get(i).getReserverSlot() == 2) display = "09:00 - 10:00";                                  
                                  if(resource_list.get(i).getReserverSlot() == 3) display = "10:00 - 11:00";
                                  if(resource_list.get(i).getReserverSlot() == 4) display = "11:00 - 12:00";
                                  if(resource_list.get(i).getReserverSlot() == 5) display = "12:00 - 13:00";
                                  if(resource_list.get(i).getReserverSlot() == 6) display = "13:00 - 14:00";
                                  if(resource_list.get(i).getReserverSlot() == 7) display = "14:00 - 15:00";
                                  if(resource_list.get(i).getReserverSlot() == 8) display = "15:00 - 16:00";
                                  if(resource_list.get(i).getReserverSlot() == 9) display = "16:00 - 17:00";
                                  if(resource_list.get(i).getReserverSlot() == 10) display = "17:00 - 18:00";
                                  %>               
                                      <td><%=resource_list.get(i).getResourse_name()%></td>
                                      <td><%=resource_list.get(i).getReserve_date()%></td>
                                      <td><%=display%></td>
                                      <td><button type="button" class="btn btn-primary" onClick="setvalue(<%=resource_list.get(i).getUnique_id()%>,<%=resource_list.get(i).getReserverSlot() %> ,<%=resource_list.get(i).getResourse_name() %> ,<%=resource_list.get(i).getReserve_date() %>)"><a href='/SE_Term_Project/StaffMainServlet?x=CancelResourseDelete&unique_id=<%=resource_list.get(i).getUnique_id()%>&slot_id=<%=resource_list.get(i).getReserverSlot()%>&resource_selected2=<%=resource_list.get(i).getResourse_name()%>&date_chosen_new1=<%=resource_list.get(i).getReserve_date()%>' style="color:white">Delete</a></button></td>
                                  </tr>
                                  <%
                                       }
                                           %>
                              </table>                                 
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
    function setvalue(a,b,c,d) {
    		document.getElementById("unique_id").value = a;
    		document.getElementById("slot_id").value = b;
    		document.getElementById("resource_selected2").value = c;
    		document.getElementById("date_chosen_new1").value = d;
    }
    
    function check_data() {
    	
    	var check = confirm("Are you sure that you want to delete the slot!!");
    	if (check == true) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	
        if(document.f1.resource_list.value =="None") {
       	 alert("Select Login type");
       	 return false;
        }
        return true;
    }
    </script>
    
    </body>
    </html>