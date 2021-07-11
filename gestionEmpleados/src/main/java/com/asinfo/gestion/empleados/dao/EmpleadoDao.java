/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.dao;

import com.asinfo.gestion.empleados.modelos.Empleado;
import com.asinfo.gestion.empleados.util.Persistencia;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author jorge
 */
public class EmpleadoDao extends Persistencia {
    
    public List<Empleado> buscarEmpleados() throws Exception {
        try{
            getEntityManager();
            return em.createQuery("Select e From Empleado e").getResultList();
        }catch(Exception exc){
            exc.printStackTrace();
            throw new Exception(exc);
        }
        finally{
            closeEntityManager();
        }
    }
    
    public Empleado guardarEmpleado(Empleado empleado) throws Exception {
        try{
            getEntityManager();
            em.getTransaction().begin();
            
            System.out.println(empleado.getSupervisor().getCodigo());
            System.out.println(empleado.getDepartamento().getCodigo());
            
            if(empleado.getCodigo() != null)
                em.merge(empleado);
            else
                em.persist(empleado);
            
            em.getTransaction().commit();
            
            return empleado;
        }catch(Exception exc){
            rollbackTransaction();
            exc.printStackTrace();
            throw new Exception(exc);
        }
        finally{
            closeEntityManager();
        }
    }
    
    public void actualizarSueldo(Empleado empleado) throws Exception {
        try{
            getEntityManager();
            em.getTransaction().begin();
            
            em.merge(empleado);
            
            em.getTransaction().commit();
            
        }catch(Exception exc){
            rollbackTransaction();
            exc.printStackTrace();
            throw new Exception(exc);
        }
        finally{
            closeEntityManager();
        }
    }
    
    
        public Connection getConexion() throws Exception {
        try {
            getEntityManager();
            em.getTransaction().begin();

            Connection con = em.unwrap(Connection.class);

            em.getTransaction().commit();

            return con;
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception(exc);
        }
    }

    public void cerrarConexion(Connection con) throws Exception {
        try {
            closeEntityManager();
            if(con != null)
                con.close();
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception(exc);
        }
    }
}
