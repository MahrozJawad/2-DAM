// ************************************************
// RED SOCIAL - CONSULTAS Y ACTUALIZACIONES
// ************************************************

/* ************************************************

find().pretty()
find().sort()
find()
find() $and, $or, ...
find() $gte, $gt, $lte, $lt, $eq, ...
find() $in, $all, $size, ...

insert
remove
update
update $set
update $unset
update $inc
update $mul
update $pull
update $push
update $pop

aggregate() 
aggregate() $group
aggregate() $sum, $avg, $min, $max
aggregate() $match, $sort, $limit, $project
	
***************************************************** */	


/* 301. 
 Obtener los usuarios de sexo Mujer
 
 SELECT * FROM usuario WHERE sexo='M';
*/ 

db.usuarios.find(
    {
        "sexo":"M"
    }).pretty()

/* 302. 
 Mostrar de todos los registros el nombre, apellidos y sexo
 
 SELECT nombre, apellidos, sexo FROM usuario;
*/ 

db.usuarios.find({"nombre":1, "apellidos":1, "sexo":1}).pretty()

/* 303. 
 De los usuarios de sexo mujer y que estén Activas. Obtener nombre y apellidos.
 
 SELECT nombre, apellidos FROM usuario WHERE sexo='M' AND activo=true;
*/ 

db.usuarios.find(
    {
        "sexo":"M",
        "activo":true
    },
    {
        "nombre":1,
        "apellidos":1
    }
).pretty()


/* 304. 
 Obtener los usuarios que o se llaman Ramón o son Mujeres
 Mostrar nombre, apellidos, sexo.
 
 SELECT nombre, apellidos, sexo FROM usuario WHERE nombre='Ramón' OR sexo='M';
*/ 

db.usuarios.find(
    {
       $or:[
            {"nombre":"Ramón"},
            {"sexo":"M"}
        ]
    },
    {
        "nombre":1,
        "apellidos":1,
        "sexo":1
    }
).pretty()


/* 305. 
 Obtener los usuarios que tengan más de un comentario
 Mostrar nombre, apellidos, total_comentarios.
 
 SELECT nombre, apellidos, total_comentarios 
 FROM usuario 
 WHERE total_comentarios>1;
*/ 

db.usuarios.find(
    {
        "total_comentarios":{$gt:0}
    },
    {
        "nombre":1,
        "apellidos":1,
        "total_comentarios":1
    }
).pretty()


/* 306. 
 Obtener los usuarios que pertenecen al grupo futbol
 Mostrar nombre, apellidos, grupos.

 SELECT nombre, apellidos, grupos 
 FROM usuario 
 WHERE grupos like '%fútbol%';
*/ 

db.usuarios.find(
    {
        "grupos":{$regex:"^fútbol$"}
    },
    {
        "nombre":1,
        "apellidos":1,
        "grupos":1
    }
).pretty()

/* 307. 
 Obtener los usuarios (nombre, apellidos, grupos) 
 que pertenecen al grupo de fútbol o cine
 
 SELECT nombre, apellidos, grupos 
 FROM usuario 
 WHERE (grupos like '%fútbol%') OR (grupos like '%cine%');
*/
 
db.usuarios.find(
    {
        $or:[
            {"grupos":{$regex:"^fútbol$"}},
            {"grupos":{$regex:"^cine$"}}
        ]
    },
    {
        "nombre":1,
        "apellidos":1,
        "grupos":1
    }
).pretty()

/* 308. 
 Obtener los usuarios que pertenecen al grupo de fútbol y cine
 Mostrar nombre, apellidos, grupos.

 SELECT nombre, apellidos, grupos 
 FROM usuario 
 WHERE (grupos like '%fútbol%') AND (grupos like '%cine%');
*/
 
db.usuarios.find(
    {
        $and:[
            {"grupos":{$regex:"^fútbol$"}},
            {"grupos":{$regex:"^cine$"}}
        ]
    },
    {
        "nombre":1,
        "apellidos":1,
        "grupos":1
    }
).pretty()

