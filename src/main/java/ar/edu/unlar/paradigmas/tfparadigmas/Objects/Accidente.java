/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unlar.paradigmas.tfparadigmas.Objects;

import ar.edu.unlar.paradigmas.tfparadigmas.database.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WinUser
 */
public class Accidente {
    
    int id;
    int empleado_id;
    int ubicacion_id;
    int motivo_id;
    int tipoaccidente_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public int getUbicacion_id() {
        return ubicacion_id;
    }

    public void setUbicacion_id(int ubicacion_id) {
        this.ubicacion_id = ubicacion_id;
    }

    public int getMotivo_id() {
        return motivo_id;
    }

    public void setMotivo_id(int motivo_id) {
        this.motivo_id = motivo_id;
    }

    public int getTipoaccidente_id() {
        return tipoaccidente_id;
    }

    public void setTipoaccidente_id(int tipoaccidente_id) {
        this.tipoaccidente_id = tipoaccidente_id;
    }
    
    
    public void MostrarAccidentes (JTable paramTablaTotalAccidentes){
    
        ConnectionDB objetoConexion = new ConnectionDB();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "";
        modelo.addColumn("Id");
        modelo.addColumn("ID Empleado");
        modelo.addColumn("ID Ubicacion");
        modelo.addColumn("ID Motivo");
        modelo.addColumn("ID Tipo Accidente");
        
        paramTablaTotalAccidentes.setModel(modelo);
        
        sql = "select accidentes.accidente_id, empleados.legajo, ubicaciones.lugar, motivos.descripcion, tipos_accidentes.tipo\n" +
                "from accidentes \n" +
                "inner join empleados on accidentes.empleado_id = empleados.empleado_id\n" +
                   "inner join ubicaciones on accidentes.ubicacion_id = ubicaciones.ubicacion_id\n" +
                    "inner join motivos on accidentes.motivo_id = motivos.motivo_id\n" +
                    "inner join tipos_accidentes on accidentes.tipo_accidente_id = tipos_accidentes.tipo_accidente_id;";
        
        String []datos = new String[5];
        
        Statement st;
        
        try {
            
            st = objetoConexion.obtenerConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                
               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               datos[3] = rs.getString(4);
               datos[4] = rs.getString(5);
               
               modelo.addRow(datos);
               
               
            }
            
            paramTablaTotalAccidentes.setModel(modelo);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
        }
    }
    
    /*public void insertarAccidentes (JTextField paramEmpleado,JTextField paramUbicacion,JTextField paramMotivo, JTextField paramTipoAccidente){
        
        setLegajo(paramLegajo.getText());
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        
        
        ConnectionDB objetoConexion = new ConnectionDB();
        
        String consulta = "INSERT INTO ACCIDENTES (empleado_id, ubicacion_id, motivo_id, tipo_accidente_id) VALUES (?,?,?,?);";
        
        try {
            
            CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
            cs.setString(1, getLegajo());
            cs.setString(2, getNombre());
            cs.setString(3, getApellido());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
        }
    }*/
}
