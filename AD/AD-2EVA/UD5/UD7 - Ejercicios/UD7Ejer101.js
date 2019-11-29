use demografia
db.comunidades.insert([
{comunidad:'Región de Murcia'},
{comunidad:'Castilla la Mancha'},
{comunidad:'Cantabria'},
{comunidad:'Comunidad Valenciana'},
{comunidad:'La Rioja'}
])
db.comunidades.find()

<--------------------Insertar Datos------------------------->
db.comunidades.insert([
	{comunidad:'Andalucía'},
	{comunidad:'Aragón'},
	{comunidad:'Islas Baleres'},
	{comunidad:'Cataluña'},
	{comunidad:'Canarias'},
	{comunidad:'Castilla'},
	{comunidad:'León'},
	{comunidad:'Madrid'},
	{comunidad:'Navarra'},
	{comunidad:'Extremadura'},
	{comunidad:'Galicia'},
	{comunidad:'País Vasco'},
	{comunidad:'Principio de Asturias'},
	{comunidad:'Ceuta'},
	{comunidad:'Melilla'}
])
<--------------------Actualiozar Datos----------------------->
db.comunidades.update(
	{comunidad: 'Navarra'},
	{
		$SET: {comunidad: 'Comunidad Foral de Navarra'}
	}
)
db.comunidades.update(
	{comunidad: 'Madrid'},
	{
		$SET: {comunidad: 'Comunidad de Madrid'}
	}
)

<-----------------Eliminar Datos----------------------------->
db.comunidades.remove(
	{$or:[
		{comunidad: 'Ceuta'},
		{comunidad: 'Melilla'}
	]}
)