/* 309. 
 Obtener los usuarios que tengan dos amigos.
 Mostrar nombre, apellidos, amigos.

 Caso especial que no sirve de ayuda a MongoDB, ya que
 amigos es un ArrayList que en BDR es una tabla referenciada:
 
 SELECT nombre, apellidos, GROUP_CONCAT(usu2), COUNT(*) 
 FROM usuario, amigos 
 WHERE usuario.id=amigos.usu1 
 GROUP BY nombre, apellidos 
 HAVING COUNT(*)=2
*/

 
db.usuarios.find(
    {
        "amigos":{$size:2}
    },
    {
        "nombre":1,
        "apellidos":1,
        "amigos":1
    }
).pretty()


/* 310. 
 Obtener los usuarios que sean de Valencia(España).
 Mostrar nombre, apellidos, ubicacion.

 SELECT nombre, apellidos, pais, ciudad 
 FROM usuario 
 WHERE pais='España' AND ciudad='Valencia'
*/ 

db.usuarios.find(
    {
        $and:[
            {"ubicacion.pais":"España"},
            {"ubicacion.ciudad":"Valencia"}
        ]
    },
    {
        "nombre":1,
        "apellidos":1,
        "ubicacion.pais":1,
        "ubicacion.ciudad":1
    }
).pretty()


// ***************************
// UPDATE
// ***************************

/* 311. 
 Desactivar a los hombres, es decir, cambiar campo activo a false 
 a todos los hombres.
 
  UPDATE usuario
	SET activo=false
	WHERE sexo='V';
*/
 
db.usuarios.update(
    {
        "sexo":"V"
    },
    {
        $set:{"activo":false}
    },
    {
        "multi":true
    }
)
				  
db.usuarios.find({}, {"nombre":1,"apellidos":1,"sexo":1,"activo":1,"_id":0}).pretty()

/* 312. 
 Actualizar a 'Arturo' el campo 'idioma' con 'Inglés':

  UPDATE usuario
	SET idioma='Inglés'
	WHERE nombre='Arturo';
*/

db.usuarios.update(
    {
        "nombre":"Arturo"
    },
    {
        $set:{"idioma":"Inglés"}
    }
)
					
db.usuarios.find({}, {"nombre":1,"apellidos":1,"idioma":1,"_id":0}).pretty()

/* 313. 
 Quitar el campo 'idioma' a Arturo:

 Caso especial en BDR, pues podemos actualizar a NULL, 
 pero no eliminar el campo con ALTER TABLE .. DROP COLUMN 
 pues lo eliminaríamos a todos los registros
 
  UPDATE usuario
	SET idioma=NULL
	WHERE nombre='Arturo';
*/ 

db.usuarios.update(
    {
        "nombre":"Arturo"
    },
    {
        $unset:{"idioma":"inglés"}
    }
)

				
db.usuarios.find({}, {"nombre":1,"apellidos":1,"idioma":1,"_id":0}).pretty()



/* 314. 
 A los usuarios mujeres, incrementarle una unidad al campo total_mensajes 
 si no es null

  UPDATE usuario
	SET total_mensajes=total_mensajes+1
	WHERE (sexo='M') AND (total_mensajes NOT IS NULL);
*/
 
db.usuarios.find({}, 
				{"nombre":1,"apellidos":1,"total_mensajes":1,"_id":0}).pretty()

db.usuarios.update(
    {
        $and:[
            {"sexo":"M"},
            {"total_mensajes":{$ne:null}}
        ]
    },
    {
        $inc:{"total_mensajes":-1}
    },
    {
        "multi":true
    }
)

					
db.usuarios.find({}, 
				{"nombre":1,"apellidos":1,"total_mensajes":1,"_id":0}).pretty()

/* 315. 
 A los usuarios que viven en Valencia(España) incrementarle una unidad 
 al campo total_mensajes.
 
  UPDATE usuario
	SET total_mensajes=total_mensajes+1
	WHERE (ciudad='Valencia') AND (pais='España');
*/
 
db.usuarios.update(
    {
        $and:[
            {"ubicacion.pais":"España"},
            {"ubicacion.ciudad":"Valencia"}
        ]
    },
    {
        $inc:{"total_mensajes":1}
    },
    {
        "multi":true
    }
)
  
