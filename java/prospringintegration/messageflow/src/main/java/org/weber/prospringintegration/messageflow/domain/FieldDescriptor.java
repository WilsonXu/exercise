package org.weber.prospringintegration.messageflow.domain;

/**
 * Created by wxu on 5/28/2015.
 */
public enum FieldDescriptor {
    TYPE(1),
    SYMBOL(2),
    DESC(4),
    OPEN_PRICE(8),
    PRICE(16),
    ALL(1 + 2 + 4 + 8 + 16);

    private int fieldId;

    FieldDescriptor(int fieldId) {
        this.fieldId = fieldId;
    }

    public int fieldId() {
        return fieldId;
    }
}
