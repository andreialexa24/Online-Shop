use proiect;

CREATE OR REPLACE VIEW istoric AS
    SELECT 
        nume,
        prenume,
        descriere,
        cantitate,
        cantitate * valoare_unitara AS ValoareTotala
    FROM
        persoana AS p,
        comanda AS c,
        produse AS o
    WHERE
        p.Numar_Cont = c.Numar_Cont
            AND o.Id_Produs = c.Id_Produs;


CREATE OR REPLACE VIEW garantii AS
    SELECT 
        descriere, EXPIRA(c.Id_Comanda) AS data_expirarii
    FROM
        produse AS p,
        comanda AS c
    WHERE
        p.Id_Produs = c.Id_Produs
    HAVING data_expirarii < CURDATE();

CREATE OR REPLACE VIEW Calitate_furnizori AS
    SELECT 
        f.Id_Furnizor AS Id,
        f.nume AS Companie,
        p.descriere AS produs,
        AVG(r.stele) AS Calitate
    FROM
        review AS r,
        produse AS p,
        furnizor AS f
    WHERE
        r.Id_Produs = p.Id_Produs
            AND f.Id_Furnizor = p.Id_Furnizor
    GROUP BY p.descriere
    ORDER BY id;

CREATE OR REPLACE VIEW Magazin AS
    SELECT 
        produse.Descriere AS Produs,
        produse.Valoare_Unitara AS Pret,
        furnizor.Nume AS Companie,
        CAST(AVG(review.Stele) AS DECIMAL (3 , 2 )) AS Stele
    FROM
        produse
            JOIN
        furnizor ON produse.Id_Furnizor = furnizor.Id_Furnizor
            LEFT JOIN
        review ON produse.Id_Produs = review.Id_Produs
    GROUP BY produse.Descriere
    ORDER BY Produs;