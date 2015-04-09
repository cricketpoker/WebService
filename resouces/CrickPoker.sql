create database if not exists cric_poker;

drop table if exists users;
drop table if exists questions;
drop table if exists user_answers;
drop table if exists user_badges;
drop table if exists player_types;
drop table if exists players;
drop table if exists teams;
drop table if exists matches;
drop table if exists user_match_score_history;
drop table if exists groups;
drop table if exists user_groups;
drop table if exists challenges;
drop table if exists user_group_challenges;

create table  if not exists users
(
user_id int not null auto_increment,
last_logged_in_time timestamp,
display_name varchar(40) not null,
tokens_left int not null,
fav_team_id int,
PRIMARY KEY(user_id)
);

create table if not exists questions
(
question_id int not null auto_increment,
question_txt longtext not null,
question_type varchar(45) not null,
answer longtext not null,
answer_format varchar(45),
match_id int not null,
bonus_factor int,
PRIMARY KEY(question_id)
);

create table if not exists user_answers
(
user_answer_id int not null auto_increment,
user_id int not null,
question_id int not null,
answer longtext not null,
answer_time timestamp not null,
is_current boolean not null,
is_locked boolean not null,
bet_points int not null,
won_points int not null,
PRIMARY KEY(user_answer_id)
);

create table if not exists user_badges
(
user_badge_id int not null auto_increment,
user_id int not null,
badge_resource_link longtext,
PRIMARY KEY(user_badge_id)
);

create table if not exists player_types
(
id int not null auto_increment,
type varchar(100)
);

create table if not exists players
(
player_id int not null auto_increment,
player_name varchar(250),
current_team_id int not null,
PRIMARY KEY (player_id)
);

create table if not exists teams
(
team_id int not null auto_increment,
team_name varchar(250),
home_city varchar(45),
team_abbr varchar(10),
PRIMARY KEY(team_id)
);

create table if not exists matches
(
match_id int not null auto_increment,
team_id1 int not null,
team_id2 int not null,
ground varchar(250) not null,
venue varchar(250) not null,
winning_team_id int,
match_date datetime not null,
PRIMARY KEY(match_id)
);

create table if not exists user_match_score_history
(
history_id int not null auto_increment,
user_id int not null,
match_id int not null,
points_before int not null,
points_won int not null,
normalization_factor float not null,
PRIMARY KEY(history_id)
);

create table if not exists groups
(
group_id int not null auto_increment,
admin_user_id int,
group_name varchar(100),
PRIMARY KEY(group_id)
);

create table if not exists user_groups
(
user_id int not null,
group_id int not null,
is_pending boolean,
PRIMARY KEY(user_id, group_id)
);

create table if not exists challenges
(
challenge_id int not null auto_increment,
challenge_name varchar(250),
PRIMARY KEY(challenge_id)
);

create table if not exists user_group_challenges
(
enrollment_id int not null auto_increment,
user_id int,
group_id int,
challenge_id int not null,
PRIMARY KEY(enrollment_id)
);

select * from users;












