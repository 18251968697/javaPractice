create table administer(	
	id varchar(10),			
	pw varchar(18),			
	PRIMARY KEY(id),
)

create table myuser(		
	id varchar(10),			
	pw varchar(18),			
	PRIMARY KEY(id),
)

create table info(			
	id varchar(10),	
	sex char(10),		
	age int,				
	mail varchar(30),		
)

create table vip(			
	id varchar(10),			
	balance real,			
	vipLevel int,			
	score int,			
	discount real,		
	PRIMARY KEY(id),
)

create table task(			
	id varchar(10),			
	taskName varchar(20),	
	ifThisType int,			
	ifThis varchar(400),	
	thenThatType int,		
	thenThat varchar(300),	
	state varchar(10),		
	description varchar(300),
	lastState varchar(10),	
	PRIMARY key(id, taskName),
)

create table transelation(	
	id varchar(10),			
	orderId varchar(20),	
	type char(1),			
	amount real,			
	balance real,			
	description varchar(50),
	date varchar(20),
)
create table post(			
	postId int,				
	title varchar(50),		
	constent varchar(500),
	date varchar(20),
	PRIMARY KEY(postId),
)
create table mail(			
	mailId int,				
	id varchar(10),			
	sendId varchar(10),		
	title varchar(50),		
	constent varchar(500),	
	date varchar(20),		
	PRIMARY KEY(mailId),
)

create table counter(	
	type char(10),		
	num int,			
	PRIMARY KEY(type),
)

insert into administer values('yz', '112804');
insert into counter values('post', 0);
insert into counter values('mail', 0);
insert into counter values('trans', 0);


select * from administer;

select * from myuser;

select * from info;

select * from vip;

select * from task;

select * from transelation;

select * from post;

select * from mail;

select * from counter;

drop table administer;
drop table myuser;
drop table info;
drop table task;
drop table transelation;
drop table vip;
drop table mail;
drop table post;
drop table counter;

