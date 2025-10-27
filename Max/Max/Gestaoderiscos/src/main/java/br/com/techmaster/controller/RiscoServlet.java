package br.com.techmaster.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.techmaster.dal.RiscoDAO;
import br.com.techmaster.dal.TipoRiscoDAO;
import br.com.techmaster.model.Risco;
import br.com.techmaster.model.TipoRisco;

@WebServlet("/risco")
public class RiscoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RiscoDAO riscoDAO;
    private TipoRiscoDAO tipoRiscoDAO;

    public void init() {
        riscoDAO = new RiscoDAO();
        tipoRiscoDAO = new TipoRiscoDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar"; // Ação padrão
        }

        try {
            switch (action) {
                case "mostrarFormularioNovo":
                    mostrarFormularioNovo(request, response);
                    break;
                case "adicionar":
                    adicionarRisco(request, response);
                    break;
                case "deletar":
                    deletarRisco(request, response);
                    break;
                case "mostrarFormularioEdicao":
                    mostrarFormularioEdicao(request, response);
                    break;
                case "atualizar":
                    atualizarRisco(request, response);
                    break;
                default:
                    listarRiscos(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarRiscos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Risco> listaRiscos = riscoDAO.listarTodos();
        request.setAttribute("listaRiscos", listaRiscos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("risco-lista.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        // Precisamos carregar os tipos de risco para o dropdown do formulário
        List<TipoRisco> listaTiposRisco = tipoRiscoDAO.listarTodos();
        request.setAttribute("listaTiposRisco", listaTiposRisco);
        RequestDispatcher dispatcher = request.getRequestDispatcher("risco-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Risco riscoExistente = riscoDAO.buscarPorId(id);
        List<TipoRisco> listaTiposRisco = tipoRiscoDAO.listarTodos();
        request.setAttribute("risco", riscoExistente);
        request.setAttribute("listaTiposRisco", listaTiposRisco);
        RequestDispatcher dispatcher = request.getRequestDispatcher("risco-form.jsp");
        dispatcher.forward(request, response);
    }

    private void adicionarRisco(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Risco novoRisco = new Risco();
        popularRiscoComRequest(request, novoRisco);
        riscoDAO.adicionar(novoRisco);
        response.sendRedirect("risco");
    }
    
    private void atualizarRisco(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Risco risco = new Risco();
        risco.setId(Integer.parseInt(request.getParameter("id")));
        popularRiscoComRequest(request, risco);
        riscoDAO.atualizar(risco);
        response.sendRedirect("risco");
    }

    private void deletarRisco(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        riscoDAO.deletar(id);
        response.sendRedirect("risco");
    }
    
    // Método auxiliar para popular o objeto Risco a partir do request
    private void popularRiscoComRequest(HttpServletRequest request, Risco risco) {
        risco.setDescricao(request.getParameter("descricao"));
        risco.setOrigem(request.getParameter("origem"));
        risco.setContexto(request.getParameter("contexto"));
        risco.setDataIdentificacao(Date.valueOf(request.getParameter("dataIdentificacao")));
        risco.setStatus(request.getParameter("status"));
        
        int tipoRiscoId = Integer.parseInt(request.getParameter("tipoRisco"));
        TipoRisco tipoRisco = new TipoRisco();
        tipoRisco.setId(tipoRiscoId);
        risco.setTipoRisco(tipoRisco);
    }
}