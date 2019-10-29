/* Realiza un script denominado bdapps.sql con instrucciones SQL para:
• Crea una base de datos desde Workbench llamada "bdapps".
• Crear en ella una tabla "programas" para guardar una colección de
software. En una primera aproximación guardaremos sólo tres
campos: un código, un nombre y la carpeta de instalación.
• Introducir dos datos de ejemplo y comprueba que se han almacenado
correctamente. */
/*Creando base de datos*/
DROP DATABASE IF EXISTS bdapps;
CREATE DATABASE bdapps;
USE bdapps;
/*Creando table*/
DROP TABLE IF EXISTS programas;
CREATE TABLE programas (
    codigo  INT NOT NULL PRIMARY KEY,
    nombre  VARCHAR(15),
    carpeta VARCHAR(15)
);
/*Insertando*/
INSERT INTO programas VALUES(0,"Libre Office", "MY OFFICE");
INSERT INTO programas VALUES(1,"Google Chrome", "chrome");

/*COmprobando*/
SELECT * FROM programas;

