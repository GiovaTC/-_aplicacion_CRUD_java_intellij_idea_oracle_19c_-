package dao;

import conexion.ConexionOracle;
import java.sql.*;

public class EmpleadoDAO {

    Connection con;

    public EmpleadoDAO() {

        con = ConexionOracle.conectar();

    }

    // =====================================================
    // INSERTAR EMPLEADO
    // =====================================================

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

            con.commit();

            System.out.println(
                    "Empleado insertado"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // =====================================================
    // LISTAR EMPLEADOS
    // =====================================================

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

            rs.close();
            cs.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // =====================================================
    // ACTUALIZAR EMPLEADO
    // =====================================================

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

            con.commit();

            System.out.println(
                    "Empleado actualizado"
            );

            cs.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // =====================================================
    // ELIMINAR EMPLEADO
    // =====================================================

    public void eliminarEmpleado(int id) {

        try {

            CallableStatement cs =
                    con.prepareCall(
                            "{call SP_ELIMINAR_EMPLEADO(?)}"
                    );

            cs.setInt(1, id);

            cs.execute();

            con.commit();

            System.out.println(
                    "Empleado eliminado"
            );

            cs.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // =====================================================
    // INSERTAR 50 REGISTROS PARA ACTIVAR TRIGGER
    // =====================================================

    public void insertar50Registros() {

        try {

            for (int i = 1; i <= 50; i++) {

                CallableStatement cs =
                        con.prepareCall(
                                "{call SP_INSERTAR_EMPLEADO(?,?,?)}"
                        );

                cs.setString(
                        1,
                        "Empleado " + i
                );

                cs.setString(
                        2,
                        "Desarrollador"
                );

                cs.setDouble(
                        3,
                        2000 + i
                );

                cs.execute();

                cs.close();

            }

            con.commit();

            System.out.println(
                    "50 empleados insertados correctamente"
            );

            System.out.println(
                    "Trigger ejecutado para el registro 50"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}