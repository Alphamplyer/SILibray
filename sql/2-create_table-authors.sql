create table authors
(
	id serial not null,
	name varchar(255) not null,
	biography text not null,
	birth_date date not null,
	death_date date
);

create unique index authors_id_uindex
	on authors (id);

alter table authors
	add constraint authors_pk
		primary key (id);