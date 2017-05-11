<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    <%Class.forName("com.mysql.jdbc.Driver"); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head> 
<script type="text/javascript">
function DisableBackButton() {
	window.history.forward()
	}
	DisableBackButton();
	window.onload = DisableBackButton;
	window.onpageshow = function(evt) { if (evt.persisted) DisableBackButton() }
	window.onunload = function() { void (0) }
</script>
<title>cool cabs</title> 
</head> 
<frameset rows="100%"> 
   <frame name="main" src="main.html" /> 
</frameset> 
<body>

<%
HttpSession ses=request.getSession(false);		
ses.invalidate();


%>





</body>

</html> 