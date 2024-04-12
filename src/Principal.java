
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Principal {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DBManager.conexionBD();

        int opc;

        do {

            System.out.println("\n1: Ver personas");
            System.out.println("2: Crear personas");
            System.out.println("3: Editar personas");
            System.out.println("4: Eliminar personas");
            System.out.println("5: Salir");

            opc = scanner.nextInt();

            if (opc == 1) {
                verPersona();
            } else if (opc == 2) {
                crearPersona();
            } else if (opc == 3) {
                editarPersona();
            } else if (opc == 4) {
                eliminarPersona();
            }
        } while (opc != 5);
    }

    public static void verPersona() {
        DBManager.obtenerPersonas();
    }

    public static void crearPersona() {
        System.out.println("Ingresar nombre");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        System.out.println("Ingresar apellido");
        String apellido = scanner.nextLine();
        System.out.println("Ingresar edad");
        int edad = scanner.nextInt();

        DBManager.agregarPersona(nombre, apellido, edad);
    }

    public static void editarPersona() {
        DBManager.obtenerPersonas();
        System.out.println("Ingresar id de la persona a editar");
        long id = scanner.nextLong();
        
        System.out.println("Ingresar nuevo nombre");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        System.out.println("Ingresar nuevo apellido");
        String apellido = scanner.nextLine();
        System.out.println("Ingresar nueva edad");
        int edad = scanner.nextInt();
        
        DBManager.editarPersona(id, nombre, apellido, edad);
    }

    public static void eliminarPersona() {
        DBManager.obtenerPersonas();
        System.out.println("Ingresar id de persona a eliminar");
        long id = scanner.nextLong();
        DBManager.eliminarPersona(id);
    }
}
