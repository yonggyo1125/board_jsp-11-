<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="java.io.*, java.util.*" %>
<%
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setSizeMax(1024 * 1024 * 20);// 1024 bytes -> 1kb -> 1024kb -> 1mb
	List<FileItem> items = upload.parseRequest(request);
	
	for (FileItem item : items) {
		String fieldName = item.getFieldName();
		if (item.isFormField()) {  // 일반 양식 데이터 
			String value = item.getString("UTF-8");
			System.out.printf("%s=%s%n", fieldName, value);
		} else { // 파일 데이터
			//item.write(new File("D:\\uploads/1"));
			String fileName = item.getName(); // 전체 경로명을 포함한 파일이름 C:\, /data/
			// File.separator - OS에 상관없이 디렉토리 구분자 (\, /) 
			String name = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
			name = System.currentTimeMillis() + "_" + name;
			item.write(new File("D:\\uploads\\" + name));

			String contentType = item.getContentType();
			System.out.printf("fileName=%s, fieldName=%s, contentType=%s%n",
					fileName, fieldName, contentType);
			
		}
	}
%>