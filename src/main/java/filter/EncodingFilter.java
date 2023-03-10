package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 가로채서 인코딩 먼저 실행
    	if(request instanceof HttpServletRequest) {
    		HttpServletRequest req = (HttpServletRequest)request;
    		if(req.getMethod().equals("POST")) {
    			request.setCharacterEncoding("UTF-8");    			
    			System.out.println("인코딩 utf-8 필터");
    		}
    	}    	
    	chain.doFilter(request, response);
	}
}
