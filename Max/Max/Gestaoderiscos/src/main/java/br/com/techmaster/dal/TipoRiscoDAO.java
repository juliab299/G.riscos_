package br.com.techmaster.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.techmaster.model.TipoRisco;

public class TipoRiscoDAO {

    public void adicionar(TipoRisco tipoRisco) throws SQLException {
        String sql = "INSERT INTO TIPO_RISCO (NOME) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoRisco.getNome());
            stmt.executeUpdate();
        }
    }

    public List<TipoRisco> listarTodos() throws SQLException {
        List<TipoRisco> tiposRisco = new ArrayList<>();
        String sql = "SELECT * FROM TIPO_RISCO ORDER BY NOME";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tiposRisco.add(new TipoRisco(rs.getInt("ID"), rs.getString("NOME")));
            }
        }
        return tiposRisco;
    }
    
    // Futuramente você criará os métodos atualizar() e deletar() aqui.
}