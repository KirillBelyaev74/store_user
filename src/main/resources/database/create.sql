create table role(
    id serial primary key,
    role varchar(10) unique not null
);

create table user(
    id serial primary key,
    role_id bigint,
    login varchar(20) unique not null,
    password varchar(100) not null,
    foreign key (role_id) references role(id)
);