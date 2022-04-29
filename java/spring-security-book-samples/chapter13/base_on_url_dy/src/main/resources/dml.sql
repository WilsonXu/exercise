set search_path = 'my_schema';

insert into role (id, name, display_name)
values (1, 'ROLE_ADMIN', 'Admin'),
       (2, 'ROLE_USER', 'User'),
       (3, 'ROLE_GUEST', 'Guest');

insert into menu (id, pattern)
values (1, '/admin/**'),
       (2, '/user/**'),
       (3, '/guest/**');

insert into menu_role (id, mid, rid)
values (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 3, 2);

insert into "user" (id, username, password, enabled, locked)
values (1, 'wilson', '{noop}123', true, false),
       (2, 'bella', '{noop}123', true, false),
       (3, 'vincent', '{noop}123', true, false);

insert into user_role (id, uid, rid)
values (1, 1, 1),
       (2, 1, 2),
       (3, 2, 2),
       (4, 3, 3);