
/*Creando base de datos*/
DROP DATABASE IF EXISTS bdtareas;
CREATE DATABASE bdtareas;
/*Creando tabla*/
DROP TABLE IF EXISTS tareas;
CREATE TABLE tareas(
    codigo INT PRIMARY KEY,
    descripcion VARCHAR(255)
);
/*Insertando los datos*/
INSERT INTO tareas VALUES(0, "Repasar SQL");
INSERT INTO tareas VALUES(1, "Instalar NetBeans");
/*Añadiendo las columnas*/
ALTER TABLE tareas ADD COLUMN fechaProvista DATE;
ALTER TABLE tareas ADD COLUMN terminada BOOLEAN;

/*Asignando valores en las columnas*/
UPDATE tareas 
SET fechaProvista = "2019-10-28"
WHERE codigo = 0;

UPDATE tareas 
SET terminada = FALSE
WHERE codigo = 0;

UPDATE tareas 
SET terminada = FALSE
WHERE codigo = 1;

UPDATE tareas 
SET fechaProvista = "2019-10-26"
WHERE codigo = 1;

/*Insertando dos datos más*/
INSERT INTO tareas VALUES(2, "Activar windows",current_date(),FALSE);
INSERT INTO tareas VALUES(3, "Actualizar Netbeans", current_date(), FALSE);

/*Muestra las tareas cuyo código sea inferior a 3.*/

SELECT * FROM tareas WHERE Codigo < 3;

/*• Muestra las tareas cuya descripcion contenga la palabra "instalar".*/

SELECT * FROM tareas WHERE descripcion LIKE '%Instalar%';

/*• Muestra el código de la última tarea (el mayor código).*/

SELECT MAX(codigo) FROM tareas;

/*• Muestra la cantidad de tareas almacenadas.*/

SELECT COUNT(codigo) FROM tareas;

/*• Muestra la cantidad de tareas que hay para cada día.*/

SELECT COUNT(*) FROM tareas;

/*• Muestra el código de la tarea más antigua (menor código) que contenga la palabra "NetBeans".*/

SELECT MIN(Codigo) FROM tareas WHERE descripcion LIKE '%NetBeans%';

/*• Muestra la descripción y fecha_prevista de las tareas cuyo código
es superior al código de la tarea más antigua (menor código) que
contenga la palabra "NetBeans".*/

SELECT descripcion AS "Descripción", fechaProvista AS "Fecha Prevista"
FROM tareas
WHERE codigo > (SELECT MIN(Codigo) FROM tareas WHERE descripcion LIKE '%NetBeans%');
