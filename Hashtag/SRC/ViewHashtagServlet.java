package se.lab1.pkg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewHashtagServlet
 */
public class ViewHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String display_msg;
    public ViewHashtagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//checking is the submit is for create post or reply post
		String post_reply = request.getParameter("post_reply");
		if (post_reply.equals("create_post"))
		{
			HashtagFeed htr1 = new HashtagFeed();
			Hashtag ht = new Hashtag();
			ArrayList<HashtagFeed> temp1 = new ArrayList<HashtagFeed>();
			String post_check = request.getParameter("post");
			htr1.setHashtag(request.getParameter("hashtag"));
			ht.setHashtag(htr1.getHashtag());
			//checking is the post is empty string
			if (post_check.isEmpty()) {
				display_msg = "Please enter non empty content";
				request.setAttribute("display_msg", display_msg);
				request.setAttribute("hashtag", ht);
				SearchHashtagDAO insert_dao = new SearchHashtagDAO();
				insert_dao.DBConnect();
				temp1 = insert_dao.GetParentpost(htr1);
				ArrayList<HashtagFeed> temp = insert_dao.GetChildpost(htr1);
				for (int a = 0; a<temp.size();a++) {
					for (int b = 0; b<temp1.size();b++) {
						if (temp.get(a).getParentfeedid() == temp1.get(b).getId()) {
							String child_content = temp.get(a).getContent();
							temp1.get(b).setChildcontent(child_content);
						}
					}
				}
				request.setAttribute("hashtagfeed", temp1);
				request.getRequestDispatcher("viewhashtag.jsp").forward(request, response);
			}
			else {
				//if non empty post then inserting and displaying the viewhashtag jsp
				htr1.setContent(request.getParameter("post"));
				SearchHashtagDAO insert_dao = new SearchHashtagDAO();
				boolean connection_check = insert_dao.DBConnect();
				if (connection_check) {
					boolean insert_check = insert_dao.HashtagFeedInsert(htr1);
					if (insert_check) {
						request.setAttribute("hashtag", ht);
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
						//if something goes wrong then setting up the error message
						display_msg = "Something went wrong during insert, try again";
						request.setAttribute("display_msg", display_msg);
						request.setAttribute("hashtag", ht);
						ArrayList<HashtagFeed> htfl = new ArrayList<HashtagFeed>();
						htfl = insert_dao.GetParentpost(htr1);
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
				}
				else {
					display_msg = "Something went wrong during DB connection, try again";
					request.setAttribute("display_msg", display_msg);
					request.setAttribute("hashtag", ht);
					ArrayList<HashtagFeed> htfl = new ArrayList<HashtagFeed>();
					htfl = insert_dao.GetParentpost(htr1);
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
			}
		}
		//Symmetric for the create post
		if (post_reply.equals("reply_post"))
		{
			String id_feed = request.getParameter("id_feed");
			HashtagFeed htr1 = new HashtagFeed();
			Hashtag ht = new Hashtag();
			ArrayList<HashtagFeed> temp1 = new ArrayList<HashtagFeed>();
			String post_check = request.getParameter("post1"+id_feed);
			htr1.setHashtag(request.getParameter("hashtag1"+id_feed));
			ht.setHashtag(htr1.getHashtag());
			if (post_check.isEmpty()) {
				display_msg = "Please enter non empty content";
				request.setAttribute("display_msg", display_msg);
				request.setAttribute("hashtag", ht);
				SearchHashtagDAO insert_dao = new SearchHashtagDAO();
				insert_dao.DBConnect();
				temp1 = insert_dao.GetParentpost(htr1);
				ArrayList<HashtagFeed> temp = insert_dao.GetChildpost(htr1);
				for (int a = 0; a<temp.size();a++) {
					for (int b = 0; b<temp1.size();b++) {
						if (temp.get(a).getParentfeedid() == temp1.get(b).getId()) {
							String child_content = temp.get(a).getContent();
							temp1.get(b).setChildcontent(child_content);
						}
					}
				}
				request.setAttribute("hashtagfeed", temp1);
				request.getRequestDispatcher("viewhashtag.jsp").forward(request, response);
			}
			else {
				htr1.setParentfeedid(Integer.parseInt(request.getParameter("parent_id1"+id_feed)));
				htr1.setId(htr1.getParentfeedid());
				ht.setHashtag(htr1.getHashtag());
				htr1.setContent(request.getParameter("post1"+id_feed));
				SearchHashtagDAO insert_dao = new SearchHashtagDAO();
				boolean connection_check = insert_dao.DBConnect();
				if (connection_check) {
					boolean insert_check = insert_dao.HashtagReplyInsert(htr1);
					if (insert_check) {
						request.setAttribute("hashtag", ht);
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
						display_msg = "Something went wrong during insert, try again";
						request.setAttribute("display_msg", display_msg);
						request.setAttribute("hashtag", ht);
						ArrayList<HashtagFeed> htfl = new ArrayList<HashtagFeed>();
						htfl = insert_dao.GetParentpost(htr1);
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
				}
				else {
					display_msg = "Something went wrong during DB connection, try again";
					request.setAttribute("display_msg", display_msg);
					request.setAttribute("hashtag", ht);
					ArrayList<HashtagFeed> htfl = new ArrayList<HashtagFeed>();
					htfl = insert_dao.GetParentpost(htr1);
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
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
