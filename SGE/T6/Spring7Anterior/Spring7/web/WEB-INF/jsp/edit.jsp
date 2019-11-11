
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
                <li><a href="<c:out value="/home.htm"/>">Listado de usuarios</a></li>
                <li class="active">Editar</li>
            </ol>
            
            <div class="panel panel-primary">
                <div class="panel-heading">Formulario para editar usuarios</div>
                <div class="panel-body">
                    <h1>Editar usuario</h1>
                <form:form method="post" commandName="usuarios">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                    <div class="form-group">
                        <form:label path="nombre">Nombre</form:label>
                        <form:input path="nombre" cssClass="form-control"></form:input>
                    </div>
                    <div class="form-group">
                        <form:label path="correo">E-mail</form:label>
                        <form:input path="correo" cssClass="form-control"></form:input>
                    </div>
                    
                    <input class="btn-primary" type="submit" value="Enviar">
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
