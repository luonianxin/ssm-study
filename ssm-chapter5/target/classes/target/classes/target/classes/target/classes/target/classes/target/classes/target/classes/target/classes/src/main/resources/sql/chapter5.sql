create table t_employee(
         id int(12) not null auto_increment,
         real_name varchar(60) not null ,
         sex int(2) not null comment '1 - 男 0 - 女',
         birthday date not null ,
         mobile varchar(20) not null,
         email varchar(20) not null,
         POSITION varchar(20) not null,
         note varchar(256),
         primary key(id)

     );
create table t_employee_task(
         id int(12) not null auto_increment,
         emp_id int(12) not null ,
         task_id  int(12) not null ,
         task_name  varchar(60) not null ,
         note varchar(256),
         primary key(id)

     );
 create table t_female_health_form(
         id int(12) not null auto_increment,
         emp_id int(12) not null ,
         heart  varchar(64) not null ,
         liver  varchar(64) not null ,
         spleen varchar(64) not null,
         lung varchar(64) not null,
         kidney varchar(64) not null,
         uterus varchar (64) not null,
         note varchar(256),
         primary key(id)

     );
create table t_male_health_form(
         id int(12) not null auto_increment,
         emp_id int(12) not null ,
         heart  varchar(64) not null ,
         liver  varchar(64) not null ,
         spleen varchar(64) not null,
         lung varchar(64) not null,
         kidney varchar(64) not null,
         prostate varchar (64) not null,
         note varchar(256),
         primary key(id)

     );
 create table t_task(
         id int(12) not null auto_increment,
         title varchar(60) not null ,
         context  varchar(256) not null ,
         note varchar(256),
         primary key(id)

     );
create table t_work_card(
         id int(12) not null auto_increment,
         emp_id int(12) not null,
         real_name varchar(60) not null,
         department varchar(60) not null,
         mobile varchar(20) not null,
         POSITION varchar(20) not null,
         note varchar(256),
         primary key(id)

     );
alter table t_employee_task add constraint FK_Reference_4 FOREIGN KEY  (emp_id)
     REFERENCES  t_employee(id) on delete restrict on update restrict
alter table t_employee_task add constraint FK_Reference_8 FOREIGN KEY  (task_id);
     REFERENCES  t_task(id) on delete restrict on update restrict;

alter table t_female_health_form add constraint FK_Reference_5
         FOREIGN KEY  (emp_id)
         REFERENCES  t_employee(id) on delete restrict on update restrict;

 alter table t_male_health_form add constraint FK_Reference_6 FOREIGN KEY
         (emp_id)
         REFERENCES  t_employee(id) on delete restrict on update restrict;
 alter table t_work_card add constraint FK_Reference_7 FOREIGN KEY
         (emp_id)
         REFERENCES  t_employee(id) on delete restrict on update restrict

