
-- +---------+-----------+----------+---------------------+
-- | movieID |   title   |  rating  |       genres        |
-- +---------+-----------+----------+---------------------+
-- |    1923 | title 1   | 5 Stars  | Action              |
-- |    3128 | title 2   | 1 Star   | Horror, War, Sci-Fi |
-- |    3595 | title 3   | 3 Stars  | Historical, Drama   |
-- +---------+-----------+----------+---------------------+

-- +---------+-----------+----------+---------------------+
-- | showID  |   title   |  rating  |       genres        |
-- +---------+-----------+----------+---------------------+
-- |    1137 | title 1   | 5 Stars  | Action              |
-- |    3361 | title 2   | 1 Star   | Horror, War, Sci-Fi |
-- |    4569 | title 3   | 3 Stars  | Historical, Drama   |
-- +---------+-----------+----------+---------------------+

-- +---------+-----------+----------+----------------+
-- | gameID  |   title   |  rating  |    platform    |
-- +---------+-----------+----------+----------------+
-- |    6696 | title 1   | 5 Stars  | XBOX           |
-- |    5840 | title 2   | 1 Star   | Nintendo Swich |
-- |    0105 | title 3   | 3 Stars  | Playsation     |
-- +---------+-----------+----------+----------------+

-- +---------+-----------+----------+----------+-------------+
-- | bookID  |   title   |  author  |  rating  |    type     |
-- +---------+-----------+----------+----------+-------------+
-- |    9967 | title 1   | author 1 | 5 Stars  | Fiction     |
-- |    3333 | title 2   | author 2 | 1 Star   | Educational |
-- |    1054 | title 3   | author 3 | 3 Stars  | Non-Fiction |
-- +---------+-----------+----------+----------+-------------+

-- Table creations (Must be inside a DB schema):
CREATE TABLE movies (
    movieID INT NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL UNIQUE,
    rating VARCHAR(45),
    genres VARCHAR(255),
    PRIMARY KEY (movieID)
);

CREATE TABLE tvshows (
    showID INT NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL UNIQUE,
    rating VARCHAR(45) NOT NULL,
    genres VARCHAR(255),
    PRIMARY KEY (showID)
);

CREATE TABLE games (
    gameID INT NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL UNIQUE,
    rating VARCHAR(45) NOT NULL,
    platform VARCHAR(45) NOT NULL,
    PRIMARY KEY (gameID)
);

CREATE TABLE books (
    bookID INT NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL UNIQUE,
    author VARCHAR(255) NOT NULL UNIQUE,
    rating VARCHAR(45) NOT NULL,
    type VARCHAR(45) NOT NULL,
    PRIMARY KEY (bookID)
);
