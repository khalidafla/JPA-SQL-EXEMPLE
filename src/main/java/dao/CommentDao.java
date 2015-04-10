/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Article;
import beans.Comment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Khalid
 */
@Stateless
public class CommentDao extends AbstractDao<Comment> {
    
    @PersistenceContext(unitName = "JPATest")
    private EntityManager em;

    @Override
    public Long ajouter(Comment comment) throws DaoException {
        try {
            //em.getTransaction().begin();
            if (comment.getArticle() != null) {
                em.getReference(Article.class, comment.getArticle().getId());
            }else{
                throw new DaoException("L'objet article referencer par commentaire est null");
            }
            em.persist(comment);
            //em.getTransaction().commit();
            return comment.getId();
        } catch (EntityNotFoundException ne) {
            throw new DaoException("L'article don't id = " + comment.getArticle().getId() + " n'existe pas dans la BD");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void modifier(Comment comment) throws DaoException {
        try {
            //em.getTransaction().begin();
            try {
                em.getReference(Comment.class, comment.getId());
            } catch (EntityNotFoundException een) {
                throw new DaoException("Le commentaire don't id = " + comment.getId() + "n'existe pas dans la BD");
            }
            if (comment.getArticle() != null) {
                em.getReference(Article.class, comment.getArticle().getId());
            }
            em.merge(comment);
            //em.getTransaction().commit();
        } catch (EntityNotFoundException ne) {
            throw new DaoException("L'article don't id = " + comment.getArticle().getId() + " n'existe pas dans la BD");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void supprimer(Long id) throws DaoException {
        Comment comment;
        try {
            //em.getTransaction().begin();
            try {
                comment = em.find(Comment.class, id);
            } catch (EntityNotFoundException een) {
                throw new DaoException("Le commentaire don't id = " + id + "n'existe pas dans la BD");
            }
            if (comment.getArticle() != null) {
                em.getReference(Article.class, comment.getArticle().getId());
            }
            em.remove(comment);
            //em.getTransaction().commit();
        } catch (EntityNotFoundException ne) {
            throw new DaoException("L'article du commentaire n'existe pas");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Comment trouver(Long id) throws DaoException {
        Comment comment;
        try {
            //em.getTransaction().begin();
            comment = em.find(Comment.class, id);
            //em.getTransaction().commit();
            return comment;
        } catch (EntityNotFoundException ne) {
            throw new DaoException("Le commentaire don't id = " + id + "n'existe pas dans la BD");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Comment> trouver(int premierResultat, int maxResultats) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Comment.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(maxResultats);
        q.setFirstResult(premierResultat);
        return q.getResultList();
    }

    @Override
    public List<Comment> trouver(String condition) throws DaoException {
        try {
            String jpql = "SELECT comment FROM Comment comment WHERE " + condition;
            return em.createQuery(jpql, Comment.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Comment> trouverTous() {
        String jpql = "SELECT c FROM Comment c";
        return em.createNamedQuery(jpql, Comment.class).getResultList();
    }

    @Override
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Article> rt = cq.from(Comment.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
