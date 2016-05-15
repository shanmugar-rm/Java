package se.src.pkg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchHashtagServlet
 */
public class SearchHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String display_msg;
    public SearchHashtagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// getting the input from jsp
		String input = request.getParameter("inputhashtag");
		//validating the entered hashtag
		boolean validate_input = inputValidate(input);
		if (!validate_input) 
		{
			//throwing error and staying in the same screen
			display_msg = "You must enter only alpha numeric search hash tags";
			request.setAttribute("display_msg", display_msg);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}
		else {
			//setting up the model class , checking if the element is present if so then collecting post contents and sending it to view hashtag jsp.
			HashtagFeed htr1 = new HashtagFeed();
			Hashtag ht = new Hashtag();
			htr1.setHashtag(input);
			ht.setHashtag(input);
			request.setAttribute("hashtag", ht);
			SearchHashtagDAO insert_dao = new SearchHashtagDAO();
			insert_dao.DBConnect();
			int count_tag = insert_dao.Count_tag(input);
			if (count_tag > 0) {
				ArrayList<HashtagFeed> htfl = insert_dao.GetParentpost(htr1);
				ArrayList<HashtagFeed> temp = insert_dao.GetChildpost(htr1);
				for (int a = 0; a<temp.size();a++) {
					for (int b = 0; b<htfl.size();b++) {
						if (temp.get(a).getParentfeedid() == htfl.get(b).getId()) {
							String child_content = temp.get(a).getContent();
							htfl.get(b).setChildcontent(child_content);
						}
					}
				}					
				request.setAttribute("hashtagfeed", htfl);
				request.getRequestDispatcher("viewhashtag.jsp").forward(request, response);
			}
			else {
				//if hashtag is not present then setting up the error message
				display_msg = "The tag you are searching is not present, please create a hashtag or search for another";
				request.setAttribute("display_msg", display_msg);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		}				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// have done all in the get method
		doGet(request, response);
	}
	
	//to validate if the entered hashtag is only alpha-numeric content.
	public boolean inputValidate (String input) {
		boolean result = false;
		result = input.matches("[a-zA-Z0-9]+");
		return result;
	}
}
