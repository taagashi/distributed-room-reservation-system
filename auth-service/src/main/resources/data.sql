INSERT INTO role_tb(role) VALUES ('EMPLOYEE')
INSERT INTO role_tb(role) VALUES ('ADMIN')
INSERT INTO role_tb(role) VALUES ('ROOT')

INSERT INTO user_tb(name, email, password) VALUES ('tulipa', 'tulipa@gmail.com', '$2a$10$VbOboa81bwH1Tp19/.hDpusnv3akuBu1xKQMjHYy1SFRtiXRfYeSa')

INSERT INTO user_role(user_id, role_id) VALUES (1, 1)
INSERT INTO user_role(user_id, role_id) VALUES (1, 2)
INSERT INTO user_role(user_id, role_id) VALUES (1, 3)