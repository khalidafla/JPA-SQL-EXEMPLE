/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Khalid
 */
public class DaoException extends Exception{
    
    public DaoException (){};
    
    public DaoException(String msg){
        super(msg);
    }
    
    public DaoException(Throwable cause){
        super(cause);
    }
    
    public DaoException(String message, Throwable cause){
        super(message, cause);
    }
}
