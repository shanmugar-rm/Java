<%@page import="java.sql.ResultSet"%>
<%@page import="se.bank.src.dbconnection"%>
<%@page import="java.io.PrintWriter" %>
<%
	
      String l=request.getParameter("l");
	System.out.println("Loginid:"+l);
      dbconnection obj=new dbconnection();
      ResultSet rs=obj.selectdata("select * from registration where loginid='"+ l +"'");
      System.out.println("RS:"+rs);
      if(rs.next())
      {%>
         <p style="color:red;">
         <%out.println("Loginid already Exists");%>
         </p>
     <% }else{
    	  if(l=="")
    	  {
    		  
    	  }else
    	  {%>
    		  <p style="color:green;">
         <%out.println("Available");%>
         </p>  
    	<%   }
    	  
      }
     
%>

