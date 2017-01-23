/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionreporte;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
public class ConexionReporte {

    private static Connection conexion;
    private static JasperPrint jasperPrint;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");

        java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dis", "root", "");

        System.out.println("Conexion exitosa");

        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("Reporte1.jasper");
        jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);

        Exporter exportador;
        exportador = new JRPdfExporter();

        exportador.setExporterInput(new SimpleExporterInput(jasperPrint));
        exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(new File("reporteSesion1PDF.pdf")));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        //  exportador.setConfiguration(configuration);
        exportador.exportReport();
        
    JFrame frame = new JFrame("Reporte");
    frame.getContentPane().add(new JRViewer(jasperPrint));
    frame.pack();
    frame.setSize(800,600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    }

}
