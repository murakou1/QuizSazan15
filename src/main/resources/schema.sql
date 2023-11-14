CREATE TABLE users (
    id IDENTITY,
    username VARCHAR NOT NULL,
    point INT
);

CREATE TABLE quiz(
    no IDENTITY,
    content VARCHAR,
    choice1 VARCHAR,
    choice2 VARCHAR,
    choice3 VARCHAR,
    choice4 VARCHAR,
    answer INT
);
//content:問題文  choice:選択肢  answer:答えの番号


