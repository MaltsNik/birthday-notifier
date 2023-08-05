CREATE TABLE USERS
(
    id bigserial PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    day_id bigint references days (id)
);