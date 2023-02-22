package commons;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.member.Member;

import static commons.MessageLibrary.*;

class MemberAccessControl {
	
	private HttpServletRequest request;
	private String requestURL;
	
	public MemberAccessControl(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		requestURL = request.getRequestURI(); // 현재 접속한 URL
		
		// 요청 메서드 
		String method = request.getMethod().toUpperCase();
		
		boolean isLogin = MemberLibrary.isLogin(request);
		
		// 비회원 접속 체크 - 비회원 전용 URL에 회원이 접속한 경우
		try {
			if (isGuestOnly() && isLogin && method.equals("GET")) { 
				throw new RuntimeException("비회원 전용 페이지 입니다.");
			}
			
			// 회원전용 페이지에 비회원이 접속한 경우 
			if (isMemberOnly() && !isLogin && method.equals("GET")) { 
				response.sendRedirect(UrlLibrary.getUrl(request, "/member/login"));
				return;
			}
			
			// 관리자 전용 페이지 접속 권한 체크 
			if (isAdminOnly()) {
				Member member = MemberLibrary.getLoginMember(request);
				// 비회원 이거나 일반회원에서 접속한 경우 
				if (member == null || !member.getUserType().equals("ADMIN")) {
					//throw new AdminAccessException();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			
			// 관리자 접근 통제가 아닌 경우(회원 접속 권한, 비회원 접속권한 
			if (!(e instanceof AdminAccessException)) {
				request.setAttribute("executeScript", true);
			}
			
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/errors/commons.jsp");
				rd.forward(request, response);

			} catch (Exception e2) {}
			
		}
	}
	 
	// 비회원 전용 URL 
	private String[] guestOnly = {
			"/member/login",
			"/member/join"
	};
	
	// 회원 전용  URL
	private String[] memberOnly = {
		"/mypage"
	};
	
	// 관리자 전용  URL 
	private String[] adminOnly = {
		"/admin"
	};
	
	// 현재 접속한  URL이 비회원 전용인지 체크 
	public boolean isGuestOnly() {
		
		for (String url : guestOnly) {
			if (requestURL.indexOf(url) != -1) { // 현재 URL에 비회원 경로가 포함된 경우
				return true;
			}
		}
		
		return false;
	}
	
	// 현재 URL이 회원 전용인지 체크 
	public boolean isMemberOnly() {
		for (String url : memberOnly) {
			if (requestURL.indexOf(url) != -1) { // 현재 URL에 회원 전용인 경우
				return true;
			}
		}
		return false;
	}
	
	// 현재 URL이 관리자 전용인경우 
	public boolean isAdminOnly() {
		for (String url : adminOnly) {
			if (requestURL.indexOf(url) != -1) {
				return true;
			}
		}
		
		return false;
 	}
}


