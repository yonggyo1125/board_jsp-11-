package commons;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		CommonRequestWrapper _request = new CommonRequestWrapper((HttpServletRequest)request);
		CommonResponseWrapper _response = new CommonResponseWrapper((HttpServletResponse)response);
		
		// 비회원, 회원, 관리자 접속 통제 처리 
		new MemberAccessControl(_request, _response);
				
		chain.doFilter(_request,_response);
		
		
	
	}
}
