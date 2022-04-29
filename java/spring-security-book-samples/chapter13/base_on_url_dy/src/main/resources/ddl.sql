set search_path = 'my_schema';

create table if not exists menu
(
    id      bigserial not null,
    pattern text default null,
    constraint menu_pk primary key (id)
    );

create table if not exists role
(
    id           bigserial not null,
    name         text default null,
    display_name text default null,
    constraint role_pk primary key (id)
    );

create table if not exists menu_role
(
    id  bigserial not null,
    mid int8 default null,
    rid int8 default null,
    constraint menu_role_pk primary key (id),
    constraint menu_role_fk_menu foreign key (mid) references menu (id),
    constraint menu_role_fk_role foreign key (rid) references role (id)
    );
create index if not exists menu_role_ix_mid on menu_role (mid);
create index if not exists menu_role_ix_rid on menu_role (rid);

create table if not exists "user"
(
    id       bigserial not null,
    username text    default null,
    password text    default null,
    enabled  boolean default null,
    locked   boolean default null,
    constraint user_pk primary key (id)
    );

create table user_role
(
    id  bigserial not null,
    uid int8 default null,
    rid int8 default null,
    constraint user_role_pk primary key (id),
    constraint user_role_fk_user foreign key (uid) references "user" (id),
    constraint user_role_fk_role foreign key (rid) references role (id)
);
create index if not exists user_role_ix_uid on user_role (uid);
create index if not exists user_role_ix_rid on user_role (rid);
