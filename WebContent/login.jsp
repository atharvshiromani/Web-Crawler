<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Login Portal</title>
</head>
<body>

<div class="panel panel-primary">
		<div class="panel-heading">
			<h1 align="center">Welcome To The Crawling Page</h1>
			<br>
		</div>
		</div>
	<%@ page import="java.sql.*"%>
	<%@ page import="javax.sql.*"%>
	<%
String userid=request.getParameter("user"); 
session.setAttribute("userid",userid);
String pwd=request.getParameter("pass");
session.setAttribute("pwd",pwd);
Class.forName("com.mysql.jdbc.Driver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crawlerlogin","root","root"); 
Statement st= con.createStatement();
String query="select * from userregistration where username='"+userid+"' and password='"+pwd+"'";
ResultSet rs=st.executeQuery(query); 
if(rs.next())
{ 
out.println("Welcome "+ userid + " !"); 
out.println("Now that you are here lets get to bussiness!");

}
 else 
{ 
response.sendRedirect("index.html");
out.println("Invalid password try again");

}
 
%>

	




	<div class="container">
		<h4>Enter the URL below to start crawling&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-down"></span></h4>


		<form action="crawl" method="get">

			<div class="form-group row">
				<div class="col-xs-7">
					<label for="usr">Catalog Url</label>&nbsp;&nbsp; <input type="text"
						name="url" id="usr" class="form-control" />
				</div>
			</div>
			<button type="submit" class="btn btn-danger" id="1">
				Crawl
				<%
				String url=request.getParameter("url"); 
				session.setAttribute("url",url);
				
				
			%>
			</button>
			<br> <br>

		</form>
<p id="p" class="label label-success">Message: ${param.message}</p><br/><br/>


		<form action="sucessfull.jsp">
			<button type="submit" class="btn btn-warning">Check Crawling
				History</button>
		</form>
	</div>


</body>
</html>