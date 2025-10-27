<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulário de Risco</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <c:if test="${risco != null}">
            <h1>Editar Risco</h1>
            <form action="risco?action=atualizar" method="post">
            <input type="hidden" name="id" value="<c:out value='${risco.id}' />" />
        </c:if>
        <c:if test="${risco == null}">
            <h1>Adicionar Novo Risco</h1>
            <form action="risco?action=adicionar" method="post">
        </c:if>

            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea class="form-control" id="descricao" name="descricao" rows="3" required><c:out value='${risco.descricao}' /></textarea>
            </div>
            
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="origem">Origem:</label>
                    <input type="text" class="form-control" id="origem" name="origem" value="<c:out value='${risco.origem}' />">
                </div>
                <div class="form-group col-md-6">
                    <label for="contexto">Contexto:</label>
                    <input type="text" class="form-control" id="contexto" name="contexto" value="<c:out value='${risco.contexto}' />">
                </div>
            </div>

            <div class="form-row">
                 <div class="form-group col-md-4">
                    <label for="tipoRisco">Tipo de Risco:</label>
                    <select id="tipoRisco" name="tipoRisco" class="form-control" required>
                        <option value="">Selecione...</option>
                        <c:forEach var="tipo" items="${listaTiposRisco}">
                            <option value="${tipo.id}" ${tipo.id == risco.tipoRisco.id ? 'selected' : ''}>
                                <c:out value="${tipo.nome}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label for="status">Status:</label>
                    <select id="status" name="status" class="form-control" required>
                        <option value="Ativo" ${risco.status == 'Ativo' ? 'selected' : ''}>Ativo</option>
                        <option value="Mitigado" ${risco.status == 'Mitigado' ? 'selected' : ''}>Mitigado</option>
                        <option value="Encerrado" ${risco.status == 'Encerrado' ? 'selected' : ''}>Encerrado</option>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label for="dataIdentificacao">Data de Identificação:</label>
                    <input type="date" class="form-control" id="dataIdentificacao" name="dataIdentificacao" value="<c:out value='${risco.dataIdentificacao}' />" required>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Salvar</button>
            <a href="risco" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>