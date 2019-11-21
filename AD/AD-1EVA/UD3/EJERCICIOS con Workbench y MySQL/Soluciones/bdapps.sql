CREATE SCHEMA bdapps DEFAULT CHARACTER SET utf8mb4 ;

USE bdapps ;

CREATE TABLE programas (
  codigo	int(11) NOT NULL,
  nombre 	varchar(60) DEFAULT NULL,
  carpeta	varchar(60) DEFAULT NULL,
  PRIMARY KEY (codigo)
);

INSERT INTO programas VALUES 
	(1, 'LibreOffice', 'C:/Program Files/LibreOffice/program'),
	(2, 'Mozilla Thunderbird', 'C:/Program Files (x86)/Mozilla Thunderbird');
	