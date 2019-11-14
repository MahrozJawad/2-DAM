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
            
                <div class="panel-body">
                    <div class="row">
                         <h1>Consultar respuesta</h1>
                    </div>  
                    
                    <form:form method="get" commandName="Pregunta">
                        <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                        <div class="form-group">
                            <form:label path="idEncuesta">id</form:label>
                            <form:input path="idEncuesta" cssClass="form-control"></form:input>
                        </div>

                        <div class="form-group">
                            <form:label path="textoPregunta">Pregunta</form:label>
                            <form:input path="textoPregunta" cssClass="form-control"></form:input>
                        </div>
                    </form:form>
                </div>
                
                
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th style="width: 85%">Respuesta</th>
                        <th style="width: 5%">Num.</th>
                        <th style="width: 15%">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${Respuestas}" var="dato">

                        <tr>
                            <td><c:out value="${dato.textoRespuesta}"></c:out></td>
                            <td><c:out value="${dato.numeroRespuestas}"></c:out></td>
                                <td>
                                    <a href="<c:url value="editarRespuesta.htm"></c:url>" class="btn btn-primary btn-xs">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true">
                                        </span> Editar
                                    </a>
                                    <a href="<c:url value="borrarRespuesta.htm"></c:url>" class="btn btn-danger btn-xs">
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


