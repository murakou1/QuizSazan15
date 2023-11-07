CREATE TABLE user{
    id IDENTITY,
    name VARCHAR NOT NULL,
    point INT
}

CREATE TABLE quiz{
    no IDENTITY,
    content VARCHAR,
    answer VARCHAR
}