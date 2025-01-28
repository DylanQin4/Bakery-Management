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
ALTER TABLE produits ALTER COLUMN prix_revient TYPE NUMERIC(38,2);
ALTER TABLE produits ALTER COLUMN prix_vente TYPE NUMERIC(38,2);


CREATE TABLE historique_prix (
    id SERIAL PRIMARY KEY,
    produit_id INT NOT NULL,
    ancien_prix_revient NUMERIC(15,2) NOT NULL,
    ancien_prix_vente NUMERIC(15,2) NOT NULL,
    date_modification TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produit_id) REFERENCES produits (id) ON DELETE CASCADE
);

DROP TRIGGER IF EXISTS trigger_on_update_prix ON produits;

CREATE OR REPLACE FUNCTION trigger_update_prix()
    RETURNS TRIGGER AS $$
BEGIN
    -- Insère l'ancien et le nouveau prix dans la table d'historique
    INSERT INTO historique_prix (
        produit_id,
        ancien_prix_revient,
        ancien_prix_vente,
        date_modification
    )
    VALUES (
               NEW.id,
               OLD.prix_revient,
               OLD.prix_vente,
                CURRENT_TIMESTAMP
           );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_on_update_prix
    AFTER UPDATE OF prix_revient, prix_vente
    ON produits
    FOR EACH ROW
    WHEN (OLD.prix_revient IS DISTINCT FROM NEW.prix_revient OR
          OLD.prix_vente IS DISTINCT FROM NEW.prix_vente)
EXECUTE FUNCTION trigger_update_prix();


CREATE TABLE p_conseilles(
    id SERIAL,
    id_produit INTEGER NOT NULL,
    date_conseillee TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    etat INTEGER DEFAULT 0,
    PRIMARY KEY(id),
    FOREIGN KEY(id_produit) REFERENCES produits(id) ON DELETE CASCADE
);

CREATE TABLE categories(
    id SERIAL,
    libelle VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(libelle)
);

CREATE TABLE produits_categories(
    id_produit INTEGER NOT NULL,
    id_categorie INTEGER NOT NULL,
    PRIMARY KEY(id_produit, id_categorie),
    FOREIGN KEY(id_produit) REFERENCES produits(id),
    FOREIGN KEY(id_categorie) REFERENCES categories(id)
);

CREATE TABLE garnitures(
    id SERIAL,
    libelle VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(libelle)
);

CREATE TABLE produits_garnitures(
    id_produit INTEGER NOT NULL,
    id_garniture INTEGER NOT NULL,
    PRIMARY KEY(id_produit, id_garniture),
    FOREIGN KEY(id_produit) REFERENCES produits(id),
    FOREIGN KEY(id_garniture) REFERENCES garnitures(id)
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

-- CREATE TABLE fabrication(
--                             id SERIAL,
--                             prix_total NUMERIC(15,2) NOT NULL,
--                             date_fabrication DATE,
--                             PRIMARY KEY(id)
-- );
--
-- CREATE TABLE details_fabrication(
--                                     id SERIAL,
--                                     quantite NUMERIC(15,2) NOT NULL,
--                                     id_produit INTEGER NOT NULL,
--                                     prix_unitaire NUMERIC(15,2) NOT NULL,
--                                     quantite INTEGER NOT NULL,
--                                     PRIMARY KEY(id),
--                                     FOREIGN KEY(id_produit) REFERENCES produits(id)
-- );
-- et il faut aussi mettre a jour trg_after_insert_fabrication

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

CREATE TABLE genre (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE vendeur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(150) NOT NULL UNIQUE,
    commission INTEGER DEFAULT 5,
    id_genre INT NOT NULL ,
    FOREIGN KEY(id_genre) REFERENCES genre(id)
);


CREATE TABLE ventes(
    id SERIAL,
    total NUMERIC(15,2) NOT NULL,
    date_vente TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_client INTEGER NOT NULL,
    id_vendeur INTEGER,
    PRIMARY KEY(id),
    FOREIGN KEY(id_client) REFERENCES clients(id),
    FOREIGN KEY(id_vendeur) references vendeur(id)
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

-- drop table details_vente;
-- drop table ventes;
-- drop table vendeur;

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

-- DROP VIEW IF EXISTS v_ventes_par_categorie_garniture;
-- DROP view v_ventes_par_categorie_garniture;
-- CREATE OR REPLACE VIEW v_ventes_par_categorie_garniture AS
-- SELECT
--     v.*,
--     ARRAY_AGG(DISTINCT c.id) AS categories,
--     ARRAY_AGG(DISTINCT g.id) AS garnitures
-- FROM
--     ventes v
--         JOIN details_vente dv ON v.id = dv.id_vente
--         JOIN produits p ON dv.id_produit = p.id
--         JOIN produits_categories pc ON p.id = pc.id_produit
--         JOIN categories c ON pc.id_categorie = c.id
--         JOIN produits_garnitures pg ON p.id = pg.id_produit
--         JOIN garnitures g ON pg.id_garniture = g.id
-- GROUP BY
--     v.id, v.date_vente, v.total;


-- SELECT *
-- FROM v_ventes_par_categorie_garniture
-- WHERE 2 = ANY(categories) AND 5 = ANY(garnitures);




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



