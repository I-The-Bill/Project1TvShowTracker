create database TvTracker;
use TvTracker;

#creating show table
create table TV_show
(
	show_id int primary key auto_increment,
    show_name varchar(50) not null unique,
    episode_count int
);
select * from TV_show;

create table Category
(
	category_id int primary key,
    category_name varchar(50) not null unique
);
create table category_show
(
	show_id int primary key,
    foreign key (show_id) references TV_show(show_id),
    category_id int,
    foreign key (category_id) references Category(category_id)
    );

#delete from TV_show;

create table TV_user
(
	user_id int primary key auto_increment,
    user_name varchar(50) not null,
    user_password varchar(50) not null 
);

create table TV_status
(
	status_id int primary key auto_increment,
    status_name varchar(30) not null unique
);

create table Watch_instance
(
	instance_id int primary key auto_increment,
    user_id int,
    foreign key (user_id) REFERENCES TV_user(user_id),
    show_id int,
    foreign key (show_id) REFERENCES TV_show(show_id),
    status_id int UNIQUE,
    foreign key (status_id) REFERENCES TV_status(status_id)
);
select * from Watch_instance;