
use gimnasio;

db.clientes.insert(
	[
		{
			"_id":1,
			"dni": '5025841d',
			"nombre":"Juan",
			"apellidos":"López",
			"fecha de nacimiento": '20-12-1990',
			"télefono": '631958452',
			"clases":[1]
		},
		{
			"_id":2,
			"dni": '54296418t',
			"nombre":"Paula",
			"apellidos":"López",
			"fecha de nacimiento": '19-03-1991',
			"télefono": '632958743',
			"clases":[1,3]
		}
	]
);

db.monitores.insert(
	[
		{
			"id_monitor": 1,
			"nombre": "José",
			"apellidos": "González",
            "clases_Impartido": [1,3]
			
		},
		{
			"id_monitor": 2,
			"nombre":"Ramón",
			"apellidos":"Fernández",
            "clases_Impartido":[2]
		}
	]
);

db.clases.insert(
	[
		{
			"cod_clase":1,
            "nombre": "Pilates",
            "horasSemanales": 3,
            "Monitor":[1]
		},
		{
			"cod_clase":2,
            "nombre": "Spinning",
            "horasSemanales": 4,
            "Monitor":[2]
		},
		{
			"cod_clase":3,
            "nombre": "Yoga",
            "horasSemanales": 2,
            "Monitor":[1]
		}
		
	]
)



