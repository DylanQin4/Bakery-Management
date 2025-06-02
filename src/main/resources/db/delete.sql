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

-- Supprimer toutes les fonctions
DO $$
    DECLARE
        func RECORD;
    BEGIN
        FOR func IN
            SELECT n.nspname AS schema_name, p.proname AS function_name, pg_catalog.pg_get_function_identity_arguments(p.oid) AS args
            FROM pg_proc p
                     JOIN pg_namespace n ON p.pronamespace = n.oid
            WHERE n.nspname = 'public'
            LOOP
                EXECUTE 'DROP FUNCTION IF EXISTS ' || quote_ident(func.schema_name) || '.' || quote_ident(func.function_name) || '(' || func.args || ') CASCADE';
            END LOOP;
    END $$;

-- Supprimer toutes les triggers
DO $$
    DECLARE
        trg RECORD;
    BEGIN
        FOR trg IN
            SELECT event_object_table AS table_name, trigger_name
            FROM information_schema.triggers
            WHERE trigger_schema = 'public'
            LOOP
                EXECUTE 'DROP TRIGGER IF EXISTS ' || quote_ident(trg.trigger_name) || ' ON ' || quote_ident(trg.table_name) || ' CASCADE';
            END LOOP;
    END $$;
