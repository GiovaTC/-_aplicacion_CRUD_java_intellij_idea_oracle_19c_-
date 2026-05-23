package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    public static Connection conectar() {

        Connection con = null;

        try {

            Class.forName(
                    "oracle.jdbc.driver.OracleDriver"
            );

            con = DriverManager.getConnection(

                    "jdbc:oracle:thin:@localhost:1521:XE",
                    "SYSTEM",
                    "12345"

            );

            // =====================================
            // DESACTIVAR AUTOCOMMIT
            // =====================================

            con.setAutoCommit(false);

            System.out.println(
                    "Conexion exitosa oracle!"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;

    }

}