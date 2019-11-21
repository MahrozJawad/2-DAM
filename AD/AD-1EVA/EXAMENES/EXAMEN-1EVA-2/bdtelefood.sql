CREATE SCHEMA bdtelefood DEFAULT CHARACTER SET utf8mb4 ;

USE bdtelefood ;

CREATE TABLE tiendas (
  idtienda	varchar(3)  NOT NULL,
  provincia	varchar(20) DEFAULT NULL,
  creada	DATE DEFAULT NULL,
  encargado	varchar(30) DEFAULT NULL,
  trabajadores int(3) DEFAULT 1,
  PRIMARY KEY (idtienda)
);

CREATE TABLE emails (
  idtienda	 varchar(3) NOT NULL ,
  email		 varchar(50)  NOT NULL,
  PRIMARY KEY (idtienda,email),
  FOREIGN KEY (idtienda) REFERENCES tiendas(idtienda)
);


INSERT INTO tiendas (idtienda, provincia, creada, encargado, trabajadores) VALUES 
	('A01','Alicante','2012-10-03','Sergio López',4),
	('A02','Alicante','2016-05-10','Antonio Sánchez',2),
	('V01','Valencia','2014-02-15','Jorge González',6),
	('V02','Valencia','2016-09-20','Marta Rodríguez',3),
	('V03','Valencia','2019-07-05','Ana Romero',2),
	('C01','Castellón','2014-06-28','Lorena Fernández',2);

INSERT INTO emails (idtienda, email) VALUES 
	('A01', 'a01@telefood.es'),
	('A01', 'sergio.lop@gmail.com'),
	('A01', 'slop@hotmail.com'),
	
	('A02', 'a02@telefood.es'),
	('A02', 'antonio.san@gmail.com'),
	
	('V01', 'v01@telefood.es'),
	('V01', 'jorge.gon@gmail.com'),
	
	('V02', 'v02@telefood.es'),
	('V02', 'marta.rod@gmail.com'),
	('V03', 'mrod@hotmail.com'),
	
	('V03', 'v03@telefood.es'),

	('C01', 'c01@telefood.es'),
	('C01', 'lorena.fer@gmail.com');

	
	
	
	