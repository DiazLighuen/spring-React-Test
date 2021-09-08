
create table cart (dtype varchar(31) not null, id bigint not null auto_increment, date datetime, paid bit, total double precision, primary key (id)) engine=InnoDB
create table cart_products (cart_id bigint not null, products_id bigint not null, primary key (cart_id, products_id)) engine=InnoDB
create table product (id bigint not null auto_increment, description TEXT, name varchar(255), price double precision, primary key (id)) engine=InnoDB
create table user (id bigint not null auto_increment, password varchar(255), user_vip bit, username varchar(255), cart_id bigint, primary key (id)) engine=InnoDB
create table user_carts (user_id bigint not null, carts_id bigint not null, primary key (user_id, carts_id)) engine=InnoDB
alter table user_carts add constraint UK_d5g1hf0ohhmlinefm7ncv8sxi unique (carts_id)
alter table cart_products add constraint FKhyhnx21758m3wmbi4ps96m0yw foreign key (products_id) references product (id)
alter table cart_products add constraint FKnlhjc091rdu9k5c8u9xwp280w foreign key (cart_id) references cart (id)
alter table user add constraint FKtqa69bib34k2c0jhe7afqsao6 foreign key (cart_id) references cart (id)
alter table user_carts add constraint FK10yte9f4roxdnooc752ii4qdb foreign key (carts_id) references cart (id)
alter table user_carts add constraint FKdjf0emykmc53xuh33tbktkj14 foreign key (user_id) references user (id)
