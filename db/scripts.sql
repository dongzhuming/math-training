drop table if exists exercise;

drop table if exists question;

create table exercise
(
  id            bigint       not null primary key,
  initial_count int          not null default 0,
  total_count   int          not null default 0,
  wrong_count   int          not null default 0,
  start_time    datetime     null,
  finish_time   datetime     null,
  name          varchar(32)  null,
  INDEX (start_time DESC, finish_time DESC)
) engine = INNODB;


create table question
(
  id           bigint       not null primary key,
  code         varchar(64) not null,
  title        varchar(128) not null,
  answer       varchar(64) not null,
  items        varchar(128) not null,
  created_date datetime     not null,
	constraint unique (code),
	INDEX (created_date DESC)
) engine = INNODB;


create table wrongness
(
  id           bigint       not null primary key,
  code         varchar(64)  not null,
  name         varchar(32)  null,
  created_date datetime     not null,
	INDEX (code DESC)
) engine = INNODB;

create table configuration
(
  id           bigint       not null primary key,
  properties   json         not null,
  name         varchar(32)  null,
  created_date  datetime    not null
) engine = INNODB;