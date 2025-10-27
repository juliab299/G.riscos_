<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestão de Riscos</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1>Gestão de Riscos</h1>
            <a href="risco?action=mostrarFormularioNovo" class="btn btn-success">Adicionar Novo Risco</a>
        </div>
        
        <a href="tipo-risco">Gerenciar Tipos de Risco</a>
        
        <hr>
        
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Descrição</th>
                    <th>Tipo</th>
                    <th>Status</th>
                    <th>Data Identificação</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="risco" items="${listaRiscos}">
                    <tr>
                        <td>${risco.id}</td>
                        <td>${risco.descricao}</td>
                        <td>${risco.tipoRisco.nome}</td>
                        <td>${risco.status}</td>
                        <td>${risco.dataIdentificacao}</td>
                        <td>
                            <a href="risco?action=mostrarFormularioEdicao&id=${risco.id}" class="btn btn-primary btn-sm">Editar</a>
                            <a href="risco?action=deletar&id=${risco.id}" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja deletar este risco?')">Deletar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>