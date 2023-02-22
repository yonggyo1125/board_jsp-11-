package controllers.admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.UrlLibrary;
import models.board.BoardConfig;
import models.board.BoardConfigSaveService;
import models.board.BoardServiceManager;
import static commons.MessageLibrary.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * /admin/board/config -> 설정 등록 
 * /admin/board/config/게시판 아이디  -> 설정 수정 
 *
 */
@WebServlet("/admin/board/config/*")  // * -> 게시판 아이디 
public class BoardConfigController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] addScript = { "ckeditor/ckeditor", "admin/config" }; 
		req.setAttribute("menuCode", "boardRegister");
		req.setAttribute("addScript", addScript);
		
		String requestURI = req.getRequestURI();
		String patternStr = "board/config/([^\\?]*)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(requestURI);
		if (matcher.find()) {
			String id = matcher.group(1);
			if (id != null && !id.isBlank()) { // 게시판 아이디가 있는경우 수정, 없는 경우는 추가
				try {
					BoardConfig boardConfig = BoardServiceManager.getInstance()
																		.getBoardConfigInfoService().get(id);
					
					req.setAttribute("boardConfig", boardConfig);
				} catch (RuntimeException e) {
					e.printStackTrace();
					alertError(resp, e, "back");
					return;
				}
			}
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/admin/board/config.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BoardServiceManager manager = BoardServiceManager.getInstance();
			BoardConfigSaveService service = manager.getBoardConfigSaveService();
			service.save(req);
			
			// 게시판 설정 저장 완료 -> 게시판 설정 목록 이동 
			String url = UrlLibrary.getUrl(req, "/admin/board");
			go(resp, url, "parent");
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			alertError(resp, e);
		}
	}
}
