USE desfusion;
/* CREAZIONE NUOVA RIGA */
INSERT INTO DESCRIZIONI (Descrizione, DescrizioneEng ,Gruppo ) VALUES
('NUOVO', 'NEWADD', 'NUOVO');

/*Show all occurence */
select *
from descrizioni
order by Gruppo;

/*Delete value */
delete FROM descrizioni
where Descrizione = 'NUOVO'
AND DescrizioneEng = 'NEWADD'
AND Gruppo = 'NUOVO';

/*Modifica Descrizione */
update descrizioni
SET Descrizione = 'PIPPO'
, DescrizioneEng = 'PLUTO'
where Descrizione = 'NUOVO'
AND DescrizioneEng = 'NEWADD'
AND Gruppo = 'NUOVO';
