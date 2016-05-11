package se.bank.src;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addrecipient
 */
@SuppressWarnings("unused")
public class addrecipient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addrecipient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		   HttpSession s=request.getSession();
	       String from_accno= request.getParameter("id");
	       int accno=Integer.parseInt(request.getParameter("accno"));
	       String name=request.getParameter("firstname");
	       String email=request.getParameter("email");
	       String lastname=request.getParameter("lastname");
	       String nickname=request.getParameter("nickname");
	       System.out.println("from_accno:"+from_accno);
	       System.out.println("accno:"+accno);
	       System.out.println("name:"+name);
	       System.out.println("lastname:"+lastname);
	       System.out.println("nickname:"+nickname);
	       dbconnection obj=new dbconnection();
	       
	       
	       ResultSet rs3=obj.selectdata("select accountnumber from registration where accountnumber='"+accno+"'");
	       int Aname=0;
	       try {
				
				while(rs3.next())
				{
					Aname=rs3.getInt("accountnumber");
					System.out.println("Hname:"+Aname);
				}
				System.out.println("accno:"+accno);
			if(accno==Aname)
			{
				
				ResultSet rs2=obj.selectdata("select * from registration where accountnumber='"+accno+"'");
				System.out.println("RS2:"+accno);
				while(rs2.next())
				   {
				   String checkfirstname=rs2.getString(1);
				   System.out.println("checkfirstname:"+checkfirstname);
				   String checklastname=rs2.getString(10);
				   System.out.println("checklastname:"+checklastname);
				  
				   if(name.equals(checkfirstname) && lastname.equals(checklastname))
				   {
				
					ResultSet rs=obj.selectdata("select * from recepient where recepient_accno='"+accno+"'");
					if(rs.next())
					   {
						request.setAttribute("zc", "Recipient already added");
						 RequestDispatcher dispatch=request.getRequestDispatcher("addrecipient.jsp?x="+from_accno);
					 	dispatch.forward(request,response);
					   }else{
						   obj.modify("insert into recepient(user_accno,recepient_accno,firstname,lastname,nickname) values('"+ from_accno +"','"+accno +"','"+ name +"','"+ lastname +"','"+ nickname +"')");
						   request.setAttribute("zc", "Recipient added");
						   String subject = "New Beneficiary has been added " + "Account number of the beneficiary: "+ accno + "Name : " + name + " " + lastname + " Sent from Worldwide Bank";
						   SendMail sendemail = new SendMail();
							sendemail.email(subject, email);
						   RequestDispatcher dispatch=request.getRequestDispatcher("addrecipient.jsp?x="+from_accno);
						   dispatch.forward(request,response);
					       //response.sendRedirect("addrecipient.jsp?x="+from_accno);  
					   }
				
				   }else
				   {
					   request.setAttribute("zc", "Accountnumber doen't match with name provided");
				    	 RequestDispatcher dispatch=request.getRequestDispatcher("addrecipient.jsp?x="+from_accno);
				     	dispatch.forward(request,response);
				   }
				   } 
			 
	    	   
	      
			} 
	       else{
	    		   request.setAttribute("zc", "Accountnumber doen't not exist");
			    	 RequestDispatcher dispatch=request.getRequestDispatcher("addrecipient.jsp?x="+from_accno);
			     	dispatch.forward(request,response);
	    	   }
	       }
	       catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
}
	}
}

	       
	


