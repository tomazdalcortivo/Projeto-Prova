package br.edu.ifpr.irati.ads.dao;

import br.edu.ifpr.irati.ads.modelo.Candidato;
import br.edu.ifpr.irati.ads.db.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {

    public CandidatoDAO() {
        try (Connection conn = new Conexao().Connect()) {
            criarTabela(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void criarTabela(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS candidato ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "cpf VARCHAR(11) NOT NULL UNIQUE)";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeStatemant(stmt);
        }
    }

    public void adicionarCandidato(Candidato candidato) {
        String sql = "INSERT INTO candidato (cpf) VALUES (?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, candidato.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }

    public Candidato buscarCandidatoPorId(int id) {
        String sql = "SELECT * FROM candidato WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String cpf = rs.getString("cpf");
                return new Candidato(id, cpf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeResultSet(rs);
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
        return null;
    }

    public List<Candidato> buscarTodosCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidato";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                candidatos.add(new Candidato(id, cpf));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeResultSet(rs);
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
        return candidatos;
    }

    public void atualizarCandidato(Candidato candidato) {
        String sql = "UPDATE candidato SET cpf = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, candidato.getCpf());
            stmt.setInt(2, candidato.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }

    public void deletarCandidato(int id) {
        String sql = "DELETE FROM candidato WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }
}
