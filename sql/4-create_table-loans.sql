create table loans
(
	id serial not null,
	user_id int not null
		constraint loans_users_id_fk
			references users,
	book_id int not null
		constraint loans_books_id_fk
			references books,
	begin_date date default current_date not null,
	end_date date not null,
	is_extended boolean default false not null,
	is_archived boolean default false not null
);

create unique index loans_id_uindex
	on loans (id);

alter table loans
	add constraint loans_pk
		primary key (id);