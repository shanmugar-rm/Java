package se.bank.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registration
 */
@SuppressWarnings("unused")
//@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//PrintWriter out=response.getWriter();
		String Firstname=request.getParameter("firstname");
		String Lastname=request.getParameter("lastname");
		String acctype=request.getParameter("type");
		String email=request.getParameter("email");
		String username=request.getParameter("loginid");
		String password=request.getParameter("password");
		//String cpassword=request.getParameter("password2");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		Random rand = new Random();

		int  n = rand.nextInt(999999999) + 1;
		 try
         {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
             
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank","root","root");
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery("select * from registration where loginid='"+username+"'");
         boolean status;
         status=rs.next();
         if(!status)
         {
        	 request.setAttribute("message", "Registration Successful");
        	st.execute("insert into registration(name,type,emailid,loginid,password,phone,address,accountnumber,lastname) values ('"+Firstname+"','"+acctype+"','"+email+"','"+username+"','"+password+"','"+phone+"','"+address+"','"+n+"','"+Lastname+"')");
        	//st.execute("insert into account(Accountnumber,Amount) values ('"+n+"','500')");
        	int trans_id1 = 10000;
 	       DAO db_object = new DAO();
 			db_object.DBConnect();
 	        String date=db_object.GetSystem_date().getSystem_date();
 	        String from_accno = "new acct";
 	        int famount = 500;
 	        String cr = "cr";
        	st.execute("insert into transaction values('"+ n +"','"+ trans_id1 +"','"+ date +"','" + from_accno +"','"  + n   +"','"+ famount +"','"+ cr +"','"+ famount +"','"+ from_accno +"')");
        	st.execute("insert into act_desc values('"+ n +"','"+ acctype +"','"+ Firstname +"','" + username +"','"  + password  +"')");
        	//st.execute("insert into reward_points values('"+ n +"','"+ acctype +"','"+ Firstname +"','" + username +"','"  + password  +"')");
        	 RequestDispatcher dispatch=request.getRequestDispatcher("/Login.jsp");
        	dispatch.include(request,response);
         }
         else{
        	 request.setAttribute("zc", "*Loginid already exist");
        	 RequestDispatcher dispatch=request.getRequestDispatcher("/registration.jsp");
         	dispatch.forward(request,response);
         }
    }
         catch(Exception ex)
         {
        	 System.out.println(ex.toString());          
         }
	}
}