db.usuarios.find({}, 
				{"nombre":1,"apellidos":1,"total_mensajes":1,"_id":0}).pretty()

/* 316. 
  Añadir a María al grupo 'baile'.
  
  UPDATE usuario
	SET grupos=CONCAT(grupos, ' baile')
	WHERE nombre='María';
*/
  
db.usuarios.update(
    {
        "nombre":"María"
    },
    {
        $push:{"grupos":"baile"}
    }
)

db.usuarios.find({"nombre":"María"}, 
				{"nombre":1,"apellidos":1,"grupos":1,"_id":0}).pretty()


/* 317. 
 Añadimos un mensaje a Arturo dirigido a Ramón 
 (poniendo algo en el mensaje y la fecha la de hoy)

 Para la fecha "de hoy" podéis usar la función Date() o el constructor de Date(). 

 La diferencia entre ambos es que: 
     - la función Date() devuelve un string 
     - el constructor un ISODate con hora UTC (-1 hora)
*/

db.usuarios.update(
    {
        "nombre":"Arturo"
    },
    {
      $push:{"mensajes":
          {
              "para":NumberLong(1),
              "fecha":Date(),
              "texto":"Hola qué tal"
          }
      }  
    }
)


db.usuarios.find({}, 
				{"nombre":1,"apellidos":1,"mensajes":1,"_id":0}).pretty()


/* 318. 
  Quitarle a María del grupo 'baile'

*/

db.usuarios.update(
    {
        "nombre":"María"
    },
    {
        $pull:{"grupos":"baile"}
    }
)
					
db.usuarios.find({}, 
				{"nombre":1,"apellidos":1,"grupos":1,"_id":0}).pretty()


// ***************************
// AGGREGATE
// ***************************


/* 319. 
 Obtener cantidad media de mensajes de cada sexo.
 
 SELECT sexo, AVG(total_mensajes) AS media_mensajes
 FROM usuario
 GROUP BY sexo		

*/

db.usuarios.aggregate(
    {
        $group:{
            "_id":"$sexo",
            "CantidadMediaSexo":{$avg:"$total_mensajes"}
        }
    }
)


/* 320. 
 Obtener cantidad media de mensajes de cada sexo de usuarios de Valencia.

 SELECT sexo, AVG(total_mensajes) AS media_mensajes
 FROM usuario
 WHERE (ciudad='Valencia') AND (pais='España')
 GROUP BY sexo		
 
*/
 
db.usuarios.aggregate(
    [
        {
            $match:{
                $and:[
                    {"ubicacion.ciudad":"Valencia"},
                    {"ubicacion.pais":"España"}
                ]
            }
        },
        {
            $group:{
                "_id":{
                    "Sexo":"$sexo"
                    },
                "CantidadMediaSexo":{$avg:"$total_mensajes"}
            }
        }
    ]
)

/* 321. 
 Para cada ciudad, obtener la cantidad de hombres y de mujeres, es decir,
 agrupar por pais, ciudad y sexo.

 SELECT pais, ciudad, sexo, COUNT(*) AS cantidad
 FROM usuario
 GROUP BY pais, ciudad, sexo		

*/
db.usuarios.aggregate(
    {
        $group:{
            "_id":{
                "Pais":"$ubicacion.pais",
                "Ciudad":"$ubicacion.ciudad",
                "Sexo":"$sexo"
            },
            "cantidad":{$sum:1}
        }
    }
)

/* 322. 
 Mostrar la ciudad de España en la que hay más mujeres.  

 SELECT ciudad
 FROM usuario
 WHERE (pais='España') AND (sexo='M')
 GROUP BY ciudad
 ORDER BY COUNT(*) DESC 
 LIMIT 1

*/

/* EQUIVALENCIAS
 (project) 	SELECT ciudad
 FROM usuario
 (match)	WHERE (pais='España') AND (sexo='M')
 (group)	GROUP BY ciudad
 (sort)		ORDER BY COUNT(*) DESC 
 (limit) 	LIMIT 1
*/	


