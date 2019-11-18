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
                <li><a href="<c:out value="home.htm"/>">Mantenimiento de marcas</a></li>
                <li class="active">Consultar modelos</li>
            </ol>
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Id Marca</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><c:out value="${marca.idMarca}"></c:out></td>
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
                            <td><c:out value="${marca.nombreMarca}"></c:out></td>
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
                    <c:forEach items="${Modelos}" var="dato">

                        <tr>
                            <td><c:out value="${dato.nombreModelo}"></c:out></td>
                            <td><c:out value="${dato.stock}"></c:out></td>
                            <td style="text-align: center">
                                    <a href="<c:url value="editarRespuesta.htm?idMarca=${marca.idMarca}&idModelo=${dato.idModelo}"></c:url>" class="btn btn-primary btn-xs">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true">
                                        </span> Editar
                                    </a>
                                    <a href="<c:url value="borrarRespuesta.htm?idMarca=${marca.idMarca}&idModelo=${dato.idModelo}"></c:url>" class="btn btn-danger btn-xs">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true">
                                        </span> Borrar
                                    </a>    
                                </td>
                            </tr>
                    </c:forEach>
                <td>
                </td>
                <td></td>
                <td>
                    <p style="text-align: center">
                        <a href="<c:url value="agregarRespuesta.htm?idMarca=${marca.idMarca}"></c:url>" class="btn btn-success">
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


