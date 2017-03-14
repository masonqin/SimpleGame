package game.controller;

import game.dao.UserDAO;
import game.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//User user = new User();
		UserDAO userdao = new UserDAO();
		
		PrintWriter out = response.getWriter();
		
		int userid = userdao.checkUser(request.getParameter("log_email"),request.getParameter("log_password"));
		if(userid>0) {
			//user = userdao.getUserById(res);
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			if(userid==1)
				response.sendRedirect("/SimpleGame/Admin.jsp");
				
			else
				response.sendRedirect("/SimpleGame/UserInfo.jsp");
			return;
				
		}
		response.sendRedirect("/SimpleGame/Failed.jsp");	
	}

}
