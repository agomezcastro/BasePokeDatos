/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexbasedatos;

import jdk.nashorn.internal.scripts.JO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author agomezcastro
 */
public class pokeInterfaz extends javax.swing.JFrame implements KeyListener {
    private Statement state;

    HttpURLConnection connection;
    String readLine = null;
    StringBuffer response;
    BufferedReader in;

    /**
     * Creates new form pokeInterfaz
     */
    public pokeInterfaz() {
        initComponents();
        PokeSonido sonido = new PokeSonido();
        sonido.music();
        campoID.addKeyListener((KeyListener) this);
        BActualizar.setOpaque(false);
        BActualizar.setContentAreaFilled(false);
        BActualizar.setBorderPainted(false);
        BGrito.setOpaque(false);
        BGrito.setContentAreaFilled(false);
        BGrito.setBorderPainted(false);
        campoID.isFocusable();
        imgLab.setIcon(new ImageIcon("C:\\Users\\alumno\\IdeaProjects\\BasePokeDatos\\src\\Imagenes\\pokelab.jpg"));
        etqFondo.setIcon(new ImageIcon("C:\\Users\\alumno\\IdeaProjects\\BasePokeDatos\\src\\Imagenes\\Pokedex.png"));
        try {
            pokeLista();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void conectarPokeAPI(String query_url) throws IOException {
        URL getUrl = new URL(query_url);
        connection = (HttpURLConnection) getUrl.openConnection();
        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.addRequestProperty("User-Agent", "");
            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Error al conectar con la API");
            } else {
                in = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //mostar la lista de pokemon en el combobox
    public void pokeLista() throws IOException, SQLException {
        String sql = "SELECT * FROM pokemon";
        String nombre;
        cmbPok.removeAllItems();

        Conexion conexion = new Conexion();
        Connection con = conexion.conectar();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            nombre = rs.getString("nombre");
            cmbPok.addItem(nombre);
        }
        /*String query_url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=807";
        conectarPokeAPI(query_url);

        //recorro el json del request que hice a la API
        JSONObject json = new JSONObject(response.toString());
        JSONArray arrayJson = json.getJSONArray("results");

        //recorro el array del json "results" y muestro los nombres de los pokemon
        for (int i = 0; i < arrayJson.length(); i++) {
            json = arrayJson.getJSONObject(i);
            cmbPok.addItem(json.getString("name"));
        }*/
    }

    public void actualizarBaseDatos() throws SQLException, IOException {
        String sql = "SELECT * FROM pokemon";
        ArrayList<String> nombreBase = new ArrayList<>();

        Conexion conexion = new Conexion();
        Connection con = conexion.conectar();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            nombreBase.add(rs.getString("nombre"));
        }

        String query_url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=807";
        conectarPokeAPI(query_url);

        ////muestro los nombres de los pokemon
        JSONObject json = new JSONObject(response.toString());
        JSONArray arrayJson = json.getJSONArray("results");

        //comparo cuantos resultados hay en la base y en la API
        if (nombreBase.size() < arrayJson.length()) {
            JOptionPane.showMessageDialog(rootPane, "Hay nuevos pokemon disponibles, se añadirán " +
                    "a la base de datos");
            for (int i = 0; i < arrayJson.length(); i++) {
                json = arrayJson.getJSONObject(i);
                String pokeNombre = json.getString("name");

                if (!nombreBase.contains(pokeNombre) || nombreBase.isEmpty()) {
                    query_url = "https://pokeapi.co/api/v2/pokemon/" + pokeNombre;
                    conectarPokeAPI(query_url);

                    //Recojo el id del pokemon
                    JSONObject id = new JSONObject(response.toString());
                    int pokeId = id.getInt(("id"));

                    //muestro la altura y el peso
                    JSONObject medidas = new JSONObject(response.toString());
                    int height = medidas.getInt("height");
                    int weight = medidas.getInt("weight");

                    //muestro la descripcion
                    String pokeBio = null;
                    JSONObject desc = new JSONObject(response.toString());
                    desc = desc.getJSONObject("species");
                    String requestDesc = desc.getString("url");
                    conectarPokeAPI(requestDesc);
                    JSONObject pokeDesc = new JSONObject(response.toString());
                    JSONArray arrayDesc = pokeDesc.getJSONArray("flavor_text_entries");

                    for (int y = 0; y < arrayDesc.length(); y++) {
                        pokeDesc = arrayDesc.getJSONObject(y);
                        JSONObject lang = pokeDesc.getJSONObject("language");
                        String idioma = lang.getString("name");
                        if (idioma.equals("es")) {
                            pokeBio = pokeDesc.getString("flavor_text");
                            break;
                        }
                    }

                    //muestro los tipos del pokemon
                    //String pokeTipo = null;
                    String tipo1 = null;
                    String tipo2 = null;
                    conectarPokeAPI(query_url);
                    JSONObject tipos = new JSONObject(response.toString());
                    JSONArray arrayTipos = tipos.getJSONArray("types");

                    for (int x = 0; x < arrayTipos.length(); x++) {
                        tipos = arrayTipos.getJSONObject(x);
                        int slot = tipos.getInt("slot");
                        tipos = tipos.getJSONObject("type");
                        String tipoIdioma = tipos.getString("url");

                        if (slot == 1) {
                            conectarPokeAPI(tipoIdioma);
                            JSONObject jsonTipoIdioma = new JSONObject(response.toString());
                            JSONArray arrayTipoIdioma = jsonTipoIdioma.getJSONArray("names");
                            for (int j = 0; j < arrayTipoIdioma.length(); j++) {
                                jsonTipoIdioma = arrayTipoIdioma.getJSONObject(j);
                                JSONObject idioma = jsonTipoIdioma.getJSONObject("language");
                                String español = idioma.getString("name");
                                if (español.equals("es")) {
                                    String idiomaEsp = jsonTipoIdioma.getString("name");
                                    tipo1 = idiomaEsp;
                                }
                            }
                        } else {
                            conectarPokeAPI(tipoIdioma);
                            JSONObject jsonTipoIdioma = new JSONObject(response.toString());
                            JSONArray arrayTipoIdioma = jsonTipoIdioma.getJSONArray("names");
                            for (int j = 0; j < arrayTipoIdioma.length(); j++) {
                                jsonTipoIdioma = arrayTipoIdioma.getJSONObject(j);
                                JSONObject idioma = jsonTipoIdioma.getJSONObject("language");
                                String español = idioma.getString("name");
                                if (español.equals("es")) {
                                    String idiomaEsp = jsonTipoIdioma.getString("name");
                                    tipo2 = idiomaEsp;
                                }
                            }
                        }
                    }

                    sql = "INSERT INTO pokemon (id, nombre, tipo1, tipo2, altura, peso, naturaleza, descripcion, image)" +
                            "VALUES (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, pokeId);
                    pst.setString(2, pokeNombre);
                    pst.setString(3, tipo1);
                    pst.setString(4, tipo2);
                    pst.setInt(5, height);
                    pst.setInt(6, weight);
                    pst.setString(7, null);
                    pst.setString(8, pokeBio);
                    pst.setString(9, pokeId + ".png");
                    pst.executeUpdate();
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay Pokemon nuevos");
        }
    }

    //mostrar todos los datos del pokemon
    public void mostrarPokemon() throws IOException {
        Conexion con = new Conexion();
        Statement st;
        try {
            st = con.conectar().createStatement();
            ResultSet rs = st.executeQuery("select tipo1, tipo2, descripcion, altura, peso, image from pokemon where nombre='" + cmbPok.getSelectedItem() + "'");
            while (rs.next()) {
                jTextArea1.setText(rs.getString("Descripcion"));
                etqTip1.setIcon(new ImageIcon("C:\\Users\\alumno\\IdeaProjects\\BasePokeDatos\\src\\Imagenes\\Tipos\\" + rs.getString("Tipo1") + ".gif"));
                if (rs.getString("Tipo2") != "") {
                    etqTip2.setIcon(new ImageIcon("C:\\Users\\alumno\\IdeaProjects\\BasePokeDatos\\src\\Imagenes\\Tipos\\" + rs.getString("Tipo2") + ".gif"));
                }
                float altura = rs.getInt("altura");
                float peso = rs.getInt("peso");
                float alt = altura / 10;
                float pes = peso / 10;
                etqAltura2.setText("Altura: " + alt + " m");
                etqPeso2.setText("Peso: " + pes + " kg");
                etqImgPok.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\alumno\\IdeaProjects\\BasePokeDatos\\src\\Imagenes\\pokemonImg\\"
                        + rs.getString("image")).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                etqImgPok.setLocation(50, 100);

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

        etqID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/id.png"))); // NOI18N
        jPanel2.add(etqID);
        etqID.setBounds(100, 40, 50, 20);

        etqNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nombre.png"))); // NOI18N
        jPanel2.add(etqNombre);
        etqNombre.setBounds(30, 70, 123, 40);

        etqTipo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tipo1.png"))); // NOI18N
        jPanel2.add(etqTipo1);
        etqTipo1.setBounds(60, 120, 90, 30);

        etqTipo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tipo2.png"))); // NOI18N
        jPanel2.add(etqTipo2);
        etqTipo2.setBounds(60, 160, 90, 30);

        etqAltura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/altura.png"))); // NOI18N
        jPanel2.add(etqAltura);
        etqAltura.setBounds(20, 200, 160, 40);

        etqPeso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/peso.png"))); // NOI18N
        jPanel2.add(etqPeso);
        etqPeso.setBounds(30, 250, 150, 40);

        etqNaturaleza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/naturaleza.png"))); // NOI18N
        jPanel2.add(etqNaturaleza);
        etqNaturaleza.setBounds(10, 300, 170, 40);

        etqDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/descripcion.png"))); // NOI18N
        jPanel2.add(etqDescripcion);
        etqDescripcion.setBounds(10, 370, 180, 50);
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
        BActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        etqVis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/verpokemon.png"))); // NOI18N
        jPanel3.add(etqVis);
        etqVis.setBounds(470, 100, 280, 50);
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
        id = campoID.getText();
        nom = campoNombre.getText();
        tip1 = campoTipo1.getText();
        tip2 = campoTipo2.getText();
        alt = campoAltura.getText();
        pes = campoPeso.getText();
        nat = campoNaturaleza.getText();
        desc = campoDescripcion.getText();

        sql = "SELECT * FROM pokemon";
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        cmbPok.removeAllItems();

        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        sql = "INSERT INTO pokemon (Id, Nombre, Tipo1, Tipo2, Altura, Peso, Naturaleza, Descripcion, image) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            if (id.equals("") || nom.equals("") || tip1.equals("") || tip2.equals("") || alt.equals("") ||
                    pes.equals("") || desc.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Por favor rellene todos los campos");
            } else if (nombres.contains(nom) || ids.contains(id)) {
                JOptionPane.showMessageDialog(rootPane, "Ese nombre/id ya existe, elige otro");
            } else {
                PreparedStatement pst = asd.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, nom);
                pst.setString(3, tip1);
                pst.setString(4, tip2);
                pst.setString(5, alt);
                pst.setString(6, pes);
                pst.setString(7, nat);
                pst.setString(8, desc);
                pst.setString(9, id + ".png");
                int x = pst.executeUpdate();
                if (x > 0)
                    JOptionPane.showMessageDialog(rootPane, "Datos Registrados");
            }
        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pokeLista();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_botonInsertarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        try {
            Conexion con = new Conexion();
            Connection asd = (Connection) con.conectar();

            if (campoID.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "El campo ID no puede estar vacio");
            } else {
                String sql = "DELETE FROM pokemon WHERE id=" + campoID.getText();
                state = asd.createStatement();
                int x = state.executeUpdate(sql);
                if (x > 0)
                    JOptionPane.showMessageDialog(rootPane, "Datos eliminados");
            }
            try {
                pokeLista();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        String id, nom, tip1, tip2, alt, pes, nat, desc;
        id = campoID.getText();
        nom = campoNombre.getText();
        tip1 = campoTipo1.getText();
        tip2 = campoTipo2.getText();
        alt = campoAltura.getText();
        pes = campoPeso.getText();
        nat = campoNaturaleza.getText();
        desc = campoDescripcion.getText();

        try {
            Conexion con = new Conexion();
            if (id.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "El campo ID no puede estar vacio");
            } else if (nom.equals("") || tip1.equals("") || tip2.equals("") || alt.equals("") || pes.equals("") ||
                    desc.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Por favor rellene todos los campos");
            } else {
                PreparedStatement asd = con.conectar().prepareStatement("UPDATE pokemon SET Nombre='" + nom +
                        "', Tipo1='" + tip1 + "', Tipo2='" + tip2 + "', Altura='" + alt + "', Peso='" + pes +
                        "', Naturaleza='" + nat + "', Descripcion='" + desc + "', image='" + id + ".png' WHERE id='" + id + "'");

                asd.execute();
                JOptionPane.showMessageDialog(rootPane, "Registro modificado");
            }

        } catch (SQLException ex) {
            Logger.getLogger(pokeInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void limpiar() {
        campoID.setText("");
        campoNombre.setText("");
        campoTipo1.setText("");
        campoTipo2.setText("");
        campoAltura.setText("");
        campoPeso.setText("");
        campoNaturaleza.setText("");
        campoDescripcion.setText("");
    }

    private void BVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BVisualizarActionPerformed
        try {
            mostrarPokemon();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BVisualizarActionPerformed

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarActionPerformed
        try {
            actualizarBaseDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            pokeLista();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BActualizarActionPerformed

    private void BGritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGritoActionPerformed
        String query_url = "https://pokeapi.co/api/v2/pokemon/" + cmbPok.getSelectedItem();
        try {
            conectarPokeAPI(query_url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject id = new JSONObject(response.toString());
        int pokeId = id.getInt(("id"));

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\alumno\\IdeaProjects\\BasePokeDatos\\src\\Sonidos\\" + pokeId + cmbPok.getSelectedItem() + ".wav").getAbsoluteFile());
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String id;
        id = campoID.getText();
        try {
            if (id.equals("")) {
                campoNombre.setEditable(true);
                limpiar();
            } else {
                Conexion con = new Conexion();
                String sql = "SELECT * FROM pokemon WHERE id=" + id;
                Statement st = con.conectar().createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String tipo1 = rs.getString("tipo1");
                    String tipo2 = rs.getString("tipo2");
                    String altura = rs.getString("altura");
                    String peso = rs.getString("peso");
                    String naturaleza = rs.getString("naturaleza");
                    String desc = rs.getString("descripcion");

                    campoNombre.setText(nombre);
                    campoTipo1.setText(tipo1);
                    campoTipo2.setText(tipo2);
                    campoAltura.setText(altura);
                    campoPeso.setText(peso);
                    campoNaturaleza.setText(naturaleza);
                    campoDescripcion.setText(desc);

                    campoNombre.setEditable(false);
                }
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }

    }
    // End of variables declaration//GEN-END:variables
}
