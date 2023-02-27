package controllers.files;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import commons.JSONResult;
import models.file.FileDeleteService;
import models.file.FileServiceManager;

@WebServlet("/file/delete")
public class FileDeleteController extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONResult<Object> result = new JSONResult<>();
		try {
			FileServiceManager manager = FileServiceManager.getInstance();
			FileDeleteService service = manager.getFileDeleteService();
			service.delete(req);
			
			result.setSuccess(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(result);
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(json);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDelete(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDelete(req, resp);
	}
	
}
