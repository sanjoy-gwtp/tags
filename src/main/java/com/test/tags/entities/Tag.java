package com.test.tags.entities;

import javax.persistence.*;

/**
 * @author sanjoy
 * on 5/27/21
 */
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @Column(unique=true)
    private String key;
    private String value;

    public Tag() {
    }

    public Tag(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
