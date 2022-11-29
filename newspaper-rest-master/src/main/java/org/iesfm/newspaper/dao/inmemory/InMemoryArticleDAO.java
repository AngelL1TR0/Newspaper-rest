package org.iesfm.newspaper.dao.inmemory;

import org.iesfm.newspaper.dao.ArticleDAO;
import org.iesfm.newspaper.entity.Article;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryArticleDAO implements ArticleDAO {
    private Map<Integer, Article> articles = new HashMap<>();


    @Override
    public List<Article> list() {
        return new LinkedList<>(articles
                .values());
    }

    @Override
    public List<Article> listSectionArticles(int idSection, String author) {
        return articles
                .values()
                .stream()
                .filter(a -> a.getSectionId() == idSection && (author == null || author.equals(a.getAuthor())))
                .collect(Collectors.toList());
    }

    @Override
    public Article getArticle(int id) {
        return articles.get(id);
    }

    @Override
    public boolean add(Article article) {
        return articles.putIfAbsent(article.getId(), article) == null;
    }

    @Override
    public boolean delete(int articleId) {
        return articles.remove(articleId) != null;
    }

    @Override
    public boolean update(int id, Article article) {
        if (articles.containsKey(id)) {
            articles.remove(id);
            articles.put(article.getId(), article);
            return true;
        } else {
            return false;
        }
    }
}
