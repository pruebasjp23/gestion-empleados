/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.dao;

import com.asinfo.gestion.empleados.modelos.Departamento;
import com.asinfo.gestion.empleados.util.Persistencia;
import java.util.List;

/**
 *
 * @author jorge
 */
public class DepartamentoDao extends Persistencia{
    
    public List<Departamento> buscarDepartamentosActivos() throws Exception {
        try{
            getEntityManager();
            return em.createQuery("Select d From Departamento d Where d.estado = true").getResultList();
        }catch(Exception exc){
            exc.printStackTrace();
            throw new Exception(exc);
        }
        finally{
            closeEntityManager();
        }
    }
    
}
