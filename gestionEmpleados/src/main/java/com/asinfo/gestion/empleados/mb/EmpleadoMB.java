/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.mb;

import com.asinfo.gestion.empleados.controladores.DepartamentoServicio;
import com.asinfo.gestion.empleados.controladores.EmpleadoServicio;
import com.asinfo.gestion.empleados.controladores.SupervisorServicio;
import com.asinfo.gestion.empleados.modelos.Departamento;
import com.asinfo.gestion.empleados.modelos.Empleado;
import com.asinfo.gestion.empleados.modelos.Supervisor;
import com.asinfo.gestion.empleados.util.Constantes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 * @class EmpleadoMB
 * @author jorge
 * @description 
 */
@ManagedBean(name = "empleadoMB")
@ViewScoped
public class EmpleadoMB implements Serializable {
    
    private final EmpleadoServicio empSrv = new EmpleadoServicio();
    private final SupervisorServicio superSrv = new SupervisorServicio();
    private final DepartamentoServicio deparSrv = new DepartamentoServicio();
    
    private List<Empleado> empleados;
    private Empleado empleado;
    private List<Supervisor> supervisores;
    private List<Departamento> departamentos;
    private BigDecimal nuevoSueldo;
    
    
    
    @PostConstruct
    public void iniciar(){
        nuevoSueldo = BigDecimal.ZERO;
        nuevoEmpleado();
        buscarDepartamentosActivos();
        buscarSupervisoresActivos();
    }
    
    public void buscarEmpleados(){
        try{
            empleados = empSrv.buscarEmpleados();
        }catch(Exception exc){
            mostrarError(exc);
        }
    }
    public void nuevoEmpleado(){
        empleado = new Empleado();
        empleado.setDepartamento(new Departamento());
        empleado.setSupervisor(new Supervisor());
    }
    public void guardarEmpleado(){
        try{
            empleado = empSrv.guardarEmpleado(empleado);
            mostrarMensajeExito();
            nuevoEmpleado();
        }catch(Exception exc){
            mostrarError(exc);
        }
    }
    
    public void buscarDepartamentosActivos(){
        try{
            departamentos = deparSrv.buscarDepartamentosActivos();
        }catch(Exception exc){
            mostrarError(exc);
        }
    }
    public void buscarSupervisoresActivos(){
        try{
            supervisores = superSrv.buscarSupervisoresActivos();
        }catch(Exception exc){
            mostrarError(exc);
        }
    }
    public void actualizarSueldo(){
        try{
            empleado.setSueldo(nuevoSueldo);
            empSrv.actualizarSueldo(empleado);
            mostrarMensajeExito();
            nuevoSueldo = BigDecimal.ZERO;
        }catch(Exception exc){
            mostrarError(exc);
        }
    }
    public void exportarEmpleados(){
        try{
            if(empleados != null && !empleados.isEmpty())
                empSrv.exportarEmpleados();
            else
                mostrarError(new Exception("No existen empleados para su exportaci&oacute;n."));
        }catch(Exception exc){
            mostrarError(exc);
        }
    }
    

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Supervisor> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<Supervisor> supervisores) {
        this.supervisores = supervisores;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public BigDecimal getNuevoSueldo() {
        return nuevoSueldo;
    }

    public void setNuevoSueldo(BigDecimal nuevoSueldo) {
        this.nuevoSueldo = nuevoSueldo;
    }
    
    
    
    private void mostrarError(Exception exc){
        exc.printStackTrace();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", exc.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
    
    private void mostrarMensajeExito(){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", Constantes.MENSAJE_EXITO);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
    
}
