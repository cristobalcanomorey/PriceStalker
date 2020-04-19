CREATE DATABASE PriceStalker;
USE PriceStalker;
CREATE USER IF NOT EXISTS 'usuarioBBDD'@'%' IDENTIFIED BY 'patata23';
GRANT ALL PRIVILEGES ON PriceStalker.* TO 'usuarioBBDD'@'%';

CREATE TABLE usuario (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(45) NOT NULL,
	correo VARCHAR(45) NOT NULL,
	password VARCHAR(45) NOT NULL
)ENGINE=INNODB CHARACTER SET=utf8;

CREATE TABLE lista (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    idUsuario INT NOT NULL,
    foreign key (idUsuario)
     references usuario (id)
		on delete cascade
    	on update cascade
)ENGINE=INNODB CHARACTER SET=utf8;

CREATE TABLE producto (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    link VARCHAR(510) NOT NULL UNIQUE,
    imgLink VARCHAR(210) NOT NULL UNIQUE
)ENGINE=INNODB CHARACTER SET=utf8;

CREATE TABLE contenido (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    precioObjetivo DECIMAL(10,2) NOT NULL,
    idLista INT NOT NULL,
    foreign key (idLista)
     references lista (id)
		on delete cascade
    	on update cascade,
	idProducto INT NOT NULL,
    foreign key (idProducto)
     references producto (id)
		on delete cascade
    	on update cascade
)ENGINE=INNODB CHARACTER SET=utf8;

CREATE TABLE precio (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    coste DECIMAL(10,2) NOT NULL,
    fecha DATETIME NOT NULL,
    idProducto INT NOT NULL,
    foreign key (idProducto)
     references producto (id)
		on delete cascade
        on update cascade
)ENGINE=INNODB CHARACTER SET=utf8;

INSERT INTO usuario (nombre,correo,password)
VALUES ('Tofol','tofolcanodaw2@gmail.com',PASSWORD('patata24'));
INSERT INTO lista (nombre,idUsuario)
VALUES ('Ropa',1);

INSERT INTO usuario (nombre,correo,password)
VALUES ('Tomas','ejemplo@gmail.com',PASSWORD('patata23'));
INSERT INTO lista (nombre,idUsuario)
VALUES ('PC Gaming',2);

INSERT INTO producto (nombre,link,imgLink)
VALUES ('Amazon Essentials Pantalones Hombre','https://www.amazon.es/Amazon-Essentials-Fleece-Sweatpant-Pantalones/dp/B075JNJB9B/ref=sr_1_2_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=pants&qid=1584725687&sr=8-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUFOSEE4TFZMM0tYVEsmZW5jcnlwdGVkSWQ9QTA4NDE0MTZZSVMyRUdZR09DSkQmZW5jcnlwdGVkQWRJZD1BMDA1MzE3Nk8yTzZHMzJKTldZRCZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=','https://images-na.ssl-images-amazon.com/images/I/71YTPz3bZqL._AC_UY879_.jpg');

INSERT INTO producto (nombre,link,imgLink)
VALUES ('ASICS Gel-Noosa Tri 12, Road Running Shoe para Hombre','https://www.amazon.es/Gel-Noosa-Running-Hombre-Seguridad-Amarillo/dp/B081TMZBGS/ref=sr_1_43?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=shoes&qid=1584725867&sr=8-43&swrs=F3A8CC41FB1CB6174CA26FBAEDD49E07','https://images-na.ssl-images-amazon.com/images/I/816v9mtkXnL._AC_UX695_.jpg');

INSERT INTO producto (nombre,link,imgLink)
VALUES ('Camiseta Hombre Verano Manga Corta 3D Geometría Impresión Moda Originales Camiseta Casual T-Shirt Blusas Camisas Camiseta Cuello Redondo Suave básica Deporte Chándal Hombre Camiseta Tops vpass','https://www.amazon.es/Camiseta-Geometr%C3%ADa-Impresi%C3%B3n-Originales-T-Shirt/dp/B084MKLY5Q/ref=sr_1_41?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=t-shirts&qid=1584726103&sr=8-41&th=1','https://images-na.ssl-images-amazon.com/images/I/61FxW6fadWL._AC_UX679_.jpg');

INSERT INTO producto (nombre,link,imgLink)
VALUES ('Corsair K70 LUX - Teclado mecánico Gaming, retroiluminación LED roja, Cherry MX Speed (Rápido y altamente preciso) - [QWERTY Español]','https://www.amazon.es/Corsair-K70-Lux-RGB-Retroiluminaci%C3%B3n/dp/B01FWCR922/ref=sr_1_6?dchild=1&fst=as%3Aoff&qid=1584726562&refinements=p_n_feature_keywords_two_browse-bin%3A2711403031%2Cp_72%3A831280031%2Cp_36%3A20000-&rnid=1323854031&s=computers&sr=1-6&th=1','https://images-na.ssl-images-amazon.com/images/I/71Y%2Bw9WUQML._AC_SX679_.jpg');

INSERT INTO producto (nombre,link,imgLink)
VALUES ('PNY VCQRTX8000-PB - Tarjeta gráfica (Quadro RTX 8000, 48 GB, GDDR6, 7680 x 4320 Pixeles, PCI Express x16 3.0, 1 Ventilador(es))','https://www.amazon.es/PNY-VCQRTX8000-PB-Tarjeta-gr%C3%A1fica-Ventilador/dp/B07NH3HKG9/ref=sr_1_1?__mk_es_ES=%C3%85M%C3%85Z%C3%95%C3%91&dchild=1&qid=1584727093&sr=8-1&srs=11606862031','https://images-na.ssl-images-amazon.com/images/I/51wVImu3aKL._AC_SX679_.jpg');

INSERT INTO contenido (precioObjetivo, idLista, idProducto)
VALUES (16.01,1,1);

INSERT INTO contenido (precioObjetivo, idLista, idProducto)
VALUES (98.69,1,2);

INSERT INTO contenido (precioObjetivo, idLista, idProducto)
VALUES (0.50,1,3);

INSERT INTO contenido (precioObjetivo, idLista, idProducto)
VALUES (128.99,2,4);

INSERT INTO contenido (precioObjetivo, idLista, idProducto)
VALUES (6700.33,2,5);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (18.99,NOW(),1);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (17.49,SUBDATE(NOW(),1),1);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (180.69,NOW(),2);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (99.69,SUBDATE(NOW(),1),2);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (2.98,NOW(),3);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (0.98,SUBDATE(NOW(),1),3);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (207.98,NOW(),4);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (129.99,SUBDATE(NOW(),1),4);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (7000.00,NOW(),5);

INSERT INTO precio (coste,fecha,idProducto)
VALUES (6754.33,SUBDATE(NOW(),1),5);
