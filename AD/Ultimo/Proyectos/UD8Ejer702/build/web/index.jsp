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
                    <h1>ESCRIBE TU NOMBRE</h1>
                </div>
                <div class="contenedor-form">
                    <label for="nombre">NOMBRE</label><br>
                    <input id="nombre" name="nombre" type="text" />
                </div>
                <div class="contenedor-form">
                    <input id="btn_submit" name="submit" type="submit" value="Mostrar" />
                </div>
            </form>
            <% } else {
            String nombre = request.getParameter("nombre");
                    String cadena = "";
                    for (int i = 0; i < nombre.length(); i++) {
                        char c = nombre.charAt(i);
                        if(nombre.length() != i)
                            cadena += c + " ";
                        else
                            cadena += c;
                        }
            %>
            <div class="contenedor-form">
                <h1>TU NOMBRE ES</h1>
            </div>
            <div class="contenedor-form">
                <p class="rotulo"><%= cadena %></p>
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
