<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Avaliações do Risco</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <a href="risco" class="btn btn-secondary mb-3"> &larr; Voltar para a Lista de Riscos</a>
        
        <div class="card mb-4">
            <div class="card-header">
                <h3>Detalhes do Risco</h3>
            </div>
            <div class="card-body">
                <h5 class="card-title">ID: ${risco.id} - ${risco.descricao}</h5>
                <p class="card-text"><strong>Tipo:</strong> ${risco.tipoRisco.nome} | <strong>Status:</strong> ${risco.status}</p>
            </div>
        </div>

        <hr>

        <h4>Adicionar Nova Avaliação</h4>
        <div class="card card-body mb-4">
            <form action="avaliacao?action=adicionar" method="post">
                <input type="hidden" name="riscoId" value="${risco.id}">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="responsavel">Responsável:</label>
                        <input type="text" class="form-control" id="responsavel" name="responsavel" required>
                    </div>
                    <div class="form-group col-md-4">
                         <label for="dataAvaliacao">Data da Avaliação:</label>
                         <input type="date" class="form-control" id="dataAvaliacao" name="dataAvaliacao" required>
                    </div>
                </div>
                 <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="impacto">Impacto (1-5):</label>
                        <input type="number" class="form-control" id="impacto" name="impacto" min="1" max="5" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="probabilidade">Probabilidade (1-5):</label>
                        <input type="number" class="form-control" id="probabilidade" name="probabilidade" min="1" max="5" required>
                    </div>
                     <div class="form-group col-md-4">
                        <label for="urgencia">Urgência (1-5):</label>
                        <input type="number" class="form-control" id="urgencia" name="urgencia" min="1" max="5" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="justificativa">Justificativa:</label>
                    <textarea class="form-control" id="justificativa" name="justificativa" rows="2"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Salvar Avaliação</button>
            </form>
        </div>
        
        <h4>Histórico de Avaliações</h4>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Data</th>
                    <th>Responsável</th>
                    <th>Impacto</th>
                    <th>Probabilidade</th>
                    <th>Urgência</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="avaliacao" items="${listaAvaliacoes}">
                    <tr>
                        <td>${avaliacao.dataAvaliacao}</td>
                        <td>${avaliacao.responsavel}</td>
                        <td>${avaliacao.impacto}</td>
                        <td>${avaliacao.probabilidade}</td>
                        <td>${avaliacao.urgencia}</td>
                        <td>
                            <a href="avaliacao?action=deletar&id=${avaliacao.id}&riscoId=${risco.id}" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza?')">Deletar</a>
                        </td>
                    </tr>
                </c:forEach>
                 <c:if test="${empty listaAvaliacoes}">
                    <tr>
                        <td colspan="6" class="text-center">Nenhuma avaliação encontrada para este risco.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>