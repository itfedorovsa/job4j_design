insert into roles(role) values ('admin');
insert into roles(role) values ('employee');
insert into roles(role) values ('guest');
insert into rules(rule) values ('private');
insert into rules(rule) values ('public');
insert into rules(rule) values ('test');
insert into states(state) values ('open'); 
insert into states(state) values ('closed'); 
insert into states(state) values ('in process'); 
insert into categories(category) values ('important');
insert into categories(category) values ('common');
insert into categories(category) values ('critical');
insert into users(name) values ('Bob');
insert into users(name) values ('Tom');
insert into users(name) values ('Jack'); 
insert into items(item) values ('fix');
insert into items(item) values ('repair');
insert into items(item) values ('wish');
insert into comments(comment) values ('quickly');
insert into comments(comment) values ('ok');
insert into comments(comment) values ('maybe');
insert into attachs(attach) values ('failure.png');
insert into attachs(attach) values ('CanonMF3010.doc');
insert into attachs(attach) values ('MyWish.txt');
insert into roles_rules(name, role_id, rule_id) values('AdminAcc', 1, 1);
insert into roles_rules(name, role_id, rule_id) values('AdminAcc', 1, 2);
insert into roles_rules(name, role_id, rule_id) values('AdminAcc', 1, 3);
insert into roles_rules(name, role_id, rule_id) values('EmployeeAcc', 2, 2);
insert into roles_rules(name, role_id, rule_id) values('EmployeeAcc', 2, 3);
insert into roles_rules(name, role_id, rule_id) values('GuestAcc', 3, 3);

