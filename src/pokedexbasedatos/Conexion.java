package pokedexbasedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author agomezcastro
 */
public class Conexion {
    Connection conexion = null;

    String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    String bd_url = "jdbc:mysql://localhost/pokemon";
    String user = "root";
    String passw = "";

    public Connection conectar() {
        //Statement stmt;
        try {
            Class.forName(jdbc_driver);
            conexion = (Connection) DriverManager.getConnection(bd_url, user, passw);

            /*if (conexion == null) {
                System.out.println("Creando nueva base de datos...");
                stmt = conexion.createStatement();

                String sql = "CREATE DATABASE POKEMON";
                stmt.executeUpdate(sql);
                System.out.println("Base de datos creada!");

                sql = "CREATE TABLE pokemon";
                stmt.executeUpdate(sql);
            }*/
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
}
