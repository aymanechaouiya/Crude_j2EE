package ma.formations.jpa.presentation.controller;

import java.io.IOException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import ma.formations.jpa.service.IService;
import ma.formations.jpa.dao.IDao;
import ma.formations.jpa.service.ServiceImpl;
import ma.formations.jpa.service.model.Article;

import ma.formations.jpa.service.model.User;

@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view/newPassword.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String newPassword = request.getParameter("password");
		String confPassword = request.getParameter("confPassword");
		String email = (String) session.getAttribute("email");
		RequestDispatcher dispatcher = null;

		if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
			IService userService = new ServiceImpl();
			User user = userService.getUserByEmail(email);
			if (user != null) {
				user.setPassword(newPassword); // Assuming User has a setPassword method
				userService.save(user); // Assuming save method updates if user exists

				request.setAttribute("status", "resetSuccess");
				dispatcher = request.getRequestDispatcher("/view/login.jsp");
			} else {
				request.setAttribute("status", "resetFailed");
				dispatcher = request.getRequestDispatcher("/view/newPassword.jsp");
			}
		} else {
			request.setAttribute("status", "resetFailed");
			dispatcher = request.getRequestDispatcher("/view/newPassword.jsp");
		}
		dispatcher.forward(request, response);
	}
}
