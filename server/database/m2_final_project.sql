-- database m2_final_project
-- rollback;
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE playlist (
    playlist_id SERIAL PRIMARY KEY,
    playlist_name varchar(50) NOT NULL UNIQUE,
    playlist_description varchar(50)
);
CREATE TABLE song (
	song_id serial PRIMARY KEY,
	song_name VARCHAR(50),
	song_artist VARCHAR(50),
	added_date DATE
);

CREATE TABLE playlist_user (
	user_id FOREIGN KEY REFERENCES users(user_id),
	playlist_id FOREIGN KEY REFERENCES playlist(playlist_id)
);

CREATE TABLE playlist_song (
    song_id FOREIGN KEY REFERENCES song(song_id),
    playlist_id FOREIGN KEY REFERENCES playlist(playlist_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN'),
    ('Cade', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN'),
    ('Brant','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'),
    ('Brock','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER');

INSERT INTO
    playlist (playlist_name, playlist_description)
VALUES
    ('Rock', 'Rock Playlist'),
    ('Rap', 'Rap Playlist'),
    ('Country', 'Country Playlist'),
    ('Jazz', 'Jazz Playlist'),
    ('Alternative', 'Alternative Playlist');

INSERT INTO
    song (song_name, song_artist, added_date)
VALUES
    ('Song1', 'Artist1', '2019-01-01'),
    ('Song2', 'Artist2', '2020-01-01'),
    ('Song3', 'Artist3', '2021-01-01'),
    ('Song4', 'Artist4', '2022-01-01'),
    ('Song5', 'Artist5', '2023-01-01');

INSERT INTO
    playlist_user (user_id, playlist_id)
VALUES
    (1,2),
    (2,3),
    (3,4),
    (4,5),
    (5,1),
    (1,5),
    (2,1);

INSERT INTO
    playlist_song(song_id, playlist_id)
VALUES
    (1,1),
    (1,5),
    (2,4),
    (3,2),
    (4,3),
    (5,3);

COMMIT TRANSACTION;
