import dao.EmpleadoDAO;

public class Main {

    public static void main(String[] args) {

        EmpleadoDAO dao =
                new EmpleadoDAO();

        // INSERTAR
        dao.insertarEmpleado(
                "Carlos",
                "Backend Developer",
                4500
        );

        // LISTAR
        dao.listarEmpleados();

        // ACTUALIZAR
        dao.actualizarEmpleado(
                1,
                "Carlos Actualizado",
                "Arquitecto Software",
                7000
        );

        // ELIMINAR
        dao.eliminarEmpleado(2);

        // LISTAR NUEVAMENTE
        dao.listarEmpleados();
    }
}