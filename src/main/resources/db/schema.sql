CREATE TABLE unites(
    id SERIAL,
    libelle VARCHAR(10) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(libelle)
);
ALTER TABLE unites ADD COLUMN description VARCHAR(50);

CREATE TABLE ingredients(
    id SERIAL,
    nom VARCHAR(150) NOT NULL,
    besoin_normal NUMERIC(15,2) NOT NULL,
    besoin_ferie NUMERIC(15,2) NOT NULL,
    id_unite INTEGER NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(nom),
    FOREIGN KEY(id_unite) REFERENCES unites(id)ON DELETE CASCADE
);

CREATE TABLE produits(
    id SERIAL,
    nom VARCHAR(200) NOT NULL,
    prix_revient NUMERIC(15,2) NOT NULL,
    prix_vente NUMERIC(15,2) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(nom)
);

CREATE TABLE mvt_stock_ingredient(
    id SERIAL,
    type_mvt VARCHAR(10) CHECK (type_mvt IN ('ENTREE', 'SORTIE')) NOT NULL,
    quantite NUMERIC(15,2) NOT NULL,
    date_mvt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_ingredient INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_ingredient) REFERENCES ingredients(id)
);

CREATE TABLE mvt_stock_produit(
    id SERIAL,
    type_mvt VARCHAR(10) CHECK (type_mvt IN ('ENTREE', 'SORTIE')) NOT NULL,
    quantite NUMERIC(15,2) NOT NULL,
    date_mvt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_produit INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_produit) REFERENCES produits(id)
);

CREATE TABLE fabrication(
    id SERIAL,
    quantite INTEGER NOT NULL,
    date_fabrication DATE,
    id_produit INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_produit) REFERENCES produits(id)
);

CREATE TABLE clients(
    id SERIAL,
    nom VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(nom)
);

CREATE TABLE fournisseurs(
    id SERIAL,
    nom VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE ventes(
    id SERIAL,
    total NUMERIC(15,2) NOT NULL,
    date_vente TIMESTAMP NOT NULL,
    id_client INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_client) REFERENCES clients(id)
);

CREATE TABLE details_vente(
    id SERIAL,
    quantite INTEGER NOT NULL,
    prix_unitaire NUMERIC(15,2) NOT NULL,
    id_vente INTEGER NOT NULL,
    id_produit INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_vente) REFERENCES ventes(id),
    FOREIGN KEY(id_produit) REFERENCES produits(id)
);

CREATE TABLE achats(
    id SERIAL,
    total NUMERIC(15,2) NOT NULL,
    date_achat TIMESTAMP NOT NULL,
    id_fournisseur INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_fournisseur) REFERENCES fournisseurs(id)
);

CREATE TABLE details_achat(
    id SERIAL,
    quantite NUMERIC(15,2) NOT NULL,
    prix_unitaire NUMERIC(15,2) NOT NULL,
    id_ingredient INTEGER NOT NULL,
    id_achat INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_ingredient) REFERENCES ingredients(id),
    FOREIGN KEY(id_achat) REFERENCES achats(id)
);

CREATE TABLE recette(
    id_ingredient INTEGER,
    id_produit INTEGER,
    quantite NUMERIC(15,2) NOT NULL,
    PRIMARY KEY(id_ingredient, id_produit),
    FOREIGN KEY(id_ingredient) REFERENCES ingredients(id),
    FOREIGN KEY(id_produit) REFERENCES produits(id)
);

-- ================================= VIEWS =================================
-- rechercher un ingredient dans fabrication
SELECT f.*
FROM fabrication f
    JOIN produits p ON f.id_produit = p.id
    JOIN recette r ON p.id = r.id_produit
    JOIN ingredients i ON r.id_ingredient = i.id
WHERE i.nom ILIKE '%ingredient_name%';

-- ================================= TRIGGERS =================================

-- Fonction qui s'exécute après l'insertion dans details_achat
CREATE OR REPLACE FUNCTION after_insert_details_achat()
    RETURNS TRIGGER AS $$
BEGIN
    -- Insérer un mouvement d'entrée dans mvt_stock_ingredient
    INSERT INTO mvt_stock_ingredient (type_mvt, quantite, date_mvt, id_ingredient)
    VALUES ('ENTREE', NEW.quantite, CURRENT_TIMESTAMP, NEW.id_ingredient);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Création du Trigger
CREATE TRIGGER trg_after_insert_details_achat
    AFTER INSERT ON details_achat
    FOR EACH ROW
EXECUTE FUNCTION after_insert_details_achat();

-- Fonction qui s'exécute après l'insertion dans fabrication
CREATE OR REPLACE FUNCTION after_insert_fabrication()
    RETURNS TRIGGER AS $$
BEGIN
    -- Insérer un mouvement d'entrée dans mvt_stock_produit
    INSERT INTO mvt_stock_produit (type_mvt, quantite, date_mvt, id_produit)
    VALUES ('ENTREE', NEW.quantite, CURRENT_TIMESTAMP, NEW.id_produit);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Création du Trigger
CREATE TRIGGER trg_after_insert_fabrication
    AFTER INSERT ON fabrication
    FOR EACH ROW
EXECUTE FUNCTION after_insert_fabrication();

-- Fonction qui s'exécute après l'insertion dans details_vente
CREATE OR REPLACE FUNCTION after_insert_details_vente()
    RETURNS TRIGGER AS $$
BEGIN
    -- Insérer un mouvement de sortie dans mvt_stock_produit
    INSERT INTO mvt_stock_produit (type_mvt, quantite, date_mvt, id_produit)
    VALUES ('SORTIE', NEW.quantite, CURRENT_TIMESTAMP, NEW.id_produit);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Création du Trigger
CREATE TRIGGER trg_after_insert_details_vente
    AFTER INSERT ON details_vente
    FOR EACH ROW
EXECUTE FUNCTION after_insert_details_vente();



