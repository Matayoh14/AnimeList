create database if not exists animelist;
use animelist;
create table if not exists anime
(
	`id` integer AUTO_INCREMENT PRIMARY KEY,
	`title` varchar(255),
	`genre` varchar(255),
	`episodes` integer,
	`season` integer
);

create table if not exists episode
(
	`id` integer not null auto_increment primary key,
	`anime_title` varchar(255), 
	`desc` varchar(255), 
	`number` integer, 
	`title` varchar(255), 
	`anime_id` integer
);