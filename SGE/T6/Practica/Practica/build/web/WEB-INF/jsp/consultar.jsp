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
            
            <ol class="breadcrumb">
                <li><a href="<c:out value="home.htm"/>">Mantenimiento de Encuestas</a></li>
                <li class="active">Consultar respuesta</li>
            </ol>
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><c:out value="${pregunta.id}"></c:out></td>
                        </tr>
                    </tbody>
                </table>

                    <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Pregunta</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><c:out value="${pregunta.textoPregunta}"></c:out></td>
                        </tr>
                    </tbody>
                </table>
                       
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th style="width: 80%">Respuesta</th>
                        <th style="width: 5%">Num.</th>
                        <th style="width: 20%">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${Respuestas}" var="dato">

                        <tr>
                            <td><c:out value="${dato.textoRespuesta}"></c:out></td>
                            <td><c:out value="${dato.numeroRespuestas}"></c:out></td>
                                <td>
                                    <a href="<c:url value="editarRespuesta.htm?idEncuesta=${pregunta.id}&idRespuesta=${dato.idRespuesta}"></c:url>" class="btn btn-primary btn-xs">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true">
                                        </span> Editar
                                    </a>
                                    <a href="<c:url value="borrarRespuesta.htm?idEncuesta=${pregunta.id}&idRespuesta=${dato.idRespuesta}"></c:url>" class="btn btn-danger btn-xs">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true">
                                        </span> Borrar
                                    </a>    
                                </td>
                            </tr>
                    </c:forEach>
                <td>
                    <a href="<c:url value="home.htm"/>" class="btn btn-warning">
                            <span class="glyphicon glyphicon-home" aria-hidden="true">
                            </span> Volver 
                    </a>
                </td>
                <td></td>
                <td>
                    <p style="text-align: center">
                        <a href="<c:url value="agregarRespuesta.htm?idEncuesta=${pregunta.id}"></c:url>" class="btn btn-success">
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


