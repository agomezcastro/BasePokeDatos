/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexbasedatos;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author agomezcastro
 */
public class pokeInterfaz extends javax.swing.JFrame {
    private Statement state;
    /**
     * Creates new form pokeInterfaz
     */
    public pokeInterfaz() {
        initComponents();
        imgOak.setIcon(new ImageIcon("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Imagenes/oak.png"));
        imgLab.setIcon(new ImageIcon("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Imagenes/lab.jpg"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        etqID = new javax.swing.JLabel();
        etqNombre = new javax.swing.JLabel();
        etqTipo1 = new javax.swing.JLabel();
        etqTipo2 = new javax.swing.JLabel();
        etqAltura = new javax.swing.JLabel();
        etqPeso = new javax.swing.JLabel();
        etqNaturaleza = new javax.swing.JLabel();
        etqDescripcion = new javax.swing.JLabel();
        campoID = new javax.swing.JTextField();
        campoNombre = new javax.swing.JTextField();
        campoTipo1 = new javax.swing.JTextField();
        campoTipo2 = new javax.swing.JTextField();
        campoAltura = new javax.swing.JTextField();
        campoPeso = new javax.swing.JTextField();
        campoNaturaleza = new javax.swing.JTextField();
        botonInsertar = new javax.swing.JButton();
        imgOak = new javax.swing.JLabel();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescripcion = new javax.swing.JTextArea();
        imgLab = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(null);

        etqID.setText("Id:");
        jPanel2.add(etqID);
        etqID.setBounds(47, 45, 18, 15);

        etqNombre.setText("Nombre:");
        jPanel2.add(etqNombre);
        etqNombre.setBounds(47, 82, 60, 15);

        etqTipo1.setText("Tipo1:");
        jPanel2.add(etqTipo1);
        etqTipo1.setBounds(47, 119, 43, 15);

        etqTipo2.setText("Tipo2:");
        jPanel2.add(etqTipo2);
        etqTipo2.setBounds(47, 165, 43, 15);

        etqAltura.setText("Altura (metros):");
        jPanel2.add(etqAltura);
        etqAltura.setBounds(47, 213, 112, 15);

        etqPeso.setText("Peso (kg):");
        jPanel2.add(etqPeso);
        etqPeso.setBounds(47, 257, 71, 15);

        etqNaturaleza.setText("Naturaleza:");
        jPanel2.add(etqNaturaleza);
        etqNaturaleza.setBounds(47, 300, 84, 15);

        etqDescripcion.setText("Descripcion:");
        jPanel2.add(etqDescripcion);
        etqDescripcion.setBounds(47, 376, 87, 15);
        jPanel2.add(campoID);
        campoID.setBounds(189, 43, 70, 19);

        campoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreActionPerformed(evt);
            }
        });
        jPanel2.add(campoNombre);
        campoNombre.setBounds(189, 80, 127, 19);
        jPanel2.add(campoTipo1);
        campoTipo1.setBounds(189, 117, 127, 19);
        jPanel2.add(campoTipo2);
        campoTipo2.setBounds(189, 163, 127, 19);
        jPanel2.add(campoAltura);
        campoAltura.setBounds(189, 211, 127, 19);
        jPanel2.add(campoPeso);
        campoPeso.setBounds(189, 255, 127, 19);
        jPanel2.add(campoNaturaleza);
        campoNaturaleza.setBounds(189, 298, 127, 19);

        botonInsertar.setText("Insertar");
        botonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertarActionPerformed(evt);
            }
        });
        jPanel2.add(botonInsertar);
        botonInsertar.setBounds(403, 58, 138, 80);
        jPanel2.add(imgOak);
        imgOak.setBounds(508, 348, 208, 139);

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(botonEliminar);
        botonEliminar.setBounds(403, 185, 138, 89);

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        jPanel2.add(botonModificar);
        botonModificar.setBounds(598, 58, 139, 80);

        botonLimpiar.setText("Nuevo registro");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(botonLimpiar);
        botonLimpiar.setBounds(598, 185, 139, 89);

        campoDescripcion.setColumns(20);
        campoDescripcion.setRows(5);
        jScrollPane1.setViewportView(campoDescripcion);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(189, 376, 223, 78);
        jPanel2.add(imgLab);
        imgLab.setBounds(2, 2, 800, 530);

        jTabbedPane1.addTab("Datos", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreActionPerformed

    private void botonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInsertarActionPerformed
        Conexion con = new Conexion();
        java.sql.Connection asd = con.conectar();
        String id, nom, tip1, tip2, alt, pes, nat, desc;
        String sql;
        id=campoID.getText();
        nom=campoNombre.getText();
        tip1=campoTipo1.getText();
        tip2=campoTipo2.getText();
        alt=campoAltura.getText();
        pes=campoPeso.getText();
        nat=campoNaturaleza.getText();
        desc=campoDescripcion.getText();
        
                
        sql= "INSERT INTO pokemon (Id, Nombre, Tipo1, Tipo2, Altura, Peso, Naturaleza, Descripcion)VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst= asd.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, nom);
            pst.setString(3, tip1);
            pst.setString(4, tip2);
            pst.setString(5, alt);
            pst.setString(6, pes);
            pst.setString(7, nat);
            pst.setString(8, desc);
            int x=pst.executeUpdate();
            if (x>0)
                JOptionPane.showMessageDialog(rootPane, "Datos Registrados");
            
        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonInsertarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        try {
            Conexion con = new Conexion();
            Connection asd = (Connection) con.conectar();
            
            
            String sql= "DELETE FROM pokemon WHERE id="+campoID.getText();
            state = asd.createStatement();
            int x= state.executeUpdate(sql);
                if(x>0)
                    JOptionPane.showMessageDialog(rootPane, "Datos eliminados");
            
        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        try {
            Conexion con = new Conexion();
            PreparedStatement asd= con.conectar().prepareStatement("UPDATE pokemon SET Nombre='"+campoNombre.getText()+
                    "', Tipo1='"+campoTipo1.getText()+ "', Tipo2='" +campoTipo2.getText()+ 
                    "', Altura='"+campoAltura.getText()+"', Peso='"+campoPeso.getText() +
                    "', Naturaleza='"+campoNaturaleza.getText()+ "', Descripcion='"+ campoDescripcion.getText()+
                    "' WHERE id='"+campoID.getText()+"'");

            
            asd.execute();
                JOptionPane.showMessageDialog(rootPane, "Registro modificado");
        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        campoID.setText("");
        campoNombre.setText("");
        campoTipo1.setText("");
        campoTipo2.setText("");
        campoAltura.setText("");
        campoPeso.setText("");
        campoNaturaleza.setText("");
        campoDescripcion.setText("");
    }//GEN-LAST:event_botonLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pokeInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pokeInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pokeInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pokeInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pokeInterfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonInsertar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField campoAltura;
    private javax.swing.JTextArea campoDescripcion;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoNaturaleza;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoPeso;
    private javax.swing.JTextField campoTipo1;
    private javax.swing.JTextField campoTipo2;
    private javax.swing.JLabel etqAltura;
    private javax.swing.JLabel etqDescripcion;
    private javax.swing.JLabel etqID;
    private javax.swing.JLabel etqNaturaleza;
    private javax.swing.JLabel etqNombre;
    private javax.swing.JLabel etqPeso;
    private javax.swing.JLabel etqTipo1;
    private javax.swing.JLabel etqTipo2;
    private javax.swing.JLabel imgLab;
    private javax.swing.JLabel imgOak;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
