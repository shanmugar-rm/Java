package se.lab1.pkg;
//searchhashtagdao where all the database stuffs are done
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


import java.text.*;

public class SearchHashtagDAO {
		
		Connection con;
		//function to connect to database
		public boolean DBConnect(){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_lab01","root","root");
				return true;
			}
			catch(Exception ex) {
				System.out.println(ex);
				return false;
			}
		}
		
		//function to create hashtag
		public boolean HashtagInsert(Hashtag hashtagname) {
			try {
				Date date = new Date();
				String current_date = new SimpleDateFormat("yyyy/MM/dd").format(date.getTime());
//				System.out.println(current_date);
				String insert_sql = "insert into Hashtag values (?,?)";
				PreparedStatement ps = con.prepareStatement(insert_sql);
				ps.setString(1, hashtagname.getHashtag());
				ps.setString(2, current_date);
				ps.executeUpdate();
				return true;
			}
			catch (Exception ex) {
				System.out.println(ex);
				return false;
			}
		}
		
		//function to insert post
		public boolean HashtagFeedInsert(HashtagFeed htf) {
			try {
				Date date = new Date();
				String current_date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(date.getTime());
				String insert_sql = "insert into Hashtagfeed values (?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(insert_sql);
				int max_id = MaxID(htf.getHashtag());
				ps.setInt(1, max_id);
				ps.setString(2, current_date);
				ps.setNull(3, java.sql.Types.INTEGER);
				ps.setString(4, htf.getHashtag());
				ps.setString(5, htf.getContent());
				ps.executeUpdate();
				return true;
			}
			catch (Exception ex) {
				System.out.println(ex);
				return false;
			}
		}
		
		//function to insert replies
		public boolean HashtagReplyInsert(HashtagFeed htf) {
			try {
				Date date = new Date();
				String current_date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(date.getTime());
				String insert_sql = "insert into Hashtagfeed values (?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(insert_sql);
				ps.setInt(1, htf.getId());
				ps.setString(2, current_date);
				ps.setInt(3, htf.getParentfeedid());
				ps.setString(4, htf.getHashtag());
				ps.setString(5, htf.getContent());
				ps.executeUpdate();
				return true;
			}
			catch (Exception ex) {
				System.out.println(ex);
				return false;
			}
		}
		
		//function to get max id
		public int MaxID (String Hashtag) {
			int max_id = 0;
			try {
				String select_max = "Select max(id) from hashtagfeed where hashtag = (?)";
				PreparedStatement ps = con.prepareStatement(select_max);
				ps.setString(1, Hashtag);
				ResultSet output = ps.executeQuery();
				if (output.next()) {
					max_id = output.getInt(1);
				}
				return (max_id + 1);
			}
			catch (Exception ex) {
				return (max_id + 1);
			}		
		}
		
		//function to get the parent post details
		public ArrayList<HashtagFeed> GetParentpost (HashtagFeed variable) {
			ArrayList<HashtagFeed> hfl = new ArrayList<HashtagFeed>();
			try {
				String hastag = variable.getHashtag();
				String select_st = "Select * from hashtagfeed where hashtag = (?) and parentFeedID is Null order by time_created desc";
				PreparedStatement ps = con.prepareStatement(select_st);
				ps.setString(1, hastag);
				ResultSet output = ps.executeQuery();
				while (output.next()) {
					HashtagFeed hf = new HashtagFeed();
					hf.setId(output.getInt(1));
					hf.setDatetime(output.getString(2));
					hf.setParentfeedid(output.getInt(3));
					hf.setHashtag(output.getString(4));
					hf.setContent(output.getString(5));
					hfl.add(hf);
				}
				return hfl;
			}
			catch (Exception ex) {
				return hfl;
			}
		}
		
		//function to get reply post contents.
		public ArrayList<HashtagFeed> GetChildpost (HashtagFeed variable) {
			ArrayList<HashtagFeed> hfl = new ArrayList<HashtagFeed>();
			try {
				String hastag = variable.getHashtag();
				String select_st = "Select * from hashtagfeed where hashtag = (?) and parentFeedID is not Null order by parentFeedID desc";
				PreparedStatement ps = con.prepareStatement(select_st);
				ps.setString(1, hastag);
				ResultSet output = ps.executeQuery();
				while (output.next()) {
					HashtagFeed hf = new HashtagFeed();
					hf.setId(output.getInt(1));
					hf.setDatetime(output.getString(2));
					hf.setParentfeedid(output.getInt(3));
					hf.setHashtag(output.getString(4));
					hf.setContent(output.getString(5));
					hfl.add(hf);
				}
				return hfl;
			}
			catch (Exception ex) {
				return hfl;
			}
		}
		
		//function to check if a hashtag is present in hashtag table
		public int Count_tag (String Hashtag) {
			int max_id = 0;
			try {
				String select_count = "Select count(*) from hashtag where name_of_hashtag = (?)";
				PreparedStatement ps = con.prepareStatement(select_count);
				ps.setString(1, Hashtag);
				ResultSet output = ps.executeQuery();
				if (output.next()) {
					max_id = output.getInt(1);
				}
				return max_id;
			}
			catch (Exception ex) {
				return max_id;
			}		
		}
}
