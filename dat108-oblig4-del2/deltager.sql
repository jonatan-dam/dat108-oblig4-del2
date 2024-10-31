

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
    passord VARCHAR(100) NOT NULL
);

-- 



INSERT INTO
  deltager(mobil, fornavn, etternavn, kjonn, passord)
VALUES
    ('41768349', 'Per', 'Viskeler', 'mann', '10asjdkasdjk289!!!2'),
    ('12345678', 'Anne', 'Nordmann', 'kvinne', '298jkasdjASDOLASD!"349');
  
    