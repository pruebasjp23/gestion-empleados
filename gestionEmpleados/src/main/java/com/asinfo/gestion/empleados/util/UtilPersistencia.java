/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jorge
 */
public class UtilPersistencia {
    
    
    public static EntityManagerFactory emf;
    
    public static void crearEntityManagerFactory(){
        emf = Persistence.createEntityManagerFactory(Constantes.UNIDAD_PERSISTENCIA);
    }
    
}
