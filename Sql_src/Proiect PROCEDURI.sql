use Proiect;

/*Returneaza data la care va expira un produs dupa achizitionare la data curenta/data comandei */
drop FUNCTION if EXISTS expira;
DELIMITER $$
CREATE FUNCTION expira(idComanda  int)
returns Date
DETERMINISTIC
BEGIN
	Declare data_Expirare date;
    select comanda.Data_Comanda from comanda WHERE comanda.Id_Comanda=idComanda
    into data_Expirare;
    set data_Expirare=date_add(data_Expirare,Interval 5 year);
    return data_Expirare;
end $$
DELIMITER ;


/*Insereaza datele nesesare o noua instanta de date pentru tabelul comanda/realizarea unei comenzi*/
drop PROCEDURE if EXISTS comandare;
DELIMITER $$
Create PROCEDURE comandare(Numar_Cont VARCHAR(8),Id_Produs int,cantitate int)
begin
	
Insert into Comanda(Id_Produs, Numar_Cont, Cantitate,Data_Comanda)
VALUES
    (Id_Produs,Numar_Cont,cantitate, CURRENT_DATE);
end $$
delimiter ;


/*efectueaza 3 comenzii diferite de cate aceeasi persoana*/
drop PROCEDURE if EXISTS cumparamult;
Delimiter //
Create PROCEDURE cumparamult(Cont varchar(8),Produs1 int,cantitate1 int ,Produs2 int,cantitate2 int,Produs3 int,cantitate3 int)
BEGIN
	call comandare(Cont,Produs1,cantitate1,current_date);
    call comandare(Cont,Produs2,cantitate2,current_date);
    call comandare(Cont,Produs3,cantitate3,current_date);
END//
Delimiter ;


/*Calculeaza salariul brut al unui angazat avand in veder deducerii*/
drop PROCEDURE if EXISTS salarii;
DELIMITER $$
Create PROCEDURE salarii()
begin
	Declare maxim int;
    Declare i int default 1;
    select max(id_angajat)
    from angajat into maxim;
    while i<=maxim do 
    UPDATE angajat
    set brut=salariu+deducere where id_angajat=i;
    set i=i+1;
    end while;
end $$
delimiter ;

/*creaza cont utilizator*/
drop PROCEDURE if EXISTS create_user;
DELIMITER $$
Create PROCEDURE create_user(Numecont VARCHAR(30),Parola2 VARCHAR(20),Id VARCHAR(8),  Data_Nasterii2 DATE,Nume2 VARCHAR(30),Prenume2 VARCHAR(30))
begin
INSERT INTO persoana(Numar_Cont,Nume,Prenume,Data_Nasterii)
VALUES
	(Id,Nume2,Prenume2, Data_Nasterii2);
	Insert into Client(Numar_Cont,Username,Parola)
VALUES
    (Id,Numecont,Parola2);
end $$
delimiter ;


/*creaza un review de catre un cont*/
drop PROCEDURE if EXISTS create_review;
DELIMITER $$
Create PROCEDURE create_review(Id_Produs int,Stele DECIMAL(6,2),Numar_Cont VARCHAR(8),Descriere varchar(100))
begin
	Insert into Review(Id_Produs,Stele,Numar_Cont,Descriere)
VALUES
(Id_Produs,Stele,Numar_Cont,Descriere);
end $$
delimiter ;

drop PROCEDURE if EXISTS create_angajat;
DELIMITER $$
Create PROCEDURE create_angajat(Pay int,User_name VARCHAR(30),Parola2 VARCHAR(20),NumarCont VARCHAR(8),  Data_Nasterii2 DATE,Nume2 VARCHAR(30),Prenume2 VARCHAR(30))
begin
	INSERT INTO persoana(Numar_Cont,Nume,Prenume,Data_Nasterii)
VALUES
	(NumarCont,Nume2,Prenume2, Data_Nasterii2);
	Insert into Client(Numar_Cont,Username,Parola)
VALUES
    (NumarCont,User_name,Parola2);
	INSERT into angajat(Numar_Cont,salariu)
values(NumarCont,Pay);
end $$
delimiter ;

#pentru tabelul produse adauga stoc nou pentru produs
DROP Procedure if exists comanda_stoc
DELIMITER //
create procedure comanda_stoc(Id int,Produs varchar(30),stoc int)
begin 
	update produse
    set produse.stoc=produse.stoc+stoc where produse.Descriere=Produs or produse.Id_Produs=Id;
end //
DELIMITER;

#sterge un cont de client 
Drop procedure if exists stergecont
DELIMITER //
Create procedure stergecont(Nume varchar(30),Prenume varchar(30))
BEGIN
	Declare Id varchar(8);
SELECT 
    persoana.Numar_Cont
FROM
    persoana
WHERE
    persoana.Nume = Nume
        AND persoana.Prenume = Prenume INTO Id;
	DELETE FROM client 
WHERE
    client.Numar_Cont = Id;
end
// DELIMITER ;
#sterge un angajat din tabela de angajati in functie de numme
DROP procedure if exists concediere
DELIMITER $$
CREATE procedure concediere(nume varchar(30),prenume varchar(30))
begin
	Declare Id varchar(8);
SELECT 
    persoana.Numar_Cont
FROM
    persoana
WHERE
    persoana.nume = nume
        AND persoana.prenume = prenume INTO Id;
	DELETE FROM angajat 
WHERE
    angajat.Numar_Cont = Id;
end $$
DELIMITER ;
/*dupa efectuarea unei comenzii ,din stocul magazinului se va extrage cantitatea comandata de catre o persoana*/
Delimiter //
create TRIGGER cumpar AFTER INSERT on comanda
FOR EACH ROW
BEGIN
	DECLARE Id INT;
    DECLARE Cantitate1 int;
    DECLARE NewComanda INT;
    SELECT max(new.Id_Comanda)
		from Comanda INTO NewComanda;
	select Id_Produs from comanda
    where Id_Comanda=NewComanda into Id;
	select cantitate
		from comanda 
        where Id_Comanda=NewComanda
        into Cantitate1;
	UPDATE produse
    set stoc=stoc-Cantitate1
    where Id_Produs=Id;
END// 
Delimiter ; 

/*calculeaza deducerea unui angajat dupa ce acesta scrie un review*/
Delimiter //
create TRIGGER bonus after insert  on review
FOR EACH ROW
BEGIN
	DECLARE maxim int;
    DECLARE i int DEFAULT 1;
    Declare cont varchar(8);
    SELECT max(Id_Review)
    from review into maxim;
    select Numar_Cont from review WHERE Id_Review=maxim into cont;
    UPDATE angajat
    set Deducere=Deducere+20 where Numar_Cont=cont;
End //
Delimiter ; 