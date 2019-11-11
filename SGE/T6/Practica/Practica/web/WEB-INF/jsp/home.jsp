<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Encuestas</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Mantenimiento de Encuestas</h1>
            </div>  

            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th style="width: 5%">ID</th>
                        <th style="width: 75%">Pregunta</th>
                        <th style="width: 20%">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato">

                        <tr>
                            <td><c:out value="${dato.idEncuesta}"></c:out></td>
                            <td><c:out value="${dato.textoPregunta}"></c:out></td>
                                <td>
                                    <a href="<c:url value="consultar.htm?idEncuesta=${dato.idEncuesta}"></c:url>" class="btn btn-warning btn-xs">
                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true">
                                        </span> Consultar
                                    </a>
                                    <a href="<c:url value="edit.htm?idEncuesta=${dato.idEncuesta}"></c:url>" class="btn btn-primary btn-xs">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true">
                                        </span> Editar
                                    </a>
                                <a href="<c:url value="delete.htm?idEncuesta=${dato.idEncuesta}"></c:url>" class="btn btn-danger btn-xs">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true">
                                    </span> Borrar
                                </a>    
                                </td>
                            </tr>
                    </c:forEach>
                <td></td>
                <td></td>
                <td>
                    <p style="text-align: center">
                        <a href="<c:url value="add.htm"/>" class="btn btn-success">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true">
                            </span> Agregar 
                        </a>
                    </p>
                </td>
                </tbody>
            </table>
        </div>
    </body>
</html>


