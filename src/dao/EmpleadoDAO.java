package dao;

import conexion.ConexionOracle;
import java.sql.*;

public class EmpleadoDAO {

    Connection con;

    public EmpleadoDAO() {
        con = ConexionOracle.conectar();
    }

    // INSERTAR
    public void insertarEmpleado(
            String nombre,
            String cargo,
            double salario
    ) {

        try {

            CallableStatement cs =
                    con.prepareCall(
                            "{call SP_INSERTAR_EMPLEADO(?,?,?)}"
                    );

            cs.setString(1, nombre);
            cs.setString(2, cargo);
            cs.setDouble(3, salario);

            cs.execute();

            System.out.println(
                    "Empleado insertado"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    // LISTAR
    public void listarEmpleados() {

        try {

            CallableStatement cs =
                    con.prepareCall(
                            "{call SP_LISTAR_EMPLEADOS(?)}"
                    );

            cs.registerOutParameter(
                    1,
                    oracle.jdbc.OracleTypes.CURSOR
            );

            cs.execute();

            ResultSet rs =
                    (ResultSet) cs.getObject(1);

            while (rs.next()) {

                System.out.println(
                        rs.getInt("ID") + " - " +
                                rs.getString("NOMBRE") + " - " +
                                rs.getString("CARGO") + " - " +
                                rs.getDouble("SALARIO")
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    // ACTUALIZAR
    public void actualizarEmpleado(
            int id,
            String nombre,
            String cargo,
            double salario
    ) {

        try {

            CallableStatement cs =
                    con.prepareCall(
                            "{call SP_ACTUALIZAR_EMPLEADO(?,?,?,?)}"
                    );

            cs.setInt(1, id);
            cs.setString(2, nombre);
            cs.setString(3, cargo);
            cs.setDouble(4, salario);

            cs.execute();

            System.out.println(
                    "Empleado actualizado"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    // ELIMINAR
    public void eliminarEmpleado(int id) {

        try {

            CallableStatement cs =
                    con.prepareCall(
                            "{call SP_ELIMINAR_EMPLEADO(?)}"
                    );

            cs.setInt(1, id);

            cs.execute();

            System.out.println(
                    "Empleado eliminado"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
