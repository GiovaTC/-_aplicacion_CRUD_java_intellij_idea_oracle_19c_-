package dao;

import conexion.ConexionOracle;
import java.sql.*;

public class EmpleadoDAO {

    Connection con;

    public EmpleadoDAO() {
        con = ConexionOracle.conectar();
    }   
}
