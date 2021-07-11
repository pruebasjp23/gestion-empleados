/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.controladores;

import com.asinfo.gestion.empleados.dao.DepartamentoDao;
import com.asinfo.gestion.empleados.modelos.Departamento;
import java.util.List;

/**
 *
 * @author jorge
 */
public class DepartamentoServicio {

    
    private final DepartamentoDao dao = new DepartamentoDao();
    
    
    public List<Departamento> buscarDepartamentosActivos() throws Exception {
        return dao.buscarDepartamentosActivos();
    }
    
}
