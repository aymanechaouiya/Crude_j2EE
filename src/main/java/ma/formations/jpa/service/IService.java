package ma.formations.jpa.service;
import java.util.List;
import ma.formations.jpa.service.model.Article;
import ma.formations.jpa.service.model.User;

public interface IService {

 Boolean checkAccount(String username,String password);
 List<Article> getAllArticle();

 void saveArticle(Article article);

 Article getArticleById(Long id);

 void updateArticle(Article article);

 void deleteArticle(Long id);
 User getUserByEmail(String email);
 void save(User user);


}
