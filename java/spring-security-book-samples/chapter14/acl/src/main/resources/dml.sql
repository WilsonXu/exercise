INSERT INTO acl_class (id, class)
VALUES
    (1,'org.wilson.springsecurity.acl.model.NoticeMessage');
INSERT INTO acl_sid (id, principal, sid)
VALUES
    (2,true,'hr'),
    (1,true,'manager'),
    (3,false,'ROLE_EDITOR');
INSERT INTO system_message (id, content)
VALUES
    (1,'111'),
    (2,'222'),
    (3,'333');