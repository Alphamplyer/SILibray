create table comments
(
	id serial not null,
	author_id int not null
		constraint comments_users_id_fk
			references users,
	book_reference varchar(255) not null
		constraint comments_books_reference_fk
			references books,
	content text not null,
	notation int not null,
	creation_time timestamp default current_timestamp not null,
);

create unique index comments_id_uindex
	on comments (id);

alter table comments
	add constraint comments_pk
		primary key (id);