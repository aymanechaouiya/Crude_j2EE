package ma.formations.jpa.presentation.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.formations.jpa.dao.DaoImplJPA;
import ma.formations.jpa.dao.IDao;
import ma.formations.jpa.service.model.User;
import ma.formations.jpa.utils.PasswordHasher;

@WebServlet("/signup.do")
public class signupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IDao Dao = new DaoImplJPA();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username already exists
        User existingUserByUsername = Dao.getUserByUsername(username);
        if (existingUserByUsername != null) {
            request.setAttribute("SignupError", "Username already exists!");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        // Check if the email already exists
        User existingUserByEmail = Dao.getUserByEmail(email);
        if (existingUserByEmail != null) {
            request.setAttribute("SignupError", "Email already exists!");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        String hashedPassword = PasswordHasher.hashPassword(password);

        User user = new User(email, username, hashedPassword);
        Dao.save(user);
        // Redirect to login page with success message
        response.sendRedirect(request.getContextPath() + "/login.do?signupSuccess=true");
    }
}
