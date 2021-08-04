INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Mr. John', 'Doe', 'john.doe@email.com', '2018-01-02', '2018-01-02');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Linus', 'Torvalds', 'linus.torvalds@email.com', '2018-01-03', '2018-01-03');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@email.com', '2018-01-04', '2018-01-04');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Erich', 'Gamma', 'erich.gamma@email.com', '2018-02-01', '2018-02-01');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Richard', 'Helm', 'richard.helm@email.com', '2018-02-10', '2018-02-10');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Ralph', 'Johnson', 'ralph.johnson@email.com', '2018-02-18', '2018-02-18');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('John', 'Vlissides', 'john.vlissides@email.com', '2018-02-28', '2018-02-28');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Dr. James', 'Gosling', 'james.gosling@email.com', '2018-03-03', '2018-03-03');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Magma', 'Lee', 'magma.lee@email.com', '2018-03-04', '2018-03-04');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Tornado', 'Roe', 'tornado.roe@email.com', '2018-03-05', '2018-03-05');
INSERT INTO customers (name, lastname, email, create_at, update_at) VALUES('Jade', 'Doe', 'jane.doe@email.com', '2018-03-06', '2018-03-06');

INSERT INTO `users` (username, password) VALUES ('user','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq');
INSERT INTO `users` (username, password) VALUES ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `user_role` (user_id, role_id) VALUES (1, 1);
INSERT INTO `user_role` (user_id, role_id) VALUES (2, 2);
INSERT INTO `user_role` (user_id, role_id) VALUES (2, 1);
