-- Supprimer toutes les tables
DO $$
    DECLARE
        tbl RECORD;
    BEGIN
        FOR tbl IN
            SELECT tablename
            FROM pg_tables
            WHERE schemaname = 'public'
            LOOP
                EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(tbl.tablename) || ' CASCADE';
            END LOOP;
    END $$;

-- Supprimer toutes les séquences
DO $$
    DECLARE
        seq RECORD;
    BEGIN
        FOR seq IN
            SELECT c.oid::regclass::text AS seqname
            FROM pg_class c
            WHERE c.relkind = 'S'
            LOOP
                EXECUTE 'DROP SEQUENCE IF EXISTS ' || seq.seqname || ' CASCADE';
            END LOOP;
    END $$;