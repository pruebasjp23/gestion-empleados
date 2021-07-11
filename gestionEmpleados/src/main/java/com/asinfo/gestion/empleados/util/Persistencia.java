/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.util;

import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class Persistencia {
    protected EntityManager em;
    
    protected void getEntityManager() throws Exception {
        try {
            if(UtilPersistencia.emf == null){
                UtilPersistencia.crearEntityManagerFactory();
            }
            
            em = UtilPersistencia.emf.createEntityManager();
            
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception(exc);
        }
    }

   
    protected void closeEntityManager() throws Exception {
        try{
            if(em !=null && em.isOpen()){
                em.clear();
                em.close();
            }
            
            em = null;
            
        }catch(IllegalStateException exc){
            exc.printStackTrace();
            throw new Exception(exc);
        }catch(Exception exc){
            exc.printStackTrace();
            throw new Exception(exc);
        }
        
    }
    
   
    protected void rollbackTransaction() {
        if(em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
    
}
