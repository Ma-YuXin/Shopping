drop database IF EXISTS shop;
create database shop;

use shop;
create table user(
id varchar(20) primary key ,
 username varchar(20),
password varchar(20),
nickname varchar(20),
status varchar(20),
phonenumber varchar(20),
address varchar(100)
);

create table orders(
id INT UNSIGNED AUTO_INCREMENT,
 goodsid varchar(20) ,
 buy_date varchar(100),
 name varchar(100),
 confirm_date varchar(100),
 status int(5),
 buy_user_id varchar(20),
 sell_user_id varchar(20),
 PRIMARY KEY ( id )
);
create table goods(
id varchar(20) primary key ,
name varchar(100),
sellerid  varchar(20) ,
buyerid  varchar(20) ,
price  double, 
introduce varchar(100),
img varchar(200),
stock  int,
c_type varchar(11),
commend varchar(1000)
);
create table list( 
id INT UNSIGNED AUTO_INCREMENT,
 userid varchar(20) ,
 newslist varchar(1000),
 PRIMARY KEY ( id )
);
create table message(
mesType varchar(100),
sender varchar(100),
getter varchar(100),
con varchar(1000),
sendTime varchar(100)
);
create table clientMessage(
mesType varchar(100),
sender varchar(100),
getter varchar(100),
con varchar(1000),
sendTime varchar(100)
);

