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

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		UserDAO userdao = new UserDAO();
		User user = new User();
		
		PrintWriter out = response.getWriter();
		
		if(userdao.checkUser(request.getParameter("email"))) {
			out.println("user already exits");
		}
		else {
			user.setFirstName(request.getParameter("firstname"));			
			user.setLastName(request.getParameter("lastname"));
			try {
	            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
	            user.setDob(dob);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			System.out.println(user.getDob());
			
			userdao.addUser(user);	
			out.println("registe success");
		}
	}

}
