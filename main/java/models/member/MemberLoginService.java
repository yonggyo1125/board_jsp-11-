package models.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import commons.validator.Validator;

/**
 * 로그인 기능
 *
 */
public class MemberLoginService {
	private MemberDao memberDao;
	private Validator<Member> validator;
	
	public MemberLoginService(MemberDao memberDao, Validator<Member> validator) {
		this.memberDao = memberDao;
		this.validator = validator;
	}
	
	/**
	 * 로그인 기능 
	 * 
	 * 유효성 검사
	 * 1. 필수 항목 체크(userId, userPw)
	 * 2. userId로 회원이 있는지 체크
	 * 3. 회원이 있다면 비밀번호 검증
	 * 
	 * 로그인 처리
	 * 4. 비밀번호가 검증 완료된 경우 로그인 처리(HttpSession)
	 * 5. 아이디 저장이 있거나 없는 경우 처리
	 * 		 - 아이디 저장이 있는 경우 쿠키에 등록(365일 - 만료시간)
	 *       - 아이디 저장이 없는 경우 쿠키 삭제
	 */
	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		// 유효성 검사 S 
		Member member = new Member();
		member.setUserId(request.getParameter("userId"));
		member.setUserPw(request.getParameter("userPw"));
		
		if (validator != null) {
			validator.check(member);
		}
		
		// 유효성 검사 E
		
		// 4. 비밀번호가 검증 완료된 경우 로그인 처리(HttpSession)
		Member member2 = memberDao.get(member.getUserId());
		HttpSession session = request.getSession();
		session.setAttribute("member", member2);
		
		// 5. 아이디 저장이 있거나 없는 경우 처리
		Cookie saveId = new Cookie("saveId", member.getUserId());
		if (request.getParameter("saveId") != null) { 
			// 아이디 쿠키 저장 
			saveId.setMaxAge(60 * 60 * 24 * 365);
		} else {
			// 아이디 쿠키 제거 
			saveId.setMaxAge(0);
		}
		
		response.addCookie(saveId);
	}
 }


