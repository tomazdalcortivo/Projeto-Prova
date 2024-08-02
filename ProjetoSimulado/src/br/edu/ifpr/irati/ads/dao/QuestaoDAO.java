package br.edu.ifpr.irati.ads.dao;

import br.edu.ifpr.irati.ads.modelo.Questao;
import br.edu.ifpr.irati.ads.modelo.Disciplina;
import br.edu.ifpr.irati.ads.db.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestaoDAO {

    public QuestaoDAO() {
     
    }

    public String criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS questao ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "numero INTEGER NOT NULL, "
                + "disciplina_id INTEGER NOT NULL, "
                + "texto_introdutorio TEXT, "
                + "enunciado TEXT, "
                + "figura TEXT, "
                + "soma_gabarito INTEGER NOT NULL, "
                + "prova_id INTEGER NOT NULL, "
                + "FOREIGN KEY (prova_id) REFERENCES prova(id), "
                + "FOREIGN KEY (disciplina_id) REFERENCES disciplina(id))";
       return sql;
    }

    public void adicionarQuestao(Questao questao, int provaId) {
        String sql = "INSERT INTO questao (numero, disciplina_id, texto_introdutorio, enunciado, figura, soma_gabarito, prova_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, questao.getNumero());
            stmt.setInt(2, questao.getDisciplina().getId());
            stmt.setString(3, questao.getTextoIntrodutorio());
            stmt.setString(4, questao.getEnunciado());
            stmt.setString(5, questao.getFigura());
            stmt.setInt(6, questao.getSomaGabarito());
            stmt.setInt(7, provaId);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int questaoId = generatedKeys.getInt(1);
                    questao.setId(questaoId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }

    public Questao buscarQuestaoPorId(int id) {
        String sql = "SELECT * FROM questao WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int numero = rs.getInt("numero");
                int disciplinaId = rs.getInt("disciplina_id");
                String textoIntrodutorio = rs.getString("texto_introdutorio");
                String enunciado = rs.getString("enunciado");
                String figura = rs.getString("figura");
                int somaGabarito = rs.getInt("soma_gabarito");
                int provaId = rs.getInt("prova_id");
                Disciplina disciplina = buscarDisciplinaPorId(disciplinaId);
                return new Questao(id, numero, disciplina, textoIntrodutorio, enunciado, figura, null, somaGabarito);
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

    public List<Questao> buscarQuestoesPorProvaId(int provaId) {
        List<Questao> questoes = new ArrayList<>();
        String sql = "SELECT * FROM questao WHERE prova_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, provaId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                int disciplinaId = rs.getInt("disciplina_id");
                String textoIntrodutorio = rs.getString("texto_introdutorio");
                String enunciado = rs.getString("enunciado");
                String figura = rs.getString("figura");
                int somaGabarito = rs.getInt("soma_gabarito");
                Disciplina disciplina = buscarDisciplinaPorId(disciplinaId);
                questoes.add(new Questao(id, numero, disciplina, textoIntrodutorio, enunciado, figura, null, somaGabarito));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeResultSet(rs);
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
        return questoes;
    }

    public void atualizarQuestao(Questao questao) {
        String sql = "UPDATE questao SET numero = ?, disciplina_id = ?, texto_introdutorio = ?, enunciado = ?, figura = ?, soma_gabarito = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questao.getNumero());
            stmt.setInt(2, questao.getDisciplina().getId());
            stmt.setString(3, questao.getTextoIntrodutorio());
            stmt.setString(4, questao.getEnunciado());
            stmt.setString(5, questao.getFigura());
            stmt.setInt(6, questao.getSomaGabarito());
            stmt.setInt(7, questao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }

    public void deletarQuestao(int id) {
        String sql = "DELETE FROM questao WHERE id = ?";
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

    public void deletarQuestoesPorProvaId(int provaId) {
        String sql = "DELETE FROM questao WHERE prova_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, provaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closePreparedStatement(stmt);
            Conexao.closeConnection(conn);
        }
    }

    private Disciplina buscarDisciplinaPorId(int disciplinaId) {
        String sql = "SELECT * FROM disciplina WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = new Conexao().Connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, disciplinaId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Disciplina(id, nome);
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
}
