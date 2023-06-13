use proiect;
drop user if EXISTS administrator;
drop user if EXISTS utilizator;
CREATE USER IF NOT EXISTS administrator IDENTIFIED BY "PAROLA_PUTERNICA";
CREATE User IF NOT EXISTS utilizator IDENTIFIED by "PAROLA_SLABA";
select user from mysql.user;   

grant all privileges on proiect.* to administrator;
GRANT EXECUTE ON proiect.* to administrator;
GRANT SELECT ON proiect.produse to utilizator;
GRANT EXECUTE ON PROCEDURE proiect.create_review to utilizator;
GRANT EXECUTE ON PROCEDURE proiect.create_user to utilizator;
GRANT EXECUTE ON PROCEDURE proiect.comandare to utilizator;
GRANT EXECUTE ON PROCEDURE proiect.cumparamult to utilizator;
SHOW GRANTS  for administrator;
SHOW GRANTS  for utilizator;