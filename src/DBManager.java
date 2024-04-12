
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;

public class DBManager {

    //Credenciales
    private static final String user = "postgres";
    private static final String pass = "jesusg*";

    private static Connection conexion;

    //Inicializa la conexión
    public static void conexionBD() {
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BDprueba", user, pass);
            System.out.println("conexión correcta");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//Inserta una persona
    public static void agregarPersona(String nombre, String apellido, int edad) {
        try {
            PreparedStatement stmn = conexion.prepareStatement("INSERT INTO persona (nombre, apellido, edad) VALUES (?,?,?)");
            stmn.setString(1, nombre);
            stmn.setString(2, apellido);
            stmn.setInt(3, edad);
            
            stmn.execute();
            System.out.println("Usuario: "+nombre+" "+apellido+" "+edad+" agregado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//Obtener datos de la tabla
    public static void obtenerPersonas(){
        try {
            PreparedStatement stmn = conexion.prepareStatement("SELECT * FROM persona order by id");
            ResultSet result = stmn.executeQuery();
            
            System.out.println("id  |  nombre  |  apellido  |  edad");
            
            while (result.next()) {
                long id = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int edad = result.getInt("edad");
                
                System.out.println(id+" \t"+nombre+ " \t"+apellido+" \t"+edad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
//Editar datos de la tabla
    public static void editarPersona(long id, String nuevoNombre, String nuevoApellido, int nuevaEdad){
        try {
            PreparedStatement stmn = conexion.prepareStatement("UPDATE persona SET nombre = ?, apellido = ?, edad = ? WHERE id = ?");
            stmn.setString(1,nuevoNombre);
            stmn.setString(2,nuevoApellido);
            stmn.setInt(3,nuevaEdad);
            stmn.setLong(4, id);
            
            int row = stmn.executeUpdate();
            if(row==0){
                System.out.println("No se modifico");
            }else{
                System.out.println("Usuario "+id+" modificado correctamente");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
//Eliminar datos de la tabla
    public static void eliminarPersona(long id){
        try {
            PreparedStatement stmn = conexion.prepareStatement("DELETE FROM persona where id = ?");
            stmn.setLong(1, id);
            
            int row = stmn.executeUpdate();
            
            if (row==0) {
                System.out.println("No se borro el registro con id "+id);
            }else{
                System.out.println("Se borro correctamente");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
