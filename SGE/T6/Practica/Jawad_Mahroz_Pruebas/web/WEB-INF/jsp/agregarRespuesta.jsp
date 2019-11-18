
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar modelo</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    </head>
    <body>
        <div class="container">
            
            <ol class="breadcrumb">
                <li><a href="<c:out value="home.htm"/>">Mantenimiento de modelo</a></li>
                <li><a href="<c:out value="consultar.htm?idMarca=${marca.idMarca}"/>">Consultar modelos</a></li>
                <li class="active">Agregar modelo</li>
            </ol>
            
            <div class="panel panel-success">
                <div class="panel-heading">Formulario para agregar modelo</div>
                <div class="panel-body">
                    <h1>Agregar modelo</h1>
                    
                <form:form method="post" commandName="marca">
                    <div class="form-group">
                        <form:label path="idMarca">Id Marca</form:label>
                        <form:input path="idMarca" cssClass="form-control"></form:input>
                        <form:errors path="idMarca"></form:errors>
                    </div>
                   
                </form:form>
                     
                <form:form method="post" commandName="modelo">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                   
                    <div class="form-group">
                        <form:label path="nombreModelo">Modelo</form:label>
                        <form:input path="nombreModelo" cssClass="form-control"></form:input>
                        <form:errors path="nombreModelo"></form:errors>
                    </div>
                    
                    <div class="form-group">
                        <form:label path="stock">Stock</form:label>
                        <form:input path="stock" cssClass="form-control"></form:input>
                        <form:errors path="stock"></form:errors>
                    </div>
                    
                    <p  style="text-align: left;">
                            
                            <input class="btn btn-success" type="submit" value="Agregar modelo"/>
                    </p>
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>