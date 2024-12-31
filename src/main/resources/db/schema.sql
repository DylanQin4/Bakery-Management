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
    FOREIGN KEY(id_unite) REFERENCES unites(id)
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
    type_mvt VARCHAR(10) NOT NULL,
    quantite NUMERIC(15,2) NOT NULL,
    date_mvt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_ingredient INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_ingredient) REFERENCES ingredients(id)
);

CREATE TABLE mvt_stock_produit(
    id SERIAL,
    type_mvt VARCHAR(10) NOT NULL,
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

SELECT p.id, p.nom, p.prix_revient, p.prix_vente,
       COALESCE(SUM(f.quantite), 0) AS quantite_fabriquee,
       COALESCE(SUM(dv.quantite), 0) AS quantite_vendue,
       (COALESCE(SUM(f.quantite), 0) - COALESCE(SUM(dv.quantite), 0)) AS stock_restant
FROM produits p
         LEFT JOIN fabrication f ON p.id = f.id_produit
         LEFT JOIN details_vente dv ON p.id = dv.id_produit
GROUP BY p.id, p.nom, p.prix_revient, p.prix_vente
HAVING (COALESCE(SUM(f.quantite), 0) - COALESCE(SUM(dv.quantite), 0)) > 0;
