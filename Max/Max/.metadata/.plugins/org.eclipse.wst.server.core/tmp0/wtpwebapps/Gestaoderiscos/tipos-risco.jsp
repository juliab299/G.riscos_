<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestão de Tipos de Risco</title>
<style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    table { width: 100%; border-collapse: collapse; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    form { margin-top: 20px; padding: 15px; border: 1px solid #ccc; border-radius: 5px; }
    input[type=text] { width: 300px; padding: 8px; }
    input[type=submit] { padding: 8px 15px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
</style>
</head>
<body>
    <h1>Gerenciar Tipos de Risco</h1>

    <h3>Adicionar Novo Tipo de Risco</h3>
    <form action="tipo-risco" method="post">
        <input type="hidden" name="action" value="adicionar">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>
        <input type="submit" value="Adicionar">
    </form>

    <hr>

    <h3>Tipos de Risco Cadastrados</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tipo" items="${listaTiposRisco}">
                <tr>
                    <td>${tipo.id}</td>
                    <td>${tipo.nome}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>