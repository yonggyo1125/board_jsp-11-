package controllers.files;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.file.FileInfo;
import models.file.FileListService;
import models.file.FileServiceManager;

@WebServlet("/file/list/*")
public class FileListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			FileServiceManager manager = FileServiceManager.getInstance();
			FileListService service = manager.getFileListService();
			List<FileInfo> files = service.gets(req);
			System.out.println(files);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
	}
}
