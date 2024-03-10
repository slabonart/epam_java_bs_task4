INSERT INTO usersdb.user_entity (id, user_name, user_password) VALUES
    (1, 'user1@epam.com', '{bcrypt}$2a$12$Sswz/XOykfjhGZmcDtJLIuzJIxOVRQMPgn3oMa6GWzV4oXVNXQVje'),
    (2, 'user2@epam.com', '{bcrypt}$2a$12$4n6IqFh.LHsBegxZC8g1F.K954SdsDCx27qIrV/AnWh2IIVlKfy.W'),
    (3, 'user3@epam.com', '{bcrypt}$2a$12$NyXmOzY9W72srC9rZfeRQei8nn40st4JSKe8nJzxtzNbBeVpMyXY.');

INSERT INTO usersdb.user_granted_authority (authority, user_id) VALUES
    ('VIEW_INFO', 1),
    ('VIEW_ADMIN', 2),
    ('VIEW_INFO', 3),
    ('VIEW_ADMIN', 3);
