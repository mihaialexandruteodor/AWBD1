delete from product_category;
delete from product;
delete from category;

insert into category(id, name) values(1, 'paintings');
insert into category(id, name) values(2, 'sculptures');

insert into product (id, name, code, reserve_price) values (1, 'The Card Players', 'PCEZ', 250);

insert into product_category values(1,1);
