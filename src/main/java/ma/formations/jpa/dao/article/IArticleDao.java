package ma.formations.jpa.dao.article;
import java.util.List;
import ma.formations.jpa.service.model.Article;
public interface IArticleDao {

    List<Article> findAll();
    void save(Article article);
    void delete(Long id);

    void deleteAll();

    Article findById(Long id);
}
