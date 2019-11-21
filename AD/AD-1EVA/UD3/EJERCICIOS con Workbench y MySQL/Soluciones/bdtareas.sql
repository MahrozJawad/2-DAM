CREATE SCHEMA bdtareas DEFAULT CHARACTER SET utf8mb4 ;

USE bdtareas ;

CREATE TABLE tareas (
  codigo		int NOT NULL,
  descripcion 	varchar(60) DEFAULT NULL,
  PRIMARY KEY (codigo)
);

INSERT INTO tareas VALUES 
	(1, 'Repasar SQL'),
	(2, 'Instalar NetBeans');

ALTER TABLE tareas ADD COLUMN fecha_prevista DATE;
ALTER TABLE tareas ADD COLUMN terminada BOOLEAN;
	
UPDATE tareas 
	SET 
	  fecha_prevista=ADDDATE(CURRENT_DATE(),1), 
	  terminada=false
	WHERE codigo=1;

UPDATE tareas 
	SET 
	  fecha_prevista=ADDDATE(CURRENT_DATE(),2), 
	  terminada=false
	WHERE codigo=2;
	
INSERT INTO tareas (codigo, descripcion, fecha_prevista, terminada) 
  VALUES 
	(3, 'Realizar copia de seguridad', '2019-10-10', false),
	(4, 'Examinar en busca de virus', '2019-10-03', true);

SELECT * 
  FROM tareas 
  WHERE codigo<3;

SELECT * 
  FROM tareas 
  WHERE descripcion like '%instalar%';

SELECT MAX(codigo) FROM tareas;

SELECT COUNT(*) FROM tareas;

SELECT fecha_prevista,COUNT(*) 
  FROM tareas 
  GROUP BY fecha_prevista;

SELECT MIN(codigo) 
  FROM tareas 
  WHERE descripcion like '%netbeans%';

SELECT descripcion, fecha_prevista 
  FROM tareas 
    WHERE codigo > (SELECT MIN(codigo) FROM tareas WHERE descripcion like '%netbeans%');


