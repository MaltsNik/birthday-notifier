--changeset nikita:1
--comment: создание колонки day в таблице users
alter table users
    add column date date;

--changeset nikita:2
--comment: перенос данных из колонки day из таблицы days в столбец day таблицы users

update users
set date=(select day from days where days.id = users.day_id limit 1);

--changeset nikita:3
--comment: удаление таблицы users и таблицы days
drop table users;
drop table days;

--changeset nikita:3
--comment: создание новой таблицы users
CREATE TABLE USERS
(
    id bigserial PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    date date
);



