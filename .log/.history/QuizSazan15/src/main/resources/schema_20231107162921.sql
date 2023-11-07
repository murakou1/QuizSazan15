CREATE TABLE user{
    id IDENTITY,
    name VARCHAR NOT NULL,
    point INT
};

CREATE TABLE quiz{
    no INT,
    content VARCHAR,
    answer VARCHAR
};