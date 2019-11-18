
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrar modelo</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    </head>
    <body>
        <div class="container">
            
            <ol class="breadcrumb">
                <li><a href="<c:out value="home.htm"/>">Mantenimiento de modelos</a></li>
                <li><a href="<c:out value="consultar.htm?idMarca=${marcas.idMarca}"/>">Consultar modelos</a></li>
                <li class="active">Borrar Respuesta</li>
            </ol>
            
            <div class="panel panel-danger">
                <div class="panel-heading">Formulario para borrar Respuesta</div>
                <div class="panel-body">
                    <h1>Borrar Respuesta</h1>
                    
                    <form:form method="get" commandName="marcas">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                        <div class="form-group">
                            <form:label path="nombreMarca">Marca</form:label>
                            <form:input path="nombreMarca" cssClass="form-control"></form:input>
                        </div>
                    </form:form>
                    
                <form:form method="post" commandName="modelos">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                    <div class="form-group">
                        <form:label path="nombreModelo">Modelo</form:label>
                        <form:input path="nombreModelo" cssClass="form-control"></form:input>
                    </div>
                    <div class="form-group">
                        <form:label path="stock">Stock</form:label>
                        <form:input path="stock" cssClass="form-control"></form:input>
                    </div>
                    
                    <p  style="text-align: left;">
                            
                        <input class="btn btn-danger" type="submit" value="Borrar Respuesta "/>
                        </p>
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
