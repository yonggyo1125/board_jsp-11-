package controllers.files;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.file.FileDownloadService;
import models.file.FileServiceManager;
import static commons.MessageLibrary.*;

@WebServlet("/file/download/*")
public class FileDownloadController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			FileServiceManager manager = FileServiceManager.getInstance();
			FileDownloadService service = manager.getFileDownloadService();
			service.download(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			alertError(resp, e, "back");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
