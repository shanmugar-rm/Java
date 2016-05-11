package se.src.pkg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateHashtagServerlet
 */
public class CreateHashtagServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String display_msg;
    public CreateHashtagServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//fetching the value from the screen
		String input = request.getParameter("inputhashtag");
		//setting the hashtag model and passing the parameter to it
		Hashtag ht = new Hashtag();
		ht.setHashtag(input);
		//calling validate method and checking is the hashtag entered is only alpha numeric
		boolean validate_input = inputValidate(input);
		if (!validate_input) 
		{
			//passing the error text to screen and staying in the same page
			display_msg = "You must enter only alpha numeric hash tags";
			request.setAttribute("display_msg", display_msg);
			request.getRequestDispatcher("createhashtag.jsp").forward(request, response);
		}
		else {
			//creating db connect and inserting hashtag to db and forwarding it to viewhashtag page
			SearchHashtagDAO insert_dao = new SearchHashtagDAO();
			boolean connection_check = insert_dao.DBConnect();
			if (connection_check) {
				boolean insert_check = insert_dao.HashtagInsert(ht);
				if (insert_check) {
					request.setAttribute("hashtag", ht);
					//setting up the Arraylist as well as it throw error in jsp page if it is not set.
					ArrayList<HashtagFeed> htfl = new ArrayList<HashtagFeed>();
					request.setAttribute("hashtagfeed", htfl);
					request.getRequestDispatcher("viewhashtag.jsp").forward(request, response);
				}
				else {
					//if error throwing the error message
					display_msg = "Something went wrong during insert, try again";
					request.setAttribute("display_msg", display_msg);
					request.getRequestDispatcher("createhashtag.jsp").forward(request, response);
				}
			}
			else {
				//if error throwing the error message
				display_msg = "Something went wrong during DB connection, try again";
				request.setAttribute("display_msg", display_msg);
				request.getRequestDispatcher("createhashtag.jsp").forward(request, response);
			}
		}				

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// calling the get method and doing all the logic over get method.
		doGet(request, response);
	}
	
	//validate function - checks is the string entered for hash tag has only alpha numeric characters.
	//have kept as boolean function so that it can return true or false
	public boolean inputValidate (String input) {
		boolean result = false;
		result = input.matches("[a-zA-Z0-9]+");
		return result;
	}
}
