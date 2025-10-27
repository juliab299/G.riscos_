package br.com.techmaster.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.techmaster.model.Risco;
import br.com.techmaster.model.TipoRisco;

public class RiscoDAO {

    public void adicionar(Risco risco) throws SQLException {
        String sql = "INSERT INTO RISCO (DESCRICAO, ORIGEM, CONTEXTO, DATA_IDENTIFICACAO, STATUS, ID_TIPO_RISCO) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, risco.getDescricao());
            stmt.setString(2, risco.getOrigem());
            stmt.setString(3, risco.getContexto());
            stmt.setDate(4, risco.getDataIdentificacao());
            stmt.setString(5, risco.getStatus());
            stmt.setInt(6, risco.getTipoRisco().getId());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Risco risco) throws SQLException {
        String sql = "UPDATE RISCO SET DESCRICAO = ?, ORIGEM = ?, CONTEXTO = ?, DATA_IDENTIFICACAO = ?, STATUS = ?, ID_TIPO_RISCO = ? WHERE ID = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, risco.getDescricao());
            stmt.setString(2, risco.getOrigem());
            stmt.setString(3, risco.getContexto());
            stmt.setDate(4, risco.getDataIdentificacao());
            stmt.setString(5, risco.getStatus());
            stmt.setInt(6, risco.getTipoRisco().getId());
            stmt.setInt(7, risco.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM RISCO WHERE ID = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Risco> listarTodos() throws SQLException {
        List<Risco> riscos = new ArrayList<>();
        // Usamos LEFT JOIN para garantir que riscos sem tipo também apareçam (se for o caso)
        String sql = "SELECT r.*, tr.NOME as TIPO_NOME FROM RISCO r " +
                     "LEFT JOIN TIPO_RISCO tr ON r.ID_TIPO_RISCO = tr.ID ORDER BY r.ID DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                riscos.add(mapResultSetToRisco(rs));
            }
        }
        return riscos;
    }

    public Risco buscarPorId(int id) throws SQLException {
        Risco risco = null;
        String sql = "SELECT r.*, tr.NOME as TIPO_NOME FROM RISCO r " +
                     "LEFT JOIN TIPO_RISCO tr ON r.ID_TIPO_RISCO = tr.ID WHERE r.ID = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    risco = mapResultSetToRisco(rs);
                }
            }
        }
        return risco;
    }

    // Método auxiliar para não repetir código de mapeamento
    private Risco mapResultSetToRisco(ResultSet rs) throws SQLException {
        Risco risco = new Risco();
        risco.setId(rs.getInt("ID"));
        risco.setDescricao(rs.getString("DESCRICAO"));
        risco.setOrigem(rs.getString("ORIGEM"));
        risco.setContexto(rs.getString("CONTEXTO"));
        risco.setDataIdentificacao(rs.getDate("DATA_IDENTIFICACAO"));
        risco.setStatus(rs.getString("STATUS"));

        TipoRisco tipoRisco = new TipoRisco();
        tipoRisco.setId(rs.getInt("ID_TIPO_RISCO"));
        tipoRisco.setNome(rs.getString("TIPO_NOME")); // Usamos o alias que criamos no SQL
        risco.setTipoRisco(tipoRisco);
        
        return risco;
    }
}