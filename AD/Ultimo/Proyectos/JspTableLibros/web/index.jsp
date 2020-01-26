<%-- 
    Document   : index
    Created on : 21-ene-2020, 19:16:01
    Author     : Mahroz
--%>

<%@page import="com.dam.Libros"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mahroz Jawad</title>
    </head>
    <style>
        table {
            border-collapse: collapse;
            border:1px solid black;
            background-color: #dddddd;
            width:auto;
            text-align:center;
        }
        td {
            width:auto;
        }
        .cabecera {
            background-color: orange;
            font-weight:bold;
            width: auto;
        }
        .estiloCeldas {
            background: white;
            text-align: left;
            
        }
    </style>
    <body>
        <%
            ArrayList<Libros> datos = new ArrayList<Libros>();
            Libros registro;
            registro = new Libros();
            registro.setId(1);
            registro.setTitulo("Macbeth");
            registro.setAutor("William Shakespeare");
            datos.add(registro);
            registro = new Libros();
            registro.setId(2);
            registro.setTitulo("La Celestina (Tragicomedia de Calisto y Melibea)");
            registro.setAutor("Fernando de Rojas");
            datos.add(registro);
            registro = new Libros();
            registro.setId(3);
            registro.setTitulo("El Lazarillo de Tormes");
            registro.setAutor("Anónimo");
            datos.add(registro);
            registro = new Libros();
            registro.setId(4);
            registro.setTitulo("20.000 Leguas de Viaje Submarino");
            registro.setAutor("Julio Verne");
            datos.add(registro);
            registro = new Libros();
            registro.setId(5);
            registro.setTitulo("Alicia en el País de las Maravillas");
            registro.setAutor("Lewis Carrol");
            datos.add(registro);
            registro = new Libros();
            registro.setId(6);
            registro.setTitulo("Cien Años de Soledad");
            registro.setAutor("Gabriel García Márquez");
            datos.add(registro);
            registro = new Libros();
            registro.setId(7);
            registro.setTitulo("La tempestad");
            registro.setAutor("William Shakespeare");
            datos.add(registro);
        %>
        <h5>Número de Registros: <%= datos.size()%></h5>
        <table border="1">
            <tr>
                <td class="cabecera">ID Libro</td>
                <td class="cabecera">Título</td>
                <td class="cabecera">Autor</td>
            </tr>

            <% for (int i = 0; i < datos.size(); i++) {%>
            <tr>
                <td class="estiloCeldas"><%= i %></td>
                <td class="estiloCeldas"><%= datos.get(i).getTitulo() %></td>
                <td class="estiloCeldas"><%= datos.get(i).getAutor() %></td>
            </tr>
            <% }%>
        </table>
    </body>
</html>
