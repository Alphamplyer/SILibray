create table books
(
	id serial not null,
	author_id int not null
		constraint books_authors_id_fk
			references authors,
	book_reference varchar(255) not null,
	name varchar(255) not null,
	summary text not null,
	release_date date not null,
	available boolean default false not null
);

create unique index books_id_uindex
	on books (id);

alter table books
	add constraint books_pk
		primary key (id);