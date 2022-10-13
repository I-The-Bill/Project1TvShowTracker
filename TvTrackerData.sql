use TvTracker;

-- INSERT INTO TABLE TV_show
INSERT INTO 
	TV_show(show_id, show_name, episode_count)
VALUES
	(null, 'Bleach', '366'),
    (null, 'Soul Eater', '8'),
    (null, 'Star Wars Andor', '8'),
    (null, 'Star Wars The Clone Wars', '8'),
    (null, '60 Days In', '5'),
    (null, 'Dahmer', '8'),
    (null, 'Narcos', '30'),
    (null, 'Breaking Bad', '62'),
    (null, 'House of the Dragon ', '17'),
    (null, 'The Lord of the Rings: The Rings of Power', '3'),
    (null, 'She-Hulk: Attorney at Law', '78'),
    (null, "The Handmaid's Tale", '0'),
    (null, "Stranger Things", '0'),
    (null, "House of the Dragon", '0'),
    (null, "Game of Thrones", '73'),
    (null, "Rick and Morty", '3'),
    (null, "Friday Night Light", '76'),
    (null, "The Punisher", '26'),
	(null, "One Piec", '10'),
	(null, "Grey’s anatomy ", '0');

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
SELECT * FROM Category;


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
    (NULL, 'allen', '12345'),
    (NULL, 'amie', '12345'),
    (NULL, 'a1li', '12345');

            -- SELECT FROM TV_user
SELECT * FROM TV_user;



-- INSERT INTO TV_status
	INSERT INTO 
	TV_status(status_id, status_name)
VALUES
 (NULL, 'completed'),
 (NULL, 'in progress'),
 (NULL, 'Not Completed');

 -- INSERT INTO Watch_instance
	INSERT INTO 
	Watch_instance(instance_id, user_id, show_id, status_id)
VALUES
 (NULL, 5, 1, 1),
 (NULL, 6, 16, 2),
 (NULL, 7, 8, 1),
 (NULL, 8, 20, 3);

SELECT * FROM Watch_instance;