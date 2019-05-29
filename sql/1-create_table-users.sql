create table users
(
	id serial not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	nickname varchar(50) not null,
	password varchar(255) not null,
	email varchar(254) not null,
	permission_level int default 0 not null,
	birth_date date not null,
	salt varchar(255) not null
);

create unique index users_email_uindex
	on users (email);

create unique index users_id_uindex
	on users (id);

create unique index users_nickname_uindex
	on users (nickname);

alter table users
	add constraint users_pk
		primary key (id);