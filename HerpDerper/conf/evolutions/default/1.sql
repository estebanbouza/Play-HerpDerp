# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table derp (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  herp_id                   bigint,
  coolness                  integer,
  constraint pk_derp primary key (id))
;

create table herp (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_herp primary key (id))
;

alter table derp add constraint fk_derp_herp_1 foreign key (herp_id) references herp (id) on delete restrict on update restrict;
create index ix_derp_herp_1 on derp (herp_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table derp;

drop table herp;

SET FOREIGN_KEY_CHECKS=1;

