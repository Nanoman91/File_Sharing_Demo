<%@page import="java.io.File"%>
<%@page import="utility.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%!String home;
	String shareDir = Util.getShareDir();%>
	<%
		home = Util.getURLWithContextPath(request);
	%>
	
	<a href="<%= home %>/index.jsp">HOME</a>
	<br>
	<br>
	<hr>
	<br>
	<br>
	<hr>
<h3>Folder List</h3>
	<%
		File rootDir = new File(shareDir);
		if (rootDir.exists() && rootDir.isDirectory()) {
			for (File f : rootDir.listFiles()) {
				if (f.isDirectory() && !f.getName().contains("#")) {
					String filePath = f.getAbsolutePath();
					String fileName = f.getName();
	%>
	<a href='downloadFile?filePath=<%=filePath%>'><%=fileName%></a>
	<br>
	<%
		}
			}
		}
	%>

	<br>
	<hr>
	<hr>
	<br>
	<h3>File List</h3>
	<%
		if (rootDir.exists() && rootDir.isDirectory()) {
			for (File f : rootDir.listFiles()) {
				if (f.isFile() && !f.getName().contains("#")) {
					String filePath = f.getAbsolutePath();
					String fileName = f.getName();
	%>
	<a href='downloadFile?filePath=<%=filePath%>'><%=fileName%></a>
	<br>
	<%
		}
			}
		}
	%>
</body>
</html>