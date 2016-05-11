package se.src.pkg;
//hashtagfeed model class
import java.util.ArrayList;

public class HashtagFeed {
	
	private int id;
	private String datetime; 
	private int parentfeedid;
	private String hashtag;
	private String content;
	//keeping child content as the replies to the post
	private ArrayList<String> child_contents=new ArrayList<String>();
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDatetime (String datetime) {
		this.datetime = datetime;
	}
	
	public String getDatetime () {
		return datetime;
	}
	
	public void setParentfeedid (int parentfeedid) {
		this.parentfeedid = parentfeedid;
	}
	
	public int getParentfeedid () {
		return parentfeedid;
	}
	
	public void setHashtag (String hashtag) {
		this.hashtag = hashtag;
	}
	
	public String getHashtag() {
		return hashtag;
	}
	
	public void setContent (String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setChildcontent (String child_content) {
		this.child_contents.add(child_content);
	}
	
	public ArrayList<String> getChildcontent () {
		return child_contents;
	}
}
