use proiect;
#Să se afişeze cel mai vandut produs; 
SELECT 
    Descriere as produs, MAX(vanzari) as vanzari 
FROM
    (SELECT 
        produse.Descriere, COUNT(*) AS vanzari
    FROM
        produse
    JOIN comanda ON comanda.Id_Produs = produse.Id_Produs
    GROUP BY produse.Descriere) AS x;
#Să se afişeze data în care au avut loc cele mai multe vanzări; 
SELECT 
    Data_comanda, MAX(vanzari) AS vanzari
FROM
    (SELECT 
        comanda.Data_Comanda, COUNT(*) AS vanzari
    FROM
        comanda
    GROUP BY comanda.Data_Comanda) AS x;
#Să se afişeze clientul (nume,prenume) care a cumpărat cele mai multe produse (şi valoarea totală a cumparaturilor).
select Prenume,Nume,max(produse) as produse from(
SELECT count(comanda.Numar_Cont) as produse ,persoana.Nume,persoana.Prenume FROM persoana
join comanda on comanda.Numar_Cont=persoana.Numar_Cont
GROUP BY comanda.Numar_Cont)as x;

#Sa se afiseze toate produsele fabricate in canada
Select produse.Descriere,furnizor.Nume from produse join furnizor on furnizor.Id_Furnizor=produse.Id_Furnizor
where furnizor.Tara="Canada";

#Sa se selecteze numele angajatiilor cu deducere intre 500 si 800
select persoana.Nume,persoana.Prenume ,angajat.Deducere from angajat JOIN persoana
on persoana.Numar_Cont=angajat.Numar_Cont 
where angajat.Deducere between 500 and 800
order by Nume,Prenume;

#sa se afiseze numele produselor mai scumpe decat 50 lei
select produse.Descriere ,produse.Valoare_Unitara from
produse where produse.Valoare_Unitara>=50
order by Valoare_Unitara;

#Sa se afiseze clientii care locuiesc in Nampa
select client.Username from client join persoana on persoana.Numar_Cont=client.Numar_Cont
join adresa on adresa.Numar_Cont=client.Numar_Cont
and adresa.Oras='Nampa';

#sa se afiseze produsele livare in orasul Kearney
select produse.Descriere from produse join comanda on comanda.Id_Produs= produse.Id_Produs join
adresa on adresa.Numar_Cont=comanda.Numar_Cont 
where adresa.Oras='Kearney';

#Sa se afiseze cate produse au fost livrate din Indonesia
select sum(produse.Stoc) as produse_livrate FROM produse JOIN furnizor on furnizor.Id_Furnizor=produse.Id_Furnizor
where furnizor.Tara="Indonesia";

#sa se afiseze clientii care au dat reviews de 5 stele;
select distinct client.Username from client join review on review.Numar_Cont=client.Numar_Cont
where review.Stele=5;

#sa se afiseze produsele cu o medie de reviews mai mica decat 3
select produse.Descriere,avg(review.stele) as medie_reviews from produse join review on review.Id_Produs=produse.Id_Produs
group by produse.Descriere
having medie_reviews<3;

#sa se selecteze persoanalele si produsul pt care au cumparat produs iar garantia este inca valabila
select persoana.Nume,persoana.Prenume,produse.Descriere,expira(comanda.Id_Comanda) as Data_termen
from produse join comanda on comanda.Id_Produs=produse.Id_Produs join persoana on persoana.Numar_Cont=comanda.Numar_Cont
HAVING Data_termen>curdate();

#sa se afiseze persoanele care au cumparat de la furnizori cu o calitate/reviews de 4 stele
select persoana.Nume,persoana.Prenume from persoana join comanda on comanda.Numar_Cont=persoana.Numar_Cont join
produse on produse.Id_Produs=comanda.Id_Produs join calitate_furnizori on produse.Descriere=calitate_furnizori.produs
where calitate_furnizori.calitate>=4;

#sa se afiseze produsele pentru care avem un stoc de minim 100 dar un maxim de 150;
select produse.Descriere,produse.Stoc from produse where produse.Stoc between 100 and 150;

#sa se afiseze angajati care locuiesc in College
select persoana.Nume,persoana.Prenume from angajat join adresa on adresa.Numar_Cont=angajat.Numar_Cont
join persoana on persoana.Numar_Cont=angajat.Numar_Cont
where adresa.Oras="College";

#sa se afiseze client care nu sunt majori
select client.Username from client join persoana on persoana.Numar_Cont=client.Numar_Cont 
where datediff(persoana.Data_Nasterii,curDate())<18;

#sa se afiseze adresa unde sau livrat produse din canada
select adresa.Oras,adresa.Strada from adresa join comanda on adresa.Numar_Cont=comanda.Numar_Cont join produse
on produse.Id_Produs=comanda.Id_Produs join furnizor on furnizor.Id_Furnizor=produse.Id_Furnizor where 
furnizor.Tara="Canada";

#sa se afiseze produsele cu furnizor Fivechat
select produse.Descriere from produse join furnizor on furnizor.Id_Furnizor=produse.Id_Furnizor 
where furnizor.Nume="Fivechat";

#sa se afiseze clienti care au parola sa inceapa cu litera M
select client.Username from client where client.Parola like 'M%';

#sa se afiseze persoanele care au cumparat produse cu reviws intre 2 si 4
select distinct persoana.Nume,persoana.Prenume from persoana join comanda on comanda.Numar_Cont=persoana.Numar_Cont join produse on produse.Id_Produs
=comanda.Id_Produs join review on review.Id_Produs=comanda.Id_Produs
where review.Stele between 2 and 4;