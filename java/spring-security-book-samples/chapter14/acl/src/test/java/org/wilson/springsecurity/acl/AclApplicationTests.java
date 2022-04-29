package org.wilson.springsecurity.acl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.wilson.springsecurity.acl.model.NoticeMessage;
import org.wilson.springsecurity.acl.service.NoticeMessageService;

import java.util.List;

@SpringBootTest
class AclApplicationTest {
    private NoticeMessageService noticeMessageService;
    private JdbcMutableAclService jdbcMutableAclService;

    @Test
    @WithMockUser(username = "manager")
    public void test01() {
        List<NoticeMessage> all = this.noticeMessageService.findAll();
        Assertions.assertEquals(0, all.size());
    }

    @Test
    @WithMockUser(username = "wilson")
    @Transactional
    @Rollback(false)
    public void test02() {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(NoticeMessage.class, 1);
        MutableAcl acl = this.jdbcMutableAclService.createAcl(objectIdentity);
        Permission permission = BasePermission.READ;
        acl.insertAce(acl.getEntries().size(), permission, new PrincipalSid("hr"), true);
        this.jdbcMutableAclService.updateAcl(acl);
    }

    @Test
    @WithMockUser(username = "hr")
    public void test03() {
        List<NoticeMessage> all = this.noticeMessageService.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals(1, all.get(0).getId());
        NoticeMessage byId = this.noticeMessageService.findById(1);
        Assertions.assertNotNull(byId);
        Assertions.assertEquals(1, byId.getId());
    }

    @Test
    @WithMockUser(username = "wilson")
    @Transactional
    @Rollback(false)
    public void test04() {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(NoticeMessage.class, 1);
        MutableAcl acl = (MutableAcl) this.jdbcMutableAclService.readAclById(objectIdentity);
        Permission permission = BasePermission.WRITE;
        acl.insertAce(acl.getEntries().size(), permission, new PrincipalSid("hr"), true);
        this.jdbcMutableAclService.updateAcl(acl);
    }

    @Test
    @WithMockUser(username = "hr")
    public void test05() {
        NoticeMessage noticeMessage = this.noticeMessageService.findById(1);
        Assertions.assertNotNull(noticeMessage);
        Assertions.assertEquals(1, noticeMessage.getId());
        noticeMessage.setContent("javaboy-1111");
        this.noticeMessageService.update(noticeMessage);
        noticeMessage = this.noticeMessageService.findById(1);
        Assertions.assertNotNull(noticeMessage);
        Assertions.assertEquals("javaboy-1111", noticeMessage.getContent());
    }

    @Test
    @WithMockUser(username = "wilson")
    @Transactional
    @Rollback(false)
    public void test06() {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(NoticeMessage.class, 99);
        MutableAcl acl = this.jdbcMutableAclService.createAcl(objectIdentity);
        Permission permission = BasePermission.CREATE;
        acl.insertAce(acl.getEntries().size(), permission, new PrincipalSid("manager"), true);
        this.jdbcMutableAclService.updateAcl(acl);
    }

    @Test
    @WithMockUser(username = "manager")
    public void test07() {
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setId(100);
        noticeMessage.setContent("999");
        this.noticeMessageService.save(noticeMessage);
    }

    @Autowired
    public void setNoticeMessageService(NoticeMessageService noticeMessageService) {
        this.noticeMessageService = noticeMessageService;
    }

    @Autowired
    public void setJdbcMutableAclService(JdbcMutableAclService jdbcMutableAclService) {
        this.jdbcMutableAclService = jdbcMutableAclService;
    }
}
