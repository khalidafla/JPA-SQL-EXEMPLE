/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Khalid
 * @param <T>
 */

public abstract class AbstractDao<T> {
    
    public abstract Long ajouter(T generic) throws DaoException;
    public abstract void modifier(T generic) throws DaoException;
    public abstract void supprimer(Long id) throws DaoException;
    public abstract T trouver(Long id) throws DaoException;
    public abstract List<T> trouver(int premierResultat, int maxResultats);
    public abstract List<T> trouver(String condition) throws DaoException;
    public abstract List<T> trouverTous();
    public abstract int count();
    
//    public Query trouverMeta(String jpql){
//        return em.createQuery(jpql);
//    }
}
