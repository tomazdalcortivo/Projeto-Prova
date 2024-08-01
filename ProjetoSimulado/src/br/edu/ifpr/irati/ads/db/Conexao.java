package br.edu.ifpr.irati.ads.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    public Connection Connect() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/projetosimulado?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8";
            String usuario = "root";
            String senha = "mysql";

            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            System.out.println("Não encontrei o Driver");
            System.exit(0);
        } catch (SQLException ex) {
            System.out.println("Não conectou com o banco de dados. Verifique os parâmetros de conexão.");
            System.out.println(ex);
            throw ex;
        }
        return null;
    }

    public static void closeStatemant(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
