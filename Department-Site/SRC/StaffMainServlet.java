package se.term_project;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StaffMainServlet
 */
public class StaffMainServlet extends HttpServlet {
	String date_chosen_new = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffMainServlet() {
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
		String resource_selected1 = (String) session.getAttribute("resource_selected");
		String update_sql = null;
		String insert_sql = null;
		Login_sys user_detail = (Login_sys) session.getAttribute("user_details_db");				
		String x = request.getParameter("x");
		if (x.equals("Home")) {
			session.setAttribute("resource_selected", "--Select one--");
			session.setAttribute("slots", null);
			session.setAttribute("date_chosen", null);
			request.getRequestDispatcher("Faculty_homepage.jsp").forward(request, response);
		}
		if(x.equals("ReserveResourse")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<ResourseStatus> resource_list = dao.GetResources();
			dao.close();
			session.setAttribute("resource_list", resource_list);
			request.getRequestDispatcher("resourse.jsp").forward(request, response);
		}
		if(x.equals("ReserveResourse1")) {
			String xx = request.getParameter("xx");
			String resource_selected = request.getParameter("resource_list");
			session.setAttribute("resource_selected", resource_selected);
			if(xx.equals("0")) {
				String date_chosen = request.getParameter("datepicker");
				session.setAttribute("date_chosen", date_chosen);
				date_chosen = date_chosen.replaceAll("/", "-");
				Date date = null;
				try {
					date = new SimpleDateFormat("MM-dd-yyyy").parse(date_chosen);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				date_chosen_new = new SimpleDateFormat("yyyy-MM-dd").format(date);
				SETermDAO dao = new SETermDAO();
				dao.DBConnect();
				ResourseStatus slots = dao.GetResourcesforadate(resource_selected, date_chosen_new);
				String[] booked_by = dao.GetReserverByDate(resource_selected,date_chosen_new);
				dao.close();
				session.setAttribute("slots", slots);
				session.setAttribute("booked_by", booked_by);
				request.getRequestDispatcher("resourse.jsp").forward(request, response);
			}
			if(xx.equals("1")) {
				update_sql = "update resourses_status set AM_8 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("2")) {
				update_sql = "update resourses_status set AM_9 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("3")) {
				update_sql = "update resourses_status set AM_10 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("4")) {
				update_sql = "update resourses_status set AM_11 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("5")) {
				update_sql = "update resourses_status set AM_12 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("6")) {
				update_sql = "update resourses_status set AM_13 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("7")) {
				update_sql = "update resourses_status set AM_14 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("8")) {
				update_sql = "update resourses_status set AM_15 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("9")) {
				update_sql = "update resourses_status set AM_16 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if(xx.equals("10")) {
				update_sql = "update resourses_status set AM_17 = 0 where resourse_name = '"+resource_selected1+"' and avilable_date = '"+date_chosen_new+"'";
			}
			if (update_sql!=null) {
				insert_sql = "insert into resourses_hold (user_name, resourse_name, reserver_date, reserver_slot, fname) values ('"+user_detail.getUser_name()+"', '"+resource_selected1+"' , '"+date_chosen_new+"' , '"+xx+"' , '"+user_detail.getFirst_name()+"' )";
				SETermDAO dao = new SETermDAO();
				dao.DBConnect();
				boolean check = dao.RunQuery(update_sql);
				if (check) {
					String insert_check = "select count(*) from resourses_hold where resourse_name = '"+resource_selected1+"' and reserver_date = '"+date_chosen_new+"' and reserver_slot = '"+xx+"'";
					int check_count = dao.Insert_check_reservation(insert_check);
					if (check_count == 0) {
						dao.RunQuery(insert_sql);
					}
					else {
						request.setAttribute("zc","Something went wrong !! Might be someone else booked this slot - try again from starting");
						request.getRequestDispatcher("resourse.jsp").forward(request, response);
					}
				}
				ResourseStatus slots = dao.GetResourcesforadate(resource_selected1, date_chosen_new);
				String[] booked_by = dao.GetReserverByDate(resource_selected,date_chosen_new);
				dao.close();
				session.setAttribute("slots", slots);
				session.setAttribute("booked_by", booked_by);
				request.getRequestDispatcher("resourse.jsp").forward(request, response);
			}
		}
		if(x.equals("CancelResourse")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<ResourseHold> booked_slots = dao.GetReserverByUser(user_detail.getUser_name());
			session.setAttribute("booked_slots", booked_slots);
			dao.close();
			request.getRequestDispatcher("cancel.jsp").forward(request, response);
		}
		if(x.equals("CancelResourseDelete")) {
			String unique_id = request.getParameter("unique_id");
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String delete_sql = "delete from resourses_hold where unique_id = '"+unique_id+"'";
			dao.RunQuery(delete_sql);
			String slot_id = request.getParameter("slot_id");
			String resource_selected2 = request.getParameter("resource_selected2");
			String date_chosen_new1 = request.getParameter("date_chosen_new1");
			if(slot_id.equals("1")) {
				update_sql = "update resourses_status set AM_8 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("2")) {
				update_sql = "update resourses_status set AM_9 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("3")) {
				update_sql = "update resourses_status set AM_10 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("4")) {
				update_sql = "update resourses_status set AM_11 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("5")) {
				update_sql = "update resourses_status set AM_12 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("6")) {
				update_sql = "update resourses_status set AM_13 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("7")) {
				update_sql = "update resourses_status set AM_14 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("8")) {
				update_sql = "update resourses_status set AM_15 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("9")) {
				update_sql = "update resourses_status set AM_16 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			if(slot_id.equals("10")) {
				update_sql = "update resourses_status set AM_17 = 1 where resourse_name = '"+resource_selected2+"' and avilable_date = '"+date_chosen_new1+"'";
			}
			dao.RunQuery(update_sql);
			ArrayList<ResourseHold> booked_slots = dao.GetReserverByUser(user_detail.getUser_name());
			session.setAttribute("booked_slots", booked_slots);
			dao.close();
			request.getRequestDispatcher("cancel.jsp").forward(request, response);
		}
		if(x.equals("MakeAnnouncement")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<Announcement> announcements = dao.GetAnnouncements();
			session.setAttribute("announcements", announcements);
			dao.close();
			request.getRequestDispatcher("Announcement.jsp").forward(request, response);
		}
		if(x.equals("DeleteAnnouncement")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<Announcement> announcements = dao.GetAnnouncements();
			session.setAttribute("announcements", announcements);
			dao.close();
			request.getRequestDispatcher("DeleteAnnouncement.jsp").forward(request, response);
		}
		if(x.equals("DeleteAnnouncement_list")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String unique_id = request.getParameter("unique_id");
			dao.DeleteAnnouncement(unique_id);
			dao.close();
			request.getRequestDispatcher("/StaffMainServlet?x=DeleteAnnouncement").forward(request, response);
		}
		if(x.equals("MakeAnnouncement_post")) {
			SETermDAO dao = new SETermDAO();
			Login_sys user_details= (Login_sys) session.getAttribute("user_details_db");
			String user_name = user_details.getFirst_name();
			String announcement_type = request.getParameter("Announcement_type");
			String announcement_heading = request.getParameter("Announcement_heading");
			String announcement_content = request.getParameter("Announcement_content");			
			dao.DBConnect();
			dao.Insert_announcement(user_name,announcement_type,announcement_heading,announcement_content);
			dao.close();
			request.getRequestDispatcher("/StaffMainServlet?x=MakeAnnouncement").forward(request, response);
		}
		if(x.equals("View_post_DiscussionBoard")) {
			SETermDAO dao = new SETermDAO();						
			dao.DBConnect();
			ArrayList<Discussion> discussion_content = dao.GetParentpost();
			ArrayList<Discussion> temp = dao.GetChildpost();
			for(int a=0;a<temp.size();a++) {
				for(int b=0;b<discussion_content.size();b++){
					if(temp.get(a).getParentfeedid() == discussion_content.get(b).getId()) {
						String child_content = temp.get(a).getContent();
						String post_made_by = temp.get(a).getUsername();
						discussion_content.get(b).setChildcontent(child_content);
						discussion_content.get(b).setPostMadeBy(post_made_by);						
					}
				}
			}
			dao.close();
			session.setAttribute("discussion_content", discussion_content);
			request.getRequestDispatcher("Discussion.jsp").forward(request, response);
		}
		if(x.equals("view_post_discussion_reply")){
			String reply_post = request.getParameter("post_reply");
			if(reply_post.equals("create_post")){
				String new_content = request.getParameter("new_question");
				Login_sys user_details = (Login_sys) session.getAttribute("user_details_db");
				String user_name = user_details.getFirst_name();
				SETermDAO dao = new SETermDAO();
				dao.DBConnect();
				dao.DiscussionInsertParent(user_name, new_content);
				dao.close();
				request.getRequestDispatcher("/StaffMainServlet?x=View_post_DiscussionBoard").forward(request, response);
			}
			if(reply_post.equals("reply_post")){
				int id = Integer.parseInt(request.getParameter("id_feed"));
				String new_content = request.getParameter("reply"+id);
				Login_sys user_details = (Login_sys) session.getAttribute("user_details_db");
				String user_name = user_details.getFirst_name();
				SETermDAO dao = new SETermDAO();
				dao.DBConnect();
				dao.DiscussionReplyInsert(user_name, new_content, id);
				dao.close();
				request.getRequestDispatcher("/StaffMainServlet?x=View_post_DiscussionBoard").forward(request, response);
			}			
		}
		if(x.equals("DeleteInappropriateContent")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<Announcement> announcements = dao.GetAnnouncements();
			session.setAttribute("announcements_inapprop", announcements);
			ArrayList<Discussion> discussion_content = dao.GetParentpost();
			ArrayList<Discussion> temp = dao.GetChildpost();
			for(int a=0;a<temp.size();a++) {
				for(int b=0;b<discussion_content.size();b++){
					if(temp.get(a).getParentfeedid() == discussion_content.get(b).getId()) {
						String child_content = temp.get(a).getContent();
						String post_made_by = temp.get(a).getUsername();
						discussion_content.get(b).setChildcontent(child_content);
						discussion_content.get(b).setPostMadeBy(post_made_by);						
					}
				}
			}
			dao.close();
			session.setAttribute("discussion_content_inapprop", discussion_content);
			dao.close();
			request.getRequestDispatcher("DeleteInappropAnnouncement.jsp").forward(request, response);
		}		
		if(x.equals("View_post_updatePhdStudentStatus")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<Phd_status> phd_student_list = new ArrayList<Phd_status>();
			phd_student_list = dao.getPhdStudentList();
			dao.close();
			session.setAttribute("phd_student_list", phd_student_list);			
			request.getRequestDispatcher("ViewPhdStudentList.jsp").forward(request, response);
		}
		if(x.equals("EditPhdStudentStatus")){
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String unique_id = request.getParameter("unique_id");
			String is_candidate = request.getParameter("is_candidate");			
			String is_candidate_boolean = "";
			if (is_candidate.equals("Yes")) {
				System.out.println(unique_id);
				is_candidate_boolean = "1";
			}
			if (is_candidate.equals("No")) {
				is_candidate_boolean = "0";
			}
			dao.updatePhD(unique_id, is_candidate_boolean);
			dao.close();
			request.getRequestDispatcher("/StaffMainServlet?x=View_post_updatePhdStudentStatus").forward(request, response);
		}
		if(x.equals("AddResource")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<ResourseStatus> list_res = dao.GetResources();
			dao.close();
			session.setAttribute("list_res", list_res);
			request.getRequestDispatcher("AddResource.jsp").forward(request, response);
		}
		if(x.equals("AddResource1")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String resource_name = request.getParameter("resource_name");
			String resource_details = request.getParameter("resource_details");
			dao.AddResource(resource_name,resource_details);
			dao.close();
			request.setAttribute("message", "Resource Added");
			request.getRequestDispatcher("AddResource.jsp").forward(request, response);
		}
		if(x.equals("UpdateUser")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String Email_id = request.getParameter("Email_id");
			String Advisor_name = request.getParameter("Advisor_name");
			String phone_number = request.getParameter("phone_number");
			Login_sys user_details = (Login_sys) session.getAttribute("user_details_db");
			dao.UpdateUser(Email_id,Advisor_name,phone_number,user_details.getUser_name());
			String password = request.getParameter("password");
			if (password!=null) {
				if (password.length() > 1) {
					dao.updatePassword(password, user_details.getUser_name());
				}				
			}
			request.setAttribute("message", "User Details Updated");
			Login_sys user_details_db = dao.GetUserdetails(user_details); 
			session.setAttribute("user_details_db", user_details_db);
			dao.close();
			request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
		}
		if(x.equals("Edit_Announcement")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String unique_id = request.getParameter("unique_id");
			String Announcement_heading = request.getParameter("Announcement_heading");
			String Announcement_content = request.getParameter("Announcement_content");
			dao.updateAnnouncement(unique_id, Announcement_heading,Announcement_content);
			dao.close();
			request.getRequestDispatcher("/StaffMainServlet?x=MakeAnnouncement").forward(request, response);
		}
		if(x.equals("DeleteInAppropriate")) {
			String type = request.getParameter("type");
			if(type.equals("DissHead")) {
				String unique_id = request.getParameter("unique_id");
				SETermDAO dao = new SETermDAO();
				dao.DBConnect();
				dao.deleteDissHead(unique_id);
				dao.close();
			}
			if(type.equals("DissContent")) {
				String content = request.getParameter("content");
				String unique_id = request.getParameter("unique_id");
				SETermDAO dao = new SETermDAO();
				dao.DBConnect();
				dao.deleteDissContent(unique_id, content);
				dao.close();
			}
			if(type.equals("Announcement")) {
				String unique_id = request.getParameter("unique_id");
				SETermDAO dao = new SETermDAO();
				dao.DBConnect();
				dao.DeleteAnnouncement(unique_id);
				dao.close();				
			}
			request.getRequestDispatcher("/StaffMainServlet?x=DeleteInappropriateContent").forward(request, response);
		}
		if(x.equals("View_post_updateAlumniinfo")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			ArrayList<Alumni_info> alumni_list = dao.selectAlumni();
			session.setAttribute("alumni_list", alumni_list);
			dao.close();
			request.getRequestDispatcher("Alumni_info.jsp").forward(request, response);
		}
		if(x.equals("View_post_updateAlumniinfo1")) {
			SETermDAO dao = new SETermDAO();
			dao.DBConnect();
			String year_passout = request.getParameter("year_passout");
			String current_address = request.getParameter("current_address");
			String current_job = request.getParameter("current_job");
			String unique_id = request.getParameter("unique_id");
			dao.updateAlumniInfo(unique_id, year_passout, current_address, current_job);
			dao.close();
			request.getRequestDispatcher("StaffMainServlet?x=View_post_updateAlumniinfo").forward(request, response);
		}
		if(x.equals("ViewDepartmentResults")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			ArrayList<Create_test_M> test_list = DAO.getTestList();
			ArrayList<Student_test> student_list = DAO.getStudentList();
			session.setAttribute("test_list", test_list);
			session.setAttribute("student_list", student_list);
			DAO.close();
			request.getRequestDispatcher("ViewTest.jsp").forward(request, response);			
		}
		if(x.equals("ViewDepartmentResults1")) {
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			String date_chosen = request.getParameter("datepicker");			
			date_chosen = date_chosen.replaceAll("/", "-");
			Date date = null;
			try {
				date = new SimpleDateFormat("MM-dd-yyyy").parse(date_chosen);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			date_chosen_new = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String test_name = request.getParameter("test_name");
			String test_details = request.getParameter("test_details");
			String fname = user_detail.getFirst_name();
			DAO.insertTest(test_name, date_chosen_new, test_details, fname);
			DAO.close();
			request.getRequestDispatcher("StaffMainServlet?x=ViewDepartmentResults").forward(request, response);			
		}
		if(x.equals("RegisterTest")) {
			String item = request.getParameter("item");
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			DAO.insertStudent(item, user_detail.getFirst_name(),user_detail.getLast_name());
			DAO.close();
			request.getRequestDispatcher("StaffMainServlet?x=ViewDepartmentResults").forward(request, response);
		}
		if(x.equals("SaveMark")) {
			String item = request.getParameter("item");			
			String mark = request.getParameter("mark"+item);		
			String parentid = request.getParameter("parentid");
			SETermDAO DAO = new SETermDAO();
			DAO.DBConnect();
			DAO.setMark(item,mark);
			ArrayList<Student_test> student_list = DAO.getStudentList();
			session.setAttribute("student_list", student_list);	
			DAO.close();
			request.getRequestDispatcher("ResultView.jsp?type=edit&item="+parentid).forward(request, response);
		}
	}

}