db.usuarios.aggregate(
    [
        {
            $match:{
                $and:[
                    {"ubicacion.pais":"España"},
                    {"sexo":"M"}
                ]
            }
        },
        {
            $group:{
                "_id":{
                    "ciudad":"$ubicacion.ciudad"
                },
                "cantidad":{$sum:1}
            }
        },
        {
            $sort:{
                "cantidad":-1
            }
        },
        {
            $limit:1
        }
    ]
)
			

	
/* 323a. 
 Obtener la cantidad de hombres en cada ciudad de España.

 SELECT ciudad, COUNT(*) AS cantidadHombres
 FROM usuario
 WHERE (pais='España') AND (sexo='V')
 GROUP BY ciudad

*/
 
db.usuarios.aggregate(
    [
        {
            $match:{
                $and:[
                    {"ubicacion.pais":"España"},
                    {"sexo":"V"}
                ]
            }
        },
        {
            $group:{
                "_id":{
                    "Ciudad":"$ubicacion.ciudad"
                },
                "Cantidad":{$sum:1}
            }
        }
    ]
)

/* 323b. 
 Obtener las ciudades de España con menos de 2 hombres.
 
 SELECT ciudad, COUNT(*) AS cantidadHombres
 FROM usuario
 WHERE (pais='España') AND (sexo='V')
 GROUP BY ciudad
 HAVING COUNT(*)<2
*/ 

/* EQUIVALENCIAS
 (project) 	SELECT ciudad, COUNT(*) AS cantidadHombres
 FROM usuario
 (match)	WHERE (pais='España') AND (sexo='V')
 (group)	GROUP BY ciudad
 (match)	HAVING COUNT(*)<2

*/	
db.usuarios.aggregate(
    [
        {
            $match:{
                $and:[
                    {"ubicacion.pais":"España"},
                    {"sexo":"V"}
                ]
            }
        },
        {
            $group:{
                "_id":{
                    "Ciudad":"$ubicacion.ciudad"
                },
                "Cantidad":{$sum:1}
            }
        },
        {
            $match:{
                "Cantidad":{$lt:2}
            }
        }
    ]
)

/* 324. 
 Para cada pais-ciudad obtener el sexo que más comentarios ha realizado.

 SELECT pais, ciudad, sexo, SUM(total_mensajes) AS num_mensajes
 FROM usuario
 GROUP BY pais, ciudad, sexo
 ORDER BY num_mensajes DESC
 LIMIT 1
 
*/ 

/* EQUIVALENCIAS
 (project) 	SELECT sexo
 FROM usuario
 (group) 	GROUP BY pais, ciudad, sexo
 (sort)		ORDER BY num_mensajes DESC
 (limit)	LIMIT 1
*/		
 
db.usuarios.find({},
           {"ubicacion.ciudad":1, "sexo":1, "total_comentarios":1, _id:0}
    ).sort({"ubicacion.ciudad":1, "sexo":1, "total_comentarios":1}
    ).pretty()


db.usuarios.aggregate(
    [
        {
            $group:{
                "_id":{
                    "Pais":"$ubicacion.pais",
                    "Ciudad":"$ubicacion.ciudad",
                    "Sexo":"$sexo"
                },
                "num_comentarios":{$sum:"$total_comentarios"}
            }
        },
        {
            $sort:{
                "num_comentarios":-1
            }
        },
        {
            $limit:1
        }
    ]
)
	
	

// ***************************
// REMOVE
// ***************************

/* 325. 
  Eliminar la primera mujer

  Nota: el segundo parámetro con valor boolean de remove 
  indica si un registro o todos
  
*/

db.usuarios.remove(
    {
        "sexo":"M"
    },
    {
        "justOne":true
    }
)  

/* 326. 
 Eliminar solo el primer hombre 
 
 Nota: remove puede tener la cláusula {"justOne":true}

*/

db.usuarios.remove(
    {
        "sexo":"V"
    },
    {
        "justOne":true
    }
)

/* 327. 
 Eliminar todos los hombres
 
 DELETE FROM usuario
 WHERE sexo='V'
*/
 
db.usuarios.remove(
    {
        "sexo":"V"
    }
)  

