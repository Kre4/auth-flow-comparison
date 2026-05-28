insert into users (username, password, enabled)
values ('alice', '{noop}password', true);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into urls (method, url)
values ('GET', '/echo'),
       ('POST', '/echo');

insert into role_urls (role_id, url_id)
values (1, 1),
       (2, 1),
       (2, 2);

insert into user_roles (username, role_id)
values ('alice', 1),
       ('alice', 2);
