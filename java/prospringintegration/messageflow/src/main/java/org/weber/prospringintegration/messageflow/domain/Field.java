package org.weber.prospringintegration.messageflow.domain;

import java.io.Serializable;

/**
 * Created by wxu on 5/28/2015.
 */
public class Field implements Serializable {
    private static final long serialVersionUID = 1L;

    FieldDescriptor fieldDescriptor;
    String value;

    public Field() {
    }

    public Field(FieldDescriptor fd, String value) {
        this.fieldDescriptor = fd;
        this.value = value;
    }

    public FieldDescriptor getFieldDescriptor() {
        return fieldDescriptor;
    }

    public void setFieldDescriptor(FieldDescriptor fieldDescriptor) {
        this.fieldDescriptor = fieldDescriptor;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        Field f = (Field) o;
        return (f != null &&
                f.value.equals(this.value) &&
                f.fieldDescriptor.fieldId() == this.fieldDescriptor.fieldId());
    }
}
