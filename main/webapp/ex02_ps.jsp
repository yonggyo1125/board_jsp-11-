<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="java.io.*, java.util.*" %>
<%
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	List<FileItem> items = upload.parseRequest(request);
	
	for (FileItem item : items) {
		if (!item.isFormField()) { // 파일 데이터 
			//item.write(new File("D:\\uploads/1"));
			String fileName = item.getName(); // 전체 경로명을 포함한 파일이름 
			String fieldName = item.getFieldName();
			String contentType = item.getContentType();
			System.out.printf("fileName=%s, fieldName=%s, contentType=%s%n",
					fileName, fieldName, contentType);
			
		}
	}
%>