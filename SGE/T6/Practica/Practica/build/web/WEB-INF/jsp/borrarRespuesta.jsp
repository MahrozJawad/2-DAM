
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de vista Spring</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    </head>
    <body>
        <div class="container">
            
            <ol class="breadcrumb">
                <li><a href="<c:out value="/home.htm"/>">Mantenimiento de encuestas</a></li>
                <li><a href="<c:out value="/consultar.htm"/>">Consultar respuestas</a></li>
                <li class="active">Borrar Respuesta</li>
            </ol>
            
            <div class="panel panel-danger">
                <div class="panel-heading">Confirmación para borrar usuario</div>
                <div class="panel-body">
                    <h1>Borrar Respuesta</h1>
                    <form:form method="post" commandName="BorrarEncuesta">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                    <div class="form-group">
                        <form:label path="textoPregunta">Pregunta</form:label>
                        <div class="form-control"><c:out value="${textoPregunta}"></c:out></div>
                    </div>
                    <div class="form-group">
                        <form:label path="textoPregunta">Respuesta</form:label>
                        <div class="form-control"><c:out value="${textoPregunta}"></c:out></div>
                    </div>
                    <div class="form-group">
                        <form:label path="textoPregunta">Número</form:label>
                        <div class="form-control"><c:out value="${textoPregunta}"></c:out></div>
                    </div>
                    
                    <p  style="text-align: center;">
                        <a href="<c:url value="home.htm"/>" class="btn btn-warning">
                            <span class="glyphicon glyphicon-home" aria-hidden="true">
                            </span> Volver 
                        </a>
                    <input class="btn btn-danger" type="submit" value="Borrar"/>
                    </p>
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
