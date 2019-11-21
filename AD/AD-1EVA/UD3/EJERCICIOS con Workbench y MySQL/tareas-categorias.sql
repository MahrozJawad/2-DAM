
/*Creando tabla*/
DROP TABLE IF EXISTS categorias;
CREATE TABLE categorias(
    codigo VARCHAR(10) PRIMARY KEY,
    descripcion VARCHAR(60)
);

/*Insertando categorias*/
INSERT INTO categorias VALUES("E", "Estudios");
INSERT INTO categorias VALUES("O", "Ocio");

/*Modifica la tabla "tareas" para añadir el código de categoría
("codcateg").*/

ALTER TABLE tareas ADD COLUMN codcateg VARCHAR(10);

/*• Asigna la categoría "E" a las tareas 1 y 2.*/

UPDATE tareas
SET codcateg = "E"
LIMIT 2; 

/*• Muestra la descripción de todas las tareas, junto con la
descripción de la categoría a la que pertenecen, para todas las
categorías de las que se haya indicado descripción.*/

SELECT tareas.descripcion AS "Descripción", categorias.descripcion AS "Categorias"
FROM tareas LEFT JOIN categorias
ON tareas.codcateg = categorias.codigo;

/*• Muestra la descripción de todas las tareas, junto con la
descripción de la categoría a la que pertenecen o NULL en caso de
que no se haya indicado esa categoría.*/

SELECT tareas.descripcion AS "Descripción", categorias.descripcion AS "Categorias"
FROM tareas LEFT JOIN categorias
ON tareas.codcateg = categorias.codigo;

/*• Muestra la descripción de todas las tareas, junto con la
descripción de la categoría a la que pertenecen o "(Ninguna)" en
caso de que no se haya indicado esa categoría.*/

SELECT tareas.descripcion AS "Descripción", IF(categorias.descripcion IS NULL,"Ninguno", categorias.descripcion) AS "Categorias"
FROM tareas LEFT JOIN categorias
ON tareas.codcateg = categorias.codigo;

/*• Muestra la descripción de todas las tareas, junto con la
descripción de la categoría a la que pertenecen, para todas las
tareas (puede que alguna categoría aparezca como NULL) y para
todas las categorías (y puede que alguna tarea aparezca como NULL,
si esa categoría no tiene tareas asignadas).(Puedes usar UNION)*/

(SELECT tareas.descripcion, categorias.descripcion
FROM tareas LEFT JOIN categorias
ON tareas.codcateg = categorias.codigo)
UNION ALL
(SELECT tareas.descripcion, categorias.descripcion
FROM tareas RIGHT JOIN categorias
ON tareas.codcateg = categorias.codigo);
