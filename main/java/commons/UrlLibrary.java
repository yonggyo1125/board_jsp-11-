package commons;

import javax.servlet.http.HttpServletRequest;

public class UrlLibrary {
	/**
	 * URL 자동 완성(context  Path 자동으로 추가)
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getUrl(HttpServletRequest request, String url) {
		url = url == null ? "":url;
		return request.getContextPath() + url;
	}
	
	public static String getUrl(HttpServletRequest request) {
		return getUrl(request, null);
	}
}
