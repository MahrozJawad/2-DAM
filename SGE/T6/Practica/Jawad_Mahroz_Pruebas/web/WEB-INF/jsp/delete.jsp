
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrar marca</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    </head>
    <body>
        <div class="container">
            
            <ol class="breadcrumb">
                <li><a href="<c:out value="home.htm"/>">Mantenimiento de encuestas</a></li>
                <li class="active">Borrar</li>
            </ol>
            
            <div class="panel panel-danger">
                <div class="panel-heading">Confirmación para borrar marca</div>
                <div class="panel-body">
                    <h1>Borrar marca</h1>
                    <form:form method="post" commandName="BorrarMarca">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                    <div class="form-group">
                        <form:label path="nombreMarca">Pregunta</form:label>
                        <div class="form-control"><c:out value="${nombreMarca}"></c:out></div>
                    </div>
                    
                    <p  style="text-align: left;">
                    <input class="btn btn-danger" type="submit" value="Borrar"/>
                    </p>
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
