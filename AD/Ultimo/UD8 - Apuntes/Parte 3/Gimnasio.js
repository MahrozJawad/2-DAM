// GIMNASIO
use gimnasio

// Insertar clientes
db.clientes.insert(
	{
		dni:		'21222333', 
		nombre:		'Juan',
		apellidos:	'López',
		fecha_nac:	'1984-10-15',
		telefono:	'651344566'
	}
  )

db.clientes.insert(
	{
		dni:		'21333444', 
		nombre:		'Gloria',
		apellidos:	'Rodríguez',
		fecha_nac:	'1998-12-05',
		telefono:	'651322478'
	}
  )

db.clientes.insert(
	{
		dni:		'21444555', 
		nombre:		'Miguel',
		apellidos:	'Soler',
		fecha_nac:	'1968-03-12',
		telefono:	'651348945'
	}
  )


// Insertar monitores
db.monitores.insert(
	{
		id_monitor:		'M01', 
		nombre:			'Sergio',
		apellidos:		'Fernández'
	}
  )

db.monitores.insert(
	{
		id_monitor:		'M02', 
		nombre:			'Ana',
		apellidos:		'García'
	}
  )


// Insertar clases
db.clases.insert(
	{
		cod_clase:		'P01', 
		nombre:			'Pilates',
		horas_sem:		4,
		impartida_por:	'M01',
		asisten: [
				'21222333', 
				'21333444'
			]
	}
  )

db.clases.insert(
	{
		cod_clase:		'S01', 
		nombre:			'Spinning',
		horas_sem:		4,
		impartida_por:	'M02',
		asisten: [
				'21222333', 
				'21333444',
				'21444555'
			]
	}
  )
  
db.clases.insert(
	{
		cod_clase:		'Y01', 
		nombre:			'Yoga',
		horas_sem:		6,
		impartida_por:	'M02',
		asisten: [
				'21333444',
				'21444555'
			]
	}
  )
  
  
// Actualizar comunidades

db.clientes.find().pretty()
db.monitores.find().pretty()
db.clases.find().pretty()
