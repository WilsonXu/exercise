package org.weber.siia.channels;

import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxu on 4/24/2015.
 */
public class AuditService {
    private List<AuditRecord> auditRecords = new ArrayList<AuditRecord>();

    public List<AuditRecord> getAuditRecords() {
        return auditRecords;
    }

    public void audit(Object auditPayload) {
        this.auditRecords.add(new AuditRecord(auditPayload));
    }
}
