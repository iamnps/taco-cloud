DROP TABLE if EXISTS Ingredient;
CREATE TABLE if not EXISTS Ingredient (
  id VARCHAR(4) NOT NULL ,
  name VARCHAR(25) not NULL ,
  type VARCHAR (25) NOT NULL

);

DROP TABLE if EXISTS Taco;
CREATE TABLE if not EXISTS Taco (
  id IDENTITY,
  name VARCHAR (50) not NULL ,
  crtAt TIMESTAMP NOT NULL
);

DROP TABLE if EXISTS Taco_Ingredients;
CREATE TABLE if not EXISTS Taco_Ingredients (
  taco bigint not NULL ,
  ingredient VARCHAR (4) NOT NULL
);

ALTER TABLE Taco_Ingredients add FOREIGN key (Taco) REFERENCES Taco(id);
ALTER TABLE Taco_Ingredients add FOREIGN key (Ingredient) REFERENCES Ingredient(id);

DROP TABLE if EXISTS Taco_Order;
CREATE TABLE if not EXISTS Taco_Order (
  id IDENTITY ,
  Name VARCHAR (50) not NULL ,
  Street VARCHAR (50) not NULL ,
  City VARCHAR (50) not NULL ,
  State VARCHAR (2) not NULL ,
  Zip VARCHAR (10) not NULL ,
  ccNumber VARCHAR (16) NOT NULL ,
  ccExpiration VARCHAR (5) NOT NULL ,
  ccCVV VARCHAR (3) NOT NULL ,
  placedAt TIMESTAMP NOT NULL
);

DROP TABLE if EXISTS Taco_Order_Tacos;
CREATE TABLE if NOT EXISTS Taco_Order_Tacos(
  tacoOrder bigint not NULL ,
  taco bigint not NULL
);

ALTER TABLE Taco_Order_Tacos add FOREIGN key (tacoOrder) REFERENCES Taco_Order(id);
ALTER TABLE Taco_Order_Tacos add FOREIGN key (taco) REFERENCES Taco(id);