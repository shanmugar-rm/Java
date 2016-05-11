<%@page  import="java.sql.*" %>
<%@ page import="se.term_project.*" %>
<%@page  import="java.util.*" %>
<%! @SuppressWarnings("unchecked") %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UAlbany CS department site</title>    
    <link href="css/bootstrap.min.css" rel="stylesheet">    
    <link href="css/CustomStyle.css" rel="stylesheet">
    <jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="SideBar.jsp"></jsp:include>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script>
   		$(function() {
     	$( "#datepicker" ).datepicker({  minDate: 0, // your min date
        maxDate: '6d'
        });
   		});
  	</script>
</head>
<body>       
    <div class="container-fluid ">		        
        <div style="margin:10px;">        
            <div class="col-md-1"></div>
            <div class="col-md-6">
		<form action="StaffMainServlet?x=ReserveResourse1" method="post" name="f1" onsubmit="return check_data()">
		<h1><p>${message}</p></h1>
            <div class="panel panel-default">
                  <%ResourseStatus slots = (ResourseStatus) session.getAttribute("slots"); %>
                  <%ArrayList<ResourseStatus> resource_list = (ArrayList<ResourseStatus>) session.getAttribute("resource_list"); %>
                  <%String[] booked_by = (String[]) session.getAttribute("booked_by");%>
                  <div class="panel-heading" style="text-align:center;"><h4>Reserve Resource</h4></div>
                  
                   <div class="panel-body" style="text-align:center" >
     				<p style="color:red">${zc}</p>

            		<div class="form-group" style="text-align:left">
                    <label for="resource_list" style="text-align:left">Select an item:</label>
                	<select name="resource_list" class="form-control" required onChange="display(this.value)" id="drop_down">
                	<%String display;                	
                	if (session.getAttribute("resource_selected")==null) {
                		display = "--Select one--"; 
                	}
                	else {
                		display = (String) session.getAttribute("resource_selected");
                	}%>
                        <option value="<%=display%>"><%=display%></option>
                        <%for(int i=0;i<resource_list.size();i++) { %>                        
                        <option value="<%=resource_list.get(i).getResourse_name()%>"><%=resource_list.get(i).getResourse_name()%></option>                        
                        <%} %>                        
                	</select>
            		</div>
            		<%for(int j=0;j<resource_list.size();j++)  {%>
            		<input type="hidden" id="<%=resource_list.get(j).getResourse_name()%>" value="<%=resource_list.get(j).getresourse_details()%>">
            		<%} %>
            		<div class="form-group" style="text-align:left">
                	<label for="resource details" style="text-align:left">Resource Details:</label><br>
                	<input type="text" class="form-control" id="resource_details" readonly>
                	</div>
            		
            	<div class="form-group" style="text-align:left">
                <label for="date" style="text-align:left">Select Date:</label><br>                
                <input type="Text" id="datepicker" name="datepicker"  placeholder="Select date" style="text-align:center;background: transparent;" name="datepicker" class="searchBar" readonly="readonly" <%if(session.getAttribute("date_chosen")!=null) {%> value=<%=session.getAttribute("date_chosen") %><%} %> >
            	</div>
            	
            <input type="hidden" name="xx" value="" id="xx">
            <button type="submit" class="btn btn-default" onClick="setvalue(0)">Search Availability</button> 
                
                <%if (slots!=null) { %>
                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 1: 08:00 - 09:00</label><br>       
                <%if (slots.getAM_8()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(1)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_8())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[1]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 2: 09:00 - 10:00</label><br>
                                <%if (slots.getAM_9()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(2)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_9())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[2]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 3: 10:00 - 11:00</label><br>
                                <%if (slots.getAM_10()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(3)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_10())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[3]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 4: 11:00 - 12:00</label><br>
                                <%if (slots.getAM_11()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(4)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_11())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[4]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 5: 12:00 - 13:00</label><br>
                                <%if (slots.getAM_12()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(5)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_12())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[5]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 6: 13:00 - 14:00</label><br>
                                <%if (slots.getAM_13()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(6)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_13())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[6]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 7: 14:00 - 15:00</label><br>
                                <%if (slots.getAM_14()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(7)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_14())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[7]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 8: 15:00 - 16:00</label><br>
                                <%if (slots.getAM_15()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(8)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_15())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[8]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 9: 16:00 - 17:00</label><br>
                                <%if (slots.getAM_16()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(9)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_16())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[9]%></p>
                <%} %>                
            	</div>
            	                <div class="form-group" style="text-align:left">
                <label for="Slot1" style="text-align:left">Slot 10: 17:00 - 18:00</label><br>
                                <%if (slots.getAM_17()) {%>
                         <p style="color:green">Available <button type="submit" class="btn btn-default" onClick="setvalue(10)">Book this slot</button></p>
                <%} %>
                <%if (!slots.getAM_17())  {%>
                         <p style="color:red">Not Available - Booked by <%=booked_by[10]%></p>
                <%} %>                
            	</div>
                    <% }%>
                  
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
    
    window.onload = function() {
    	display1();
    }
    
    function display1() {
    	var k = document.getElementById("drop_down").value;    	
    	document.getElementById("resource_details").value = document.getElementById(k).value;
    }
    
    function setvalue(k) {
    	
    	document.getElementById("xx").value = k;
    }
    
    function display(l) {    	
    	var k = document.getElementById(l).value;    	
    	document.getElementById("resource_details").value = k;
    }
    
    function check_data() {
    	
    	if (document.getElementById("xx").value != 0) {
    		var check = confirm("Are you sure that you want to book the slot!!");
        	if (check == true) {
        		return true;
        	}
        	else {
        		return false;
        	}
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