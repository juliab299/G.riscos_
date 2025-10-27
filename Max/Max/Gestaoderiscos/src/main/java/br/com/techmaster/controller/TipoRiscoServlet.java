package br.com.techmaster.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.techmaster.dal.TipoRiscoDAO;
import br.com.techmaster.model.TipoRisco;

@WebServlet("/tipo-risco") // Define a URL que ativará este Servlet
public class TipoRiscoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TipoRiscoDAO tipoRiscoDAO;

    // O método init é chamado uma vez quando o servlet é carregado pela primeira vez.
    public void init() {
        tipoRiscoDAO = new TipoRiscoDAO();
    }

    // Trata requisições POST (ex: envio de formulários)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Garante que os caracteres (como acentos) sejam interpretados corretamente
        request.setCharacterEncoding("UTF-8");
        doGet(request, response); // Reutiliza a lógica do doGet
    }

    // Trata requisições GET (ex: clicar em um link ou digitar a URL)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar"; // Ação padrão
        }

        try {
            switch (action) {
                case "adicionar":
                    adicionarTipoRisco(request, response);
                    break;
                default:
                    listarTiposRisco(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarTiposRisco(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<TipoRisco> listaTiposRisco = tipoRiscoDAO.listarTodos();
        request.setAttribute("listaTiposRisco", listaTiposRisco);
        RequestDispatcher dispatcher = request.getRequestDispatcher("tipos-risco.jsp");
        dispatcher.forward(request, response);
    }

    private void adicionarTipoRisco(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        if (nome != null && !nome.isEmpty()) {
            TipoRisco novoTipo = new TipoRisco();
            novoTipo.setNome(nome);
            tipoRiscoDAO.adicionar(novoTipo);
        }
        response.sendRedirect("tipo-risco"); // Redireciona para a lista (ação padrão)
    }
}