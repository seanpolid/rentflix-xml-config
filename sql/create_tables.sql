create schema cps278_final;

use cps278_final;

create table rating (
	id tinyint unsigned not null auto_increment,
    name varchar(20) not null,
    primary key (id)
);

create table genre (
	id tinyint unsigned not null auto_increment,
    name varchar(20) not null,
    primary key (id)
);

create table movie (
	id  mediumint unsigned not null auto_increment,
    name varchar(20) not null,
    description varchar(500) not null,
    year_made smallint unsigned not null,
    release_date date not null,
    total_copies smallint unsigned not null,
    cost Decimal(5, 2) not null,
    length smallint unsigned not null,
    rating_id tinyint unsigned not null,
    genre_id tinyint unsigned not null,
    primary key (id),
    foreign key (rating_id) references rating (id),
    foreign key (genre_id) references genre (id)
);

create table customer (
	id mediumint unsigned not null auto_increment,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(40) not null,
    profile_img varchar(100),
    primary key (id)
);

create table invoice (
	id int unsigned not null auto_increment,
    customer_id mediumint unsigned not null,
    date datetime not null,
    total Decimal(7, 2) not null,
    primary key (id),
    foreign key (customer_id) references customer (id)
);

create table invoice_movie (
	id int unsigned not null auto_increment,
    invoice_id int unsigned not null,
    movie_id mediumint unsigned not null,
    return_date datetime,
    primary key (id),
    foreign key (invoice_id) references invoice (id),
    foreign key (movie_id) references movie (id)
);
    