package se.term_project;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentMainServlet
 */
public class StudentMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		Login_sys user_detail = (Login_sys) session.getAttribute("user_details_db");	
		String x = request.getParameter("x");
		if (x.equals("Home")) {
			request.getRequestDispatcher("Student_homepage.jsp").forward(request, response);
		}
		if (x.equals("ViewOfficeHours")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			ArrayList<Course_information> all_course = new ArrayList<Course_information>();			
			all_course = DAO.All_course();			
			session.setAttribute("all_course", all_course);
			DAO.close();
			request.getRequestDispatcher("ViewOfficeHours.jsp").forward(request, response);
		}
		if (x.equals("SendEmail")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			ArrayList<Course_information> all_course = new ArrayList<Course_information>();			
			all_course = DAO.All_course();			
			session.setAttribute("all_course", all_course);
			DAO.close();
			request.getRequestDispatcher("SendEmail.jsp").forward(request, response);
		}
		if (x.equals("ApplyJob")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<Announcement> announcements = dao.GetAnnouncements();
			session.setAttribute("announcements", announcements);
			dao.close();
			request.getRequestDispatcher("ApplyJob.jsp").forward(request, response);
		}
		if (x.equals("SuggestedCourses")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<Suggestion> suggestions = dao.GetSuggestions(user_detail.getmajor());			
			session.setAttribute("suggestion", suggestions);
			dao.close();
			request.getRequestDispatcher("suggestion.jsp").forward(request, response);
		}
		if (x.equals("ProgramReq")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String prog_req = dao.GetRequirements(user_detail.getmajor(), user_detail.getProgram_taken());
			Student_status student_status = dao.getStudentStatus(user_detail.getUser_name());
			session.setAttribute("prog_req", prog_req);
			session.setAttribute("student_status", student_status);
			dao.close();
			request.getRequestDispatcher("programreq.jsp").forward(request, response);
		}
	}

}
