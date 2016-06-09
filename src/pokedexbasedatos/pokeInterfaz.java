/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexbasedatos;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
        PokeSonido sonido = new PokeSonido();
        sonido.music();
        BActualizar.setOpaque(false);
        BActualizar.setContentAreaFilled(false);
        BActualizar.setBorderPainted(false);
        BGrito.setOpaque(false);
        BGrito.setContentAreaFilled(false);
        BGrito.setBorderPainted(false);
        imgLab.setIcon(new ImageIcon("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Imagenes/pokelab.jpg"));
        etqFondo.setIcon(new ImageIcon("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Imagenes/Pokedex.png"));
        cargarCmb();
    }

    public void cargarCmb(){
        try {
            Conexion con = new Conexion();
            Statement st;
            st = con.conectar().createStatement();
            ResultSet rs = st.executeQuery("select id, nombre from pokemon");
            while(rs.next()){
                cmbPok.addItem(rs.getString("Nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        botonInsertar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescripcion = new javax.swing.JTextArea();
        imgLab = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cmbPok = new javax.swing.JComboBox();
        BGrito = new javax.swing.JButton();
        BVisualizar = new javax.swing.JButton();
        BActualizar = new javax.swing.JButton();
        etqAltura2 = new javax.swing.JLabel();
        etqPeso2 = new javax.swing.JLabel();
        etqVis = new javax.swing.JLabel();
        etqTip1 = new javax.swing.JLabel();
        etqTip2 = new javax.swing.JLabel();
        etqImgPok = new javax.swing.JLabel();
        etqFondo = new javax.swing.JLabel();

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
        campoID.setBounds(189, 43, 100, 30);

        campoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreActionPerformed(evt);
            }
        });
        jPanel2.add(campoNombre);
        campoNombre.setBounds(189, 80, 127, 30);
        jPanel2.add(campoTipo1);
        campoTipo1.setBounds(189, 117, 127, 30);
        jPanel2.add(campoTipo2);
        campoTipo2.setBounds(189, 163, 127, 30);
        jPanel2.add(campoAltura);
        campoAltura.setBounds(189, 211, 127, 30);
        jPanel2.add(campoPeso);
        campoPeso.setBounds(189, 255, 127, 30);
        jPanel2.add(campoNaturaleza);
        campoNaturaleza.setBounds(189, 298, 127, 40);

        jLabel4.setText("Limpiar");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(640, 260, 60, 15);

        jLabel3.setText("Eliminar");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(470, 260, 70, 15);

        jLabel2.setText("Modificar");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(630, 130, 70, 15);

        jLabel1.setText("Insertar");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(470, 130, 60, 15);

        botonInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pokeball.png"))); // NOI18N
        botonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertarActionPerformed(evt);
            }
        });
        jPanel2.add(botonInsertar);
        botonInsertar.setBounds(440, 60, 110, 89);

        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pokeball.png"))); // NOI18N
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(botonEliminar);
        botonEliminar.setBounds(440, 190, 110, 89);

        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pokeball.png"))); // NOI18N
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        jPanel2.add(botonModificar);
        botonModificar.setBounds(610, 60, 110, 89);

        botonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pokeball.png"))); // NOI18N
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(botonLimpiar);
        botonLimpiar.setBounds(610, 190, 110, 89);

        campoDescripcion.setColumns(20);
        campoDescripcion.setRows(5);
        jScrollPane1.setViewportView(campoDescripcion);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(189, 376, 223, 78);
        jPanel2.add(imgLab);
        imgLab.setBounds(2, 2, 800, 530);

        jTabbedPane1.addTab("Datos", jPanel2);

        jPanel3.setLayout(null);

        jTextArea1.setBackground(new java.awt.Color(51, 255, 51));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(480, 250, 270, 180);

        cmbPok.setBackground(new java.awt.Color(51, 255, 51));
        cmbPok.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(cmbPok);
        cmbPok.setBounds(470, 150, 190, 24);

        BGrito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BGrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGritoActionPerformed(evt);
            }
        });
        jPanel3.add(BGrito);
        BGrito.setBounds(50, 450, 50, 30);

        BVisualizar.setBackground(new java.awt.Color(51, 255, 51));
        BVisualizar.setForeground(new java.awt.Color(0, 0, 0));
        BVisualizar.setText("Ver");
        BVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BVisualizarActionPerformed(evt);
            }
        });
        jPanel3.add(BVisualizar);
        BVisualizar.setBounds(680, 150, 70, 25);

        BActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Refresh.png"))); // NOI18N
        BActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(BActualizar);
        BActualizar.setBounds(270, 10, 40, 50);
        jPanel3.add(etqAltura2);
        etqAltura2.setBounds(660, 450, 100, 30);
        jPanel3.add(etqPeso2);
        etqPeso2.setBounds(490, 450, 150, 30);

        etqVis.setFont(new java.awt.Font("Liberation Serif", 1, 16)); // NOI18N
        etqVis.setForeground(new java.awt.Color(255, 255, 255));
        etqVis.setText("Elige el pokemon que quieres ver");
        jPanel3.add(etqVis);
        etqVis.setBounds(470, 100, 270, 50);
        jPanel3.add(etqTip1);
        etqTip1.setBounds(500, 200, 110, 30);
        jPanel3.add(etqTip2);
        etqTip2.setBounds(670, 200, 90, 30);
        jPanel3.add(etqImgPok);
        etqImgPok.setBounds(80, 100, 270, 340);
        jPanel3.add(etqFondo);
        etqFondo.setBounds(0, 0, 800, 510);

        jTabbedPane1.addTab("Pokedex", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
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

    private void BVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BVisualizarActionPerformed
        etqImgPok.setIcon(new ImageIcon("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Imagenes/"+cmbPok.getSelectedItem()+".jpg"));
        
        Conexion con = new Conexion();
        Statement st;
        try {
            st = con.conectar().createStatement();
            ResultSet rs = st.executeQuery("select tipo1, tipo2, descripcion, altura, peso from pokemon where nombre='"+cmbPok.getSelectedItem()+"'");
            while(rs.next()){
                jTextArea1.setText(rs.getString("Descripcion"));
                etqTip1.setIcon(new ImageIcon("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Imagenes/"+rs.getString("Tipo1")+".gif"));
                if(rs.getString("Tipo2")!=""){
                    etqTip2.setIcon(new ImageIcon("/home/local/DANIELCASTELAO/agomezcastro/NetBeansProjects/PokedexBaseDatos/src/Imagenes/"+rs.getString("Tipo2")+".gif"));
                }
                etqAltura2.setText("Altura: "+rs.getString("altura")+" m");
                etqPeso2.setText("Peso: "+rs.getString("peso")+" kg");
            }
        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BVisualizarActionPerformed

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarActionPerformed
        cmbPok.removeAllItems();
        cargarCmb();
    }//GEN-LAST:event_BActualizarActionPerformed

    private void BGritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGritoActionPerformed
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sonidos/" + cmbPok.getSelectedItem()+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BGritoActionPerformed

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
    private javax.swing.JButton BActualizar;
    private javax.swing.JButton BGrito;
    private javax.swing.JButton BVisualizar;
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
    private javax.swing.JComboBox cmbPok;
    private javax.swing.JLabel etqAltura;
    private javax.swing.JLabel etqAltura2;
    private javax.swing.JLabel etqDescripcion;
    private javax.swing.JLabel etqFondo;
    private javax.swing.JLabel etqID;
    private javax.swing.JLabel etqImgPok;
    private javax.swing.JLabel etqNaturaleza;
    private javax.swing.JLabel etqNombre;
    private javax.swing.JLabel etqPeso;
    private javax.swing.JLabel etqPeso2;
    private javax.swing.JLabel etqTip1;
    private javax.swing.JLabel etqTip2;
    private javax.swing.JLabel etqTipo1;
    private javax.swing.JLabel etqTipo2;
    private javax.swing.JLabel etqVis;
    private javax.swing.JLabel imgLab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
