-- Выполняется при spring.sql.init.mode=always (только первая инициализация пустой БД)
insert into users (username, password, enabled)
values ('alice', '{noop}password', true);

insert into authorities (username, authority) values ('alice', 'ROLE_USER');
insert into authorities (username, authority) values ('alice', 'ROLE_ADMIN');
