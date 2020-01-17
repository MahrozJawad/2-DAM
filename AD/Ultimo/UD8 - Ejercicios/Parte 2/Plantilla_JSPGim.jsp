
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
    </head>
    <body>
   
                <div class="contenedor">
                    <div class="contenedor-form">
                        <h1>CLIENTE</h1>
                    </div>
                    <div class="contenedor-form">
                        <table> 
                            <tr>
                                <td>DNI:</td>
                                <td><span class="rotulo"><% out.print(cliente.getDni()); %></span></td>
                            </tr>
                            <tr>
                                <td>NOMBRE:</td>
                                <td><span class="rotulo"><% out.print(cliente.getNombre()); %></span></td>
                            </tr>
                            <tr>
                                <td>APELLIDOS:</td>
                                <td><span class="rotulo"><% out.print(cliente.getApellidos()); %></span></td>
                            </tr>
                            <tr>
                                <td>NACIMIENTO:</td>
                                <td><span class="rotulo"><% out.print(cliente.getFecha_nac()); %></span></td>
                            </tr>
                            <tr>
                                <td>TELÉFONO:</td>
                                <td><span class="rotulo"><% out.print(cliente.getTelefono()); %></span></td>
                            </tr>
                        </table>
                    </div>
                    <div class="contenedor-form">
                        <form id="formulario" name="formulario" action="#" method="POST" >
                        <input id="btn_submit" name="btn_submit" type="submit" value="Volver" />
                        </form>
                    </div>
                </div>

        
				<div class="contenedor">
					<form id="formulario" name="formulario" action="#" method="POST" >
						<div class="contenedor-form">
							<h1>BUSCAR CLIENTE</h1>
						</div>
						<div class="contenedor-form">
							<label for="dni">DNI</label><br>
							<input id="dni" name="dni" type="text" />
						</div>
						<div class="contenedor-form">
							<input id="btn_submit" name="btn_submit" type="submit" value="Buscar" />
						</div>
					</form>
				</div>
      

    </body>
</html>
