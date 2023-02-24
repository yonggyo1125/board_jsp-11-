<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="<c:url value='/file/upload' />" enctype="multipart/form-data">
	<input type="hidden" name="gid" value="testgid">
	<input type="file" name="file" multiple>
	<button type="submit">업로드</button>
</form>