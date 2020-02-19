create table reporter(
    userno int auto_increment,
    userid varchar(100),
    name varchar(20),
    activity double,
    primary key (userno)
) default character set utf8 collate utf8_general_ci;

create table news(
    newsno int auto_increment,
    userno int,
    date date,
    title varchar(20),
    content text,
    primary key (newsno),
    foreign key (userno) references reporter (userno)
) default character set utf8 collate utf8_general_ci;