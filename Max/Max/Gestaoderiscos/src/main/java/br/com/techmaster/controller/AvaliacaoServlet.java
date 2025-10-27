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

import br.com.techmaster.dal.AvaliacaoRiscoDAO;
import br.com.techmaster.dal.RiscoDAO;
import br.com.techmaster.model.AvaliacaoRisco;
import br.com.techmaster.model.Risco;

@WebServlet("/avaliacao")
public class AvaliacaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AvaliacaoRiscoDAO avaliacaoDAO;
    private RiscoDAO riscoDAO; // Precisamos dele para buscar os dados do risco

    public void init() {
        avaliacaoDAO = new AvaliacaoRiscoDAO();
        riscoDAO = new RiscoDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar"; 
        }

        try {
            switch (action) {
                case "adicionar":
                    adicionarAvaliacao(request, response);
                    break;
                case "deletar":
                    deletarAvaliacao(request, response);
                    break;
                default: // "listar"
                    listarAvaliacoes(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarAvaliacoes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int riscoId = Integer.parseInt(request.getParameter("riscoId"));
        
        // Busca a lista de avaliações
        List<AvaliacaoRisco> listaAvaliacoes = avaliacaoDAO.listarPorRiscoId(riscoId);
        
        // Busca o objeto do Risco para exibir seus detalhes na página
        Risco risco = riscoDAO.buscarPorId(riscoId);
        
        // Envia os dados para a página JSP
        request.setAttribute("listaAvaliacoes", listaAvaliacoes);
        request.setAttribute("risco", risco);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("avaliacao-lista.jsp");
        dispatcher.forward(request, response);
    }

    private void adicionarAvaliacao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int riscoId = Integer.parseInt(request.getParameter("riscoId"));
        
        AvaliacaoRisco novaAvaliacao = new AvaliacaoRisco();
        novaAvaliacao.setRiscoId(riscoId);
        novaAvaliacao.setDataAvaliacao(Date.valueOf(request.getParameter("dataAvaliacao")));
        novaAvaliacao.setResponsavel(request.getParameter("responsavel"));
        novaAvaliacao.setImpacto(Integer.parseInt(request.getParameter("impacto")));
        novaAvaliacao.setProbabilidade(Integer.parseInt(request.getParameter("probabilidade")));
        novaAvaliacao.setUrgencia(Integer.parseInt(request.getParameter("urgencia")));
        novaAvaliacao.setJustificativa(request.getParameter("justificativa"));
        
        avaliacaoDAO.adicionar(novaAvaliacao);
        
        // Redireciona de volta para a página de listagem de avaliações daquele risco
        response.sendRedirect("avaliacao?action=listar&riscoId=" + riscoId);
    }

    private void deletarAvaliacao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int riscoId = Integer.parseInt(request.getParameter("riscoId")); // Precisamos para redirecionar
        
        avaliacaoDAO.deletar(id);
        
        response.sendRedirect("avaliacao?action=listar&riscoId=" + riscoId);
    }
}