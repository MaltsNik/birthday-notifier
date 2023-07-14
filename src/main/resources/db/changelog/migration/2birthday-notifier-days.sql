create table DAYS
(
    id     bigserial primary key,
    day    date,
    user_id bigint references users (id)
);