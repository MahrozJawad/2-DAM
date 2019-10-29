USE bdtareas ;

CREATE TABLE categorias (
  codigo		varchar(10) NOT NULL,
  descripcion 	varchar(60) DEFAULT NULL,
  PRIMARY KEY (codigo)
);

INSERT INTO categorias VALUES 
	('E', 'Estudios'),
	('O', 'Ocio');

ALTER TABLE tareas ADD COLUMN codcateg VARCHAR(10);

	
UPDATE tareas 
	SET 
	  codcateg='E'	  
	WHERE codigo=1 OR codigo=2;
	
	
SELECT tareas.descripcion as tar_descripcion, categorias.descripcion as cat_descripcion
  FROM tareas, categorias
  WHERE tareas.codcateg=categorias.codigo;

SELECT tareas.descripcion as tar_descripcion, categorias.descripcion as cat_descripcion
  FROM tareas LEFT JOIN categorias
	ON tareas.codcateg=categorias.codigo;

SELECT tareas.descripcion as tar_descripcion, 
	IF(ISNULL(categorias.descripcion),'(Ninguna)',categorias.descripcion) AS cat_descripcion
  FROM tareas LEFT JOIN categorias
	ON tareas.codcateg=categorias.codigo;

SELECT tareas.descripcion as tar_descripcion, categorias.descripcion as cat_descripcion
  FROM tareas, categorias
  WHERE tareas.codcateg=categorias.codigo
                  UNION
SELECT tareas.descripcion as tar_descripcion, categorias.descripcion as cat_descripcion
  FROM tareas LEFT JOIN categorias
	ON tareas.codcateg=categorias.codigo
  WHERE categorias.descripcion IS NULL
                  UNION
SELECT tareas.descripcion as tar_descripcion, categorias.descripcion as cat_descripcion
  FROM tareas RIGHT JOIN categorias
	ON tareas.codcateg=categorias.codigo
  WHERE tareas.descripcion IS NULL;
  
  






