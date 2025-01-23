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

INSERT INTO categories (libelle) VALUES
    ('Viennoiserie'),
    ('Snacks'),
    ('Desserts'),
    ('Pains traditionnels'),
    ('Produits de fête');

INSERT INTO garnitures (libelle) VALUES
    ('Chocolat'),
    ('Fruits frais'),
    ('Crème pâtissière'),
    ('Confiture'),
    ('Fromage'),
    ('Noisettes'),
    ('Amandes');

INSERT INTO produits_categories (id_produit, id_categorie) VALUES
    (1, 4), -- Baguette, Pains traditionnels
    (2, 1), -- Croissant, Viennoiserie
    (2, 2), -- Croissant, Snacks
    (3, 1), -- Pain au chocolat, Viennoiserie
    (3, 2), -- Pain au chocolat, Snacks
    (4, 3); -- Tarte aux pommes, Desserts

INSERT INTO produits_garnitures (id_produit, id_garniture) VALUES
    (3, 1), -- Pain au chocolat, Chocolat
    (4, 2), -- Tarte aux pommes, Fruits frais
    (4, 3), -- Tarte aux pommes, Crème pâtissière
    (2, 5), -- Croissant, Fromage
    (4, 7); -- Tarte aux pommes, Amandes

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

INSERT INTO vendeur (nom, commission)
VALUES ('Rakoto', 5),
       ('Jean', 5);

INSERT INTO ventes (total, date_vente, id_client, id_vendeur) VALUES
    (1000.50, '2025-01-01 10:30:00', 1, 1),
    (500.00, '2025-01-02 14:00:00', 2, 1),
    (1200.75, '2025-01-03 16:45:00', 3, 2),
    (800.25, '2025-01-04 12:15:00', 4, 2),
    (400.00, '2025-01-05 09:00:00', 3, 1), -- Vente sans vendeur
(950.80, '2025-01-06 11:30:00', 2, 1);

INSERT INTO details_vente (quantite, prix_unitaire, id_vente, id_produit) VALUES
    (2, 500.25, 1, 1),
    (1, 500.00, 2, 2),
    (3, 400.25, 3, 3),
    (4, 200.00, 4, 4),
    (5, 80.00, 5, 1),
    (6, 150.13, 6, 2),
    (1, 100.00, 1, 3),
    (2, 250.00, 1, 4),
    (3, 300.00, 2, 1),
(2, 400.00, 3, 4);

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

