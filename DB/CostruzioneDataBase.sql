-- Database Section
-- ________________ 
DROP DATABASE IF EXISTS DesFusion;

CREATE DATABASE DesFusion;
USE DesFusion;

-- DBSpace Section
-- _______________

-- Tables Section
-- _____________ 

CREATE TABLE DESCRIZIONI (
     Id INT NOT NULL AUTO_INCREMENT,
     Descrizione VARCHAR(20) NOT NULL,
     DescrizioneEng VARCHAR(20) NOT NULL,
     Gruppo VARCHAR(20) NOT NULL,     
     PRIMARY KEY (Id));

-- Index Section
-- _____________ 

CREATE UNIQUE INDEX ID_DESCRIZIONI
     ON DESCRIZIONI (Id);
    
-- Views Section
-- _____________


-- Insertions Section
-- _____________ 

INSERT INTO DESCRIZIONI (Descrizione, DescrizioneEng ,Gruppo ) VALUES
('PROVA', 'Try', 'VTS_PROVA'),
('PROVA2', 'Try2', 'VTS_PROVA');