package ma.formations.jpa.presentation.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/forgotPass.do")
public class PassForget extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display form for adding an article
        request.getRequestDispatcher("/view/ForgetPass.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        RequestDispatcher dispatcher = null;
        int otpValue = 0;
        HttpSession mySession = request.getSession();

        if (email != null && !email.trim().isEmpty()) {
            // Generate OTP
            Random rand = new Random();
            otpValue = rand.nextInt(1255650);

            // Email settings
            String to = email; // recipient email
            String from = "aymanechaouiya053@gmail.com"; // sender email
            String host = "smtp.gmail.com";
            String port = "465";
            String username = "aymanechaouiya053@gmail.com"; // replace with your email
            String password = "M0oouNxiiaZGp4TPC5Anb0lukvXqo"; // replace with your email password

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", port);

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Password Reset OTP");
                message.setText("Your OTP is: " + otpValue);

                Transport.send(message);
                System.out.println("Message sent successfully");

                // Set OTP and email in session
                mySession.setAttribute("otp", otpValue);
                mySession.setAttribute("email", email);

                request.setAttribute("message", "OTP has been sent to your email.");
                dispatcher = request.getRequestDispatcher("/view/EnterOtp.jsp");
            } catch (MessagingException e) {
                e.printStackTrace();
                request.setAttribute("message", "Failed to send OTP. Please try again.");
                dispatcher = request.getRequestDispatcher("/view/ForgetPass.jsp");
            }
        } else {
            request.setAttribute("message", "Please enter a valid email address.");
            dispatcher = request.getRequestDispatcher("/view/ForgetPass.jsp");
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
