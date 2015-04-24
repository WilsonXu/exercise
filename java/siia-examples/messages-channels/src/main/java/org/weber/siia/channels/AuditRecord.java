package org.weber.siia.channels;

/**
 * Created by wxu on 4/24/2015.
 */
public class AuditRecord {
    private Object auditInstance;

    public AuditRecord(Object auditInstance) {
        this.auditInstance = auditInstance;
    }

    public Object getAuditInstance() {
        return auditInstance;
    }
}
