
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
                <li class="active">Borrar</li>
            </ol>
            
            <div class="panel panel-danger">
                <div class="panel-heading">Confirmación para borrar usuario</div>
                <div class="panel-body">
                    <h1>Borrar usuario</h1>
                    <form:form method="post" commandName="usuarios">
                    <div class="form-group">
                        <form:label path="nombre">Nombre</form:label>
                        <div class="form-control"><c:out value="${nombre}"></c:out></div>
                    </div>
                    <div class="form-group">
                        <form:label path="correo">E-mail</form:label>
                        <div class="form-control"><c:out value="${correo}"></c:out></div>
                    </div>
                    
                    <input class="btn btn-danger" type="submit" value="Borrar">
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
