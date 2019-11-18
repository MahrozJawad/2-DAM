
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar marca</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    </head>
    <body>
        <div class="container">
            
            <ol class="breadcrumb">
                <li><a href="<c:out value="home.htm"/>">Mantenimiento de marcas</a></li>
                <li class="active">Agregar Marca</li>
            </ol>
            
            <div class="panel panel-success">
                <div class="panel-heading">Formulario para agregar Marca</div>
                <div class="panel-body">
                    <h1>Agregar marca</h1>
                <form:form method="post" commandName="AgregarMarca">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                    <div class="form-group">
                        <form:label path="nombreMarca">Marca</form:label>
                        <form:input path="nombreMarca" cssClass="form-control"></form:input>
                        <form:errors path="nombreMarca"></form:errors>
                    </div>
                    
                    <p  style="text-align: left;">
                        <input class="btn btn-success" type="submit" value="Agregar Encuesta"/>
                    </p>
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
