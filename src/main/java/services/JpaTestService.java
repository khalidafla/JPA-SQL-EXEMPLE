/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.Article;
import beans.Comment;
import dao.ArticleDao;
import dao.CommentDao;
import dao.DaoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Khalid
 */
@Stateless
public class JpaTestService {
    
    @EJB
    private CommentDao commentDao;
    
    @EJB
    private ArticleDao articleDao;

    public void ajouterCommentair(Comment comment) {
        try {
            commentDao.ajouter(comment);
        } catch (DaoException ex) {
            Logger.getLogger(JpaTestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouterArtcle(Article article) {
        try {
            articleDao.ajouter(article);
        } catch (DaoException ex) {
            Logger.getLogger(JpaTestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Article> listerArticle() {
        return articleDao.trouverTous();
    }

    public List<Comment> listComment() {
        return commentDao.trouverTous();
    }

}
