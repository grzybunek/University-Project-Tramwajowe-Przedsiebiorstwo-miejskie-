/*
Created: 13.11.2020
Modified: 19.11.2020
Model: Logical model
Database: Oracle 19c
*/


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE ZTMseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Pocztaseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Pracownikseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Satnowiskoseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Wynagrodzenieseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Adresseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Zajezdniaseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Linieseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Przystankiseq
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Pojazdyseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Modeleseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Markiseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Przyjazdseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Godzinaseq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

-- Create tables section -------------------------------------------------

-- Table ZTM

CREATE TABLE ZTM(
  Nr_biura Integer NOT NULL,
  Nazwa Varchar2(50 ) NOT NULL,
  Miasto Varchar2(50 ) NOT NULL,
  Data_zalozenia Date NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table ZTM

CREATE INDEX IX_Relationship1 ON ZTM (Nr_adresu)
/

-- Add keys for table ZTM

ALTER TABLE ZTM ADD CONSTRAINT BiuroPK PRIMARY KEY (Nr_biura)
/

-- Table Pracownicy

CREATE TABLE Pracownicy(
  Nr_pracownika Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(40 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  Plec Char(1 )
        CHECK (Plec IN ('K','M') ),
  Pesel Char(11 ),
  Data_zatrudnienia Date NOT NULL,
  Data_zwolnienia Date,
  Nr_biura Integer NOT NULL,
  Nr_adresu Integer NOT NULL,
  Nr_stanowiska Integer NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_Zatrudnia ON Pracownicy (Nr_biura)
/

CREATE INDEX IX_Relationship3 ON Pracownicy (Nr_adresu)
/

CREATE INDEX IX_Relationship4 ON Pracownicy (Nr_stanowiska)
/

-- Add keys for table Pracownicy

ALTER TABLE Pracownicy ADD CONSTRAINT Unique_Identifier1 PRIMARY KEY (Nr_pracownika)
/

-- Table Motorniczowie

CREATE TABLE Motorniczowie(
  Nr_pracownika Integer NOT NULL,
  Data_badania Date NOT NULL,
  Nr_licencji_tramwajowej Integer NOT NULL,
  Data_waznosci_licencji Date NOT NULL
)
/

-- Add keys for table Motorniczowie

ALTER TABLE Motorniczowie ADD CONSTRAINT Unique_Identifier2 PRIMARY KEY (Nr_pracownika)
/

-- Table Tramwaje

CREATE TABLE Tramwaje(
  Nr_pojazdu Integer NOT NULL,
  Rok_produkcji Integer NOT NULL,
  Liczba_drzwi Integer NOT NULL,
  Liczba_miejsc_siedzacych Integer NOT NULL,
  Data_przegladu Date NOT NULL,
  Liczba_miejsc_stojacych Integer NOT NULL,
  Czy_biletomat Char(1 ) NOT NULL,
  Czy_niskopodlogowy Integer NOT NULL,
  Liczba_kasownikow Integer NOT NULL,
  Nr_biura Integer NOT NULL,
  Nr_modelu Integer NOT NULL
)
/

-- Create indexes for table Tramwaje

CREATE INDEX IX_ZTM_posiada_tramwaje ON Tramwaje (Nr_biura)
/

CREATE INDEX IX_Relationship12 ON Tramwaje (Nr_modelu)
/

-- Add keys for table Tramwaje

ALTER TABLE Tramwaje ADD CONSTRAINT Unique_Identifier6 PRIMARY KEY (Nr_pojazdu)
/

-- Table Zajezdnie

CREATE TABLE Zajezdnie(
  Nr_zajezdni Integer NOT NULL,
  Liczba_tramwajow Integer NOT NULL,
  Miejsca_parkingowe Integer NOT NULL,
  Nr_biura Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Zajezdnie

CREATE INDEX IX_Zarzadza ON Zajezdnie (Nr_biura)
/

CREATE INDEX IX_Relationship7 ON Zajezdnie (Nr_adresu)
/

-- Add keys for table Zajezdnie

ALTER TABLE Zajezdnie ADD CONSTRAINT Unique_Identifier9 PRIMARY KEY (Nr_zajezdni)
/

-- Table Linie

CREATE TABLE Linie(
  Nr_linii Integer NOT NULL,
  Czy_nocna Char(1 ) NOT NULL,
  Liczba_przystankow Integer NOT NULL,
  Nr_biura Integer NOT NULL
)
/

-- Create indexes for table Linie

CREATE INDEX IX_ZTM_posiada_linie ON Linie (Nr_biura)
/

-- Add keys for table Linie

ALTER TABLE Linie ADD CONSTRAINT Unique_Identifier10 PRIMARY KEY (Nr_linii)
/

-- Table Przystanki

CREATE TABLE Przystanki(
  Nr_przystanku Integer NOT NULL,
  Nazwa_przystanku Varchar2(50 ) NOT NULL,
  Czy_biletomat Char(1 ) NOT NULL,
  Czy_wiata Char(1 ) NOT NULL,
  Nr_biura Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Przystanki

CREATE INDEX IX_ZTM_posiada_przystanek ON Przystanki (Nr_biura)
/

CREATE INDEX IX_Relationship8 ON Przystanki (Nr_adresu)
/

-- Add keys for table Przystanki

ALTER TABLE Przystanki ADD CONSTRAINT Unique_Identifier11 PRIMARY KEY (Nr_przystanku)
/

-- Table Zajezdnia_Linia

CREATE TABLE Zajezdnia_Linia(
  Nr_zajezdni Integer NOT NULL,
  Nr_linii Integer NOT NULL
)
/

-- Table Zajezdnia_Tramwaj

CREATE TABLE Zajezdnia_Tramwaj(
  Nr_zajezdni Integer NOT NULL,
  Nr_pojazdu Integer NOT NULL
)
/

-- Table Przyjazd_na_przystanek

CREATE TABLE Przyjazd_na_przystanek(
  Nr_linii Integer NOT NULL,
  Nr_przystanku Integer NOT NULL,
  Nr_godziny Integer NOT NULL
)
/

-- Create indexes for table Przyjazd_na_przystanek

CREATE INDEX Godziny ON Przyjazd_na_przystanek (Nr_godziny)
/

-- Table Pracownik_Tramwaj

CREATE TABLE Pracownik_Tramwaj(
  Nr_pracownika Integer NOT NULL,
  Nr_pojazdu Integer NOT NULL
)
/

-- Table Tramwaj_Linia

CREATE TABLE Tramwaj_Linia(
  Nr_pojazdu Integer NOT NULL,
  Nr_linii Integer NOT NULL
)
/

-- Table Adresy

CREATE TABLE Adresy(
  Nr_adresu Integer NOT NULL,
  Miasto Varchar2(30 ) NOT NULL,
  Ulica Varchar2(30 ) NOT NULL,
  Nr_lokalu Varchar2(5 ) NOT NULL,
  Nr_poczty Integer NOT NULL
)
/

-- Create indexes for table Adresy

CREATE INDEX IX_Relationship2 ON Adresy (Nr_poczty)
/

-- Add keys for table Adresy

ALTER TABLE Adresy ADD CONSTRAINT PK_Adresy PRIMARY KEY (Nr_adresu)
/

-- Table and Columns comments section

COMMENT ON COLUMN Adresy.Nr_adresu IS 'Unikatowy identyfikator adresu'
/
COMMENT ON COLUMN Adresy.Miasto IS 'Miasto'
/
COMMENT ON COLUMN Adresy.Ulica IS 'Ulica'
/
COMMENT ON COLUMN Adresy.Nr_lokalu IS 'Numer lokalu'
/

-- Table Poczty

CREATE TABLE Poczty(
  Nr_poczty Integer NOT NULL,
  Kod_poczty Char(6 ) NOT NULL,
  Poczta Varchar2(30 ) NOT NULL
)
/

-- Add keys for table Poczty

ALTER TABLE Poczty ADD CONSTRAINT PK_Poczty PRIMARY KEY (Nr_poczty)
/

ALTER TABLE Poczty ADD CONSTRAINT Kod_poczty UNIQUE (Kod_poczty)
/

-- Table and Columns comments section

COMMENT ON COLUMN Poczty.Nr_poczty IS 'Identyfikator poczty'
/
COMMENT ON COLUMN Poczty.Kod_poczty IS 'Kod poczty'
/
COMMENT ON COLUMN Poczty.Poczta IS 'Lokalizacja poczty'
/

-- Table Stanowiska

CREATE TABLE Stanowiska(
  Nr_stanowiska Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Opis Varchar2(400 ) NOT NULL
)
/

-- Add keys for table Stanowiska

ALTER TABLE Stanowiska ADD CONSTRAINT PK_Stanowiska PRIMARY KEY (Nr_stanowiska)
/

ALTER TABLE Stanowiska ADD CONSTRAINT Nazwa_stanowiska UNIQUE (Nazwa)
/

-- Table and Columns comments section

COMMENT ON COLUMN Stanowiska.Nr_stanowiska IS 'Unikatowy identyfikator stanowiska'
/
COMMENT ON COLUMN Stanowiska.Nazwa IS 'Nazwa stanowiska'
/
COMMENT ON COLUMN Stanowiska.Opis IS 'Opis stanowiska '
/

-- Table Wynagrodzenia

CREATE TABLE Wynagrodzenia(
  Nr_wynagrodzenia Integer NOT NULL,
  Data Date NOT NULL,
  Kwota_podstawowa Number(8,2) NOT NULL,
  Kwota_dodatkowa Number(8,2),
  Nr_pracownika Integer NOT NULL
)
/

-- Create indexes for table Wynagrodzenia

CREATE INDEX IX_Relationship6 ON Wynagrodzenia (Nr_pracownika)
/

-- Add keys for table Wynagrodzenia

ALTER TABLE Wynagrodzenia ADD CONSTRAINT PK_Wynagrodzenia PRIMARY KEY (Nr_wynagrodzenia)
/

-- Table and Columns comments section

COMMENT ON COLUMN Wynagrodzenia.Nr_wynagrodzenia IS 'Unikatowy numer wynagrodzenia'
/
COMMENT ON COLUMN Wynagrodzenia.Data IS 'Data wypłaty wynagrodzenia'
/
COMMENT ON COLUMN Wynagrodzenia.Kwota_podstawowa IS 'Kwota podstawowa'
/
COMMENT ON COLUMN Wynagrodzenia.Kwota_dodatkowa IS 'Kwota dodatkowa'
/

-- Table Marki

CREATE TABLE Marki(
  Nr_marki Integer NOT NULL,
  Nazwa Varchar2(20 ) NOT NULL,
  Opis Varchar2(400 )
)
/

-- Add keys for table Marki

ALTER TABLE Marki ADD CONSTRAINT PK_Marki PRIMARY KEY (Nr_marki)
/

ALTER TABLE Marki ADD CONSTRAINT Nazwa UNIQUE (Nazwa)
/

-- Table and Columns comments section

COMMENT ON COLUMN Marki.Nr_marki IS 'Unikatowy numer marki'
/
COMMENT ON COLUMN Marki.Nazwa IS 'Nazwa marki'
/
COMMENT ON COLUMN Marki.Opis IS 'Opis marki'
/

-- Table Modele

CREATE TABLE Modele(
  Nr_modelu Integer NOT NULL,
  Kod_modelu Varchar2(15 ) NOT NULL,
  Opis Varchar2(400 ),
  Nr_marki Integer NOT NULL
)
/

-- Create indexes for table Modele

CREATE INDEX IX_Relationship9 ON Modele (Nr_marki)
/

-- Add keys for table Modele

ALTER TABLE Modele ADD CONSTRAINT PK_Modele PRIMARY KEY (Nr_modelu)
/

ALTER TABLE Modele ADD CONSTRAINT Kod_modelu UNIQUE (Kod_modelu)
/

-- Table and Columns comments section

COMMENT ON COLUMN Modele.Nr_modelu IS 'Unikatowy identyfikator modelu'
/
COMMENT ON COLUMN Modele.Kod_modelu IS 'Kod modelu'
/
COMMENT ON COLUMN Modele.Opis IS 'Opis modelu'
/

-- Table Godziny_przyjazdow

CREATE TABLE Godziny_przyjazdow(
  Nr_godziny Integer NOT NULL,
  Godzina Integer NOT NULL
        CHECK (Godzina between 0 and 23),
  Minuta Integer NOT NULL
        CHECK (Minuta between 0 and 59)
)
/

-- Add keys for table Godziny_przyjazdow

ALTER TABLE Godziny_przyjazdow ADD CONSTRAINT PK_Godziny_przyjazdow PRIMARY KEY (Nr_godziny)
/

-- Table and Columns comments section

COMMENT ON COLUMN Godziny_przyjazdow.Nr_godziny IS 'Unikatowy numer godziny'
/
COMMENT ON COLUMN Godziny_przyjazdow.Godzina IS 'Godzina'
/
COMMENT ON COLUMN Godziny_przyjazdow.Minuta IS 'Minuta'
/

-- Trigger for sequence ZTMseq for column Nr_biura in table ZTM ---------
CREATE OR REPLACE TRIGGER ts_ZTM_ZTMseq BEFORE INSERT
ON ZTM FOR EACH ROW
BEGIN
  :new.Nr_biura := ZTMseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_ZTM_ZTMseq AFTER UPDATE OF Nr_biura
ON ZTM FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_biura in table ZTM as it uses sequence.');
END;
/

-- Trigger for sequence Pracownikseq for column Nr_pracownika in table Pracownicy ---------
CREATE OR REPLACE TRIGGER ts_Pracownicy_Pracownikseq BEFORE INSERT
ON Pracownicy FOR EACH ROW
BEGIN
  :new.Nr_pracownika := Pracownikseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Pracownicy_Pracownikseq AFTER UPDATE OF Nr_pracownika
ON Pracownicy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_pracownika in table Pracownicy as it uses sequence.');
END;
/

-- Trigger for sequence Pojazdyseq for column Nr_pojazdu in table Tramwaje ---------
CREATE OR REPLACE TRIGGER ts_Tramwaje_Pojazdyseq BEFORE INSERT
ON Tramwaje FOR EACH ROW
BEGIN
  :new.Nr_pojazdu := Pojazdyseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Tramwaje_Pojazdyseq AFTER UPDATE OF Nr_pojazdu
ON Tramwaje FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_pojazdu in table Tramwaje as it uses sequence.');
END;
/

-- Trigger for sequence Zajezdniaseq for column Nr_zajezdni in table Zajezdnie ---------
CREATE OR REPLACE TRIGGER ts_Zajezdnie_Zajezdniaseq BEFORE INSERT
ON Zajezdnie FOR EACH ROW
BEGIN
  :new.Nr_zajezdni := Zajezdniaseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Zajezdnie_Zajezdniaseq AFTER UPDATE OF Nr_zajezdni
ON Zajezdnie FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_zajezdni in table Zajezdnie as it uses sequence.');
END;
/

-- Trigger for sequence Linieseq for column Nr_linii in table Linie ---------
CREATE OR REPLACE TRIGGER ts_Linie_Linieseq BEFORE INSERT
ON Linie FOR EACH ROW
BEGIN
  :new.Nr_linii := Linieseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Linie_Linieseq AFTER UPDATE OF Nr_linii
ON Linie FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_linii in table Linie as it uses sequence.');
END;
/

-- Trigger for sequence Przystankiseq for column Nr_przystanku in table Przystanki ---------
CREATE OR REPLACE TRIGGER ts_Przystanki_Przystankiseq BEFORE INSERT
ON Przystanki FOR EACH ROW
BEGIN
  :new.Nr_przystanku := Przystankiseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Przystanki_Przystankiseq AFTER UPDATE OF Nr_przystanku
ON Przystanki FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_przystanku in table Przystanki as it uses sequence.');
END;
/

-- Trigger for sequence Adresseq for column Nr_adresu in table Adresy ---------
CREATE OR REPLACE TRIGGER ts_Adresy_Adresseq BEFORE INSERT
ON Adresy FOR EACH ROW
BEGIN
  :new.Nr_adresu := Adresseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Adresy_Adresseq AFTER UPDATE OF Nr_adresu
ON Adresy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_adresu in table Adresy as it uses sequence.');
END;
/

-- Trigger for sequence Pocztaseq for column Nr_poczty in table Poczty ---------
CREATE OR REPLACE TRIGGER ts_Poczty_Pocztaseq BEFORE INSERT
ON Poczty FOR EACH ROW
BEGIN
  :new.Nr_poczty := Pocztaseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Poczty_Pocztaseq AFTER UPDATE OF Nr_poczty
ON Poczty FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_poczty in table Poczty as it uses sequence.');
END;
/

-- Trigger for sequence Satnowiskoseq for column Nr_stanowiska in table Stanowiska ---------
CREATE OR REPLACE TRIGGER ts_Stanowiska_Satnowiskoseq BEFORE INSERT
ON Stanowiska FOR EACH ROW
BEGIN
  :new.Nr_stanowiska := Satnowiskoseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Stanowiska_Satnowiskoseq AFTER UPDATE OF Nr_stanowiska
ON Stanowiska FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_stanowiska in table Stanowiska as it uses sequence.');
END;
/

-- Trigger for sequence Wynagrodzenieseq for column Nr_wynagrodzenia in table Wynagrodzenia ---------
CREATE OR REPLACE TRIGGER ts_Wynagrodzenia_Wynagrodzenieseq BEFORE INSERT
ON Wynagrodzenia FOR EACH ROW
BEGIN
  :new.Nr_wynagrodzenia := Wynagrodzenieseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wynagrodzenia_Wynagrodzenieseq AFTER UPDATE OF Nr_wynagrodzenia
ON Wynagrodzenia FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_wynagrodzenia in table Wynagrodzenia as it uses sequence.');
END;
/

-- Trigger for sequence Markiseq for column Nr_marki in table Marki ---------
CREATE OR REPLACE TRIGGER ts_Marki_Markiseq BEFORE INSERT
ON Marki FOR EACH ROW
BEGIN
  :new.Nr_marki := Markiseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Marki_Markiseq AFTER UPDATE OF Nr_marki
ON Marki FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_marki in table Marki as it uses sequence.');
END;
/

-- Trigger for sequence Modeleseq for column Nr_modelu in table Modele ---------
CREATE OR REPLACE TRIGGER ts_Modele_Modeleseq BEFORE INSERT
ON Modele FOR EACH ROW
BEGIN
  :new.Nr_modelu := Modeleseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Modele_Modeleseq AFTER UPDATE OF Nr_modelu
ON Modele FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_modelu in table Modele as it uses sequence.');
END;
/

-- Trigger for sequence Godzinaseq for column Nr_godziny in table Godziny_przyjazdow ---------
CREATE OR REPLACE TRIGGER ts_Godziny_przyjazdow_Godzinaseq BEFORE INSERT
ON Godziny_przyjazdow FOR EACH ROW
BEGIN
  :new.Nr_godziny := Godzinaseq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Godziny_przyjazdow_Godzinaseq AFTER UPDATE OF Nr_godziny
ON Godziny_przyjazdow FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_godziny in table Godziny_przyjazdow as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE Pracownicy ADD CONSTRAINT Zatrudnia FOREIGN KEY (Nr_biura) REFERENCES ZTM (Nr_biura)
/



ALTER TABLE Zajezdnie ADD CONSTRAINT Zarzadza FOREIGN KEY (Nr_biura) REFERENCES ZTM (Nr_biura)
/



ALTER TABLE Tramwaje ADD CONSTRAINT ZTM_posiada_tramwaje FOREIGN KEY (Nr_biura) REFERENCES ZTM (Nr_biura)
/



ALTER TABLE Linie ADD CONSTRAINT ZTM_posiada_linie FOREIGN KEY (Nr_biura) REFERENCES ZTM (Nr_biura)
/



ALTER TABLE Przystanki ADD CONSTRAINT ZTM_posiada_przystanek FOREIGN KEY (Nr_biura) REFERENCES ZTM (Nr_biura)
/



ALTER TABLE ZTM ADD CONSTRAINT BiuroZTM_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Adresy ADD CONSTRAINT Adres_ma_poczte FOREIGN KEY (Nr_poczty) REFERENCES Poczty (Nr_poczty)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_ma_stanowisko FOREIGN KEY (Nr_stanowiska) REFERENCES Stanowiska (Nr_stanowiska)
/



ALTER TABLE Wynagrodzenia ADD CONSTRAINT Otrzymuje_wynagrodzenie FOREIGN KEY (Nr_pracownika) REFERENCES Pracownicy (Nr_pracownika)
/



ALTER TABLE Zajezdnie ADD CONSTRAINT Zajezdnia_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Przystanki ADD CONSTRAINT Przystanek_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Modele ADD CONSTRAINT Marka_ma_model FOREIGN KEY (Nr_marki) REFERENCES Marki (Nr_marki)
/



ALTER TABLE Tramwaje ADD CONSTRAINT Tramwaj_ma_model FOREIGN KEY (Nr_modelu) REFERENCES Modele (Nr_modelu)
/



ALTER TABLE Przyjazd_na_przystanek ADD CONSTRAINT Przyjazd_godzina FOREIGN KEY (Nr_godziny) REFERENCES Godziny_przyjazdow (Nr_godziny)
/





