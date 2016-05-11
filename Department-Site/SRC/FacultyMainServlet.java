package se.term_project;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FacultyMain
 */
public class FacultyMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		Login_sys user_detail = (Login_sys) session.getAttribute("user_details_db");
		String x = request.getParameter("x");
		if (x.equals("Home")) {
			request.getRequestDispatcher("Faculty_homepage.jsp").forward(request, response);
		}
		if (x.equals("Post_Information")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			ArrayList<Course_information> faculty_course = new ArrayList<Course_information>();
			ArrayList<Course_information> all_course = new ArrayList<Course_information>();
			faculty_course = DAO.GetFaculty_course(user_detail);
			all_course = DAO.All_course();
			session.setAttribute("faculty_course", faculty_course);
			session.setAttribute("all_course", all_course);
			DAO.close();
			request.getRequestDispatcher("Faculty_postcourseinformation.jsp").forward(request, response);
		}
		if (x.equals("AddCourse")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			String course_id = request.getParameter("course_id");
			String course_title = request.getParameter("course_title");
			String office_hours = request.getParameter("office_hours");
			String office_location = request.getParameter("office_location");
			String email_id = request.getParameter("email_id");
			Login_sys user_details = (Login_sys) session.getAttribute("user_details_db");
			String TA_name = request.getParameter("TA_name");
			String TA_email_id = request.getParameter("TA_email_id");
			String TA_office_location = request.getParameter("TA_office_location");
			String user_id = user_details.getUser_name();
			String user_name = user_details.getFirst_name();
			InputStream inputsyllabus = null;
			Part syllabus = request.getPart("syllabus");
			if (syllabus != null) {
				inputsyllabus = syllabus.getInputStream();
			}		
			//DAO.RunQuery("insert into course_information (course_id, course_title, parent_id, user_id, user_name, office_hours, office_location, email_id, syllabus) values( '"+course_id+"', '"+course_title+"', NULL, '"+user_id+"', '"+user_name+"', '"+office_hours+"' , '"+office_location+"' , '"+email_id+"' , '"+inputsyllabus+"')");
			DAO.Insert_Course(course_id,course_title, office_hours, office_location, email_id,user_id, user_name, inputsyllabus, TA_name, TA_email_id, TA_office_location);
			DAO.close();
			request.getRequestDispatcher("/FacultyMainServlet?x=Post_Information").forward(request, response);
		}
		if (x.equals("EditCourse")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			String course_id = request.getParameter("course_id");
			String course_title = request.getParameter("course_title");
			String office_hours = request.getParameter("office_hours");
			String office_location = request.getParameter("office_location");
			String email_id = request.getParameter("email_id");
			String TA_name = request.getParameter("TA_name");
			String TA_email_id = request.getParameter("TA_email_id");
			String TA_office_location = request.getParameter("TA_office_location");
			String radio_check = request.getParameter("Same_syllabus");
			int array_pos = Integer.parseInt(request.getParameter("item"));
			String fname = request.getParameter("fname");
			String user_id = request.getParameter("user_id");			
			ArrayList<Course_information> faculty_course = (ArrayList<Course_information>) session.getAttribute("faculty_course");
			String unique_id = faculty_course.get(array_pos).getUnique_id();
			if (radio_check.equals("new_syllabus")) {
				InputStream inputsyllabus = null;
				Part syllabus = request.getPart("syllabus");
				if (syllabus != null) {
					inputsyllabus = syllabus.getInputStream();
				}
				DAO.Update_Course_Syllabus(course_id,course_title, office_hours, office_location, email_id,inputsyllabus, unique_id , TA_name, TA_email_id, TA_office_location);
			}
			else {
				DAO.Update_course(course_id,course_title, office_hours, office_location, email_id, unique_id , TA_name, TA_email_id, TA_office_location, fname, user_id);
			}
			DAO.close();
			request.getRequestDispatcher("/FacultyMainServlet?x=Post_Information").forward(request, response);
		}
		if (x.equals("EditCourse1")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			String course_id = request.getParameter("course_id");
			String course_title = request.getParameter("course_title");
			String office_hours = request.getParameter("office_hours");
			String office_location = request.getParameter("office_location");
			String fname = request.getParameter("fname");
			String user_id = request.getParameter("user_id");		
			String email_id = request.getParameter("email_id");
			String TA_name = request.getParameter("TA_name");
			String TA_email_id = request.getParameter("TA_email_id");
			String TA_office_location = request.getParameter("TA_office_location");
			String radio_check = request.getParameter("Same_syllabus");
			int array_pos = Integer.parseInt(request.getParameter("item"));
			ArrayList<Course_information> faculty_course = (ArrayList<Course_information>) session.getAttribute("all_course");
			String unique_id = faculty_course.get(array_pos).getUnique_id();
			if (radio_check.equals("new_syllabus")) {
				InputStream inputsyllabus = null;
				Part syllabus = request.getPart("syllabus");
				if (syllabus != null) {
					inputsyllabus = syllabus.getInputStream();
				}
				DAO.Update_Course_Syllabus(course_id,course_title, office_hours, office_location, email_id,inputsyllabus, unique_id , TA_name, TA_email_id, TA_office_location);
			}
			else {
				DAO.Update_course(course_id,course_title, office_hours, office_location, email_id, unique_id , TA_name, TA_email_id, TA_office_location, fname, user_id);
			}
			DAO.close();
			request.getRequestDispatcher("/FacultyMainServlet?x=Post_Information").forward(request, response);
		}
		if (x.equals("Delete")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			String unique_id = request.getParameter("unique_id");
			DAO.DeleteCourse(unique_id);
			DAO.close();
			request.getRequestDispatcher("/FacultyMainServlet?x=Post_Information").forward(request, response);
		}
	}

}
