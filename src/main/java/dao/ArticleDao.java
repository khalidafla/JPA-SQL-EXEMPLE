/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Article;
import beans.Comment;
import java.util.List;
import java.util.Set;
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
public class ArticleDao extends AbstractDao<Article> {

    /* Puisque le type de transaction qu'ont utilise est JPA on aura pas besoin de
    manipuler les transactions vu que tous le travail est gerer par le conteneur, 
    c'est pour cette raison que vous aller trouver les transaction commenter*/
    @PersistenceContext(unitName = "JPATest")
    private EntityManager em;

    @Override
    public Long ajouter(Article article) throws DaoException {
        //em.getTransaction().begin();
        em.persist(article);
        //em.getTransaction().commit();
        return article.getId();
    }

    @Override
    public void modifier(Article article) throws DaoException {
        try {
            //em.getTransaction().begin();
            try {
                em.getReference(Article.class, article.getId());
            } catch (EntityNotFoundException enfe) {
                throw new DaoException("L'article avec id = " + article.getId() + " n'existe plus dans la BD");
            }
            Set<Comment> comments = article.getComments();
            em.merge(article);
            for(Comment comm : comments){
                em.getReference(Comment.class, comm.getId());
                comm.setArticle(article);
                em.merge(comm);
            }
            //em.getTransaction().commit();
        } catch (EntityNotFoundException ene){
            throw new DaoException("Un des commentaires de l'article que vous essayer de modifier n'existe pas dans la BD");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void supprimer(Long id) throws DaoException {
        Article article;
        try {
            //em.getTransaction().begin();
            try {
                article = em.getReference(Article.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new DaoException("L'article avec id = " + id + " n'existe plus dans la BD");
            }
            for (Comment com : article.getComments()) {
                em.remove(com);
            }
            em.remove(article);
            //em.getTransaction().commit();
        } catch (EntityNotFoundException ne) {
            throw new DaoException("Un des commentaires de l'article que vous essayer de supprimer n'existe pas dans la BD");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Article trouver(Long id) throws DaoException {
        Article article;
        try {
            //em.getTransaction().begin();
            article = em.find(Article.class, id);
            //em.getTransaction().commit();
            return article;
        } catch (EntityNotFoundException enfe) {
            throw new DaoException("L'article avec id = " + id + " n'existe plus dans la BD");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Article> trouver(int premierResultat, int maxResultats) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Article.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(maxResultats);
        q.setFirstResult(premierResultat);
        return q.getResultList();
    }

    @Override
    public List<Article> trouverTous() {
        String jpql = "SELECT a FROM Article a";
        return em.createNamedQuery(jpql, Article.class).getResultList();
    }

    @Override
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Article> rt = cq.from(Article.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Article> trouver(String condition) throws DaoException {
        try {
            String jpql = "SELECT article FROM Article article WHERE " + condition;
            return em.createQuery(jpql, Article.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
