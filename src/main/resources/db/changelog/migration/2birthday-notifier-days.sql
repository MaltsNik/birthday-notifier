----changeset nikita:1
--comment: создание таблицы days
create table DAYS
(
    id      bigserial primary key,
    day     date
);
--changeset nikita:2
--comment: заполнение таблицы days
insert into DAYS(day)
values('2000-12-01');
insert into DAYS(day)
values('2005-08-05');
insert into DAYS(day)
values('2010-10-10');
insert into DAYS(day)
values('2015-12-01');

