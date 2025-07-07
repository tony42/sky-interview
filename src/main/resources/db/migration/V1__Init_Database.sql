-- default database schema for spring security jdbc authentication

create table users(
                      username varchar(50) not null primary key COLLATE UTF8_GENERAL_CI,
                      password varchar(100) not null COLLATE UTF8_GENERAL_CI,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null COLLATE UTF8_GENERAL_CI,
                             authority varchar(50) not null COLLATE UTF8_GENERAL_CI,
                             constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

-- create users and authorities

insert into users (username, password, enabled) values
    ('frank@sky.com', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true),
    ('bill@sky.com', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true),
    ('tony@sky.com', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true),
    ('steve@sky.com', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true),
    ('joe@sky.com', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true),
    ('john@sky.com', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true),
    ('james@sky.com', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true);

insert into authorities (username, authority) values
    ('frank@sky.com', 'ROLE_USER'),
    ('bill@sky.com', 'ROLE_USER'),
    ('tony@sky.com', 'ROLE_USER'),
    ('steve@sky.com', 'ROLE_USER'),
    ('joe@sky.com', 'ROLE_USER'),
    ('john@sky.com', 'ROLE_USER'),
    ('james@sky.com', 'ROLE_USER');

-- project tables

create table movie(
                        id bigint auto_increment not null primary key,
                        name varchar(100) not null COLLATE UTF8_GENERAL_CI,
                        average_rating float
    );

insert into movie (name) values
    ('The Shawshank Redemption'),
    ('The Godfather'),
    ('The Dark Knight'),
    ('Pulp Fiction'),
    ('Forrest Gump'),
    ('Inception'),
    ('Fight Club'),
    ('The Matrix'),
    ('Goodfellas'),
    ('The Lord of the Rings: The Return of the King');

create table user (
    email varchar(100) not null primary key COLLATE UTF8_GENERAL_CI,
    name varchar(100) COLLATE UTF8_GENERAL_CI
);

insert into user (email, name) values
     ('frank@sky.com', 'Frank Zappa'),
     ('bill@sky.com', 'Bill Gates'),
     ('tony@sky.com', 'Tony Stark'),
     ('steve@sky.com', 'Steve Jobs'),
     ('joe@sky.com', 'Joe Biden'),
     ('john@sky.com', 'John Doe'),
     ('james@sky.com', 'James Bond');

create table rating
(
    id         bigint auto_increment not null primary key,
    movie_id   bigint       not null,
    user_email varchar(100) not null COLLATE UTF8_GENERAL_CI,
    rating     int          not null,
    comment    varchar(500) COLLATE UTF8_GENERAL_CI,
    constraint fk_review_movie foreign key (movie_id) references movie (id),
    constraint fk_review_user foreign key (user_email) references user (email)
);