DROP TABLE IF EXISTS "Itinerario";
CREATE TABLE IF NOT EXISTS "Itinerario" (
	"Id_itinerario"	INTEGER,
	"Usuario"	TEXT NOT NULL,
	"Atraccion"	TEXT,
	"Id_promocion"	INTEGER,
	"Tipo_producto"	TEXT,
	FOREIGN KEY("Atraccion") REFERENCES "Atraccion"("Nombre"),
	FOREIGN KEY("Id_promocion") REFERENCES "Promocion"("Id_promocion"),
	FOREIGN KEY("Usuario") REFERENCES "Usuario"("Id_usuario"),
	PRIMARY KEY("Id_itinerario" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Atracciones_promos";
CREATE TABLE IF NOT EXISTS "Atracciones_promos" (
	"Id"	INTEGER,
	"Atraccion"	TEXT NOT NULL,
	"Id_promocion"	INTEGER NOT NULL,
	FOREIGN KEY("Id_promocion") REFERENCES "Promocion"("Id_promocion"),
	FOREIGN KEY("Atraccion") REFERENCES "Atraccion"("Nombre"),
	PRIMARY KEY("Id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Promocion";
CREATE TABLE IF NOT EXISTS "Promocion" (
	"Id_promocion"	INTEGER,
	"Nombre"	TEXT NOT NULL,
	"Tipo_atraccion"	TEXT NOT NULL,
	"Valor"	REAL,
	PRIMARY KEY("Id_promocion" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Atraccion";
CREATE TABLE IF NOT EXISTS "Atraccion" (
	"Id_atraccion"	INTEGER,
	"Nombre"	TEXT NOT NULL UNIQUE,
	"Tipo_atraccion"	TEXT NOT NULL,
	"Tiempo"	REAL NOT NULL,
	"Costo"	REAL NOT NULL,
	"Cupo"	INTEGER NOT NULL,
	PRIMARY KEY("Id_atraccion" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Usuario";
CREATE TABLE IF NOT EXISTS "Usuario" (
	"Id_usuario"	INTEGER,
	"Nombre"	TEXT NOT NULL,
	"Presupuesto"	INTEGER NOT NULL,
	"Tiempo"	INTEGER,
	"Preferencia"	TEXT,
	PRIMARY KEY("Id_usuario" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Tipo_atraccion";
CREATE TABLE IF NOT EXISTS "Tipo_atraccion" (
	"Tipo"	TEXT,
	PRIMARY KEY("Tipo")
);
DROP TABLE IF EXISTS "Tipo_promo";
CREATE TABLE IF NOT EXISTS "Tipo_promo" (
	"Id"	INTEGER NOT NULL,
	"Nombre"	TEXT,
	PRIMARY KEY("Nombre")
);

INSERT INTO "Tipo_atraccion" ("Tipo") VALUES (' AVENTURA'),
 ('DEGUSTACION'),
 ('PAISAJE');
INSERT INTO "Tipo_promo" ("Id","Nombre") VALUES (1,'PromoAbsoluta'),
 (2,'PromoPorcentual'),
 (3,'PromoAxB');
INSERT INTO "Usuario" ("Id_usuario","Nombre","Presupuesto","Tiempo","Preferencia") VALUES (1,'Eowyn',5,5,'AVENTURA'),
 (2,'Gandalf',120,20,'PAISAJE'),
 (3,'Sam',36,30,'DEGUSTACION'),
 (4,'Galadriel',120,3,'PAISAJE'),
 (5,'Frodo',70,7,'DEGUSTACION');
 INSERT INTO "Atraccion" ("Id_atraccion","Nombre","Tipo_atraccion","Tiempo","Costo","Cupo") VALUES (1,'Moria','AVENTURA',2.0,10.0,6),
 (2,'Minas Tirith','PAISAJE',2.5,5.0,25),
 (3,'La Comarca','DEGUSTACION',6.5,3.0,150),
 (4,'Mordor','AVENTURA',3.0,25.0,4),
 (5,'Abismo de Helm','PAISAJE',2.0,5.0,15),
 (6,'Lothlorien','DEGUSTACION',1.0,35.0,30),
 (7,'Erebor','PAISAJE',3.0,12.0,32),
 (8,'Bosque Negro','AVENTURA',4.0,3.0,12),
 (9,'Rivendel','DEGUSTACION',4.0,15.0,20);
INSERT INTO "Promocion" ("Id_promocion","Nombre","Tipo_atraccion","Valor") VALUES (1,'PromoAbsoluta','AVENTURA',15.0),
 (2,'PromoPorcentual','DEGUSTACION',0.5),
 (3,'PromoAxB','PAISAJE',NULL);
INSERT INTO "Atracciones_promos" ("Id","Atraccion","Id_promocion") VALUES (1,'Moria',1),
 (2,'Mordor',1),
 (3,'Lothlorien',2),
 (4,'La Comarca',2),
 (5,'Rivendel',2),
 (6,'Minas Tirith',3),
 (7,'Erebor',3),
 (8,'Abismo de Helm',3);
