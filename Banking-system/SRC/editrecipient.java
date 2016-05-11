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
 * Servlet implementation class editrecipient
 */
@SuppressWarnings("unused")
public class editrecipient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editrecipient() {
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
		   //HttpSession s=request.getSession();
	       String recepientid= request.getParameter("id");
	       String accno=request.getParameter("accno");
	       String name=request.getParameter("firstname");
	       String lastname=request.getParameter("lastname");
	       String nickname=request.getParameter("nickname");
	       System.out.println("recepientid:"+recepientid);
	       System.out.println("accno:"+accno);
	       System.out.println("name:"+name);
	       System.out.println("lastname:"+lastname);
	       System.out.println("nickname:"+nickname);
	       dbconnection obj=new dbconnection();
	       obj.modify("update recepient set recepient_accno='"+accno+"',firstname='"+name+"',lastname='"+lastname+"',nickname='"+nickname+"' where recepient_id='"+recepientid+"'");
	       response.sendRedirect("viewrecipient.jsp");  
			 
		}
	       
	}
	


