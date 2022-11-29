package org.iesfm.newspaper.dao;

import org.iesfm.newspaper.entity.Article;

import java.util.List;

public interface ArticleDAO {

    /**
     * Lista los artículos de una sección. Si el parámetro author no es null se filtra por autor
     * @param idSection
     * @param author
     * @return
     */
    List<Article> listSectionArticles(int idSection, String author);

    /**
     * Devuelve todos los artículos del periódico
     * @return
     */
    List<Article> list();

    /**
     * Devuelve un artículo
     * @param id
     * @return
     */
    Article  getArticle(int id);

    /**
     * Guarda un nuevo artículo. Si ya existía el artículo devuelve false
     * @param article
     * @return
     */
    boolean add(Article article);

    /**
     * Elimina un artículo. Si no existía el artículo devuelve false
     * @param articleId
     * @return
     */
    boolean delete(int articleId);

    /**
     * Actualiza el artículo. Si no existía un artículo con el id proporcionado devuelve false.
     * @param id
     * @param article
     * @return
     */
    boolean update(int id, Article article);

}
