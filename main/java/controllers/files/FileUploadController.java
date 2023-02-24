package controllers.files;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.file.FileInfo;
import models.file.FileServiceManager;
import models.file.FileUploadService;

@WebServlet("/file/upload")
public class FileUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			FileServiceManager manager = FileServiceManager.getInstance();
			FileUploadService service = manager.getFileUploadService();
			
			List<FileInfo> files = service.upload(req);
			System.out.println(files);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
