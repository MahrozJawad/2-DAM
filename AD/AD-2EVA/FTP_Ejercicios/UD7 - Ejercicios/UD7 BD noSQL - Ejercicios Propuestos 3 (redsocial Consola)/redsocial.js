use redsocial

db.usuario.drop();

db.usuario.save({
"_id":NumberLong(1),
"nombre":"Ramón",
"apellidos":"García Trueca",
"activo":true,
"fecha":ISODate("2014-09-11T05:03:09.000Z"),
"grupos":["cine","fútbol"],
"amigos":[NumberLong(2)],
"sexo":"V",
"nacimiento":ISODate("1979-10-24T00:00:00.000Z"),
"ubicacion":{"pais":"España","ciudad":"Valencia"},
"comentarios":[{"titulo":"A por ellos","texto":"Oe, oe, oe","fecha":ISODate("2014-09-11T20:30:25.000Z"),"grupo":"fútbol"},{"titulo":"Re: Favorito para el mundial de Brasil","texto":"Pues yo creo que España va a dar de nuevo la campanada","fecha":ISODate("2014-06-03T18:17:00.000Z"),"grupo":"fútbol"}],
"mensajes":[{"para":NumberLong(2),"fecha":ISODate("2014-09-10T20:43:00.000Z"),"texto":"Claro, a las 5 donde siempre?"}],
"total_comentarios":2,
"total_mensajes":1
});



db.usuario.save({
"_id":NumberLong(2),
"nombre":"Arturo",
"apellidos":"Cuenca Bertolín",
"activo":true,
"fecha":ISODate("2014-09-10T15:04:05.000Z"),
"grupos":["teatro","cine"],
"amigos":[NumberLong(1)],
"sexo":"V",
"nacimiento":ISODate("1978-05-04T00:00:00.000Z"),
"ubicacion":{"pais":"España","ciudad":"Valencia"},
"comentarios":[{"titulo":"Hola a todos","texto":"Encantado de apuntarme al grupo","fecha":ISODate("2014-09-10T20:14:15.000Z"),"grupo":"cine"},{"titulo":"Me recomendais una pelicula?","texto":"Me recomendais alguna de las ultimas novedades para este fin de semana? Gracias","fecha":ISODate("2014-09-10T21:03:27.000Z"),"grupo":"cine"}],
"mensajes":[{"para":NumberLong(1),"fecha":ISODate("2014-09-10T20:41:00.000Z"),"texto":"Que te parece si quedamos"},{"para":NumberLong(1),"fecha":ISODate("2014-09-10T20:54:00.000Z"),"texto":"Ok, alli nos vemos"}],
"total_comentarios":2,
"total_mensajes":2
});



db.usuario.save({
"_id":NumberLong(3),
"nombre":"Laura",
"apellidos":"Górriz Pardo",
"activo":true,
"fecha":ISODate("2014-09-12T08:00:09.000Z"),
"grupos":["cine","teatro","running"],
"nacimiento":ISODate("1979-10-24T00:00:00.000Z"),
"sexo":"M",
"ubicacion":{"pais":"España","ciudad":"Castellón"},
"total_comentarios":null,
"total_mensajes":null
});



db.usuario.save({
"_id":NumberLong(4),
"nombre":"Alejandro",
"apellidos":"León Córdoba",
"activo":true,
"fecha":ISODate("2014-05-01T14:12:00.000Z"),
"grupos":["fútbol","cocina"],
"amigos":[NumberLong(1),NumberLong(5)],
"sexo":"V",
"nacimiento":ISODate("2000-11-12T00:00:00.000Z"),
"ubicacion":{"pais":"Venezuela","ciudad":"Valencia"},
"comentarios":[{"titulo":"Favorito para el mundial de Brasil","texto":"Que equipo piensan que es el favorito para ganar el mundial de Brasil?. Yo voto por la anfitriona, Brasil.","fecha":ISODate("2014-06-03T16:04:15.000Z"),"grupo":"fútbol"}],
"mensajes":[],
"total_comentarios":1,
"total_mensajes":0
});




db.usuario.save({
"_id":NumberLong(5),
"nombre":"María",
"apellidos":"Soriano Martín",
"activo":true,
"fecha":ISODate("2014-06-11T05:03:09.000Z"),
"grupos":["cine","fútbol"],
"amigos":[NumberLong(8),NumberLong(4)],
"nacimiento":ISODate("2001-01-02T00:00:00.000Z"),
"sexo":"M",
"ubicacion":{"pais":"España","ciudad":"Castellón"},
"comentarios":[{"titulo":"Re: Favorito para el mundial de Brasil","texto":"Argentina, seguro que Messi lo borda","fecha":ISODate("2014-06-04T06:17:10.000Z"),"grupo":"fútbol"}],
"mensajes":[],
"total_comentarios":1,
"total_mensajes":0
});



db.usuario.save({
"_id":NumberLong(6),
"nombre":"Gabriel",
"apellidos":"Torres García",
"activo":true,
"fecha":ISODate("2014-06-21T08:30:39.000Z"),
"grupos":[],
"nacimiento":ISODate("2002-04-07T00:00:00.000Z"),
"sexo":"V",
"ubicacion":{"pais":"España","ciudad":"Alicante"},
"total_comentarios":null,
"total_mensajes":null
});


db.usuario.save({
"_id":NumberLong(7),
"nombre":"Daniela",
"apellidos":"Serna Ortíz",
"activo":false,
"fecha":ISODate("2014-07-12T08:00:09.000Z"),
"grupos":[],
"amigos":[],
"sexo":"M",
"nacimiento":ISODate("1979-04-30T00:00:00.000Z"),
"ubicacion":{"pais":"España","ciudad":"Castellón"},
"comentarios":[],
"mensajes":[],
"total_comentarios":0,
"total_mensajes":0
});





db.usuario.save({
"_id":NumberLong(8),
"nombre":"Marta",
"apellidos":"Fernández García",
"activo":true,
"fecha":ISODate("2014-09-12T08:18:09.000Z"),
"grupos":["cine"],
"amigos":[NumberLong(5)],
"nacimiento":ISODate("2001-10-24T00:00:00.000Z"),
"sexo":"M",
"ubicacion":{"pais":"España","ciudad":"Valencia"},
"comentarios":[],
"mensajes":[],
"total_comentarios":0,
"total_mensajes":0
});



db.usuario.find().pretty();

