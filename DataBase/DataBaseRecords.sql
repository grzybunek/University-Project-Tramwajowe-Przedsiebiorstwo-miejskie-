insert into MARKI(nazwa,opis) values ('PESA','Tramwaj marki PESA')


insert into MARKI(nazwa,opis) values ('CONSTAL','Tramwaj marki CONSTAL')


insert into MODELE(kod_modelu,opis, nr_marki) values ('116NA','Model tramwaju marki CONSTAL o kodzie 116NA',2)
insert into MODELE(kod_modelu,opis, nr_marki) values ('2020N','Model tramwaju marki PESA o kodzie 2020N',1)


insert into POCZTY(kod_poczty,poczta) values ('00-034','Warszawa')
insert into POCZTY(kod_poczty,poczta) values ('00-156','Warszawa')
insert into POCZTY(kod_poczty,poczta) values ('00-120','Warszawa')
insert into POCZTY(kod_poczty,poczta) values ('05-124','Skrzeszew')


insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Warszawa','Nowogrodzka','69',1)
insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Warszawa','Nowy Œwiat','23',3)
insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Janówek Pierwszy','Nowodworska','118A',4)
insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Warszawa','Zlota','1',2)
insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Warszawa','Mickiewicza','420',3)
insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Warszawa','Mickiewicza','32',3)
insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Warszawa','Ogrodowa','12B',1)
insert into ADRESY(miasto,ulica,nr_lokalu,nr_poczty) values ('Warszawa','Maciusia Pierwszego','21B',1)


insert into ZTM(nazwa,miasto,data_zalozenia,nr_adresu) values ('Tramwajowe przedsiêbiorstwo miejskie','Warszawa','19970128',2)


insert into STANOWISKA(nazwa,opis) values ('Ksiegowa','Osoba odpowiedzialna za finanse')
insert into STANOWISKA(nazwa,opis) values ('Motorniczy','Osoba kierujaca tramwajem')
insert into STANOWISKA(nazwa,opis) values ('Kontroler biletów','Osoba odpowiedzialna za kontrolê biletów')
insert into STANOWISKA(nazwa,opis) values ('Logistyk','Osoba odpowiedzialna za logistyke np. rozkad jazdy')
insert into STANOWISKA(nazwa,opis) values ('Konserwator','Osoba odpowiedzialna za dbanie o wlasnoœæ firmy')


insert into PRACOWNICY(imie, nazwisko,data_urodzenia, plec, pesel, data_zatrudnienia, nr_biura,nr_adresu,nr_stanowiska) values('Jan','Kowalski','19821105','M',98048651851,'20150901',1,3,2)
insert into PRACOWNICY(imie, nazwisko,data_urodzenia, plec, pesel, data_zatrudnienia, nr_biura,nr_adresu,nr_stanowiska) values('Urszula','Dreszer','19861015','K',86041568131,'20160505',1,5,1)
insert into PRACOWNICY(imie, nazwisko,data_urodzenia, data_zatrudnienia, nr_biura,nr_adresu,nr_stanowiska) values('Sasza','Dmytro','19980101','20180719',1,4,5)
insert into PRACOWNICY(imie, nazwisko,data_urodzenia,plec, data_zatrudnienia,data_zwolnienia, nr_biura,nr_adresu,nr_stanowiska) values('Jakub','Dreszer','19850217','M','20120928','20170219',1,5,3)

insert into MOTORNICZOWIE(nr_pracownika, data_badania,nr_licencji_tramwajowej,data_waznosci_licencji) values(2,'20200319',98415,'20220401')


insert into ZAJEZDNIE(liczba_tramwajow,miejsca_parkingowe,nr_biura,nr_adresu) values(20,200,1,1)
insert into ZAJEZDNIE(liczba_tramwajow,miejsca_parkingowe,nr_biura,nr_adresu) values(20,250,1,6)


insert into LINIE(czy_nocna,liczba_przystankow,nr_biura) values(1,14,1)


insert into PRZYSTANKI(nazwa_przystanku,czy_biletomat,czy_wiata,nr_biura,nr_adresu) values('Bohaterów Modlina',1,1,1,7)
insert into PRZYSTANKI(nazwa_przystanku,czy_biletomat,czy_wiata,nr_biura,nr_adresu) values('Rondo Maæka',0,1,1,8)


insert into TRAMWAJE(rok_produkcji,liczba_drzwi,liczba_miejsc_siedzacych,data_przegladu,liczba_miejsc_stojacych,czy_biletomat,czy_niskopodlogowy,liczba_kasownikow,nr_biura,nr_modelu) values(2010,10,60,'20200120',30,1,1,2,1,1)
insert into TRAMWAJE(rok_produkcji,liczba_drzwi,liczba_miejsc_siedzacych,data_przegladu,liczba_miejsc_stojacych,czy_biletomat,czy_niskopodlogowy,liczba_kasownikow,nr_biura,nr_modelu) values(2012,10,60,'20210501',30,0,1,2,1,2)


insert into WYNAGRODZENIA(data,kwota_podstawowa,nr_pracownika) values('20200110', 3000,1)
insert into WYNAGRODZENIA(data,kwota_podstawowa,kwota_dodatkowa,nr_pracownika) values('20200110', 3000,200,2)
insert into WYNAGRODZENIA(data,kwota_podstawowa,kwota_dodatkowa,nr_pracownika) values('20200210', 3000,200,2)
insert into WYNAGRODZENIA(data,kwota_podstawowa,nr_pracownika) values('20200310', 3500,2)


insert into GODZINY_PRZYJAZDoW(godzina,minuta) values(12,12)
insert into GODZINY_PRZYJAZDoW(godzina,minuta) values(12,30)
insert into GODZINY_PRZYJAZDoW(godzina,minuta) values(13,10)

insert into PRZYJAZD_NA_PRZYSTANEK(nr_linii,nr_przystanku,nr_godziny) values(1,1,1)
insert into PRZYJAZD_NA_PRZYSTANEK(nr_linii,nr_przystanku,nr_godziny) values(1,2,2)
insert into PRZYJAZD_NA_PRZYSTANEK(nr_linii,nr_przystanku,nr_godziny) values(1,1,3)




-- Wypisanie rozkladu jazdy przystanku o nr 1 
select nr_linii,godzina,minuta
from PRZYJAZD_NA_PRZYSTANEK
inner join GODZINY_PRZYJAZDÓW
on PRZYJAZD_NA_PRZYSTANEK.nr_godziny = GODZINY_PRZYJAZDoW.nr_godziny
where PRZYJAZD_NA_PRZYSTANEK.nr_przystanku=1;


-- Wypisanie wszystkich pracowników pracujacych jako ksiegowa
select * from PRACOWNICY p
    inner join STANOWISKA s
    on p.nr_stanowiska=s.nr_stanowiska
    where s.NAZWA='Ksiegowa';

-- Wypisanie pracowników mieszkajacych w Warszawie
select * from PRACOWNICY p
inner join ADRESY a
on p.nr_adresu=a.nr_adresu
where a.MIASTO = 'Warszawa';

-- Sprawdzenie historii wyplat przez pracownika o imieniu Uruszula Dreszer

select w.* from WYNAGRODZENIA w
    inner join PRACOWNICY p
    on w.nr_pracownika=p.nr_pracownika
    where p.imie='Urszula' and p.nazwisko='Dreszer';
    

-- Wyszukiwanie zwolnionych pracowników  
select * from PRACOWNICY
    where data_zwolnienia IS NOT NULL;



