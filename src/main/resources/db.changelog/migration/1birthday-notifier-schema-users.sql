CREATE TABLE USERS
(
    id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fullname VARCHAR(255) NOT NULL,
    age  INT(150) NOT NULL,
    birthday VARCHAR(255) NOT NULL,

);