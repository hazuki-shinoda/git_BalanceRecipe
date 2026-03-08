/*
 * LoginCheckFilter
 * セッションにデータが入っているか調べる
 * 		ログイン済みである、または、ログインなしで見られる特定のサーブレットあれば進む
 * 		そうでなければLoginサーブレットへ*/

package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*") 
public class LoginCheckFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String uri = httpRequest.getRequestURI();

        boolean isAllowPage = uri.endsWith("Login") || 
                              uri.endsWith("ExecuteLogin") || 
                              uri.endsWith("RegisterSurvey") || 
                              uri.contains("/jsp/register.jsp") || 
                              uri.contains("/css/") || 
                              uri.contains("/js/");

        boolean isLoggedIn = (session != null && session.getAttribute("LOGIN_INFO") != null);
        
        if (isLoggedIn || isAllowPage) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login");
        }
    }
}