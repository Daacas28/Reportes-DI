/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesesion3;

import reportesesion2.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
//import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dam2
 */
public class ParticipantesDatasource implements JRDataSource {

    int indice = -1;
    private List<Participante> listParticipantes = new ArrayList<>();

    @Override
    public boolean next() throws JRException {
        return ++indice < listParticipantes.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;

        if ("id".equals(jrf.getName())) {
            valor = listParticipantes.get(indice).getId();
        } else if ("nombre".equals(jrf.getName())) {
            valor = listParticipantes.get(indice).getNombre();
        } else if ("username".equals(jrf.getName())) {
            valor = listParticipantes.get(indice).getUserName();
        } else if ("password".equals(jrf.getName())) {
            valor = listParticipantes.get(indice).getPassword();
        } else if ("comentarios".equals(jrf.getName())) {
            valor = listParticipantes.get(indice).getComentarios();
        } else if ("puntos".equals(jrf.getName())) {
            valor = listParticipantes.get(indice).getPuntos();

        }

        return valor;
    }

    public void addParticipante(Participante participante) {
        this.listParticipantes.add(participante);
    }

    public static void main(String[] args) throws JRException {

        ParticipantesDatasource datasource = new reportesesion3.ParticipantesDatasource();

        for (int i = 1; i <= 15; i++) {
            Participante p = new Participante(i, "Particpante " + i, "Usuario " + i, "Pass" + i, "Comentarios para " + i);
            p.setPuntos(i);
            datasource.addParticipante(p);
        }
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("Reporte3.jasper");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("JASPER_AUTOR", "Pepe Luis");
        parametros.put("JASPER_TITTLE", "Reporte Sesion 3");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource);
        Exporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new java.io.File("reporteSesion3PDF.pdf")));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();
    }
}
