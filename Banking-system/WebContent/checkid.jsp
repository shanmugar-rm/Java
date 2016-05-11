<%@page import="se.bank.src.dbconnection"%>
<%@ page import="java.io.*,java.sql.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
 
<%
 
            String sn=request.getParameter("ver");
 					dbconnection st=new dbconnection();
                    //ResultSet rs = st.executeQuery("select * from emp where empno="+sn);
                    while(sn.length()<6)
                    {
                    	out.println("Loginid should be greater than 6  characters");
                    }
                    
                    ResultSet rs = st.selectdata("select * from registration where loginid='"+sn+"'");  // this is for name
                    if(rs.next())
                    {    
                        out.println("<font color=red>");
                        out.println("Name already taken");
                        out.println("</font>");
 
                    }else {
 
                        out.println("<font color=green>");
                        out.println("Available");
                        out.println("</font>");
 
                    }
                                        	
                    
  
%>