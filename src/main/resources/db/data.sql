INSERT INTO unites (libelle, description) VALUES
    ('kg', 'Kilogramme'),
    ('g', 'Gramme'),
    ('l', 'Litre'),
    ('p', 'Pièce');

INSERT INTO ingredients (nom, besoin_normal, besoin_ferie, id_unite) VALUES
    ('Farine', 100, 150, 1),
    ('Sucre', 20, 30, 2),
    ('Levure', 5, 7, 2),
    ('Beurre', 50, 70, 1),
    ('Oeufs', 10, 15, 4),
    ('Lait', 2, 3, 3);

INSERT INTO produits (nom, prix_revient, prix_vente) VALUES
    ('Baguette', 0.50, 1.00),
    ('Croissant', 0.70, 1.50),
    ('Pain au chocolat', 0.75, 1.60),
    ('Tarte aux pommes', 3.00, 6.00);

INSERT INTO clients (nom, type) VALUES
    ('Super Maki', 'PROFESSIONNEL'),
    ('Jumbo', 'PROFESSIONNEL'),
    ('Kibo', 'PROFESSIONNEL'),
    ('Divers', 'PARTICULIER');

INSERT INTO fournisseurs (nom) VALUES
    ('Moulin de la Ville'),
    ('Fournisseur Laitier'),
    ('Producteur d''Œufs'),
    ('Beurrerie Artisanale');

INSERT INTO recette (id_ingredient, id_produit, quantite) VALUES
    (1, 1, 0.25),
    (3, 1, 0.01),
    (1, 2, 0.15),
    (4, 2, 0.10),
    (5, 2, 1),
    (2, 4, 0.05),
    (6, 4, 0.10);

INSERT INTO ventes (total, date_vente, id_client) VALUES
    (25.00, '2024-12-01 10:00:00', 1),
    (10.00, '2024-12-02 12:00:00', 2),
    (15.50, '2024-12-03 08:30:00', 3);

INSERT INTO details_vente (quantite, prix_unitaire, id_vente, id_produit) VALUES
    (10, 1.00, 1, 1),
    (5, 1.50, 1, 2),
    (3, 6.00, 2, 4),
    (5, 1.60, 3, 3);

INSERT INTO achats (total, date_achat, id_fournisseur) VALUES
    (50.00, '2024-12-01 09:00:00', 1),
    (30.00, '2024-12-02 11:00:00', 2);

INSERT INTO details_achat (quantite, prix_unitaire, id_ingredient, id_achat) VALUES
    (20, 2.50, 1, 1), -- Farine
    (10, 3.00, 2, 2), -- Sucre
    (5, 1.50, 3, 2),  -- Levure
    (8, 4.00, 4, 1); -- Beurre

INSERT INTO fabrication (quantite, date_fabrication, id_produit) VALUES
    (100, '2024-12-01', 1),
    (50, '2024-12-02', 2),
    (30, '2024-12-03', 3),
    (10, '2024-12-04', 4);
INSERT INTO fabrication (quantite, date_fabrication, id_produit) VALUES
    (100, '2024-12-02', 1),
    (50, '2024-12-03', 2),
    (30, '2024-12-04', 3),
    (10, '2024-12-05', 4);

INSERT INTO mvt_stock_ingredient (type_mvt, quantite, date_mvt, id_ingredient) VALUES
    ('Entree', 20, '2024-12-01 10:00:00', 1), -- Stock initial Farine
    ('Sortie', 10, '2024-12-02 11:12:00', 1), -- Utilisation Farine
    ('Entree', 5, '2024-12-03 16:32:00', 3),  -- Stock initial Levure
    ('Sortie', 2, '2024-12-04 17:40:00', 3);
