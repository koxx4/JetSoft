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

--vehicle model
insert into data.vehicle_model (id, manufacturer_id, manufacture_code, max_passenger_count, max_distance, max_pilot_count, lift_capacity, model_name) values (1, 5, '55154-9607', 95, 3076, 2, 8965, 'Ram 3500');
insert into data.vehicle_model (id, manufacturer_id, manufacture_code, max_passenger_count, max_distance, max_pilot_count, lift_capacity, model_name) values (2, 5, '0944-3944', 238, 1202, 2, 6520, 'XG350');
insert into data.vehicle_model (id, manufacturer_id, manufacture_code, max_passenger_count, max_distance, max_pilot_count, lift_capacity, model_name) values (3, 4, '13734-033', 240, 1041, 3, 8781, 'Impala');
insert into data.vehicle_model (id, manufacturer_id, manufacture_code, max_passenger_count, max_distance, max_pilot_count, lift_capacity, model_name) values (4, 1, '0054-0119', 136, 3063, 2, 3810, 'Avenger');


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

insert into data.vehicle (id, vehicle_type_id, vehicle_model_id, next_maintenance_date, friendly_name, rent_date) values (1, 1, 1, '2023-05-30', 'rutrum ac lobortis', '2021-09-05');
insert into data.vehicle (id, vehicle_type_id, vehicle_model_id, next_maintenance_date, friendly_name, rent_date) values (2, 1, 3, '2023-07-01', 'varius', '2021-10-30');
insert into data.vehicle (id, vehicle_type_id, vehicle_model_id, next_maintenance_date, friendly_name, rent_date) values (3, 1, 4, '2023-04-29', 'morbi vel', '2022-09-13');
insert into data.vehicle (id, vehicle_type_id, vehicle_model_id, next_maintenance_date, friendly_name, rent_date) values (4, 1, 1, '2023-06-11', 'luctus rutrum', '2021-09-05');

INSERT INTO data.flight
(
 route_id,
 vehicle_id,
 generated_from_schedule_id,
 active,
 flight_number,
 available_passengers_seats,
 min_pilot_count,
 confidential,
 last_modification_date,
 archival,
 planned_departure,
 planned_arrival
 )
VALUES(2, 1, null, false, 'POLWARWŁORZY', 100, 2, false, '2022-10-14 08:27:53+02', false, '2023-10-14 12:27:53+02', '2023-10-14 15:27:53+02');