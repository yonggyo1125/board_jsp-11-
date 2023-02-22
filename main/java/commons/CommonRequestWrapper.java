package commons;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CommonRequestWrapper extends HttpServletRequestWrapper {

	public CommonRequestWrapper(HttpServletRequest request) {
		super(request);
		// 요청시 공통기능
		String method = request.getMethod().toUpperCase();
		if (method.equals("POST")) {
			// POST일때 한글 깨짐 방지 처리 
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 로그인 상태 
		request.setAttribute("isLogin", MemberLibrary.isLogin(request));
	}
	/**
	@Override
	public Cookie[] getCookies() {
		
		Cookie[] cookies = super.getCookies();
		try {
			for (Cookie cookie : cookies) {
				String value = AES256.decrypt(cookie.getValue());
				cookie.setValue(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cookies;
	}
	*/
	/**
	 * 쿠키 개별 조회 
	 * 
	 * @param name
	 * @return
	 */
	/**
	public String getCookie(String name) {
		name = String.valueOf(Objects.hash(name));
		
		for (Cookie cookie : getCookies()) {
			if (cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		
		return null;
	}
	*/
}
