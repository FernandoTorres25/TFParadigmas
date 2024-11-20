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
public class Empleado {
    int id;
    String legajo;
    String nombre;
    String apellido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public void MostrarEmpleados (JTable paramTablaTotalEmpleados){
    
        ConnectionDB objetoConexion = new ConnectionDB();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "";
        modelo.addColumn("Id");
        modelo.addColumn("Legajo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        
        paramTablaTotalEmpleados.setModel(modelo);
        
        sql = "SELECT * FROM EMPLEADOS ORDER BY EMPLEADO_ID;";
        
        String []datos = new String[4];
        
        Statement st;
        
        try {
            
            st = objetoConexion.obtenerConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                
               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               datos[3] = rs.getString(4);
               
               modelo.addRow(datos);
               
               
            }
            
            paramTablaTotalEmpleados.setModel(modelo);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
        }
    }
    
    public void insertarEmpleados (JTextField paramLegajo,JTextField paramNombre,JTextField paramApellido){
        
        setLegajo(paramLegajo.getText());
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        
        ConnectionDB objetoConexion = new ConnectionDB();
        
        String consulta = "INSERT INTO EMPLEADOS (legajo,nombre, apellido) VALUES (?,?,?);";
        
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
    }
    
    public void SeleccionarEmpleados (JTable paramTablaEmpleado, JTextField paramID, JTextField paramLegajo, JTextField paramNombre, JTextField paramApellido){
    
        try {
            
            int fila = paramTablaEmpleado.getSelectedRow();
            
            if(fila>=0){
            
                paramID.setText(paramTablaEmpleado.getValueAt(fila, 0).toString());
                paramLegajo.setText(paramTablaEmpleado.getValueAt(fila, 1).toString());
                paramNombre.setText(paramTablaEmpleado.getValueAt(fila, 2).toString());
                paramApellido.setText(paramTablaEmpleado.getValueAt(fila, 3).toString());
            }
            
            else{
                
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
        }
        
    }
    
    public void ModificarEmpleados (JTextField paramId,JTextField paramLegajo,JTextField paramNombre,JTextField paramApellido){
        
        setId(Integer.parseInt(paramId.getText())); 
        setLegajo(paramLegajo.getText());
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        
        ConnectionDB objetoConexion = new ConnectionDB();
        
        String consulta = "UPDATE EMPLEADOS SET LEGAJO=?, NOMBRE=?, APELLIDO=? WHERE EMPLEADO_ID = ?;";
        
        try {
            
            CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
            cs.setString(1, getLegajo());
            cs.setString(2, getNombre());
            cs.setString(3, getApellido());
            cs.setInt(4, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamente");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
        }
    }
    
    public void EliminarEmpleados (JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText())); 
        
        
        ConnectionDB objetoConexion = new ConnectionDB();
        
        String consulta = "DELETE FROM EMPLEADOS WHERE EMPLEADO_ID=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
           
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
        }
    }
    
}
