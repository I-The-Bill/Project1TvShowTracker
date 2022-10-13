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
	(null,'Anime'),
	(null,'Anime'),
	(null,'Sci-Fi'),
	(null,'Sci-Fi'),
	(null,'Reality'),
	(null,'Thriller'),
	(null,'Crime'),
	(null,'Drama'),
	(null,'Drama'),
	(null,'Drama'),
	(null,'Action'),
	(null,'Tragedy'),
	(null,'Mystery'),
	(null,'Drama'),
	(null,'Drama'),
	(null,'sitcom'),
	(null,'Drama'),
	(null,'Action fiction'),
	(null,'Drama'),
    (null,'anime');

    -- SELECT FROM TV_show
SELECT * FROM Category;