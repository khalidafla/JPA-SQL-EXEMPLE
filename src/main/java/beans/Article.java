/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;
import utils.JodaDateTimeConverter;

/**
 *
 * @author Khalid
 */
@Entity
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "titre", length = 50)
    private String titre;

    @Column(name = "contenu", columnDefinition = "TEXT")
    private String contenu;

    @Column(name = "date_publication", columnDefinition = "TIMESTAMP")
    @Converter(name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class)
    @Convert("dateTimeConverter")
    private DateTime datePublication;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Set<Comment> getComments() {
        if (comments == null) {
            comments = new HashSet<>();
        }
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public DateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(DateTime datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Article[ id=" + id + " ]";
    }

}
