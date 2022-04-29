package org.wilson.springsecurity.acl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.wilson.springsecurity.acl.mapper.NoticeMessageMapper;
import org.wilson.springsecurity.acl.model.NoticeMessage;

import java.util.List;

@Service
public class NoticeMessageService {
    private NoticeMessageMapper noticeMessageMapper;

    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<NoticeMessage> findAll() {
        return this.noticeMessageMapper.findAll();
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public NoticeMessage findById(Integer id) {
        return this.noticeMessageMapper.findById(id);
    }

    @PreAuthorize("hasPermission(#noticeMessage, 'CREATE')")
    public void save(NoticeMessage noticeMessage) {
        this.noticeMessageMapper.save(noticeMessage);
    }

    @PreAuthorize("hasPermission(#noticeMessage, 'WRITE')")
    public void update(NoticeMessage noticeMessage) {
        this.noticeMessageMapper.update(noticeMessage);
    }

    @Autowired
    public void setNoticeMessageMapper(NoticeMessageMapper noticeMessageMapper) {
        this.noticeMessageMapper = noticeMessageMapper;
    }
}
