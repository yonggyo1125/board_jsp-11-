<%@ page contentType="text/html; charset=utf-8" %>
<form method="post" action="ex02_ps.jsp" enctype="multipart/form-data">
	<input type="text" name="userId">
	<input type="password" name="userPw">
	<input type="file" name="file" multiple>
	<button type="submit">파일 전송</button>
</form>
<%
	String path = application.getRealPath(".");
	out.print(path);
%>