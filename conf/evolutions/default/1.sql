# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table department (
  id                        bigint not null,
  deptno                    integer,
  dname                     varchar(255),
  loc                       varchar(255),
  constraint pk_department primary key (id))
;

create table employee (
  id                        bigint not null,
  empno                     integer,
  name                      varchar(255),
  department_id             bigint,
  constraint pk_employee primary key (id))
;

create sequence device_gen;

create sequence employee_seq;

alter table employee add constraint fk_employee_department_1 foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_employee_department_1 on employee (department_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists department;

drop table if exists employee;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists device_gen;

drop sequence if exists employee_seq;

