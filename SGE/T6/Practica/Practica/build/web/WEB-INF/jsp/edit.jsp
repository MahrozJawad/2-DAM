
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
                <li><a href="<c:out value="home.htm"/>">Mantenimiento de encuestas</a></li>
                <li class="active">Editar</li>
            </ol>
            
            <div class="panel panel-primary">
                <div class="panel-heading">Formulario para editar Encuestas</div>
                <div class="panel-body">
                    <h1>Editar Encuesta</h1>
                <form:form method="post" commandName="preguntas">
                    <form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>
                    <div class="form-group">
                        <form:label path="textoPregunta">Pregunta</form:label>
                        <form:input path="textoPregunta" cssClass="form-control"></form:input>
                    </div>
                    
                    <p  style="text-align: center;">
                        <a style="margin-right: 30%" href="<c:url value="home.htm"/>" class="btn btn-warning">
                            <span class="glyphicon glyphicon-home" aria-hidden="true">
                            </span> Volver 
                        </a>
                            
                        <input class="btn btn-primary" type="submit" value="Editar Encuesta "/>
                    </p>
                </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
