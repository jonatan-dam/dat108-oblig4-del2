

-- Sletter hele sulamitten og oppretter på nytt.
DROP SCHEMA IF EXISTS deltager_schema CASCADE;
CREATE SCHEMA deltager_schema;
SET search_path TO deltager_schema;
    
-- 


CREATE TABLE deltager
(
    mobil CHAR(8) PRIMARY KEY,
    fornavn VARCHAR(20) NOT NULL,
    etternavn VARCHAR(20) NOT NULL,
    kjonn VARCHAR(6) CHECK (kjonn='mann' OR kjonn='kvinne'),
    passordhash VARCHAR(100) NOT NULL,
    salt VARCHAR(100) NOT NULL
);

-- 




    