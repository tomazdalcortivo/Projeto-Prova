package br.edu.ifpr.irati.ads.dao;

import br.edu.ifpr.irati.ads.modelo.Resposta;
import br.edu.ifpr.irati.ads.modelo.Questao;
import br.edu.ifpr.irati.ads.modelo.Candidato;
import br.edu.ifpr.irati.ads.db.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RespostaDAO {

    public RespostaDAO() {
        try (Connection conn = new Conexao().Connect()) {
            criarTabela(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void criarTabela(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS resposta ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "questao_id INTEGER NOT NULL, "
                   + "candidato_id INTEGER NOT NULL, "
                   + "soma INTEGER NOT NULL, "
                   + "FOREIGN KEY (questao_id) REFERENCES questao(id), "
                   + "FOREIGN KEY (candidato_id) REFERENCES candidato(id))";
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

    public void adicionarResposta(Resposta resposta) {
        String sql = "INSERT INTO resposta (questao_id, candidato_id, soma) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, resposta.getQuestao().getId());
            stmt.setInt(2, resposta.getCandidato().getId());
            stmt.setInt(3, resposta.getSoma());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }

    public Resposta buscarRespostaPorId(int id) {
        String sql = "SELECT * FROM resposta WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Questao questao = new QuestaoDAO().buscarQuestaoPorId(rs.getInt("questao_id"));
                Candidato candidato = new CandidatoDAO().buscarCandidatoPorId(rs.getInt("candidato_id"));
                int soma = rs.getInt("soma");
                return new Resposta(id, questao, candidato, soma);
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

    public List<Resposta> buscarTodasRespostas() {
        List<Resposta> respostas = new ArrayList<>();
        String sql = "SELECT * FROM resposta";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Questao questao = new QuestaoDAO().buscarQuestaoPorId(rs.getInt("questao_id"));
                Candidato candidato = new CandidatoDAO().buscarCandidatoPorId(rs.getInt("candidato_id"));
                int soma = rs.getInt("soma");
                respostas.add(new Resposta(id, questao, candidato, soma));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeResultSet(rs);
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
        return respostas;
    }

    public void atualizarResposta(Resposta resposta) {
        String sql = "UPDATE resposta SET questao_id = ?, candidato_id = ?, soma = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, resposta.getQuestao().getId());
            stmt.setInt(2, resposta.getCandidato().getId());
            stmt.setInt(3, resposta.getSoma());
            stmt.setInt(4, resposta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }

    public void deletarResposta(int id) {
        String sql = "DELETE FROM resposta WHERE id = ?";
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
