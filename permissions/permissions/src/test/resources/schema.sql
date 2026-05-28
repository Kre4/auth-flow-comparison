create table if not exists users
(
    username varchar(50)  not null primary key,
    password varchar(100) not null,
    enabled  boolean      not null
);

create table if not exists urls
(
    id     serial primary key,
    method varchar(8)   not null,
    url    varchar(128) not null,
    unique (method, url)
);

create table if not exists roles
(
    id   serial primary key,
    name varchar(64)
);

create table if not exists role_urls
(
    role_id int references roles (id),
    url_id  int references urls (id)
);

create table if not exists user_roles
(
    username varchar(50) not null references users (username),
    role_id  int         not null references roles (id),
    primary key (username, role_id)
);
