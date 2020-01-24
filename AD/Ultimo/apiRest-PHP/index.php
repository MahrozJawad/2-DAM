<?php
	session_start();
	
	require_once "apirest_variables.php";
	require_once "inc/apirest_lecturaparam.php";
	
	require_once "inc/apirest_metodos.php";
	require_once "inc/apirest_funciones.php";
	require_once "inc/apirest_mysql.php";

	
	$tablasValidas=array();
	foreach ($tablas as $key => $value) {
		$tablasValidas[]=$key;
	}
	
	
	// ********************************************** 	
	// GESTIONAR CADA MÉTODO
	
	$mostrarData=false;
	$mostrarManual=false;
	if (isset($params['action'])) {
		if ($params['action']=='datos') {
			$mostrarData=true;
		}
		if ($params['action']=='') {
			$mostrarManual=true;
		}
	} else {
		$mostrarManual=true;
	}
	
	if ($mostrarData) {
		mostrarDatos();
	} 
	
	if ($mostrarManual) {
		require_once "inc/manual.php";
	} 
	
	if ( (!$mostrarData) && (!$mostrarManual) ) {
		switch ($method) {
			case 'GET': //consulta
				metodoGET();
				break;     
			case 'POST': //inserta
				if (count($_POST)>0) {
					metodoPOST($_POST);			
				} else {
					metodoPOST($arrayDatos);			
				}
				break;                
			case 'PUT': //actualiza
				metodoPUT($arrayDatos);
				break;      
			case 'DELETE': //elimina
				metodoDELETE();
				break;
			default: // Método NO soportado
				metodoNoSoportado();
				break;
		}		
	}
	
	unset($_SESSION['sqlQuery']);
	unset($_SESSION['sqlError']);
