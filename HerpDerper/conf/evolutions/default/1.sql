# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table derp (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  coolness                  integer,
  constraint pk_derp primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table derp;

SET FOREIGN_KEY_CHECKS=1;

