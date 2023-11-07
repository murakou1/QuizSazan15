CREATE TABLE userinfo (
    userName VARCHAR NOT NULL PRIMARY KEY,
    age INT,
    height DOUBLE NOT NULL
);

CREATE TABLE quiz(
    no INT,
    content VARCHAR,
    answer VARCHAR
);