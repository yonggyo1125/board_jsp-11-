package commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import models.member.Member;

public class MemberLibrary {
	
	/** 
	 * 로그인 상태 체크 
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		Member member = getLoginMember(request);
		
		return member != null; // null이 아니면 로그인, null이면 미 로그인
	}
	
	/**
	 * 로그인한 회원 정보
	 * 
	 * @param request
	 * @return
	 */
	public static Member getLoginMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		return member;
	}
}
