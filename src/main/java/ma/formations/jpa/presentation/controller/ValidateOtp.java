package ma.formations.jpa.presentation.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/validateOtp")
public class ValidateOtp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Display the OTP validation form
		request.getRequestDispatcher("/view/EnterOtp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int value = Integer.parseInt(request.getParameter("otp"));
		HttpSession session = request.getSession();
		int otp = (int) session.getAttribute("otp");

		RequestDispatcher dispatcher = null;

		if (value == otp) {
			request.setAttribute("message", "OTP verified successfully.");
			dispatcher = request.getRequestDispatcher("/view/newPassword.jsp");
		} else {
			request.setAttribute("message", "Invalid OTP. Please try again.");
			dispatcher = request.getRequestDispatcher("/view/EnterOtp.jsp");
		}
		dispatcher.forward(request, response);
	}
}
