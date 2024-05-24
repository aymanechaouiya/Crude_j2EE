package ma.formations.jpa.presentation.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.formations.jpa.service.IService;
import ma.formations.jpa.service.ServiceImpl;
import ma.formations.jpa.service.model.Article;

@WebServlet("/updateArticle.do")
public class UpdateArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IService service = new ServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Article article = service.getArticleById(id);
        request.setAttribute("article", article);
        request.getRequestDispatcher("/view/updateArticle.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String description = request.getParameter("description");
        Double quantity = Double.parseDouble(request.getParameter("quantity"));
        Double price = Double.parseDouble(request.getParameter("price"));

        Article article = service.getArticleById(id);
        article.setDescription(description);
        article.setQuantite(quantity);
        article.setPrice(price);

        service.updateArticle(article);

        response.sendRedirect("articles.do");
    }
}
