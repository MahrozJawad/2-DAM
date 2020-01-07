
/* el tamaño del array de amigos.*/
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

/* hay que poner multi para que se efecte a todos los registros...*/
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
/*SET añade y unset lo borra el registro entero*/
db.usuarios.update(
    {
        "nombre":"Arturo"
    },
    {
        $set:{"idioma":"Inglés"}
    }
)
db.usuarios.update(
    {
        "nombre":"Arturo"
    },
    {
        $unset:{"idioma":"inglés"}
    }
)
/*Para incrementar el valor se pone "inc"*/
db.usuarios.update(
    {
        $and:[
            {"sexo":"M"},
            {"total_mensajes":{$ne:null}}
        ]
    },
    {
        $inc:{"total_mensajes":1}
    },
    {
        "multi":true
    }
)
/*"$Push" para insertar datos en una array*/










