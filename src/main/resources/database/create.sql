create table users(
    username varchar(50) primary key,
    password varchar(500) not null,
    enabled boolean not null)

create table authorities(
    username varchar(50) not null,
    authority varchar(20) not null,
    foreign key (username) references users(username),
    unique(username, authority))