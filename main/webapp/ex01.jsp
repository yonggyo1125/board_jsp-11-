<%@ page contentType="text/html; charset=utf-8" %>
<form method="post" action="ex01_ps.jsp" enctype="multipart/form-data">
	<input type="text" name="userId">
	<input type="password" name="userPw">
	<input type="file" name="file">
	<button type="submit">로그인!</button>
</form>

<script>
window.addEventListener("DOMContentLoaded", function() {
	const data = { userId: "user01", userPw : 12345678 };
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "ex01_ps.jsp");
	xhr.setRequestHeader("content-type", "application/json; charset=utf-8");
	xhr.onreadystatechange = function() {
		if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
			console.log(xhr.responseText);
		}	
	};
	xhr.send(JSON.stringify(data));
});
</script>
