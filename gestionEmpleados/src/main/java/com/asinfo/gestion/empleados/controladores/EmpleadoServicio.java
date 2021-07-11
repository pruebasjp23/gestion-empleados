/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.gestion.empleados.controladores;

import com.asinfo.gestion.empleados.dao.EmpleadoDao;
import com.asinfo.gestion.empleados.modelos.Empleado;
import com.asinfo.gestion.empleados.util.Constantes;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 *
 * @author jorge
 */
public class EmpleadoServicio {

    private final EmpleadoDao dao = new EmpleadoDao();

    public List<Empleado> buscarEmpleados() throws Exception {
        return dao.buscarEmpleados();
    }

    public Empleado guardarEmpleado(Empleado empleado) throws Exception {
        return dao.guardarEmpleado(empleado);
    }

    public void actualizarSueldo(Empleado empleado) throws Exception {
        dao.actualizarSueldo(empleado);
    }

    public void exportarEmpleados() throws Exception {
        Connection con = null;
        try {
            con = dao.getConexion();

            String nombreReporte = Constantes.REPORTE_EMPLEADOS;
            String urlReportes = Constantes.PATH_REPORTES;

            Map<String, Object> params = new HashMap<>();
            params.put("SUBREPORT_DIR", urlReportes);
            File archivoJasper = new File(urlReportes + nombreReporte + ".jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
            
            if (jasperPrint != null) {
                FacesContext facesContext = FacesContext.getCurrentInstance();

                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.reset();

                ServletOutputStream ouputStream = response.getOutputStream();
                JRXlsExporter exporterXLS = new JRXlsExporter();
                exporterXLS.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(ouputStream));
                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                configuration.setDetectCellType(Boolean.TRUE);
                configuration.setWhitePageBackground(Boolean.FALSE);
                configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                configuration.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                exporterXLS.setConfiguration(configuration);
                response.setHeader("Content-Disposition", "inline;filename=" + nombreReporte + ".xls");
                response.setContentType("application/vnd.ms-excel");
                exporterXLS.exportReport();
                ouputStream.flush();
                ouputStream.close();

                facesContext.responseComplete();
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new Exception(exc);
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception(exc);
        } finally {
            dao.cerrarConexion(con);
        }
    }
}
