drop table if exists anime CASCADE;

create table anime
(
	`id` integer AUTO_INCREMENT PRIMARY KEY,
	`title` varchar(255),
	`genre` varchar(255),
	`episodes` integer,
	`season` integer
);