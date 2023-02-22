package commons;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 공통 메세지(자바스크립트) 관련
 *
 */
public class MessageLibrary {
	
	/**
	 * 에러 전용 alert 
	 * @param {Exception} e
	 */
	public static void alertError(HttpServletResponse response, Exception e) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String message = e.getMessage();
			out.printf("<script>alert('%s');</script>", message);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void alertError(HttpServletResponse response, Exception e, String after) {
		alertError(response, e);
		
		/** 메세지 출력 후 후속 작업 */
		if (after.equals("back")) { // 다시 앞으로 이동 
			go(response, -1); 
		}
	}
	
	/**
	 * 페이지 이동 
	 * 
	 * @param response
	 * @param url
	 * @param target
	 */
	public static void go(HttpServletResponse response, String url, String target) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			target = target == null ? "self" : target;
			out.printf("<script>%s.location.replace('%s');</script>", target, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void go(HttpServletResponse response, String url) {
		go(response, url, "self");
	}
	
	/**
	 * 매개변수인 step 만큼 뒤로, 앞으로 이동 
	 * 
	 * @param response
	 * @param step
	 * @param target
	 */
	public static void go(HttpServletResponse response, int step, String target) {
		try {
			response.setContentType("text/html; charset=utf-8");
			target = target == null ? "self" : target;
			PrintWriter out = response.getWriter();
			out.printf("<script>%s.history.go(%d);</script>", target, step);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void go(HttpServletResponse response, int step) {
		go(response, step, "self");
	}
}
