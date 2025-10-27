package br.com.techmaster.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    // URL de conexão JDBC para o banco de dados H2 em arquivo
    // 'database.h2.db' será criado na pasta raíz do seu projeto
    private static final String URL = "jdbc:h2:~/gestao_riscos_db";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Bloco estático para carregar o driver JDBC e criar as tabelas na inicialização
    static {
        try {
            // Carrega o driver do H2
            Class.forName("org.h2.Driver");
            // Tenta criar as tabelas ao iniciar a aplicação
            criarTabelas();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver H2 não encontrado!", e);
        }
    }

    // Método para obter uma nova conexão com o banco de dados
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
        }
    }
    
    // Método para criar as tabelas se elas não existirem
    private static void criarTabelas() {
        // Usamos try-with-resources para garantir que a conexão e o statement sejam fechados
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            
            String sqlTipoRisco = "CREATE TABLE IF NOT EXISTS TIPO_RISCO (" +
                                  "ID INT AUTO_INCREMENT PRIMARY KEY," +
                                  "NOME VARCHAR(255) NOT NULL)";
            stmt.execute(sqlTipoRisco);
            
            String sqlRisco = "CREATE TABLE IF NOT EXISTS RISCO (" +
                              "ID INT AUTO_INCREMENT PRIMARY KEY," +
                              "DESCRICAO VARCHAR(1000) NOT NULL," +
                              "ORIGEM VARCHAR(500)," +
                              "CONTEXTO VARCHAR(1000)," +
                              "DATA_IDENTIFICACAO DATE NOT NULL," +
                              "STATUS VARCHAR(100) NOT NULL," + // Ativo, Mitigado, Encerrado
                              "ID_TIPO_RISCO INT," +
                              "FOREIGN KEY (ID_TIPO_RISCO) REFERENCES TIPO_RISCO(ID))";
            stmt.execute(sqlRisco);
            
            String sqlAvaliacao = "CREATE TABLE IF NOT EXISTS AVALIACAO_RISCO (" +
                                  "ID INT AUTO_INCREMENT PRIMARY KEY," +
                                  "ID_RISCO INT NOT NULL," +
                                  "DATA_AVALIACAO DATE NOT NULL," +
                                  "RESPONSAVEL VARCHAR(255) NOT NULL," +
                                  "IMPACTO INT NOT NULL," + // Ex: 1 a 5
                                  "PROBABILIDADE INT NOT NULL," + // Ex: 1 a 5
                                  "URGENCIA INT NOT NULL," + // Ex: 1 a 5
                                  "JUSTIFICATIVA VARCHAR(1000)," +
                                  "FOREIGN KEY (ID_RISCO) REFERENCES RISCO(ID))";
            stmt.execute(sqlAvaliacao);
            
             // Você pode adicionar as outras tabelas (PLANO_MITIGACAO, ACAO_CORRETIVA, etc.) aqui da mesma forma

            System.out.println("Tabelas criadas com sucesso (se não existiam).");
            
        } catch (SQLException e) {
            // Não lançamos exceção aqui para não parar a aplicação se as tabelas já existirem
            // e houver algum outro problema. Apenas logamos o erro.
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}