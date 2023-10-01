create table posts (
	id bigint(20) not null AUTO_INCREMENT,
	title VARCHAR(200),
	user_id bigint(20),
	primary key(`id`)
);