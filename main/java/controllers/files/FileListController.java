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
import models.file.FileInfoNotFoundException;
import models.file.FileListService;
import models.file.FileServiceManager;

@WebServlet("/file/list/*")
public class FileListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		JSONResult<List<FileInfo>> result = new JSONResult<>();
		
		try {
			FileServiceManager manager = FileServiceManager.getInstance();
			FileListService service = manager.getFileListService();
			List<FileInfo> files = service.gets(req);
			if (files == null || files.size() == 0) {
				throw new FileInfoNotFoundException();
			}
			
			//  성공시 처리
			result.setSuccess(true);
			result.setData(files);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			
			// 실패시 처리 
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
