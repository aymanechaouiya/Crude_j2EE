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

@WebServlet("/addArticle.do")
public class AddArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IService service = new ServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display form for adding an article
        request.getRequestDispatcher("/view/addArticle.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        Double quantity = Double.parseDouble(request.getParameter("quantity"));
        Double price = Double.parseDouble(request.getParameter("price"));

        Article article = new Article();
        article.setDescription(description);
        article.setQuantite(quantity);
        article.setPrice(price);

        service.saveArticle(article);

        // Redirect to ArticleServlet after adding the article
        response.sendRedirect("articles.do");
    }
}
