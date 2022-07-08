INSERT INTO roles(role) VALUES ('admin'), ('employee'), ('guest');
INSERT INTO rules(rule) VALUES ('private'), ('public'), ('test');
INSERT INTO states(state) VALUES ('open'), ('closed'), ('in process');
INSERT INTO categories(category) VALUES ('important'), ('common'), ('critical');
INSERT INTO users(name) VALUES ('Bob'), ('Tom'), ('Jack');
INSERT INTO items(item) VALUES ('fix'), ('repair'), ('wish');
INSERT INTO comments(comment) VALUES ('quickly'), ('ok'), ('maybe');
INSERT INTO attachs(attach) VALUES ('failure.png'), ('CanonMF3010.doc'), ('MyWish.txt');
INSERT INTO roles_rules(name, role_id, rule_id) VALUES('AdminAcc', 1, 1), ('AdminAcc', 1, 2), ('AdminAcc', 1, 3);
INSERT INTO roles_rules(name, role_id, rule_id) VALUES('EmployeeAcc', 2, 2), ('EmployeeAcc', 2, 3), ('GuestAcc', 3, 3);


