<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema de Gestão de Riscos</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    body { padding: 50px; }
    .container { max-width: 600px; }
    .card { margin-bottom: 20px; }
</style>
</head>
<body>
    <div class="container text-center">
        <h1>Bem-vindo ao Sistema de Gestão de Riscos</h1>
        <p class="lead">Selecione uma opção abaixo para começar.</p>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Riscos</h5>
                <p class="card-text">Cadastre, avalie e gerencie os riscos do projeto.</p>
                <a href="risco" class="btn btn-primary">Gerenciar Riscos</a>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Tipos de Risco</h5>
                <p class="card-text">Administre as categorias para classificação dos riscos.</p>
                <a href="tipo-risco" class="btn btn-secondary">Gerenciar Tipos de Risco</a>
            </div>
        </div>
    </div>
</body>
</html>