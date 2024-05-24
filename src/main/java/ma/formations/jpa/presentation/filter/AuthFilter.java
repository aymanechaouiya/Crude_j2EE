package ma.formations.jpa.presentation.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"*.do"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // Allow access to signup and forgot password pages without login
        if (requestURI.endsWith("signup.do") || requestURI.endsWith("/forgotPass.do") || requestURI.endsWith("/validateOtp.do" )||requestURI.endsWith("/newPassword.do")) {
            chain.doFilter(request, response);
            return;
        }

        // Check if the user is logged in
        if (null != httpRequest.getSession().getAttribute("username")) {
            chain.doFilter(request, response);
        } else {
            // Redirect to login page if not logged in
            request.getRequestDispatcher("/login.do").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
