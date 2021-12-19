delete FROM Taco_Order_Tacos;
delete FROM Taco_Ingredients;
delete FROM Taco;
delete FROM Taco_Order;

delete FROM Ingredient;
INSERT INTO Ingredient (id, name, type) VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, type) VALUES ('CLTO', 'Corn Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, type) VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO Ingredient (id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO Ingredient (id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO Ingredient (id, name, type) VALUES ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO Ingredient (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO Ingredient (id, name, type) VALUES ('JACK', 'Monterrey', 'CHEESE');
INSERT INTO Ingredient (id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO Ingredient (id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');