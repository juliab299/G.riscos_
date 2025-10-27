package br.com.techmaster.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.techmaster.model.AvaliacaoRisco;

public class AvaliacaoRiscoDAO {

    public void adicionar(AvaliacaoRisco avaliacao) throws SQLException {
        String sql = "INSERT INTO AVALIACAO_RISCO (ID_RISCO, DATA_AVALIACAO, RESPONSAVEL, IMPACTO, PROBABILIDADE, URGENCIA, JUSTIFICATIVA) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, avaliacao.getRiscoId());
            stmt.setDate(2, avaliacao.getDataAvaliacao());
            stmt.setString(3, avaliacao.getResponsavel());
            stmt.setInt(4, avaliacao.getImpacto());
            stmt.setInt(5, avaliacao.getProbabilidade());
            stmt.setInt(6, avaliacao.getUrgencia());
            stmt.setString(7, avaliacao.getJustificativa());
            stmt.executeUpdate();
        }
    }

    public List<AvaliacaoRisco> listarPorRiscoId(int riscoId) throws SQLException {
        List<AvaliacaoRisco> avaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM AVALIACAO_RISCO WHERE ID_RISCO = ? ORDER BY DATA_AVALIACAO DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, riscoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    avaliacoes.add(mapResultSetToAvaliacaoRisco(rs));
                }
            }
        }
        return avaliacoes;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM AVALIACAO_RISCO WHERE ID = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    // Você pode adicionar os métodos 'atualizar' e 'buscarPorId' aqui se precisar da funcionalidade de edição.
    // Por simplicidade, vamos focar em adicionar, listar e deletar por enquanto.

    private AvaliacaoRisco mapResultSetToAvaliacaoRisco(ResultSet rs) throws SQLException {
        AvaliacaoRisco avaliacao = new AvaliacaoRisco();
        avaliacao.setId(rs.getInt("ID"));
        avaliacao.setRiscoId(rs.getInt("ID_RISCO"));
        avaliacao.setDataAvaliacao(rs.getDate("DATA_AVALIACAO"));
        avaliacao.setResponsavel(rs.getString("RESPONSAVEL"));
        avaliacao.setImpacto(rs.getInt("IMPACTO"));
        avaliacao.setProbabilidade(rs.getInt("PROBABILIDADE"));
        avaliacao.setUrgencia(rs.getInt("URGENCIA"));
        avaliacao.setJustificativa(rs.getString("JUSTIFICATIVA"));
        return avaliacao;
    }
}