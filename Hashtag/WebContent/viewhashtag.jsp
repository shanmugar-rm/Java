<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="se.src.pkg.Hashtag" %>
<%@ page import="se.src.pkg.HashtagFeed" %>
<%@ page import="java.util.ArrayList" %>	 
<%! @SuppressWarnings("unchecked") %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View hashtag</title>
</head>
<body>
<h1> View hash tag</h1>
<script type="text/javascript">
function change() {
	document.getElementById("post/value").value = "create_post"; 
}
function change1(id) {
	document.getElementById("post/value").value = "reply_post";
	document.getElementById("id/feed").value = id;
}
</script>
<% Hashtag ht = (Hashtag)request.getAttribute("hashtag");%>
<% out.write(ht.getHashtag()); %>
<form method = "post" action="ViewHashtagServlet"> 
<input type = "hidden" name = "post_reply" id="post/value">
<input type = "hidden" name = "hashtag" value = "<%=ht.getHashtag() %>">
<input type="text" name ="post" style="font-size: 10pt; height: 20px; width:580px;" align ="middle">
<br><br><input type="submit" value = "Create_Post" onclick="change()">
<br>${display_msg} <br>
<% ArrayList<HashtagFeed> htf = (ArrayList<HashtagFeed>)request.getAttribute("hashtagfeed"); %> 
<% for (int i = 0; i<htf.size();i++) { %>
<br><%=htf.get(i).getDatetime()%>
<%=htf.get(i).getContent() %>	
<%if (htf.get(i).getChildcontent() != null) { %>
<%for (int j = 0; j < htf.get(i).getChildcontent().size() ; j++) { %>
<br><%=htf.get(i).getChildcontent().get(j)%>	
<% } %>	
<% } %> 
<br><input type="text" name ="post1<%=htf.get(i).getId()%>" style="font-size: 10pt; height: 20px; width:580px;" align ="middle">
<input type = "hidden" name ="parent_id1<%=htf.get(i).getId()%>" value = "<%=htf.get(i).getId()%>" >
<input type = "hidden" name = "hashtag1<%=htf.get(i).getId()%>" value = "<%=htf.get(i).getHashtag() %>">
<input type = "hidden" name = "post_reply" id="post/value">
<input type = "hidden" name = "id_feed" id="id/feed">
<br><br><input type="submit" value = "Reply_Post" onclick="change1(<%=htf.get(i).getId()%>)">
<%}%>
</form>
</body>
</html>
