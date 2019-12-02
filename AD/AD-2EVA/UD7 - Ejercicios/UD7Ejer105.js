
use libreria

db.libros.insert(
	[
		{
			"ISBN": '978-84-1545-219-5',
			"título":"Implantación de Sistemas Operativos",
			"autores":['Juan López'],
			"paginas":500,
			"año de edición":2012,
			"editorial":'garceta',
			"imagen de portada":'http://garceta.es/images/2195_IMSO.gif'
		},
		{
			"ISBN": '978-84-1545-262-1',
			"título":"Sistemas de Gestión Empresarial",
			"autores":['César San Juan'],
			"paginas":292,
			"año de edición":2013,
			"editorial":'garceta',
			"imagen de portada":'http://garceta.es/images/2621_SGE.gif'
		},
	]
);