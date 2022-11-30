package org.iesfm.newspaper.service;

import org.iesfm.newspaper.dao.ArticleDAO;
import org.iesfm.newspaper.dao.SectionDAO;
import org.iesfm.newspaper.entity.Article;
import org.iesfm.newspaper.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private SectionDAO sectionDAO;

    public List<Article> articleList(int idSection, String author){
        return articleDAO.listSectionArticles(idSection, author);
    }
    public List<Article> list(){
        return articleDAO.list();
    }
    public Article getArticle(int id){
        return articleDAO.getArticle(id);
    }
    public boolean addArticle(Article article){
        return articleDAO.add(article);
    }
    public boolean deleteArticle(int id){
        return articleDAO.delete(id);
    }
    public boolean updateArticle(int id, Article article){
        return articleDAO.update(id, article);
    }

    public List<Section> sectionList(){
        return sectionDAO.list();
    }

    public Section getSection(int id){
        return sectionDAO.getSection(id);
    }

    public boolean addSection(Section section){
        return sectionDAO.add(section);
    }

    public boolean deleteSection(int id){
        return sectionDAO.delete(id);
    }

    public boolean updateSection(int id, Section section){
        return sectionDAO.update(id, section);
    }

}
