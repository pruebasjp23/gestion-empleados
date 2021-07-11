/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.controladores;

import com.asinfo.gestion.empleados.dao.SupervisorDao;
import com.asinfo.gestion.empleados.modelos.Supervisor;
import java.util.List;

/**
 *
 * @author jorge
 */
public class SupervisorServicio {

    private final SupervisorDao dao = new SupervisorDao();
    
    
    public List<Supervisor> buscarSupervisoresActivos() throws Exception {
        return dao.buscarSupervisoresActivos();
    }
    
}
