CREATE TABLE users (
    username VARCHAR(64) PRIMARY KEY,
    password VARCHAR(64) NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(64) NOT NULL,
    authority VARCHAR(64) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);