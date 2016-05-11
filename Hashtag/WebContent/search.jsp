<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Hashtag page</title>
</head>
<body>
<h1> Search hashtag </h1>
<form method = "post" action="SearchHashtagServlet"> 
<input type="text" name ="inputhashtag" style="font-size: 10pt; height: 20px; width:580px;" align ="middle">
<br><br><input type="submit" value = "Search" align = "middle">
</form>
${display_msg}	  	  
</body>
</html>