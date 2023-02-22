<%
response.setHeader("Content-Disposition", "attachment; filename=test.txt");
response.setHeader("Content-Type", "application/octet-stream");
response.setHeader("Cache-Control", "must-revalidate");
response.setHeader("Pragma", "public");
response.setIntHeader("Expires", 0);
%>
test1
test2
test3
test4