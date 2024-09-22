--INSERT INTO users (username, password)
--VALUES
--    ('Alan', 'alanword'),
--    ('John', 'johnword'),
--    ('Adam', 'adamword');

INSERT INTO users (username, password)
VALUES
    ('Alan', '$2a$12$kWOpFPdkVOjOMOqFtNsTZOu.KnGgtYNGo.7Fhe7BQuWMknvviNMne'),
    ('John', '$2a$12$YJsrPR5ItipY72sne4AugO.65FGlmu2fZkgxWSKl1vkOJabSxLUou'),
    ('Adam', '$2a$12$4iATTz.eyMdDHfk/MQ6jnOUBbDU17s6aqEmoJy.lbE8cyrIUipJyy');

INSERT INTO authorities (username, authority)
VALUES
    ('Alan', 'USER'),
    ('John', 'MODERATOR'),
    ('Adam', 'ADMIN');