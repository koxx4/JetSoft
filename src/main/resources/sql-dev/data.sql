--nationality

insert into data.nationality (id, name) values (1, 'Polska');
insert into data.nationality (id, name) values (2, 'Niemcy');
insert into data.nationality (id, name) values (3, 'Hiszpania');
insert into data.nationality (id, name) values (4, 'Włochy');
insert into data.nationality (id, name) values (5, 'Portugalia');
insert into data.nationality (id, name) values (6, 'Francja');
insert into data.nationality (id, name) values (7, 'Grecja');
insert into data.nationality (id, name) values (8, 'Austria');
insert into data.nationality (id, name) values (9, 'Szwecja');
insert into data.nationality (id, name) values (10, 'Chorwacja');

--city
insert into data.city (id, nationality_id , name) values (1, 1, 'Wrocław');
insert into data.city (id, nationality_id , name) values (2, 1, 'Warszawa');
insert into data.city (id, nationality_id , name) values (3, 2, 'Frankfurt');
insert into data.city (id, nationality_id , name) values (4, 2, 'Monachium');
insert into data.city (id, nationality_id , name) values (5, 2, 'Berlin');
insert into data.city (id, nationality_id , name) values (6, 3, 'Madryt');
insert into data.city (id, nationality_id , name) values (7, 3, 'Barcelona');
insert into data.city (id, nationality_id , name) values (8, 4, 'Neapol');
insert into data.city (id, nationality_id , name) values (9, 4, 'Rzym');
insert into data.city (id, nationality_id , name) values (10, 4,'Wenecja');
insert into data.city (id, nationality_id , name) values (11, 5, 'Lizbona');
insert into data.city (id, nationality_id , name) values (12, 6, 'Paryż');
insert into data.city (id, nationality_id , name) values (13, 6, 'Lyon');
insert into data.city (id, nationality_id , name) values (14, 7, 'Ateny');
insert into data.city (id, nationality_id , name) values (15, 8, 'Wiedeń');
insert into data.city (id, nationality_id , name) values (16, 9, 'Sztokholm');
insert into data.city (id, nationality_id , name) values (17, 10, 'Zagrzeb');

-- vehicle_type

insert into data.vehicle_type (id, type_name) values (1, 'Samolot');
insert into data.vehicle_type (id, type_name) values (2, 'Śmigłowiec');

-- pilot_status

insert into ppd.pilot_status (id, status) values (1, 'Dostępny');
insert into ppd.pilot_status (id, status) values (2, 'W trasie');
insert into ppd.pilot_status (id, status) values (3, 'Niedostępny');

-- vehicle_manufacturer

insert into data.vehicle_manufacturer (id, company_name) values (1, 'Mann Group');
insert into data.vehicle_manufacturer (id, company_name) values (2, 'Ward, Bailey and Cremin');
insert into data.vehicle_manufacturer (id, company_name) values (3, 'Stiedemann');
insert into data.vehicle_manufacturer (id, company_name) values (4, 'Dach Inc');
insert into data.vehicle_manufacturer (id, company_name) values (5, 'Hudson LLC');

-- route

INSERT INTO data.route
(source_city_id, destination_city_id, distance)
VALUES(1, 2, 300);

INSERT INTO data.route
(source_city_id, destination_city_id, distance)
VALUES(2, 9, 800);

INSERT INTO data.route
(source_city_id, destination_city_id, distance)
VALUES(1, 5, 400);