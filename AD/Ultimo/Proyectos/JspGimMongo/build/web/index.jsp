<%@page import="com.dam.gimmongoclass.Gimnasio"%>
<%@page import="com.dam.gimmongoclass.Cliente"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mahorz Jawad</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
    </head>
    <body>
        <div class="contenedor">
      <%
          String valueBoton = (String)request.getParameter("submit");   
        if(valueBoton == null || valueBoton.equals("Volver")) { %>
        
            <form id="formulario" name="formulario" action="#" method="POST" >
                <div class="contenedor-form">
                    <h1>Buscar Cliente: </h1>
                </div>
                <div class="contenedor-form">
                    <label for="nombre">DNI</label><br>
                    <input id="dni" name="dni" type="text" />
                </div>
                <div class="contenedor-form">
                    <input id="btn_submit" name="submit" type="submit" value="Mostrar" />
                </div>
            </form>
            <% } else {
            String dni = request.getParameter("dni");
            Cliente c = Gimnasio.obtieneCliente(dni);
            %>
            <div class="contenedor-form">
                <h1>TU NOMBRE ES</h1>
            </div>
            <div class="contenedor-form">
                <p>Nombre: <span class="rotulo"><%= c.getNombre() %></span></p>
                <p>Apellidos: <span class="rotulo"><%= c.getApellidos() %></span></p>
                <p>Fecha de nacimiento: <span class="rotulo"><%= c.getFecha_nac() %></span></p>
                <p>Teléfono: <span class="rotulo"><%= c.getTelefono() %></span></p>
            </div>
            <div class="contenedor-form">
                <form id="formulario" name="formulario" action="#" method="POST" >
                <input id="btn_submit" name="btn_submit" type="submit" value="Volver" />
                </form>
            </div>
            <% } %>
            
        </div>
    </body>
</html>
