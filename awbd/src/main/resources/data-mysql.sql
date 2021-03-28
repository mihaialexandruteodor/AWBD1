delete from movie_category;
delete from info;
delete from movie;
delete from director;
delete from studio;

insert into movie_category(id, name) values(1, 'action');
insert into movie_category(id, name) values(2, 'romance');
insert into movie_category(id, name) values(3, 'drama');

insert into movie (id, name, release_year, price) values (1, 'The Raid', 2011, 59);
insert into info(id, product_id, description) values (1, 1, 'A S.W.A.T. team becomes trapped in a tenement run by a ruthless mobster and his army of killers and thugs.');
insert into movie_category (product_id, category_id) values(1,1);
insert into director(id, name) values (1, 'Gareth Evans');
insert into studio(id, name) values (1, 'XYZ Films');

