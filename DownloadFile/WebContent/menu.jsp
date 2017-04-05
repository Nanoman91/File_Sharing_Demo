<%@page import="controllers.DownloadServlet"%>
<%@page import="utility.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


<h1>
	<a href="<%=Util.getURLWithContextPath(request)%>/index.jsp"> <span
		class="label label-info">HOME</span>
	</a>
</h1>
<%-- 	<a href="<%= request.getHeader("Referer")%>">Previous</a> --%>
<%!String filePath;%>
<%
	filePath = request.getParameter("filePath");
	if (null != filePath) {
		filePath = Util.removeLastSegment(filePath);

	}
%>
<h1>
	<a href='downloadFile?filePath=<%=filePath%>'><span
		class="label label-primary">Up Directory</span></a>
</h1>
<br>
<hr>
<hr>
<br>