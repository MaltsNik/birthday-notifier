--changeset nikita:1
--comment: создание таблицы users
CREATE TABLE USERS
(
    id bigserial PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    day_id BIGINT references days (id)
);
--changeset nikita:2
--comment: заполнение таблицы users
insert into USERS(fullName)
values('Иванов Иван');
insert into USERS(fullName)
values('Сидоров Семен');
insert into USERS(fullName)
values('Петров Петр');
insert into USERS(fullName)
values('Мухоморова Мария');
insert into USERS(fullName)
values('Шапкин Павел');



