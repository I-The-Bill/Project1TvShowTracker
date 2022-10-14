use TvTracker;

-- INSERT INTO TABLE TV_show
INSERT INTO 
	TV_show(show_id, show_name, episode_count)
VALUES
	(null, 'Bleach', '366'),
    (null, 'Soul Eater', '51'),
    (null, 'Star Wars Andor', '6'),
    (null, 'Star Wars The Clone Wars', '133'),
    (null, '60 Days In', '87'),
    (null, 'Dahmer', '10'),
    (null, 'Narcos', '30'),
    (null, 'Breaking Bad', '62'),
    (null, 'House of the Dragon ', '90'),
    (null, 'The Lord of the Rings: The Rings of Power', '50'),
    (null, 'She-Hulk: Attorney at Law', '78'),
    (null, "The Handmaid's Tale", '50'),
    (null, "Stranger Things", '34'),
    (null, "House of the Dragon", '7'),
    (null, "Game of Thrones", '73'),
    (null, "Rick and Morty", '51'),
    (null, "Friday Night Light", '76'),
    (null, "The Punisher", '26'),
	(null, "One Piec", '400'),
	(null, "Grey’s anatomy ", '1034');

-- SELECT FROM TV_show
SELECT * FROM TV_show;


-- INSERT INTO TABLE Category
INSERT INTO 
	Category(category_id, category_name)
VALUES
	(1,'Anime'),
	(2,'Sci-Fi'),
	(3,'Reality'),
	(4,'Thriller'),
	(5,'Crime'),
	(6,'Drama'),
	(7,'Action'),
	(8,'Tragedy'),
	(9,'Mystery'),
	(10,'sitcom'),
	(11,'Action fiction');

    -- SELECT FROM Category
SELECT * FROM Category
group by category_id;


-- INSERT INTO TABLE category_show
INSERT INTO 
	category_show(show_id, category_id)
VALUES
    (1,1), (2,1), (3,2),(4,2), (5,3),
    (6,4), (7,5), (8,6), (9,6), (10,6),
    (11,7), (12,8), (13,9), (14,6),
    (15,6), (16,10), (17,6), (18,11),
    (19,6), (20,1);

        -- SELECT FROM category_show
SELECT * FROM category_show;


-- INSERT INTO TABLE TV_user
	INSERT INTO 
	TV_user(user_id, user_name, user_password)
VALUES
    (NULL, 'john', '12345'),
    (NULL, 'allen', '1234'),
    (NULL, 'amie', '123'),
    (NULL, 'a1li', '12'),
    (NULL, 'DJ', '112'),
    (NULL, 'Doe', '1321'),
    (NULL, 'Jane', '122'),
    (NULL, 'JJ', '1323'),
    (NULL, 'Bill', '1325'),
    (NULL, 'Mansour', '132');

            -- SELECT FROM TV_user
SELECT * FROM TV_user;
-- drop table TV_user;



INSERT INTO 
	Watch_instance(instance_id, user_id, show_id, status_id)
VALUES
 (NULL, 1, 1, 1),
 (NULL, 2, 16, 2),
 (NULL, 3, 8, 1),
 (NULL, 4, 20, 3),
 (NULL, 5, 2, 2),
 (NULL, 6, 3, 3),
 (NULL, 7, 1, 2),
 (NULL, 8, 4, 1),
 (NULL, 9, 6, 2),
 (NULL, 10, 7, 1),
 (NULL, 1, 18, 1),
 (NULL, 2, 9, 2),
 (NULL, 3, 11, 1),
 (NULL, 4, 12, 3),
 (NULL, 5, 13, 3),
 (NULL, 6, 15, 1),
 (NULL, 7, 10, 2),
 (NULL, 8, 17, 1),
 (NULL, 9, 14, 3),
 (NULL, 10, 19, 3);

SELECT * FROM Watch_instance;
-- select * from tv_show;
SELECT * FROM TV_show inner join watch_instance on tv_show.show_id = watch_instance.show_id;
SELECT * FROM TV_status 
		INNER JOIN Watch_instance ON TV_status.status_id = Watch_instance.status_id 
		INNER JOIN TV_show ON Watch_instance.show_id = TV_show.show_id 
		inner join TV_user on TV_user.user_id = Watch_instance.user_id  
		WHERE show_name= 'Bleach' && Watch_instance.user_id = 1;
select * from tv_user;
select * from tv_show;
select * from tv_status;
SELECT * FROM Watch_instance;

select user_id from tv_user where user_name = 'Bill';

SELECT * FROM Watch_instance;
SELECT * FROM TV_show INNER JOIN Watch_instance ON TV_show.show_id = Watch_instance.show_id;
SELECT * FROM TV_status INNER JOIN Watch_instance ON TV_status.status_id = Watch_instance.status_id INNER JOIN TV_show ON Watch_instance.show_id = TV_show.show_id;
#getStatus
SELECT status_name FROM TV_status INNER JOIN Watch_instance ON TV_status.status_id = Watch_instance.status_id INNER JOIN TV_show ON Watch_instance.show_id = TV_show.show_id where show_name= 'Dahmer';

update watch_instance set watch_instance.status_id = 3
	WHERE watch_instance.show_id=  1 && watch_instance.user_id = 1;
	-- INNER JOIN Watch_instance ON TV_status.status_id = Watch_instance.status_id 
-- 	INNER JOIN TV_show ON Watch_instance.show_id = TV_show.show_id 
-- 	inner join TV_user on TV_user.user_id = Watch_instance.user_id  
--     
-- 	WHERE show_name= 'Bleach' && Watch_instance.user_id = 1;

