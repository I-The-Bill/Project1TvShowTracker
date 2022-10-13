create database TvTracker;
use TvTracker;

#creating show table
create table TV_show
(
	show_id int primary key auto_increment,
    show_name varchar(30) not null unique,
    episode_count int
);

create table Category
(
	category_id int primary key,
    category_name varchar(50) not null unique
);

create table TV_user
(
	user_id int primary key auto_increment,
    user_name varchar(50) not null unique,
    user_password varchar(50) not null unique
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
    status_id int,
    foreign key (status_id) REFERENCES TV_status(status_id)
);