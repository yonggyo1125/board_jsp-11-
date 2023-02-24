package controllers.files;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import commons.JSONResult;
import models.file.FileInfo;
import models.file.FileServiceManager;
import models.file.FileUploadService;

@WebServlet("/file/upload")
public class FileUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONResult<List<FileInfo>> result = new JSONResult<>();
		
		try {
			FileServiceManager manager = FileServiceManager.getInstance();
			FileUploadService service = manager.getFileUploadService();
			
			List<FileInfo> files = service.upload(req);
			result.setSuccess(true);
			result.setData(files);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		String json = om.writeValueAsString(result);
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(json);
	}
	
}
