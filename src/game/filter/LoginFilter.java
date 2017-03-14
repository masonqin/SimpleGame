package game.filter;

import game.dao.UserDAO;
import game.model.User;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({"/UserInfo.jsp","/Plantform.jsp","/Room.jsp"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Load filter");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		//System.out.println("Destroy filter");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		//System.out.println("Do filter");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession ses = req.getSession();
		if(ses.getAttribute("userid") != null) {
			int userid = (int)ses.getAttribute("userid");
			if(userid>0) {
				UserDAO userdao = new UserDAO();
				User user = userdao.getUserById(userid);
				request.setAttribute("userinfo", user);
				chain.doFilter(request, response);	
				return;
			}				
		} 
		((HttpServletResponse) response).sendRedirect("/SimpleGame/Failed.jsp");
	

		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		//System.out.println("Init filter");
	}

}
