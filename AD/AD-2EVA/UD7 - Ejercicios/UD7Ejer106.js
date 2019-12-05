
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
			"clase":[1,2]
		}
	]
);

db.monitores.insert(
	[
		{
			"id_monitor": 1,
			"nombre": "José"
			"apellidos": "González"
			
		},
		{
			"id_monitor": 2,
			"nombre":"Ramón",
			"apellidos":"Fernández"
		}
	]
);

db.clases.insert(
	[
		{
			"cod_clase":1
		},
		{
			"cod_clase":2
		},
		{
			"cod_clase":3
		}
		
	]
)



