package se.term_project;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SETermDAO {
	Connection con;
	//function to connect to database
	public boolean DBConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_term_project","root","root");
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public void close() {
		try {
			con.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public boolean user_present (Login_sys variable) {
		int max_id = 0;
		boolean found = false;
		String user_name = variable.getUser_name();
		String password = variable.getpassword_sys();		
		String student_employee = variable.getStudent_employee();
		try {
			String select_count = "Select count(*) from login_sys where user_name = (?) and password_sys =(?) and student_employee = (?)";
			PreparedStatement ps = con.prepareStatement(select_count);
			ps.setString(1, user_name);
			ps.setString(2, password);
			ps.setString(3, student_employee);
			ResultSet output = ps.executeQuery();
			if (output.next()) {
				max_id = output.getInt(1);
			}
			if (max_id > 0) {
				found = true;
			}
			return found;
		}
		catch (Exception ex) {
			return found;
		}		
	}
	
	public Login_sys GetUserdetails (Login_sys variable) {
		Login_sys user_details = new Login_sys();
		try {
			String user_name = variable.getUser_name();
			String student_employee = variable.getStudent_employee();
			String select_st = "Select * from login_sys where user_name = (?) and student_employee = (?)";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, user_name);
			ps.setString(2, student_employee);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				user_details.setUser_name(output.getString(1));
				user_details.setPassword_sys(output.getString(2));
				user_details.setFirst_name(output.getString(3));
				user_details.setLast_name(output.getString(4));
				user_details.setAdvisor_name(output.getString(5));
				user_details.setProgram_taken(output.getString(6));
				user_details.setStudent_employee(output.getString(7));
				user_details.setemail_id(output.getString(8));
				user_details.setyear_joined(output.getString(9));
				user_details.setmajor(output.getString(10));
				user_details.setphone(output.getString(11));
			}
			return user_details;
		}
		catch (Exception ex) {
			return user_details;
		}
	}
	
	public ArrayList<ResourseStatus> GetResources () {
		ArrayList<ResourseStatus> resource_list = new ArrayList<ResourseStatus>();
		try {
			String select_st = "select distinct(resourse_name) , resourse_details from resourses_status order by resourse_name";
			PreparedStatement ps = con.prepareStatement(select_st);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				ResourseStatus temp = new ResourseStatus();
				temp.setResourse_name(output.getString(1));
				temp.setresourse_details(output.getString(2));
				resource_list.add(temp);
			}
			return resource_list;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return resource_list;
		}
	}
	
	public ResourseStatus GetResourcesforadate (String resourse_name , String avi_date) {
		ResourseStatus resource_timeslot = new ResourseStatus();
		try {
			String select_st = "select * from resourses_status where resourse_name = (?) and avilable_date = (?)";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, resourse_name);
			ps.setString(2, avi_date);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				resource_timeslot.setResourse_name(output.getString(1));
				resource_timeslot.setAvilable_date(output.getDate(2));
				resource_timeslot.setAM_8(output.getBoolean(3));
				resource_timeslot.setAM_9(output.getBoolean(4));
				resource_timeslot.setAM_10(output.getBoolean(5));
				resource_timeslot.setAM_11(output.getBoolean(6));
				resource_timeslot.setAM_12(output.getBoolean(7));
				resource_timeslot.setAM_13(output.getBoolean(8));
				resource_timeslot.setAM_14(output.getBoolean(9));
				resource_timeslot.setAM_15(output.getBoolean(10));
				resource_timeslot.setAM_16(output.getBoolean(11));
				resource_timeslot.setAM_17(output.getBoolean(12));
				resource_timeslot.setresourse_details(output.getString(13));
			}
			return resource_timeslot;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return resource_timeslot;
		}
	}
	
	public boolean RunQuery(String stm) {
		try {
			Statement st=con.createStatement();
	        st.execute(stm);
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public boolean Login_sys_insert (Login_sys user_details) {
		try {
			String space = " ";
			String insert_sql = "insert into login_sys values (?,?,?,?,?,?,?,?,?,?,?)";
			if (user_details.getStudent_employee().equals("Student")) {
				String insert_sql1 = "insert into alumni_info (net_id, fname, lname, current_address, current_company, year_passout) values(?,?,?,?,?,?)";
				PreparedStatement ps1 = con.prepareStatement(insert_sql1);
				ps1.setString(1, user_details.getUser_name());
				ps1.setString(2, user_details.getFirst_name());
				ps1.setString(3, user_details.getLast_name());
				ps1.setString(4, space);
				ps1.setString(5, space);
				ps1.setString(6, space);
				ps1.executeUpdate();
				
				String insert_sql2 = "insert into student_status (net_id, fname, core_course_status, elective_status, intern_status) values(?,?,?,?,?)";
				PreparedStatement ps2 = con.prepareStatement(insert_sql2);
				ps2.setString(1, user_details.getUser_name());
				ps2.setString(2, user_details.getFirst_name());
				ps2.setString(3, "TBD");
				ps2.setString(4, "TBD");
				ps2.setString(5, "TBD");
				ps2.executeUpdate();
			}			
			PreparedStatement ps = con.prepareStatement(insert_sql);			
			ps.setString(1, user_details.getUser_name());
			ps.setString(2, user_details.getpassword_sys());
			ps.setString(3, user_details.getFirst_name());
			ps.setString(4, user_details.getLast_name());
			ps.setString(5, user_details.getAdvisor_name());
			ps.setString(6, user_details.getProgram_taken());
			ps.setString(7, user_details.getStudent_employee());
			ps.setString(8, user_details.getemail_id());
			ps.setString(9, user_details.getyear_joined());
			ps.setString(10, user_details.getmajor());
			ps.setString(11, user_details.getphone());
			ps.executeUpdate();			
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public ArrayList<Course_information> GetFaculty_course (Login_sys variable) {
		ArrayList<Course_information> faculty_course = new ArrayList<Course_information>();
		try {
			String user_name = variable.getUser_name();
			String select_st = "Select * from course_information where user_id = (?)";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, user_name);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				Course_information temp = new Course_information();
				temp.setCourse_id(output.getString(1));
				temp.setCourse_title(output.getString(2));
				temp.setUnique_id(output.getString(3));
				temp.setTA_name(output.getString(4));
				temp.setTA_email_id(output.getString(5));
				temp.setTA_office_location(output.getString(6));
				temp.setUser_id(output.getString(7));
				temp.setUser_name(output.getString(8));
				temp.setOffice_hours(output.getString(9));
				temp.setOffice_location(output.getString(10));
				temp.setEmail_id(output.getString(11));
				temp.setSyllabus(output.getBlob(12));
				faculty_course.add(temp);
			}
			
			return faculty_course;
		}
		catch (Exception ex) {
			return faculty_course;
		}
	}
	
	public ArrayList<Course_information> All_course () {
		ArrayList<Course_information> faculty_course = new ArrayList<Course_information>();
		try {
			String select_st = "Select * from course_information";
			PreparedStatement ps = con.prepareStatement(select_st);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				Course_information temp = new Course_information();
				temp.setCourse_id(output.getString(1));
				temp.setCourse_title(output.getString(2));
				temp.setUnique_id(output.getString(3));
				temp.setTA_name(output.getString(4));
				temp.setTA_email_id(output.getString(5));
				temp.setTA_office_location(output.getString(6));
				temp.setUser_id(output.getString(7));
				temp.setUser_name(output.getString(8));
				temp.setOffice_hours(output.getString(9));
				temp.setOffice_location(output.getString(10));
				temp.setEmail_id(output.getString(11));
				temp.setSyllabus(output.getBlob(12));
				faculty_course.add(temp);
			}
			
			return faculty_course;
		}
		catch (Exception ex) {
			return faculty_course;
		}
	}
	
	public ArrayList<ResourseHold> GetReserverByUser(String user_name) {
		ArrayList<ResourseHold> booked_list = new ArrayList<ResourseHold>();
		try {
			String select_st = "Select * from resourses_hold where user_name = (?) and reserver_date >= current_date()";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, user_name);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				ResourseHold temp = new ResourseHold();
				temp.setUser_name(output.getString(1));
				temp.setResourse_name(output.getString(2));
				temp.setReserve_date(output.getDate(3));
				temp.setReserveSlot(output.getInt(4));
				temp.setUnique_id(output.getInt(5));
				booked_list.add(temp);
			}
			return booked_list;
		}
		catch (Exception ex) {
			return booked_list;
		}
	}
	
	public int Insert_check_reservation (String query) {
		int count = 0;
		try {
			PreparedStatement ps = con.prepareStatement(query); 
			ResultSet output = ps.executeQuery();
			while(output.next()){
				count = output.getInt(1);
			}
			return count;
		}
		catch (Exception ex) {
			return count;
		}
	}
	
	public boolean Insert_Course(String course_id, String course_title, String office_hours, String office_location, String email_id, String user_id, String User_name, InputStream inputsyllabus, String TA_name, String TA_email_id, String TA_office_location) {
		try {
			String insert_sql = "insert into course_information (course_id, course_title, user_id, user_name, office_hours, office_location, email_id, syllabus, TA_name, TA_email_id, TA_office_location) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, course_id);
			ps.setString(2, course_title);
			ps.setString(3, user_id);
			ps.setString(4, User_name);
			ps.setString(5, office_hours);
			ps.setString(6, office_location);
			ps.setString(7, email_id);
			ps.setBlob(8, inputsyllabus);
			ps.setString(9, TA_name);
			ps.setString(10, TA_email_id);
			ps.setString(11, TA_office_location);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public boolean Update_Course_Syllabus(String course_id, String course_title, String office_hours, String office_location, String email_id, InputStream inputsyllabus, String unique_id, String TA_name, String TA_email_id, String TA_office_location) {
		try {
			String update_sql = "update course_information set course_id =?, course_title =? , office_hours=?, office_location=?, email_id=?, syllabus=? , TA_name =?, TA_email_id =? , TA_office_location=?  where unique_id =?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, course_id);
			ps.setString(2, course_title);
			ps.setString(3, office_hours);
			ps.setString(4, office_location);
			ps.setString(5, email_id);
			ps.setBlob(6, inputsyllabus);
			ps.setString(7, TA_name);
			ps.setString(8, TA_email_id);
			ps.setString(9, TA_office_location);
			ps.setString(10, unique_id);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public boolean Update_course(String course_id, String course_title, String office_hours, String office_location, String email_id, String unique_id, String TA_name, String TA_email_id, String TA_office_location, String fname, String user_id) {
		try {
			String update_sql = "update course_information set course_id =?, course_title =? , office_hours=?, office_location=?, email_id=?, TA_name =?, TA_email_id =? , TA_office_location=? , user_id = ? , user_name = ?  where unique_id =?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, course_id);
			ps.setString(2, course_title);
			ps.setString(3, office_hours);
			ps.setString(4, office_location);
			ps.setString(5, email_id);
			ps.setString(6, TA_name);
			ps.setString(7, TA_email_id);
			ps.setString(8, TA_office_location);
			ps.setString(9, user_id);
			ps.setString(10, fname);
			ps.setString(11, unique_id);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public boolean DeleteCourse(String unique_id) {
		try {
			String delete_sql = "delete from course_information where unique_id =?";
			PreparedStatement ps = con.prepareStatement(delete_sql);
			ps.setString(1, unique_id);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public ArrayList<Announcement> GetAnnouncements() {
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		try {
			String select_sql = "select * from announcements order by unique_id desc";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ResultSet output = ps.executeQuery();
			while(output.next()){
				Announcement temp = new Announcement();
				temp.setUser_name(output.getString(1));
				temp.setAnnouncement_type(output.getString(2));
				temp.setAnnouncement_date(output.getDate(3));
				temp.setAnnouncement_content(output.getString(4));
				temp.setUnique_id(output.getString(5));
				temp.setAnnouncement_heading(output.getString(6));
				announcements.add(temp);
			}
			return announcements;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return announcements;
		}
	}
	
	public boolean Insert_announcement(String user_name, String announcement_type, String announcement_heading, String announcement_content) {
		try {
			String insert_sql ="insert into announcements (user_name,announcement_type,announcement_date,announcement_heading,announcement_content) values(?,?,current_date(),?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, user_name);
			ps.setString(2, announcement_type);
			ps.setString(3, announcement_heading);
			ps.setString(4, announcement_content);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public boolean DeleteAnnouncement(String unique_id) {
		try {
			String delete_sql = "delete from announcements where unique_id=?";
			PreparedStatement ps = con.prepareStatement(delete_sql);
			ps.setString(1, unique_id);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public boolean DiscussionInsertParent(String user_name, String content) {
		try {
			Date date = new Date();
			String current_date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(date.getTime());
			String insert_sql = "insert into discussion (date_posted, parentfeedid, user_name, post_content) values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, current_date);
			ps.setNull(2, java.sql.Types.INTEGER);
			ps.setString(3, user_name);
			ps.setString(4, content);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public boolean DiscussionReplyInsert(String user_name, String content, int parent_id) {
		try {
			Date date = new Date();
			String current_date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(date.getTime());
			String insert_sql = "insert into discussion (date_posted, parentfeedid, user_name, post_content) values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, current_date);
			ps.setInt(2, parent_id);
			ps.setString(3, user_name);
			ps.setString(4, content);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public ArrayList<Discussion> GetParentpost () {
		ArrayList<Discussion> parent_post = new ArrayList<Discussion>();
		try {			
			String select_st = "Select * from discussion where parentfeedid is null order by date_posted desc";
			PreparedStatement ps = con.prepareStatement(select_st);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				Discussion temp = new Discussion();
				temp.setId(output.getInt(1));
				temp.setDatetime(output.getString(2));
				temp.setParentfeedid(output.getInt(3));
				temp.setUsername(output.getString(4));
				temp.setContent(output.getString(5));
				parent_post.add(temp);
			}
			return parent_post;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return parent_post;
		}
	}
		
	public ArrayList<Discussion> GetChildpost () {
		ArrayList<Discussion> children_post = new ArrayList<Discussion>();
		try {			
			String select_st = "Select * from discussion where parentfeedid is not null order by parentFeedID desc";
			PreparedStatement ps = con.prepareStatement(select_st);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				Discussion temp = new Discussion();
				temp.setId(output.getInt(1));
				temp.setDatetime(output.getString(2));
				temp.setParentfeedid(output.getInt(3));
				temp.setUsername(output.getString(4));
				temp.setContent(output.getString(5));
				children_post.add(temp);
			}
			return children_post;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return children_post;
		}
	}

	public boolean Login_sys_check(Login_sys user_info) {
		try {
			String select_st = "select count(*) from login_sys where user_name = ?";
			int count = 0;
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, user_info.getUser_name());
			ResultSet output = ps.executeQuery();			
			while (output.next()) {
				count = output.getInt(1);
			}
			if (count > 0) {
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception ex){
			System.out.println(ex);
			return false;
		}		
	}

	public boolean insertPhD(String net_id, String year_joined, String first_name, String last_name) {
		try {
			String insert_sql = "insert into phd_status (net_id, year_joined, is_candidte, first_name, last_name) values (?,?,0,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, net_id);
			ps.setString(2, year_joined);
			ps.setString(3, first_name);
			ps.setString(4, last_name);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public ArrayList<Phd_status> getPhdStudentList() {
		ArrayList<Phd_status> student_list = new ArrayList<Phd_status>();
		try {
			String select_sql = "select * from phd_status";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ResultSet output = ps.executeQuery();			
			while (output.next()) {
				Phd_status temp = new Phd_status();
				temp.setUnique_id(output.getString(1));
				temp.setnet_id(output.getString(2));
				temp.setyear_joined(output.getString(3));
				temp.setis_candidate(output.getString(4));
				temp.setfirst_name(output.getString(5));
				temp.setlast_name(output.getString(6));
				student_list.add(temp);
			}
			return student_list;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return student_list;			
		}		
	}

	public boolean updatePhD(String unique_id, String is_candidate_boolean) {
		try{
			String update_sql = "update phd_status set is_candidte = ? where unique_id = ?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, is_candidate_boolean);
			ps.setString(2, unique_id);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
	}

	public String[] GetReserverByDate(String resource_selected, String date_chosen_new) {
		String[] list_perday = new String[11];
		for (int i = 0 ; i <10;i++) {				
			list_perday[i] = null;			
		}
		try {
			String select_sql = "select fname, reserver_slot from resourses_hold where resourse_name = ? and reserver_date = ? order by reserver_slot asc";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ps.setString(1, resource_selected);
			ps.setString(2, date_chosen_new);
			ResultSet output = ps.executeQuery();			
			while(output.next()) {
				int slot = output.getInt(2);
				list_perday[slot] = output.getString(1);
			}
			return list_perday;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return list_perday;
		}		
	}

	public boolean AddResource(String resource_name, String resource_details) {
		try {
			for(int i = 0;i<31;i++) {
				String insert_sql = "insert into resourses_status values(?,date_add(current_date(),Interval ? day),1,1,1,1,1,1,1,1,1,1,?)";
				PreparedStatement ps = con.prepareStatement(insert_sql);
				ps.setString(1, resource_name);
				ps.setString(2, String.valueOf(i));
				ps.setString(3, resource_details);				
				ps.executeUpdate();
			}
			return true;
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}		
	}

	public boolean UpdateUser(String email_id, String advisor_name, String phone_number, String user_name) {
		try {
			String update_sql = "update login_sys set email_id = ? , advisor_name = ? , phone = ? where user_name = ?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, email_id);
			ps.setString(2, advisor_name);
			ps.setString(3, phone_number);
			ps.setString(4, user_name);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
		
	}

	public boolean updatePassword(String password, String user_name) {
		try {
			String update_sql = "update login_sys set password_sys = ? where user_name= ?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, password);
			ps.setString(2, user_name);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex){
			System.out.println(ex);
			return false;
		}
	}

	public boolean updateAnnouncement(String unique_id, String announcement_heading, String announcement_content) {
		try {
			String update_sql = "update announcements set announcement_heading=? , announcement_content = ? where unique_id = ?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, announcement_heading);
			ps.setString(2, announcement_content);
			ps.setString(3, unique_id);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}		
	}

	public boolean deleteDissHead(String unique_id) {
		try {
			String delete_sql = "delete from discussion where unique_id = ?";
			String delete_sql1 = "delete from discussion where parentfeedid = ?";
			PreparedStatement ps = con.prepareStatement(delete_sql);
			PreparedStatement ps1 = con.prepareStatement(delete_sql1);
			ps.setString(1, unique_id);
			ps1.setString(1, unique_id);
			ps.executeUpdate();
			ps1.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}		
	}

	public boolean deleteDissContent(String unique_id, String content) {
		try {
			String delete_sql = "delete from discussion where parentfeedid = ? and post_content = ?";
			PreparedStatement ps = con.prepareStatement(delete_sql);
			ps.setString(1, unique_id);
			ps.setString(2, content);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}	
	}

	public ArrayList<Alumni_info> selectAlumni() {
		ArrayList<Alumni_info> alumni_list = new ArrayList<Alumni_info>();
		try {
			String select_sql = "select * from alumni_info order by year_passout asc";
			PreparedStatement ps = con.prepareStatement(select_sql);			
			ResultSet output = ps.executeQuery();			
			while(output.next()) {
				Alumni_info temp = new Alumni_info();
				temp.SetUnique_id(output.getString(1));
				temp.Setnet_id(output.getString(2));
				temp.Setfname(output.getString(3));
				temp.Setlname(output.getString(4));
				temp.Setcurrent_address(output.getString(5));
				temp.Setcurrent_company(output.getString(6));
				temp.Setyear_passout(output.getString(7));
				alumni_list.add(temp);
			}
			return alumni_list;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return alumni_list;
		}
		
	}

	public boolean updateAlumniInfo(String unique_id, String year_passout, String current_address, String current_job) {
		try {
			String update_sql = "update alumni_info set current_address=?, current_company=? , year_passout=? where unique_id = ?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, current_address);
			ps.setString(2, current_job);
			ps.setString(3, year_passout);
			ps.setString(4, unique_id);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}		
	}

	public ArrayList<Create_test_M> getTestList() {
		ArrayList<Create_test_M> test_list = new ArrayList<Create_test_M>();
		try {
			String select_sql = "select * from create_test order by test_date desc";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ResultSet output = ps.executeQuery();
			while(output.next()) {
				Create_test_M temp = new Create_test_M();
				temp.setUnique_id(output.getString(1));
				temp.settest_name(output.getString(2));
				temp.settested_created_by(output.getString(3));
				temp.settest_date(output.getString(4));
				temp.settest_details(output.getString(5));
				test_list.add(temp);
			}
			return test_list;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return test_list;
		}
	}

	public boolean insertTest(String test_name, String date_chosen_new, String test_details, String fname) {
		try {
			String insert_sql = "insert into create_test (test_name, test_created_by, test_date, test_details) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, test_name);
			ps.setString(2, fname);
			ps.setString(3, date_chosen_new);
			ps.setString(4, test_details);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public ArrayList<Student_test> getStudentList() {
		ArrayList<Student_test> student_list = new ArrayList<Student_test>();
		try {
			String select_sql = "select * from student_test order by parentid";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ResultSet output = ps.executeQuery();
			while(output.next()){
				Student_test temp = new Student_test();
				temp.setUnique_id(output.getString(1));
				temp.setparentid(output.getString(2));
				temp.setstudent_fname(output.getString(3));
				temp.setstudent_lname(output.getString(4));
				temp.setstudent_mark(output.getFloat(5));
				student_list.add(temp);
			}
			return student_list;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return student_list;
		}		
	}

	public boolean setMark(String item, String mark) {			
		try {
			String update_sql = "update student_test set student_mark = ? where unique_id = ?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, mark);
			ps.setString(2, item);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public boolean insertStudent(String item, String first_name, String last_name) {
		try {
			String insert_sql = "insert into student_test (parentid, student_fname, student_lname, student_mark) values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, item);
			ps.setString(2, first_name);
			ps.setString(3, last_name);
			ps.setFloat(4, 0);
			ps.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}		
	}

	public ArrayList<Suggestion> GetSuggestions(String getmajor) {
		ArrayList<Suggestion> suggestion = new ArrayList<Suggestion>();
		try {
			String select_sql = "select * from suggestion where concentration = ?";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ps.setString(1, getmajor);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				Suggestion temp = new Suggestion();
				temp.setconcentration(output.getString(1));
				temp.setSuggested_courses(output.getString(2));
				temp.setUnique_id(output.getString(3));
				suggestion.add(temp);
			}
			return suggestion;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return suggestion;
		}
	}

	public String GetRequirements(String getmajor, String program_taken) {
		String prog_req = new String();
		try {
			String select_sql = "select requirements from program_requirements where concentration = ? and program = ?";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ps.setString(1, getmajor);
			ps.setString(2, program_taken);
			ResultSet output = ps.executeQuery();
			while(output.next()) {
				prog_req = output.getString(1);
			}
			return prog_req;			
		}
		catch(Exception ex) {
			System.out.println(ex);
			return prog_req;
		}
	}

	public Student_status getStudentStatus(String user_name) {
		Student_status temp = new Student_status();
		try {
			String select_sql = "select * from student_status where net_id = ?";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ps.setString(1, user_name);
			ResultSet output =ps.executeQuery();
			while(output.next()) {				
				temp.setUnique_id(output.getString(1));
				temp.setNet_id(output.getString(2));
				temp.setfname(output.getString(3));
				temp.setCore_course_status(output.getString(4));
				temp.setelective_status(output.getString(5));
				temp.setintern_status(output.getString(6));				
			}
			return temp;			
		}
		catch(Exception ex) {
			System.out.println(ex);
			return temp;
		}
	}		
}