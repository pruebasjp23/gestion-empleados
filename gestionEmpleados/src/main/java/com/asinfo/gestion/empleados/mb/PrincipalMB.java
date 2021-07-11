/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.mb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jorge
 */
@ManagedBean(name = "principalMB")
@ViewScoped
public class PrincipalMB implements Serializable {
 
    public String gestionEmpleados(){
        return "empleados/listaEmpleados.xhtml";
    }
}
