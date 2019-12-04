
db.comunidades.update(
	{comunidad:'Comunidad Valenciana'},
	{
		$set: {provincias: ["Alicante","Castellón","Valencia","Murcia"]}
	}
)

db.comunidades.update(
	{comunidad:'Aragón'},
	{
		$set: {provincias: ["Huesca","Zaragoza","Teruel"]}
	}
)

db.comunidades.update(
	{comunidad:'Cataluña'},
	{
		$set: {provincias: ["Barcelona","Gerona","Lérida","Tarragona"]}
	}
)

db.comunidades.update(
	{comunidad:'Extremadura'},
	{
		$set: {provincias: ["Cáceres","Badajoz"]}
	}
)

