/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewBeans;

import beans.Article;
import beans.Comment;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.joda.time.DateTime;
import services.JpaTestService;

/**
 *
 * @author Khalid
 */
@ManagedBean
@ViewScoped
public class JpaTest {
    
    @EJB
    private JpaTestService service;
    
    private String etat;
    
    @PostConstruct
    public void init(){
        
        Article article = new Article();
        article.setContenu("Le contenu de l'article");
        article.setTitre("Le titre de l'article");
        article.setDatePublication(new DateTime());  
        
        service.ajouterArtcle(article);
        
        Comment com1 = new Comment();
        com1.setContenu("Le premier commentaire");
        com1.setArticle(article);
        Comment com2 = new Comment();
        com2.setContenu("Le deuxieme commentaire");
        com2.setArticle(article);
        Comment com3 = new Comment();
        com3.setContenu("Le troisième commentaire");
        com3.setArticle(article);
        
        service.ajouterCommentair(com1);
        service.ajouterCommentair(com2);
        service.ajouterCommentair(com3);
        
        etat = "Les données sont bien enregistrer dans la base de données";
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
