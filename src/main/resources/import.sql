delete from Roles;
insert into Roles (id, name) values (nextval('Roles_SEQ'), 'Admin');
insert into Roles (id, name) values (nextval('Roles_SEQ'), 'Tester');
insert into Roles (id, name) values (nextval('Roles_SEQ'), 'User');

delete from Permissions;
insert into Permissions (unique_name, caption) values ('read', 'Read');
insert into Permissions (unique_name, caption) values ('write', 'Write');
insert into Permissions (unique_name, caption) values ('create', 'Create');
insert into Permissions (unique_name, caption) values ('delete', 'Delete');

delete from Persons;
insert into Persons (id, email, birthdate) values (nextval('Persons_SEQ'), 'a@it-com', '2000-01-01');
insert into Persons (id, email, birthdate) values (nextval('Persons_SEQ'), 'b@it-com', '2001-01-01');
insert into Persons (id, email, birthdate) values (nextval('Persons_SEQ'), 'c@it-com', '2002-01-01');