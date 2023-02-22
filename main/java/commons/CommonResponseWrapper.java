package commons;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CommonResponseWrapper extends HttpServletResponseWrapper {

	public CommonResponseWrapper(HttpServletResponse response) {
		super(response);
		
		// 응답시 공통 기능 
		
	}
	/**
	@Override
	public void addCookie(Cookie cookie) {
		Cookie cookie2 = cookie;
		if (cookie != null) {
			try {
				int hash = Objects.hash(cookie.getName());
				String name = String.valueOf(hash);
				String value = AES256.encrypt(cookie.getValue());
				cookie2 = new Cookie(name, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		super.addCookie(cookie2);
	}
	*/
}
